package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Group;
import model.Task;
import service.GroupService;
import service.TaskService;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class AllTasksController  {

    @FXML
    protected Text task_no_data;

    @FXML
    protected VBox tasksContainer;

    @FXML
    protected JFXComboBox<String> priorityFilter, statusFilter, listFilter;

    private HashMap<String, Integer> listHash;

    @FXML
    protected JFXButton applyFilters, resetFilters;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        Group group = new Group("All");
//        GroupService groupService = new GroupService();
////        List<Group> groups = groupService.getGroups();
////        groups.add(group);
////        listHash = new HashMap<String, Integer>();
////
////        if (!(groups.size() == 1)) {
////            groups.forEach(group1 -> {
////                listHash.put(group.getName(),group.getId());
////            });
////        }
////
////        setPriority();
////        setStatus();
////        setGroups(groups);
////        resetFilters(groups.size() - 1);
////        loadTasks(tasksContainer);
////        applyFilters();
////
////    }
//
//    private void setPriority(){
//        priorityFilter.getItems().add("All");
//        priorityFilter.getItems().add("Low");
//        priorityFilter.getItems().add("Medium");
//        priorityFilter.getItems().add("High");
//        priorityFilter.setValue(priorityFilter.getItems().get(0));
//    }
//
//    private void setStatus() {
//        statusFilter.getItems().add("Both");
//        statusFilter.getItems().add("Uncompleted");
//        statusFilter.getItems().add("Completed");
//        statusFilter.setValue(statusFilter.getItems().get(0));
//    }
//
//    private void applyFilters() {
//        applyFilters.setOnMouseClicked(event -> {
//            tasksContainer.getChildren().clear();
//            loadTasks(tasksContainer);
//        });
//    }
//
//    private void setGroups(List<Group> groups) {
//        if (!groups.isEmpty()) {
//            groups.forEach(group -> {
//                listFilter.getItems().add(group.getName());
//            });
//            listFilter.setValue(listFilter.getItems().get(groups.size() - 1));
//        } else {
//            listFilter.setPromptText("No lists available");
//        }
//    }
//
//    private void loadTasks(VBox container) {
//
//        TaskService taskService = new TaskService();
//        List<Task> taskList;
//
//        if (priorityFilter.getValue().equals("All") && statusFilter.getValue().equals("Both") && listFilter.getValue().equals("All")) {
//            taskList = taskService.getTasks();
//        } else {
//            System.out.println(listFilter.getValue());
//            taskList = taskService.getTasks(priorityFilter.getValue(), statusFilter.getValue(), listFilter.getValue());
//        }
//
//        task_no_data = new Text();
//        task_no_data.setText("No tasks to display");
//        task_no_data.setFill(Paint.valueOf("#707070"));
//        task_no_data.setStrokeWidth(0.0);
//        task_no_data.setStrokeType(StrokeType.OUTSIDE);
//        task_no_data.setFont(new Font(14.0));
//
//        if (!container.getChildren().isEmpty()) {
//            if (taskList.isEmpty()) {
//                container.getChildren().add(task_no_data);
//                container.setAlignment(Pos.CENTER);
//            } else {
//                container.getChildren().remove(0);
//                container.setAlignment(Pos.TOP_CENTER);
//            }
//        } else {
//            if (taskList.isEmpty()) {
//                container.getChildren().add(task_no_data);
//                container.setAlignment(Pos.CENTER);
//            }
//        }
//
//        taskList.forEach(task -> {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/lg/Task.fxml"));
//                Parent root = loader.load();
//                JFXCheckBox checkBox = (JFXCheckBox) loader.getNamespace().get("status");
//                Text name = (Text) loader.getNamespace().get("name");
//                Text description = (Text) loader.getNamespace().get("description");
//                Text list = (Text) loader.getNamespace().get("list");
//                Text deadline = (Text) loader.getNamespace().get("deadline");
//                Label priority = (Label) loader.getNamespace().get("priority");
//
//                name.setText(task.getName());
//                checkBox.setSelected(task.isComplete());
//                description.setText(task.getDescription());
//                list.setText(task.getGroup());
//                deadline.setText(Controller2.dateTimeFormat(task.getDeadline_date(),task.getDeadline_time()));
//                priority.setText(task.getPriority());
//                priority.getStyleClass().remove(0);
//
//                switch (task.getPriority()) {
//                    case "Low":
//                        priority.getStyleClass().add("label-low");
//                        break;
//
//                    case "Medium":
//                        priority.getStyleClass().add("label-medium");
//                        break;
//
//                    case "High":
//                        priority.getStyleClass().add("label-high");
//                        break;
//
//                    default:
//                        break;
//                }
//
//                container.getChildren().add(root);
//
//            } catch (Exception e) {
//                System.out.println("Error Setting Tasks " + e);
//                e.printStackTrace();
//            }
//        });
//
//    }
//
//    private void resetFilters (int listLastValue) {
//
//        resetFilters.setOnMouseClicked(event -> {
//            priorityFilter.setValue(priorityFilter.getItems().get(0));
//            statusFilter.setValue(statusFilter.getItems().get(0));
//            listFilter.setValue(listFilter.getItems().get(listLastValue));
//        });
//
//    }

}
