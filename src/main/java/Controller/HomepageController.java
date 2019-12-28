package Controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    @FXML
    protected ImageView settings;

    @FXML
    protected JFXButton newTaskButton, completeTaskButton, removeTaskButton;

    @FXML
    protected VBox scrollBarContainer;

    @FXML
    protected FontAwesomeIconView closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ControllerHelper.closeApp(closeButton);

    }

    private void loadAllTasks(){

        //Task service..
        //And settle on scrollBarContainer <VBox>

    }


}
