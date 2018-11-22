import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));

        BorderPane root = new BorderPane();

        InfoBox ibox = new InfoBox();
        ButtonBar bBar = new ButtonBar();
        CoverBox cBox = new CoverBox();


        root.setBottom(bBar.create());
        root.setCenter(cBox.create());
        root.setTop(ibox.create());


        Scene scene = new Scene(root);

        primaryStage.setTitle("MP3Player");
        primaryStage.setScene(scene);
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
