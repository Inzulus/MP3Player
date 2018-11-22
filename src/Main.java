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


        HBox rightPane = new HBox();
        rightPane.setStyle("-fx-background-color: rgb(30,30,30);");
        Label lRight = new Label("Right");
        HBox.setMargin(lRight, new Insets(5));
        rightPane.getChildren().add(lRight);

        HBox bottomPane = new HBox();
        //bottomPane.setStyle("-fx-background-color: rgb(60,60,60);");
        //Label lLeft = new Label("Left");
        //HBox.setMargin(lLeft, new Insets(5));
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        bottomPane.getChildren().add(slider);

        HBox leftPane = new HBox();
        leftPane.setStyle("-fx-background-color: rgb(90,90,90);");

        HBox viewPane = new HBox();
        //Image image = new Image(getClass().getResourceAsStream("Small-mario.png"));
        //ImageView imageView = new ImageView(image);
        //viewPane.getChildren().add(imageView);

        root.setBottom(bBar.create());
        root.setCenter(cBox.create());
        root.setLeft(leftPane);
        root.setRight(rightPane);
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
