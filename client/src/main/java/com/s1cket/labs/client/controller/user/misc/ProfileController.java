package com.s1cket.labs.client.controller.user.misc;

import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.model.dto.UserDto;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("ProfileController.fxml")
public class ProfileController {

    private Stage stage;

    @FXML
    private VBox root;
    @FXML
    private Label nickname;
    @FXML
    private Label address;
    @FXML
    private Label publicKey;

    private String nicknameText;
    private String addressText;
    private String publicKeyText;


    @FXML
    public void initialize() {
        stage = new Stage();
        stage.setTitle("Profile");
        stage.setScene(new Scene(root));
    }

    public void show(UserDto userDto) {
        setFields(userDto.getLogin(), userDto.getAddress(), userDto.getPublicKey());
        stage.show();
    }

    public void show(InterlocutorDto interlocutorDto) {
        setFields(interlocutorDto.getNickname(),
                interlocutorDto.getAddress(),
                interlocutorDto.getPublicKey());
        stage.show();
    }

    private void setFields(String nickname, String address, String publicKey) {
        nicknameText = nickname;
        addressText = address;
        publicKeyText = publicKey;
        this.nickname.setText("Nickname: " + nickname);
        this.address.setText("Address: " + address);
        this.publicKey.setText("Public Key: " + publicKey);
    }

    public void copyNickname(MouseEvent mouseEvent) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(nicknameText);
        clipboard.setContent(content);
    }

    public void copyAddress(MouseEvent mouseEvent) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(addressText);
        clipboard.setContent(content);
    }

    public void copyPublicKey(MouseEvent mouseEvent) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(publicKeyText);
        clipboard.setContent(content);
    }
}
