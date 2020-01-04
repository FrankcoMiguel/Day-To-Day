package Controller;

import Model.Task;
import Service.TaskService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NewTaskController implements Initializable {

    @FXML
    protected TextField taskName;

    @FXML
    protected JFXDatePicker date;

    @FXML
    protected ImageView backButton;

    @FXML
    protected FontAwesomeIconView closeButton;

    @FXML
    protected JFXButton addTask;

    private TaskService taskService;
    private ControllerHelper controllerHelper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ControllerHelper.closeApp(closeButton);
        saveTask();
        goBack();



    }

    private void saveTask(){

        addTask.setOnAction(event -> {

            if (!taskName.getText().isEmpty() && getCharactersCount() && date.getValue().isAfter(LocalDate.now())){

                taskService = new TaskService();
                taskService.AddTask(new Task(taskName.getText(), date.getValue(),"Incomplete"));

            }

        });

    }

    @FXML
    protected boolean getCharactersCount(){

        return taskName.getText().length() < 40;

    }

    private void goBack(){

        backButton.setOnMouseClicked(event -> {

            controllerHelper = new ControllerHelper();
            controllerHelper.switchStage(backButton,"Homepage");

        });

    }





}
