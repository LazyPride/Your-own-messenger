package com.s1cket.labs.client.controller;

import com.s1cket.labs.client.controller.user.ChatController;
import com.s1cket.labs.client.controller.user.LoginController;
import com.s1cket.labs.client.controller.user.chat.HistoryCotroller;
import com.s1cket.labs.client.controller.user.chat.SendController;
import com.s1cket.labs.client.model.dto.UserDto;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private VBox parentNode;

    private FxControllerAndView<LoginController, Node> loginCV;
    private FxControllerAndView<ChatController, Node> chatCV;

    private final FxWeaver fxWeaver;
    private MenuController menuController;
    private HistoryCotroller historyCotroller;
    private SendController sendController;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController(FxWeaver fxWeaver,
                          MenuController menuController,
                          HistoryCotroller historyCotroller,
                          SendController sendController) {
        this.fxWeaver = fxWeaver;
        this.menuController = menuController;
        this.historyCotroller = historyCotroller;
        this.sendController = sendController;
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
        menuController.disable();
        historyCotroller.disable();
        sendController.disable();
    }

    public void loadChatScreen(UserDto userDto) {
        parentNode.getChildren().clear();
        chatCV.getController().load(userDto);
        parentNode.getChildren().add(chatCV.getView().get());
        menuController.enable(userDto);

    }
}
