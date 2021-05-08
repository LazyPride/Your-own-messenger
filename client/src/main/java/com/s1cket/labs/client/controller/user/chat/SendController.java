package com.s1cket.labs.client.controller.user.chat;

import com.s1cket.labs.client.model.dto.EnvelopeDto;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.model.dto.LetterDto;
import com.s1cket.labs.client.model.dto.UserDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@FxmlView("SendController.fxml")
public class SendController {

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(SendController.class);
    public TextArea textArea;
    private InterlocutorDto interlocutorDto;
    private UserDto userDto;

    public SendController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
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
        this.interlocutorDto = interlocutorDto;
    }

    public void handleSend(MouseEvent mouseEvent) {
        var text = textArea.getText();
        // TODO: Encode text
        var timestamp = OffsetDateTime.now();

        EnvelopeDto envelopeDto = EnvelopeDto.builder()
                .addressFrom(userDto.getAddress())
                .addressTo(interlocutorDto.getAddress())
                .build();

        LetterDto letterDto = LetterDto.builder()
                .text(text)
                .createTime(timestamp)
                .envelope(envelopeDto)
                .build();

        envelopeDto.setLetter(letterDto);
    }
}
