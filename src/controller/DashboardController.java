package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Group;
import model.Task;
import service.GroupService;
import service.TaskService;
import util.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {



    @FXML
    protected Text greeting, date, allTasks, allLists;


    @FXML
    protected JFXButton newTask, newList;

    @FXML
    protected VBox dashboard, taskContainer, groupContainer;

    @FXML
    private Controller controller;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        controller = new Controller();
        date.setText("Today is " + LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        setGreeting();
        loadTasks();
        loadList();

    }

    private void setGreeting() {
        if (LocalTime.now().getHour() >= 0 && LocalTime.now().getHour() < 6) {
            greeting.setText("It's late but keep the good work!");
        } else if (LocalTime.now().getHour() >= 6 && LocalTime.now().getHour() < 12) {
            greeting.setText("Good morning");
        } else if (LocalTime.now().getHour() >= 12 && LocalTime.now().getHour() < 18) {
            greeting.setText("Good afternoon");
        } else if (LocalTime.now().getHour() >= 18 && LocalTime.now().getHour() <= 23) {
            greeting.setText("Good night");
        }
    }

    private void loadTasks() {

        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getSummary();

        if (!tasks.isEmpty()) {
            taskContainer.getChildren().clear();
            taskContainer.setAlignment(Pos.TOP_CENTER);
            tasks.forEach(task -> {
                try {
                   FXMLLoader layout = new FXMLLoader(getClass().getResource("/layout/lg/TaskSummary.fxml"));
                   Parent taskContent = layout.load();
                   TaskSummaryController controller = layout.getController();

                   controller.setTask(task);
                   taskContainer.getChildren().add(taskContent);

                } catch (IOException e) {
                    System.out.println("Error setting Task Summary content");
                    e.printStackTrace();
                }
            });
            allTasks.setDisable(false);
            allTasks.setVisible(true);
        } else {
            taskContainer.setAlignment(Pos.CENTER);
            Text text = new Text("No tasks available");
            text.setFont(new Font("System", 12));
            taskContainer.getChildren().clear();
            taskContainer.getChildren().add(text);
            allTasks.setDisable(true);
            allTasks.setVisible(false);
        }

    }

    private void loadList() {

        GroupService groupService = new GroupService();
        List<Group> groups = groupService.getSummary();

        if (!groups.isEmpty()) {
            groupContainer.getChildren().clear();
            HBox header = GroupSummary.generateHeader();
            groupContainer.getChildren().add(header);
            groups.forEach(group -> {
                GroupSummary groupSummary = new GroupSummary(group);
                HBox groupContent = groupSummary.generateGroup();
                groupContainer.getChildren().add(groupContent);
            });
            allLists.setDisable(false);
            allLists.setVisible(true);
        } else {
            groupContainer.setAlignment(Pos.CENTER);
            Text text = new Text("No lists available");
            text.setFont(new Font("System", 12));
            groupContainer.getChildren().clear();
            groupContainer.getChildren().add(text);
            allLists.setDisable(true);
            allLists.setVisible(false);
        }
    }


    @FXML
    protected void addTask(){
        controller.openDialog(newTask, false, "TaskForm");
    }

    @FXML
    protected void addList(){
        controller.openDialog(newList, false, "ListForm");
    }

    @FXML
    protected void openLists(){
        try {

            FXMLLoader layout = new FXMLLoader(getClass().getResource("/layout/lg/Lists.fxml"));
            Parent lists = layout.load();
            dashboard.getChildren().clear();
            dashboard.getChildren().add(lists);

        } catch (Exception e) {

            System.out.println("Error adding Lists content");

        }

    }

    @FXML
    protected void openTasks(){
        try {

            FXMLLoader layout = new FXMLLoader(getClass().getResource("/layout/lg/Tasks.fxml"));
            Parent tasks = layout.load();
            dashboard.getChildren().clear();
            dashboard.getChildren().add(tasks);

        } catch (Exception e) {

            System.out.println("Error adding Tasks content");
            e.printStackTrace();

        }
    }

}
