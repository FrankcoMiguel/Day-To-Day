package Controller;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

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






}
