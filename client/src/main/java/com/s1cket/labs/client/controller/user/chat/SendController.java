package com.s1cket.labs.client.controller.user.chat;

import com.s1cket.labs.client.model.dto.*;
import com.s1cket.labs.client.service.EncryptionService;
import com.s1cket.labs.client.service.EnvelopeService;
import com.s1cket.labs.client.service.WebSocketService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@FxmlView("SendController.fxml")
public class SendController {

    @FXML
    private TextArea textArea;
    @FXML
    private Button sendButton;

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(SendController.class);

    private InterlocutorDto interlocutorDto;
    private UserDto userDto;

    private final EnvelopeService envelopeService;
    private HistoryCotroller historyCotroller;
    private WebSocketService webSocketService;
    private EncryptionService encryptionService;

    @Autowired
    public SendController(FxWeaver fxWeaver,
                          EnvelopeService envelopeService,
                          HistoryCotroller historyCotroller,
                          WebSocketService webSocketService,
                          EncryptionService encryptionService) {
        this.fxWeaver = fxWeaver;
        this.envelopeService = envelopeService;
        this.historyCotroller = historyCotroller;
        this.webSocketService = webSocketService;
        this.encryptionService = encryptionService;
        logger.debug("Constructor");
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void load(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setInterlocutor(InterlocutorDto interlocutorDto) {
        textArea.setDisable(false);
        sendButton.setDisable(false);
        this.interlocutorDto = interlocutorDto;
    }

    public void handleSend(MouseEvent mouseEvent) {
        var text = textArea.getText();
        var encryptedText = encryptionService.encrypt(interlocutorDto.getPublicKey(), text);
        var encryptedTextForMe = encryptionService.encrypt(userDto.getPublicKey(), text);
        var timestamp = OffsetDateTime.now();

        EnvelopeDto envelopeDto = EnvelopeDto.builder()
                .addressFrom(userDto.getAddress())
                .addressTo(interlocutorDto.getAddress())
                .interlocutor(interlocutorDto)
                .build();

        LetterDto letterDto = LetterDto.builder()
                .text(encryptedTextForMe)
                .createTime(timestamp)
                .envelope(envelopeDto)
                .build();

        envelopeDto.setLetter(letterDto);

        var savedEnvelope = envelopeService.save(envelopeDto);
        logger.info("" + savedEnvelope);

        var letter = new LetterWebDto(encryptedText);

        var envelope = EnvelopeWebDto.builder()
                .addressFrom(envelopeDto.getAddressFrom())
                .addressTo(envelopeDto.getAddressTo())
                .letter(letter)
                .build();

        if (!envelope.getAddressTo().equals(userDto.getAddress())) {
            webSocketService.send(envelope);
        }

        textArea.clear();
        historyCotroller.addEnvelope(savedEnvelope);
    }

    public void disable() {
        textArea.setDisable(true);
        sendButton.setDisable(true);
    }
}
