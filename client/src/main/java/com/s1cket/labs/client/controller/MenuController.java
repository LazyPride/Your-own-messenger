package com.s1cket.labs.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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