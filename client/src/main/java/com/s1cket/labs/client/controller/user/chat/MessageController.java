package com.s1cket.labs.client.controller.user.chat;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
@FxmlView("MessageController.fxml")
public class MessageController {
    @FXML
    private VBox message;
    @FXML
    private Label text;
    @FXML
    private Label timestamp;

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    public MessageController() {
        logger.debug("Constructor");
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void set(String text, OffsetDateTime timestamp, Pos alignment) {
        this.message.setAlignment(alignment);
        this.text.setText(text);
        this.timestamp.setText(timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
    }

}
