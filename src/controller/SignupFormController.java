package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;
import util.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupFormController implements Initializable {

    @FXML
    protected AnchorPane signupForm;

    @FXML
    protected JFXTextField name, email;

    @FXML
    protected JFXPasswordField password, repeatPassword;

    @FXML
    protected FontIcon nameCheck, emailCheck, passwordCheck, repeatPasswordCheck;

    @FXML
    protected JFXButton signup;

    @FXML
    protected Text login;

    private Controller controller;

    private String regex_name ="^[a-zA-Z]*$";
    private String regex_email = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private String regex_password = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void switchToLogin() throws IOException {

        controller = new Controller();
        String[] text = {"Welcome Back!", "Log into your account"};
        String[] colors = {"#000000"};

        controller.switchLoginForms(login, "LoginForm");

    }

    @FXML
    protected void ValidateFields() {

        nameCheck.setVisible(!name.getText().isEmpty() && name.getText().matches(regex_name));
        emailCheck.setVisible(!email.getText().isEmpty() && email.getText().matches(regex_email));
        passwordCheck.setVisible(!password.getText().isEmpty() && password.getText().matches(regex_password));
        repeatPasswordCheck.setVisible(password.getText().equals(repeatPassword.getText()) && !repeatPassword.getText().isEmpty());

    }

}
