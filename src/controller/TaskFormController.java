package controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Group;
import model.Task;
import service.GroupService;
import service.TaskService;
import util.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class TaskFormController implements Initializable {

    @FXML
    protected Text title;

    @FXML
    protected JFXTextField name;

    @FXML
    protected JFXTextArea description;

    @FXML
    protected JFXComboBox<String> priority;

    @FXML
    protected JFXComboBox<String> list;

    private HashMap<String, Integer> listHash;

    @FXML
    protected JFXDatePicker date;

    @FXML
    protected JFXTimePicker time;

    @FXML
    protected JFXButton done, cancel;

    private Task task;

    private Controller controller;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        controller = new Controller();
        setPriority();
        listHash = new HashMap<String, Integer>();
        setGroups(listHash);
        setDefaultDeadline(date, time);

    }


    @FXML
    protected void saveTask(){

        LocalDateTime deadlineLimit = LocalDateTime.of(LocalDate.now(), LocalTime.now().plusMinutes(29));
        LocalDateTime deadline = LocalDateTime.of(date.getValue(), time.getValue());

        displayTask();

        if (!name.getText().isEmpty() && !list.getItems().isEmpty() && !description.getText().isEmpty() && deadline.isAfter(deadlineLimit)) {

            if (task == null) {
                Task taskNew = new Task(name.getText(),description.getText(),date.getValue(),time.getValue(),priority.getValue(), listHash.get(list.getValue()));
                TaskService taskService = new TaskService(taskNew);
                taskService.add();
                controller.closeDialog(done);
                controller.refreshApplication(done,stage,scene,root,"Dashboard");

            } else {
                task.setName(name.getText());
                task.setDescription(description.getText());
                task.setDeadline_date(date.getValue());
                task.setDeadline_time(time.getValue());
                task.setPriority(priority.getValue());
                task.setGroup_id(listHash.get(list.getValue()));
                task.setMod_date(LocalDate.now());
                TaskService taskService = new TaskService(task);
                taskService.update();
                controller.closeDialog(done);
                controller.refreshApplication(done,stage,scene,root,"Tasks");
            }

        } else {

            if (name.getText().isEmpty()) {
                name.setPromptText("Please type the task name");
                name.setUnFocusColor(Paint.valueOf("#EC2F2F"));
            }

            if (description.getText().isEmpty()) {
                description.setPromptText("Please type the task description");
                description.setUnFocusColor(Paint.valueOf("#EC2F2F"));
            }

            if (deadline.isBefore(deadlineLimit)) {
                date.setDefaultColor(Paint.valueOf("#EC2F2F"));
                time.setDefaultColor(Paint.valueOf("#EC2F2F"));
            }
        }

    }

    @FXML
    protected void cancelTask() {
        controller.closeDialog(cancel);
    }

    @FXML
    protected void resetFields () {

        if (name.getPromptText().equals("Please type the task name")) {
            name.setPromptText("Title");
            name.setUnFocusColor(Paint.valueOf("#4d4d4d"));
        }

        if (description.getPromptText().equals("Please type the task description")) {
            description.setPromptText("Description");
            description.setUnFocusColor(Paint.valueOf("#4d4d4d"));
        }

        if (date.getDefaultColor().equals(Paint.valueOf("#EC2F2F"))) {
            date.setDefaultColor(Paint.valueOf("#EC892F"));
        }

        if (time.getDefaultColor().equals(Paint.valueOf("#EC2F2F"))) {
            time.setDefaultColor(Paint.valueOf("#EC892F"));
        }

    }

    public void setTask(Task task){
        this.task = task;
        title.setText("Modify task");
        name.setText(task.getName());
        description.setText(task.getDescription());
        list.setValue(task.getGroup());
        priority.setValue(task.getPriority());
        date.setValue(task.getDeadline_date());
        time.setValue(task.getDeadline_time());


    }

    //Sourcing Task Form items
    private void setPriority() {
        priority.getItems().add("Low");
        priority.getItems().add("Medium");
        priority.getItems().add("High");
        priority.setValue(priority.getItems().get(0));
    }

    private void setGroups(HashMap<String, Integer> listHash) {
        GroupService groupService = new GroupService();
        List<Group> groups = groupService.getLists();

        if (!groups.isEmpty()) {
            groups.forEach(group -> {
                listHash.put(group.getName(), group.getId());
                list.getItems().add(group.getName());
            });

            list.setValue(list.getItems().get(0));

        } else {
            list.setPromptText("No lists available");
        }

    }

    private void setDefaultDeadline(JFXDatePicker datePicker, JFXTimePicker timePicker) {

        datePicker.setValue(LocalDate.now().plusDays(1));
        timePicker.setValue(LocalTime.now());

    }

    private void displayTask() {

        System.out.println("Task Name: " + name.getText() + "\n" +
                "Description: " + description.getText() + "\n" +
                "List: " + "value: " + list.getValue() + " - id: " + listHash.get(list.getValue()) + "\n" +
                "Priority: " + priority.getValue() + "\n" +
                "Date: " + date.getValue().toString() + "\n" +
                "Time: " + time.getValue().toString());

    }



}
