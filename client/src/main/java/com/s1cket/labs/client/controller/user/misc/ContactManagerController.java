package com.s1cket.labs.client.controller.user.misc;

import com.s1cket.labs.client.controller.MainController;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.model.dto.UserDto;
import com.s1cket.labs.client.service.InterlocutorService;
import com.s1cket.labs.client.service.exception.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

@Component
@FxmlView("ContactManagerController.fxml")
public class ContactManagerController {

    private Stage stage;

    @FXML
    private AnchorPane root;
    @FXML
    private TextField nicknameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField publicKeyTextField;
    @FXML
    private Label notificationLabel;

    private MainController mainController;
    private InterlocutorService interlocutorService;
    private UserDto userDto;

    @Autowired
    ContactManagerController(MainController mainController, InterlocutorService interlocutorService) {
        this.mainController = mainController;
        this.interlocutorService = interlocutorService;
    }

    @FXML
    public void initialize() {
        stage = new Stage();
        stage.setTitle("Add contact");
        stage.setScene(new Scene(root));
    }

    public void show(UserDto userDto) {
        this.userDto = userDto;
        stage.show();
    }

    public void handleSave(MouseEvent mouseEvent) {
        var nickname = nicknameTextField.getText();
        var address = addressTextField.getText();
        var publicKey = publicKeyTextField.getText();

        var interlocutorDto = InterlocutorDto.builder()
                .nickname(nickname)
                .address(address)
                .publicKey(publicKey)
                .user(userDto)
                .envelopes(new TreeSet<>())
                .build();

        InterlocutorDto savedDto;
        try {
            savedDto = interlocutorService.save(interlocutorDto);
        }
        catch (ValidationException e) {
            notificationLabel.setText(e.getMessage());
            return;
        }

        nicknameTextField.clear();
        addressTextField.clear();
        publicKeyTextField.clear();
        
        userDto.getInterlocutors().add(savedDto);
        mainController.loadChatScreen(userDto);
    }

    public void handleClose(MouseEvent mouseEvent) {
        stage.close();
    }
}