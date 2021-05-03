package com.s1cket.labs.client.controller;

import com.s1cket.labs.client.controller.user.LoginController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@FxmlView("MainController.fxml")
public class MainController {
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabAdd;

    private final FxWeaver fxWeaver;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    private Map<Tab, FxControllerAndView<LoginController, Node>> mapTabCV = new HashMap<>();

    public MainController( FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        logger.debug("initialize");
    }

    @FXML
    public void onTabAdd() {
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

        var loginCV = fxWeaver.load(LoginController.class);
        mapTabCV.put(tab, loginCV);
        switchTab(tab);
        return tab;
    }

    private void onSelectionChanged(Event event) {
        Tab tab = (Tab) event.getTarget();
        if (tab.isSelected()) {
            switchTab(tab);
        }
    }

    private void switchTab(Tab tab) {
        var loginCV = mapTabCV.get(tab);
        var view = loginCV.getView();
        var controller = loginCV.getController();
        if (view.isPresent()) {
            Node node = view.get();
            tab.setContent(node);
            controller.setView(node);
            controller.setTab(tab);
        }
        else {
            logger.error("Login view is not present!");
        }
    }
}