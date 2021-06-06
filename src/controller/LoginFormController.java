package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {


    @FXML
    protected JFXButton login;

    @FXML
    protected Text register;

    @FXML
    protected AnchorPane loginForm;

    private Controller controller;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        controller = new Controller();

    }

    @FXML
    private void switchToSignup() {

        String[] text = {"Let's get started", ""};
        String[] colors = {"#EC892F"};

        controller.switchLoginForms(register, "SignupForm");

    }

    @FXML
    private void loginApp() {
        controller.refreshApplication(login, stage, scene, root, "Dashboard");
    }

}
