package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Group;

public class GroupSummary {

    private Group group;


    public GroupSummary(Group group) {
        this.group = group;
    }

    public HBox generateGroup() {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefWidth(360.0);
        hBox.setPrefHeight(40.0);
        hBox.setSpacing(30.0);
        hBox.getStyleClass().add("task");

        Text list = new Text(group.getName());
        list.setFill(Paint.valueOf("#ec892f"));
        list.setStrokeType(StrokeType.OUTSIDE);
        list.setStrokeWidth(0.0);
        list.setWrappingWidth(150.0);
        list.setFont(new Font("Arial Bold", 16.0));

        Text count = new Text(group.getTotal_completed() + "/" + group.getTotal_tasks());
        count.setFill(Paint.valueOf("#ec892f"));
        count.setStrokeType(StrokeType.OUTSIDE);
        count.setStrokeWidth(0.0);
        count.setLayoutX(10.0);
        count.setLayoutY(39.0);
        count.setTextAlignment(TextAlignment.CENTER);
        count.setWrappingWidth(135.0);
        count.setFont(new Font("Arial Bold", 16.0));

        hBox.setPadding(new Insets(15.0,20.0,15.0,20.0));
        hBox.getChildren().add(list);
        hBox.getChildren().add(count);

        return hBox;

    }

    public static HBox generateHeader() {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefWidth(360.0);
        hBox.setPrefHeight(56.0);
        hBox.setSpacing(30.0);

        Text listCol = new Text("List");
        listCol.setFill(Paint.valueOf("#707070"));
        listCol.setStrokeType(StrokeType.OUTSIDE);
        listCol.setStrokeWidth(0.0);
        listCol.setWrappingWidth(150.0);
        listCol.setFont(new Font("System Bold", 17.0));


        Text completedTask = new Text("Tasks completed");
        completedTask.setFill(Paint.valueOf("#707070"));
        completedTask.setLayoutX(152.0);
        completedTask.setLayoutY(45.0);
        completedTask.setStrokeType(StrokeType.OUTSIDE);
        completedTask.setStrokeWidth(0.0);
        completedTask.setTextAlignment(TextAlignment.CENTER);
        completedTask.setWrappingWidth(135.0);
        completedTask.setFont(new Font("System Bold", 17.0));

        hBox.setPadding(new Insets(15.0,20.0,15.0,20.0));

        hBox.getChildren().add(listCol);
        hBox.getChildren().add(completedTask);
        return hBox;

    }

}
