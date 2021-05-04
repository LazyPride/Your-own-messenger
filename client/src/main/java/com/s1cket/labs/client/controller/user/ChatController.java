package com.s1cket.labs.client.controller.user;

import com.s1cket.labs.client.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import net.rgielen.fxweaver.core.FxWeaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatController {

    @FXML
    BorderPane borderPane;

    private MainController mainController;
    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    public ChatController(MainController mainController, FxWeaver fxWeaver) {
        this.mainController = mainController;
        this.fxWeaver = fxWeaver;
        logger.debug("Constructor");
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

}
