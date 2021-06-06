package controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Group;
import model.Task;
import org.kordamp.ikonli.javafx.FontIcon;
import util.Controller;


import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class TaskController implements Initializable {


    @FXML
    protected JFXCheckBox status;

    @FXML
    protected Text name, list, description, deadline_date, deadline_time;

    @FXML
    protected VBox lateral_bar;

    @FXML
    protected Label priority;

    @FXML
    protected FontIcon edit, remove;

    private Task task;
    private Controller controller;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        controller = new Controller();
    }

    public void setTask(Task task) {
        this.task = task;
        setContent();
    }


    private void setContent(){

        switch (task.getPriority()){
            case "Low":
                lateral_bar.setStyle("-fx-background-color: #82E188");
                break;

            case "Medium":
                lateral_bar.setStyle("-fx-background-color: #ECE586");
                break;

            case "High":
                lateral_bar.setStyle("-fx-background-color: #F56C5C");
                break;

            default:
                break;
        }

        status.setSelected(task.isComplete());
        name.setText(task.getName());
        description.setText(task.getDescription());
        list.setText(task.getGroup());
        deadline_date.setText(task.getDeadline_date().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        deadline_time.setText(task.getDeadline_time().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        priority.setText("Priority: " + task.getPriority());

    }



    @FXML
    protected void editTask(){

        controller.openDialogTask(edit,false,"TaskForm", task);

    }

    @FXML
    protected void removeTask(){

        controller.confirmationDialog(remove,false,"Are you sure", "", "Remove", false,false, task, false, "Tasks", new Group());

    }

    @FXML
    private void setComplete(){

        if (status.isSelected()){
            status.setSelected(false);
            controller = new Controller();
            controller.confirmationDialog(status, false, "Are you sure to complete this task", "", "Yes", true, false, task, true, "Tasks", new Group());
        } else {

            status.setSelected(true);
            controller = new Controller();
            controller.confirmationDialog(status, false, "Are you sure to undo this task", "", "Undo", true, false, task, true, "Tasks", new Group());

        }

    }


}
