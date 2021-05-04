package com.s1cket.labs.client.controller.user;

import com.s1cket.labs.client.controller.MainController;
import com.s1cket.labs.client.controller.user.chat.HeaderController;
import com.s1cket.labs.client.controller.user.chat.HistoryCotroller;
import com.s1cket.labs.client.controller.user.chat.SendController;
import com.s1cket.labs.client.controller.user.menu.InterlocutorMenuController;
import com.s1cket.labs.client.model.dto.UserDto;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import lombok.AllArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChatController {
    @FXML
    private final HeaderController headerController;
    @FXML
    private final HistoryCotroller historyCotroller;
    @FXML
    private final SendController sendController;
    @FXML
    private final InterlocutorMenuController interlocutorMenuController;

    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void load(UserDto userDto) {
        headerController.initialize();
        historyCotroller.initialize();
        sendController.initialize();
        interlocutorMenuController.initialize();

        interlocutorMenuController.load(userDto);
    }
}
