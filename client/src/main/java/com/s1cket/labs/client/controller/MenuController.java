package com.s1cket.labs.client.controller;

import com.s1cket.labs.client.controller.user.LoginController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
@FxmlView("MenuController.fxml")
public class MenuController {
    @FXML
    private MenuBar menuBar;

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(MenuController.class);

    public MenuController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        menuBar.setFocusTraversable(true);
        logger.debug("initialize");
    }

    @FXML
    private void handleAboutAction(ActionEvent event) {
    }

    @FXML
    public void handleProfileAction(ActionEvent event) {
    }

    @FXML
    public void handleLogOutAction(ActionEvent event) {
    }

    @FXML
    public void handleAddContactAction(ActionEvent event) {
    }
}