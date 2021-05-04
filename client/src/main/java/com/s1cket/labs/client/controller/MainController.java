package com.s1cket.labs.client.controller;

import com.s1cket.labs.client.controller.user.ChatController;
import com.s1cket.labs.client.controller.user.LoginController;
import com.s1cket.labs.client.model.dto.UserDto;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@FxmlView("MainController.fxml")
public class MainController {
    @FXML
    private StackPane parentNode;

    private FxControllerAndView<LoginController, Node> loginCV;
    private FxControllerAndView<ChatController, Node> chatCV;

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        logger.debug("Constructor");
    }

    @FXML
    public void initialize() {
        loginCV = fxWeaver.load(LoginController.class);
        chatCV = fxWeaver.load(ChatController.class);
        loadLoginScreen();
        logger.debug("initialize");
    }

    public void loadLoginScreen() {
        parentNode.getChildren().clear();
        parentNode.getChildren().add(loginCV.getView().get());
    }

    public void loadChatScreen(UserDto userDto) {
        parentNode.getChildren().clear();
        parentNode.getChildren().add(chatCV.getView().get());
    }
}
