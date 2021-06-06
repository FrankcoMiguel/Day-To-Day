package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Group;
import model.Task;
import service.GroupService;
import service.TaskService;
import util.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogConfirmController implements Initializable {

    @FXML
    protected Text title;

    @FXML
    protected VBox titleContainer;

    @FXML
    protected JFXButton confirm, cancel;


    private boolean logout;
    private Task task;
    private Group group;

    private boolean confirmClass;
    private boolean completing;
    private String subtitleText;
    private String previous;

    private Controller controller;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        controller = new Controller();

    }

    @FXML
    protected void confirmDialog() {

        if (logout) {
            controller.switchScene(confirm, stage, scene, root, "Login");
        }

        if (task.getName() != null) {

            TaskService taskService = new TaskService(task);
            if (completing) {
                taskService.setComplete();

                if (previous.equals("Dashboard")) {
                    controller.refreshApplication(confirm, stage, scene, root, "Dashboard");
                } else {
                    controller.refreshApplication(confirm, stage, scene, root, "Tasks");
                }

            } else {
                taskService.delete();
                controller.refreshApplication(confirm, stage, scene, root, "Tasks");

            }
        }

        if (group.getName() != null) {

            GroupService groupService = new GroupService(group);
            groupService.delete();
            controller.refreshApplication(confirm, stage, scene, root, "Lists");

        }

    }

    @FXML
    protected void cancelDialog() {
        controller.closeDialog(cancel);
    }

    public void setLogout(boolean logout){
        this.logout = logout;
    }

    public void setTask(Task task){
        this.task = task;
    }

    public void setGroup(Group group){
        this.group = group;
    }

    public void setConfirmClass(boolean confirmClass) {
        this.confirmClass = confirmClass;

        if (confirmClass) {

            confirm.setStyle("-fx-background-color: #ec892f");
            cancel.setTextFill(Paint.valueOf("#ec892f"));

        } else {

            cancel.setStyle("-fx-background-color: #ec892f");
            confirm.setTextFill(Paint.valueOf("#ec892f"));

        }

    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setConfirmText(String confirmText){
        this.confirm.setText(confirmText);
    }

    public void setCompleting(boolean completing){
        this.completing = completing;
    }

    public void setPrevious(String previous) {this.previous = previous;}

    public void setSubtitleText(String subtitleText) {
        this.subtitleText = subtitleText;
        if (!subtitleText.equals("")){
            Text text = new Text(subtitleText);
            text.setFont(new Font("System", 15));
            text.setWrappingWidth(344.5);
            text.setTextAlignment(TextAlignment.CENTER);
            titleContainer.getChildren().add(text);
        }
    }
}
