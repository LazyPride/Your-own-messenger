package com.s1cket.labs.client.controller.user.chat;

import com.s1cket.labs.client.events.IFrameListener;
import com.s1cket.labs.client.model.dto.EnvelopeDto;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.service.WebSocketService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@FxmlView("HistoryController.fxml")
public class HistoryCotroller {

    @FXML
    private VBox history;

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(HistoryCotroller.class);
    private InterlocutorDto interlocutorDto;
    private WebSocketService webSocketService;

    @Autowired
    public HistoryCotroller(FxWeaver fxWeaver, WebSocketService webSocketService) {
        this.fxWeaver = fxWeaver;
        this.webSocketService = webSocketService;
        this.webSocketService.registerListener(new EnvelopeListener());
        logger.debug("Constructor");
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void setHistory(InterlocutorDto interlocutorDto) {
        this.interlocutorDto = interlocutorDto;

        history.getChildren().clear();
        var envelopes = interlocutorDto.getEnvelopes();

        if (envelopes == null) {
            return;
        }
        // TODO: decrypt all. sort by time.

        for (var envelope : envelopes) {
            drawEnvelope(envelope);
        }
    }

    public void addEnvelope(EnvelopeDto envelope) {
        if (envelope.getInterlocutor().getAddress().equals(interlocutorDto.getAddress())) {
            interlocutorDto.getEnvelopes().add(envelope);
            drawEnvelope(envelope);
        }
    }

    private void drawEnvelope(EnvelopeDto envelope) {
        FxControllerAndView<MessageController, Node> message =
                fxWeaver.load(MessageController.class);
        var messageController = message.getController();
        var messageView = message.getView().orElse(new Label("Error loading view!"));

        var letter = envelope.getLetter();
        // TODO: decryptLetter. (EncryptionService)
        String text = letter.getText();
        OffsetDateTime timestamp = letter.getCreateTime();
        Pos alignment = getAlignment(envelope);

        messageController.set(text, timestamp, alignment);
        history.getChildren().add(messageView);
    }

    private Pos getAlignment(EnvelopeDto envelope) {
        InterlocutorDto interlocutor = envelope.getInterlocutor();
        if (envelope.getAddressFrom().equals(interlocutor.getAddress())) {
            return Pos.BASELINE_LEFT;
        }
        else {
            return Pos.BASELINE_RIGHT;
        }
    }

    public void disable() {
        history.getChildren().clear();
        history.getChildren().add(new Label("Select a chat to start messaging"));
    }

    private class EnvelopeListener implements IFrameListener {

        @Override
        public void onFrameReceive(Object payload) {
            Platform.runLater(() -> addEnvelope((EnvelopeDto) payload));
        }
    }
}
