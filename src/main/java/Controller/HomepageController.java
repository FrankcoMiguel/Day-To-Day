package Controller;

import Service.TaskService;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    @FXML
    protected ImageView settings;

    @FXML
    protected JFXButton newTaskButton, completeTaskButton, removeTaskButton;

    @FXML
    protected VBox scrollBarContainer;

    @FXML
    protected FontAwesomeIconView closeButton;

    private TaskService taskService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        taskService = new TaskService();
        ControllerHelper.closeApp(closeButton);
        try {

            loadAllTasks();

        } catch (IOException e) {

            System.out.println("Error displaying tasks \n" + e);

        }

        createNewTask();

    }

    private void loadAllTasks() throws IOException {

        // GET ALL TASKS
        taskService.GetAll();
        ControllerHelper controllerHelper = new ControllerHelper();
        controllerHelper.setTasks(scrollBarContainer);


    }

    private void createNewTask(){

        newTaskButton.setOnAction(event -> {

            ControllerHelper controllerHelper = new ControllerHelper();
            controllerHelper.switchStage(newTaskButton,"NewTask");

        });

    }


}
