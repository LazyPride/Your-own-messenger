package com.s1cket.labs.client.controller.user;

import com.s1cket.labs.client.controller.MainController;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.model.dto.UserDto;
import com.s1cket.labs.client.model.dto.UserLoginDto;
import com.s1cket.labs.client.model.dto.UserRegistrationDto;
import com.s1cket.labs.client.service.*;
import com.s1cket.labs.client.service.exception.ServiceException;
import com.s1cket.labs.client.service.exception.ValidationException;
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
import java.util.TreeSet;

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
    private InterlocutorService interlocutorService;
    private RegistrationService registrationService;
    private KeyService keyService;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(MainController mainController,
                           UserService userService,
                           InterlocutorService interlocutorService,
                           RegistrationService registrationService,
                           KeyService keyService) {
        this.userService = userService;
        this.mainController = mainController;
        this.interlocutorService = interlocutorService;
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

        UserDto savedUser = userService.save(user);
        logger.info("Created user: " + savedUser);

        InterlocutorDto interlocutorDto = InterlocutorDto.builder()
                .publicKey(savedUser.getPublicKey())
                .address(savedUser.getAddress())
                .user(savedUser)
                .envelopes(new TreeSet<>())
                .nickname(savedUser.getLogin())
                .build();
        InterlocutorDto saved = null;
        try {
            saved = interlocutorService.save(interlocutorDto);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        logger.info("Created self-interlocutor: " + saved);

        notification.setText("Successfully registered!");
    }
}
