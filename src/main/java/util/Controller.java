package util;

import com.jfoenix.controls.JFXDialog;
import controller.ApplicationController;
import controller.DialogConfirmController;
import controller.ListFormController;
import controller.TaskFormController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Group;
import model.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Controller {


    public void switchScene(Node node, Stage stage, Scene scene, Parent root, String layout) {

        try {
            root = FXMLLoader.load(getClass().getResource("/layout/lg/" + (layout) + ".fxml"));
            stage = (Stage) node.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            System.out.println("Error switching scenes");
            e.printStackTrace();

        }

    }

    public void refreshApplication(Node node, Stage stage, Scene scene, Parent root, String initTab) {
        try {
            FXMLLoader layout_app = new FXMLLoader(getClass().getResource("/layout/lg/Application.fxml"));
            root = layout_app.load();
            ApplicationController controller = layout_app.getController();
            controller.setInitTab(initTab);
            controller.initialTab();
            stage = (Stage) node.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            System.out.println("Error switching scenes");
            e.printStackTrace();

        }
    }

    public static String dateTimeFormat(LocalDate deadline_date, LocalTime deadline_time) {

        String date = LocalDateTime.of(deadline_date, deadline_time).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        String time = LocalDateTime.of(deadline_date, deadline_time).format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        ;
        return date + " " + time;
    }

    StackPane getLoginContainer(Node control) {

        VBox root = (VBox) control.getScene().getRoot();
        AnchorPane anchorPane = (AnchorPane) root.getChildren().get(1);
        VBox vBox = (VBox) anchorPane.getChildren().get(1);
        return (StackPane) vBox.getChildren().get(1);

    }

    public void switchLoginForms(Node control, String layout) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layout/lg/" + (layout) + ".fxml"));
            StackPane container = (StackPane) getLoginContainer(control);
            AnchorPane form = (AnchorPane) container.getChildren().get(0);
            form.translateXProperty().set(0);

            Timeline timelineOld = new Timeline();
            KeyValue kvOld = new KeyValue(form.translateXProperty(), form.getScene().getWidth(), Interpolator.EASE_IN);
            KeyFrame kfOld = new KeyFrame(Duration.millis(500), kvOld);
            timelineOld.getKeyFrames().add(kfOld);
            timelineOld.setOnFinished(event -> {

                root.translateXProperty().set(form.getScene().getWidth());

                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_OUT);
                KeyFrame kf = new KeyFrame(Duration.millis(400), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
                container.getChildren().add(root);
                container.getChildren().remove(0);


            });
            timelineOld.play();
        } catch (IOException e) {
            System.out.println("Error switchingLoginForms " + e);
        }
    }

    public void openDialog(Node node, boolean overlayClose, String layout) {
        try {
            StackPane container = (StackPane) node.getScene().getRoot();
            FXMLLoader layout_dialog = new FXMLLoader(getClass().getResource("/layout/lg/" + (layout) + ".fxml"));
            Region region = layout_dialog.load();
            JFXDialog dialog = new JFXDialog(container, region, JFXDialog.DialogTransition.BOTTOM, overlayClose);
            dialog.show();

        } catch (Exception e) {
            System.out.println("Error opening Dialog " + e);
            e.printStackTrace();
        }
    }

    public void confirmationDialog(Node node, boolean overlayClose, String title, String subTitleText, String confirmText, boolean confirmClass, boolean logout, Task task, boolean completing, String previous, Group group) {
        try {
            StackPane container = (StackPane) node.getScene().getRoot();
            FXMLLoader layout_dialog = new FXMLLoader(getClass().getResource("/layout/lg/DialogConfirm.fxml"));
            Region region = layout_dialog.load();
            JFXDialog dialog = new JFXDialog(container, region, JFXDialog.DialogTransition.BOTTOM, overlayClose);

            DialogConfirmController controller = layout_dialog.getController();
            controller.setTitle(title);
            controller.setSubtitleText(subTitleText);
            controller.setConfirmText(confirmText);
            controller.setConfirmClass(confirmClass);
            controller.setLogout(logout);
            controller.setTask(task);
            controller.setCompleting(completing);
            controller.setPrevious(previous);
            controller.setGroup(group);
            dialog.show();

        } catch (Exception e) {
            System.out.println("Error opening Dialog " + e);
            e.printStackTrace();
        }
    }

    public void openDialogGroup(Node node, boolean overlayClose, String layout, Group group) {
        try {
            StackPane container = (StackPane) node.getScene().getRoot();
            FXMLLoader layout_dialog = new FXMLLoader(getClass().getResource("/layout/lg/" + (layout) + ".fxml"));
            Region region = layout_dialog.load();
            JFXDialog dialog = new JFXDialog(container, region, JFXDialog.DialogTransition.BOTTOM, overlayClose);

            ListFormController controller = layout_dialog.getController();
            controller.setGroup(group);

            dialog.show();

        } catch (Exception e) {
            System.out.println("Error opening Dialog " + e);
            e.printStackTrace();
        }
    }

    public void openDialogTask(Node node, boolean overlayClose, String layout, Task task) {
        try {
            StackPane container = (StackPane) node.getScene().getRoot();
            FXMLLoader layout_dialog = new FXMLLoader(getClass().getResource("/layout/lg/" + (layout) + ".fxml"));
            Region region = layout_dialog.load();
            JFXDialog dialog = new JFXDialog(container, region, JFXDialog.DialogTransition.BOTTOM, overlayClose);

            TaskFormController controller = layout_dialog.getController();
            controller.setTask(task);
            dialog.show();

        } catch (Exception e) {
            System.out.println("Error opening Dialog " + e);
            e.printStackTrace();
        }
    }

    public void closeDialog(Node node) {
        try {
            StackPane container = (StackPane) node.getScene().getRoot();
            JFXDialog dialog = (JFXDialog) container.getChildren().get(1);
            dialog.close();

        } catch (Exception e) {
            System.out.println("Error opening Dialog " + e);
            e.printStackTrace();
        }
    }

}
