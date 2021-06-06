package controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.GroupService;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.TaskService;
import util.Controller;
import util.LoaderController;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable{

    @FXML
    public StackPane main;

    @FXML
    public VBox contentRoot;

    @FXML
    public JFXProgressBar loader;

    @FXML
    public Label loader_status;


    private Stage stage;
    private Scene scene;
    private Parent root;
    private Controller controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loader.setProgress(0.0);
        start();

    }

    private void start(){

        //Load Task method implementation
        LoaderController splash_loading = new LoaderController() {
            @Override
            protected void loadTasks() {

                //List/Group table creation
                GroupService groupService = new GroupService();
                groupService.create();

                //Task table creation
                TaskService taskService = new TaskService();
                taskService.create();

            }
        };

        loader.progressProperty().bind(splash_loading.progressProperty());
        loader_status.textProperty().bind(splash_loading.messageProperty());

        splash_loading.setOnSucceeded(event -> {

            controller = new Controller();
            controller.refreshApplication(loader, stage, scene, root, "Dashboard");

        });

        new Thread(splash_loading).start();

    }

}
