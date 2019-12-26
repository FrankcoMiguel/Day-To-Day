package Controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable{

    @FXML
    protected VBox main;

    @FXML
    protected JFXProgressBar loader;

    @FXML
    protected Label loader_status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ControllerHelper.dragApp(main);
        loader.setProgress(0.0);
        start();

    }

    private void start(){

        LoaderController splash_loading = new LoaderController() {
            @Override
            protected void loadTasks() {

                // Get all tasks..

            }
        };

        loader.progressProperty().bind(splash_loading.progressProperty());
        loader_status.textProperty().bind(splash_loading.messageProperty());

        splash_loading.setOnSucceeded(event -> {

            ControllerHelper controllerHelper = new ControllerHelper();
            controllerHelper.switchSplash(loader_status);

        });

        new Thread(splash_loading).start();

    }


}
