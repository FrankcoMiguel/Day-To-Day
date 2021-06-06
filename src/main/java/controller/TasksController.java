package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import model.Task;
import service.TaskService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class TasksController implements Initializable {

    @FXML
    protected VBox taskContainerLeft, taskContainerCenter, taskContainerRight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

         getLists();

    }

    private void getLists() {

        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getTasks();
        taskContainerLeft.getChildren().clear();
        taskContainerCenter.getChildren().clear();
        taskContainerRight.getChildren().clear();

        AtomicInteger container = new AtomicInteger();

        tasks.forEach(task -> {

            try {

                FXMLLoader layout = new FXMLLoader(getClass().getResource("/layout/lg/Task.fxml"));
                Parent taskContent = layout.load();
                TaskController controller = layout.getController();
                controller.setTask(task);

                switch (container.get()) {
                    case 0:
                        taskContainerLeft.getChildren().add(taskContent);
                        container.set(1);
                        break;

                    case 1:
                        taskContainerCenter.getChildren().add(taskContent);
                        container.set(2);
                        break;

                    case 2:
                        taskContainerRight.getChildren().add(taskContent);
                        container.set(0);
                        break;

                    default:
                        break;
                }


            } catch (IOException e) {
                System.out.println("Error setting Task content");
                e.printStackTrace();
            }

        });


    }

}
