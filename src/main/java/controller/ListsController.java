package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import model.Group;
import service.GroupService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ListsController implements Initializable {

    @FXML
    protected VBox listContainerLeft, listContainerCenter, listContainerRight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

         getLists();

    }

    private void getLists() {

        GroupService groupService = new GroupService();
        List<Group> groups = groupService.getLists();
        listContainerLeft.getChildren().clear();
        listContainerCenter.getChildren().clear();
        listContainerRight.getChildren().clear();

        AtomicInteger container = new AtomicInteger();

        groups.forEach(group -> {

            try {

                FXMLLoader layout = new FXMLLoader(getClass().getResource("/layout/lg/List.fxml"));
                Parent listContent = layout.load();
                ListController controller = layout.getController();
                controller.setGroup(group);

                switch (container.get()) {
                    case 0:
                        listContainerLeft.getChildren().add(listContent);
                        container.set(1);
                        break;

                    case 1:
                        listContainerCenter.getChildren().add(listContent);
                        container.set(2);
                        break;

                    case 2:
                        listContainerRight.getChildren().add(listContent);
                        container.set(0);
                        break;

                    default:
                        break;
                }


            } catch (IOException e) {
                System.out.println("Error setting Group content");
                e.printStackTrace();
            }

        });


    }

}
