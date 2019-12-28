package Controller;


import Model.Task;
import Service.TaskService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ControllerHelper {

    private static double x, y;

    static void dragApp(Pane pane){

        pane.setOnMousePressed(event -> {

            pane.setCursor(Cursor.OPEN_HAND);
            x = event.getSceneX();
            y = event.getSceneY();

        });

        pane.setOnMouseDragged(event -> {

            pane.setCursor(Cursor.CLOSED_HAND);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });

        pane.setOnMouseReleased(event -> pane.setCursor(Cursor.DEFAULT));

    }

    static void closeApp(FontAwesomeIconView closeButton){

        closeButton.setOnMouseClicked(event -> System.exit(0));

    }

    void switchSplash(Node control){

        try {

            Parent root = FXMLLoader.load(getClass().getResource("../layout/Homepage.fxml"));
            root.translateXProperty().set(control.getScene().getWidth());
            Pane pane = (Pane) control.getScene().getRoot();
            pane.getChildren().clear();
            pane.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.DISCRETE);
            KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

        } catch (IOException e) {

            System.out.println("Error switching stages");
            e.printStackTrace();

        }

    }

    void setTasks(VBox container) throws IOException {

        TaskService taskService = new TaskService();
        List<Task> taskList = taskService.GetAll();

        if (taskList != null){

            for (Task task : taskList){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/Task.fxml"));
                Parent root = loader.load();
                Text status = (Text) loader.getNamespace().get("status");
                Text taskname = (Text) loader.getNamespace().get("task");
                Text deadline = (Text) loader.getNamespace().get("deadline");
                status.setText(task.getStatus());
                taskname.setText(task.getName());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, YYYY");
                deadline.setText(task.getDeadline().format(formatter));
                container.getChildren().add(root);

            }

        }



    }






}
