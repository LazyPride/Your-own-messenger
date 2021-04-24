package com.s1cket.labs.client.controller;

import com.s1cket.labs.client.controller.user.ChatScreen;
import com.s1cket.labs.client.controller.user.LoginScreen;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxContextLoader;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@FxmlView("Main.fxml")
public class MainController {
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabAdd;

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController( FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    @FXML
    public void onTabAdd(Event event) {
        logger.debug("onTabAdd");

        if (tabAdd.isSelected()) {
            Tab tab = createTab();
            int tabsSize = tabPane.getTabs().size();
            tabPane.getTabs().add(tabsSize - 1, tab);
            tabPane.getSelectionModel().select(tab);
        }
    }

    private Tab createTab() {
        Tab tab = new Tab("New User");
        tab.setOnSelectionChanged(this::onSelectionChanged);
        FxControllerAndView<LoginScreen, StackPane> loginScreen =
                fxWeaver.load(LoginScreen.class);
        tab.setContent(loginScreen.getView().get());
        return tab;
    }

    private void onSelectionChanged(Event event) {
        logger.debug("onSelectionChanged");
        Tab tab = (Tab) event.getTarget();
        if (tab.isSelected()) {
            FxControllerAndView<LoginScreen, StackPane> loginScreen =
                    fxWeaver.load(LoginScreen.class);
            loginScreen.getController().setParentTab(tab);
        }
    }
}