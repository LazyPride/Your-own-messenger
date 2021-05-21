package com.s1cket.labs.client.controller.user;

import com.s1cket.labs.client.controller.MainController;
import com.s1cket.labs.client.model.dto.UserDto;
import com.s1cket.labs.client.model.dto.UserLoginDto;
import com.s1cket.labs.client.model.dto.UserRegistrationDto;
import com.s1cket.labs.client.service.KeyService;
import com.s1cket.labs.client.service.UserService;
import com.s1cket.labs.client.service.RegistrationService;
import com.s1cket.labs.client.service.exception.ServiceException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.HashSet;

@Component
@FxmlView("LoginController.fxml")
public class LoginController {
    @FXML
    private Text notification;
    @FXML
    private TextField user;
    @FXML
    private TextField password;

    private MainController mainController;

    private UserService userService;
    private RegistrationService registrationService;
    private KeyService keyService;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(MainController mainController,
                           UserService userService,
                           RegistrationService registrationService,
                           KeyService keyService) {
        this.userService = userService;
        this.mainController = mainController;
        this.registrationService = registrationService;
        this.keyService = keyService;
    }

    @FXML
    public void login() {
        String userText = user.getText().strip();
        String passwordText = password.getText().strip();

        UserDto userDto;
        try {
            userDto = userService.findByLogin(userText);
        } catch (ServiceException e) {
            logger.info(e.getMessage() + "Please register first. (Sync feature is not there yet)");
            notification.setText(e.getMessage() + "Please register first. (Sync feature is not there yet)");
            return;
        }

        UserLoginDto uld = new UserLoginDto(userText, passwordText);

        try {
            registrationService.loginUser(uld).block();
        }
        catch (Exception e) {
            logger.info("Invalid credentials!");
            notification.setText("Invalid credentials!");
            return;
        }

        logger.info("Logging in as " + userText);
        mainController.loadChatScreen(userDto);
    }

    @FXML
    public void register(MouseEvent mouseEvent) {
        String login = user.getText().strip();
        String passwordText = password.getText().strip();

        try {
            userService.findByLogin(login);
            logger.info("User " + login + " is already exist locally! You can login.");
            notification.setText("User " + login + " is already exist locally! You can login.");
            return;
        } catch (ServiceException ignored) {

        }


        KeyPair keyPair = keyService.generateKeyPair();
        String address = KeyService.bytesToHex(keyService.getAddress(keyPair.getPublic()));
        String privateKeyHex = KeyService.bytesToHex(keyPair.getPrivate().getEncoded());
        String publicKeyHex = KeyService.bytesToHex(keyPair.getPublic().getEncoded());

        UserDto user = UserDto.builder()
                .login(login)
                .password(passwordText)
                .address(address)
                .privateKey(privateKeyHex)
                .publicKey(publicKeyHex)
                .interlocutors(new HashSet<>())
                .build();

        UserRegistrationDto userRegistration = UserRegistrationDto.builder()
                .login(login)
                .password(passwordText)
                .address(address)
                .build();

        try {
            registrationService.registerUser(userRegistration).block();
        }
        catch (Exception e) {
            logger.info("Login is occupied!");
            notification.setText("Login is occupied!");
            return;
        }

        userService.save(user);
        logger.info("Created user: " + user);
        notification.setText("Successfully registered!");
    }
}
