package com.s1cket.labs.client.controller;

import com.s1cket.labs.client.ClientApp;
import com.s1cket.labs.client.controller.user.misc.ContactManagerController;
import com.s1cket.labs.client.controller.user.misc.ProfileController;
import com.s1cket.labs.client.model.dto.UserDto;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@FxmlView("MenuController.fxml")
public class MenuController {
    @FXML
    private MenuItem profile;
    @FXML
    private MenuItem addContact;
    @FXML
    private MenuItem logOut;
    @FXML
    private MenuItem help;
    @FXML
    private MenuBar menuBar;

    private UserDto userDto;

    private final FxWeaver fxWeaver;
    private final ClientApp clientApp;
    private final MainController mainController;
    private final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    public MenuController(FxWeaver fxWeaver,
                          ClientApp clientApp,
                          @Lazy MainController mainController) {
        this.fxWeaver = fxWeaver;
        this.clientApp = clientApp;
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        menuBar.setFocusTraversable(true);
        logger.debug("initialize");
    }

    public void disable() {
        profile.setDisable(true);
        addContact.setDisable(true);
        logOut.setDisable(true);
    }

    public void enable(UserDto userDto) {
        this.userDto = userDto;
        profile.setDisable(false);
        addContact.setDisable(false);
        logOut.setDisable(false);
    }

    @FXML
    private void handleAboutAction(ActionEvent event) {
        var hostServices = HostServicesDelegate.getInstance(clientApp);
        hostServices.showDocument("https://github.com/LazyPride/Your-own-messenger");
    }

    @FXML
    private void handleProfileAction(ActionEvent event) {
        var profileScreen = fxWeaver.load(ProfileController.class);
        profileScreen.getController().show(userDto);
    }

    @FXML
    private void handleLogOutAction(ActionEvent event) {
        mainController.loadLoginScreen();
    }

    @FXML
    private void handleAddContactAction(ActionEvent event) {
        var contactManager = fxWeaver.load(ContactManagerController.class);
        contactManager.getController().show(userDto);
    }

}