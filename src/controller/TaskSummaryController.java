package controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.Group;
import model.Task;
import util.Controller;


import java.net.URL;
import java.util.ResourceBundle;

public class TaskSummaryController implements Initializable {

    @FXML
    protected JFXCheckBox status;

    @FXML
    protected Text name, list, deadline;

    @FXML
    protected Label priority;

    private Task task;
    private Controller controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setTask(Task task){
        this.task = task;
        setContent();

    }

    private void setContent(){
        status.setSelected(task.isComplete());
        name.setText(task.getName());
        name.setStrikethrough(task.isComplete());
        list.setText(task.getGroup());
        deadline.setText(Controller.dateTimeFormat(task.getDeadline_date(), task.getDeadline_time()));
        priority.setText(task.getPriority());
        switch (task.getPriority()) {
            case "Low":
                priority.getStyleClass().add("label-low");
                break;

            case "Medium":
                priority.getStyleClass().add("label-medium");
                break;

            case "High":
                priority.getStyleClass().add("label-high");
                break;

            default:
                break;
        }
    }

    @FXML
    private void setComplete(){

        controller = new Controller();
        if (status.isSelected()){
            status.setSelected(false);
            controller.confirmationDialog(status, false, "Are you sure to complete this task", "", "Yes", true, false, task, true, "Dashboard", new Group());
        } else {
            status.setSelected(true);
            controller.confirmationDialog(status, false, "Are you sure to undo this task", "", "Undo", true, false, task, true, "Dashboard", new Group());

        }

    }





}
