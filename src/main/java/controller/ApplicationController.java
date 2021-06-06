package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import util.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    @FXML
    protected VBox main, container;

    @FXML
    protected FontIcon logout;

    @FXML
    protected JFXButton dashboard, tasks, lists;

    @FXML
    protected FontIcon dashboardIcon, taskIcon, listIcon;

    private Controller controller;

    private String initTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        controller = new Controller();
        dashboard.setOnAction(event -> switchTab("Dashboard", dashboard, dashboardIcon));
        tasks.setOnAction(event -> switchTab("Tasks", tasks, taskIcon));
        lists.setOnAction(event -> switchTab("Lists", lists, listIcon));

    }

    public void setInitTab(String tabName) {
        this.initTab = tabName;
    }

    public void initialTab(){
        switch (initTab) {
            case "Dashboard":
                switchTab("Dashboard", dashboard, dashboardIcon);
                break;

            case "Tasks":
                switchTab("Tasks", tasks, taskIcon);
                break;

            case "Lists":
                switchTab("Lists", lists, listIcon);
                break;

//            case "Settings":
//                switchTab("Settings", settings, settingsIcon);
//                break;

            default:
                break;
        }
    }

    @FXML
    private void logout() {
//        controller.confirmationDialog(logout, false, "Are you sure?", "", "Logout", false, true, new Task(), false, "", new Group());
        System.exit(0);
    }

    private void switchTab(String layout, JFXButton tab, FontIcon icon){
        setTab(layout);
        changeStyles(tab, icon);
    }

    private void setTab(String layout){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layout/lg/"+ (layout) + ".fxml"));
            container.getChildren().clear();
            container.getChildren().add(root);

        } catch (Exception e) {
            System.out.println("Error setting Tab " + layout + " " + e);
        }

    }

    private void changeStyles(JFXButton tab, FontIcon icon) {

        dashboard.getStyleClass().remove(2);
        dashboard.getStyleClass().add("tab-button");
        dashboardIcon.getStyleClass().remove(1);
        dashboardIcon.getStyleClass().add("tab-button-icon");

        tasks.getStyleClass().remove(2);
        tasks.getStyleClass().add("tab-button");
        taskIcon.getStyleClass().remove(1);
        taskIcon.getStyleClass().add("tab-button-icon");

        lists.getStyleClass().remove(2);
        lists.getStyleClass().add("tab-button");
        listIcon.getStyleClass().remove(1);
        listIcon.getStyleClass().add("tab-button-icon");

//        settings.getStyleClass().remove(2);
//        settings.getStyleClass().add("tab-button");
//        settingsIcon.getStyleClass().remove(1);
//        settingsIcon.getStyleClass().add("tab-button-icon");

        tab.getStyleClass().remove(2);
        tab.getStyleClass().add("tab-button-active");
        icon.getStyleClass().remove(1);
        icon.getStyleClass().add("tab-button-icon-active");

    }


}