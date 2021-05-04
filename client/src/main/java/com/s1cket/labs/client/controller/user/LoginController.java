package com.s1cket.labs.client.controller.user;

import com.s1cket.labs.client.controller.MainController;
import com.s1cket.labs.client.model.dto.UserDto;
import com.s1cket.labs.client.service.UserService;
import com.s1cket.labs.client.service.exception.ServiceException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(MainController mainController, UserService userService) {
        this.userService = userService;
        this.mainController = mainController;
    }

    @FXML
    public void login() {
        String userText = user.getText().strip();
        String passwordText = password.getText().strip();

        try {
            UserDto userDto = userService.findByLogin(userText);
            /* TODO: Server request */
            logger.info("Logging in as " + userText);
            mainController.loadChatScreen(userDto);
        } catch (ServiceException e) {
            logger.info(e.getMessage());
            notification.setText(e.getMessage());
        }

    }
}
