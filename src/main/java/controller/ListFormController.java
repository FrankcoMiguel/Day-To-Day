package controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Group;
import service.GroupService;
import util.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ListFormController implements Initializable {

    @FXML
    protected Text title;

    @FXML
    protected JFXTextField name;

    @FXML
    protected JFXButton done, cancel;

    private final String regex ="^[a-zA-Z]+(\\s[a-zA-Z]+)?$";

    private Controller controller;
    private Group group;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new Controller();
    }

    //Add List

    @FXML
    protected void saveList(){

        if (!name.getText().isEmpty()) {

            if (name.getText().matches(regex)) {

                try {
                    if (group == null) {
                        Group groupNew = new Group(name.getText());
                        GroupService groupService = new GroupService(groupNew);
                        groupService.add();
                        controller.refreshApplication(done, stage, scene, root, "Dashboard");
                    } else {
                        group.setName(name.getText());
                        group.setMod_date(LocalDate.now());
                        GroupService groupService = new GroupService(group);
                        groupService.update();
                        controller.refreshApplication(done, stage, scene, root, "Lists");

                    }
                    controller.closeDialog(cancel);


                } catch (Exception e) {
                    System.out.println("This list already exists " + e);
                    name.clear();
                    name.setPromptText("This list already exists");
                    name.setUnFocusColor(Paint.valueOf("#EC2F2F"));
                }

            } else {

                name.setPromptText("Please use only letters");
                name.setUnFocusColor(Paint.valueOf("#EC2F2F"));
            }

        } else {
            name.setPromptText("Please type the list name");
            name.setUnFocusColor(Paint.valueOf("#EC2F2F"));
        }

    }

    @FXML
    protected void cancelList(){
        controller.closeDialog(cancel);
    }

    @FXML
    protected void resetField () {
        if (!name.getPromptText().equals("Title")) {
            name.setPromptText("Title");
        }

        if (!name.getUnFocusColor().equals(Paint.valueOf("#ec892f"))) {
            name.setUnFocusColor(Paint.valueOf("#4d4d4d"));
        }

    }


    //Modify List
    public void setGroup(Group group) {
        this.group = group;
        title.setText("Modify list");
        name.setText(group.getName());

    }


}
