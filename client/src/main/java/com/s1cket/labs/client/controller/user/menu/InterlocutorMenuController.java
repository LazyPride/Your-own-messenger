package com.s1cket.labs.client.controller.user.menu;

import com.s1cket.labs.client.model.dto.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.text.html.ListView;

@Component
@NoArgsConstructor
public class InterlocutorMenuController {
    @FXML
    private ListView interlocutors;

    private ObservableList interlocutorsList = FXCollections.observableArrayList();

    private final Logger logger = LoggerFactory.getLogger(InterlocutorMenuController.class);

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void load(UserDto userDto) {
        var s = userDto.getInterlocutors();
        logger.info("" + s);
    }
}
