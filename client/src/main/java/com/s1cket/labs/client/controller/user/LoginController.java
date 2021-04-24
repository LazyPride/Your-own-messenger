package com.s1cket.labs.client.controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@FxmlView("LoginController.fxml")
public class LoginController {
    @FXML
    StackPane stackPane;

    private Tab parentTab;
    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        logger.debug("Constructor");
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    @FXML
    public void login() {
        logger.debug("login");

        FxControllerAndView<ChatController, BorderPane> chatScreen =
                fxWeaver.load(ChatController.class);
        this.parentTab.setContent(chatScreen.getView().get());
    }

    public void setParentTab(Tab tab) {
        this.parentTab = tab;
    }
}
