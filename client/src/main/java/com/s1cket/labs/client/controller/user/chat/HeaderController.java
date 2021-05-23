package com.s1cket.labs.client.controller.user.chat;

import com.s1cket.labs.client.controller.user.misc.ProfileController;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxWeaver;
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
    private Hyperlink profile;

    private InterlocutorDto interlocutorDto;

    private final Logger logger = LoggerFactory.getLogger(HeaderController.class);
    private FxWeaver fxWeaver;

    public HeaderController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void setHeader(InterlocutorDto interlocutorDto) {
        this.interlocutorDto = interlocutorDto;
        nickname.setText(interlocutorDto.getNickname());
        // TODO: onlineService.isUserOnline(nickname);
        profile.setText("Profile");
    }

    public void handleProfileClick(MouseEvent mouseEvent) {
        var profileScreen = fxWeaver.load(ProfileController.class);
        profileScreen.getController().show(interlocutorDto);
    }
}
