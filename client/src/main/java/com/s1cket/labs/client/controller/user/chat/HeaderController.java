package com.s1cket.labs.client.controller.user.chat;

import com.s1cket.labs.client.model.dto.InterlocutorDto;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@FxmlView("HeaderController.fxml")
public class HeaderController {
    @FXML
    private Label nickname;
    @FXML
    private Label online;
    @FXML
    private Hyperlink profile;

    private final Logger logger = LoggerFactory.getLogger(HeaderController.class);

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void setHeader(InterlocutorDto interlocutorDto) {
        nickname.setText(interlocutorDto.getNickname());
        // TODO: onlineService.isUserOnline(nickname);
        online.setText("to be done");
        profile.setText("Profile");
    }
}
