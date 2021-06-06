package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.Group;
import model.Task;
import util.Controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class ListController implements Initializable {


    @FXML
    protected Text name, createdOn, lastUpdate, tasksCompleted, totalTasks;

    @FXML
    protected Label edit, remove;

    private Group group;

    private Controller controller;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        controller = new Controller();

    }

    public void setGroup(Group group){
        this.group = group;
        setContent();
    }

    private void setContent(){
        name.setText(group.getName());
        createdOn.setText("Created on: " +   group.getAdd_date().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        lastUpdate.setText("Last update: " + group.getMod_date().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        tasksCompleted.setText("Tasks completed: " + group.getTotal_completed());
        totalTasks.setText("Total tasks: " + group.getTotal_tasks());
    }

    @FXML
    private void editList(){
        controller.openDialogGroup(edit,false,"ListForm", group);
    }

    @FXML
    private void removeList(){
        controller.confirmationDialog(remove, false, "Are you sure?", "(The tasks related will be removed)", "Remove", false, false, new Task(), false, "", group);
    }

}
