package com.s1cket.labs.client.controller.user;

import com.s1cket.labs.client.controller.user.chat.HeaderController;
import com.s1cket.labs.client.controller.user.chat.HistoryCotroller;
import com.s1cket.labs.client.controller.user.chat.SendController;
import com.s1cket.labs.client.controller.user.menu.InterlocutorMenuController;
import com.s1cket.labs.client.model.dto.UserDto;
import com.s1cket.labs.client.service.WebSocketService;
import javafx.fxml.FXML;
import lombok.AllArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FxmlView("ChatController.fxml")
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
    private WebSocketService webSocketService;

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void load(UserDto userDto) {
        webSocketService.connect(userDto);

        interlocutorMenuController.load(userDto);
        sendController.load(userDto);
    }
}
