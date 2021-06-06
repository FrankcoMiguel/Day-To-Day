package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    protected StackPane formBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addLogin();

    }

    private void addLogin () {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/layout/lg/LoginForm.fxml"));
            formBox.getChildren().add(root);

        } catch (IOException e) {
            System.out.println("Error adding login " + e);
        }



    }



}
