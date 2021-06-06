import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/layout/lg/Splash.fxml"));
        primaryStage.setScene(new Scene(root, 1400, 900));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/media/logotype-lg.png"));
        primaryStage.setTitle("Day-to-Day");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
