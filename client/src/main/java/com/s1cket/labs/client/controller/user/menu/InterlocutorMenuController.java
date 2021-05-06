package com.s1cket.labs.client.controller.user.menu;

import com.s1cket.labs.client.controller.user.chat.HeaderController;
import com.s1cket.labs.client.controller.user.chat.HistoryCotroller;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.model.dto.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@FxmlView("InterlocutorMenuController.fxml")
public class InterlocutorMenuController {
    @FXML
    private ListView interlocutorsListView;

    private final HeaderController headerController;
    private final HistoryCotroller historyCotroller;

    private UserDto userDto;

    private String selectedInterlocutor;

    private final Logger logger = LoggerFactory.getLogger(InterlocutorMenuController.class);

    @Autowired
    public InterlocutorMenuController(HeaderController headerController,
                                      HistoryCotroller historyCotroller) {
        this.headerController = headerController;
        this.historyCotroller = historyCotroller;
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    public void load(UserDto userDto) {
        this.userDto = userDto;
        List<String> nicknames = userDto.getInterlocutors().stream()
                .map(InterlocutorDto::getNickname)
                .collect(Collectors.toList());
        ObservableList interlocutorsList = FXCollections.observableArrayList();
        interlocutorsList.setAll(nicknames);
        interlocutorsListView.setItems(interlocutorsList);
    }

    @FXML
    private void onInterlocutorSelected(MouseEvent mouseEvent) throws Exception {
        var selected = interlocutorsListView.getSelectionModel().getSelectedItems();
        if (selected.size() > 0) {
            String nickname = (String) selected.get(0);
            if (!nickname.equals(selectedInterlocutor)) {
                selectedInterlocutor = nickname;
                InterlocutorDto interlocutorDto = userDto.getInterlocutors().stream()
                        .filter(i -> selectedInterlocutor.equals(i.getNickname()))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Interlocutor "
                                + selectedInterlocutor + " is not found!"));
                logger.info("Loading for " + interlocutorDto);
                headerController.setHeader(interlocutorDto);
                historyCotroller.setHistory(interlocutorDto);
            }
        }
    }
}
