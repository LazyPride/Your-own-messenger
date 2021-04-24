package com.s1cket.labs.client.controller.user;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import net.rgielen.fxweaver.core.FxWeaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChatController {

    @FXML
    BorderPane borderPane;

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    public ChatController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        logger.debug("Constructor");
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

}
