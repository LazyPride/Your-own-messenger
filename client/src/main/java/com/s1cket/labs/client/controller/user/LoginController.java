package com.s1cket.labs.client.controller.user;

import com.s1cket.labs.client.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("LoginController.fxml")
public class LoginController {
    @FXML
    private TextField user;
    @FXML
    private TextField password;

    private MainController mainController;

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(MainController mainController, FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        this.mainController = mainController;
        logger.debug("Constructor");
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    @FXML
    public void login() {
        logger.debug("login");

        /**
         * TODO: Fetch login and password from fields.
         * Call login from UserLoginService:
         *   - if user exists locally then ask server to accept access.
         */
        String userText = user.getText();
        String passwordText = password.getText();

        logger.info("Logging in as " + userText + ":" + passwordText);

        mainController.loadChatScreen();
    }
}
