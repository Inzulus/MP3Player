package scenes.playerview;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.ButtonBar;
import resources.CoverBox;
import resources.InfoBox;
import resources.PositionSlider;


public class PlayerView extends Application {

    private InfoBox ibox = new InfoBox();
    private ButtonBar bBar = new ButtonBar();
    private CoverBox cBox = new CoverBox();
    private PositionSlider pSlider = new PositionSlider();

    @Override
    public void start(Stage primaryStage){

        BorderPane root = new BorderPane();
        VBox playBox = new VBox();
        playBox.getChildren().addAll(bBar.create(),pSlider.create());
        playBox.setPadding(new Insets(5));


        root.setBottom(playBox);
        root.setCenter(cBox.create());
        root.setTop(ibox.create());


        Scene scene = new Scene(root);

        primaryStage.setTitle("MP3Player");
        primaryStage.setScene(scene);
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }




    public InfoBox getIbox() {
        return ibox;
    }

    public ButtonBar getbBar() {
        return bBar;
    }

    public CoverBox getcBox() {
        return cBox;
    }

    public PositionSlider getpSlider() {
        return pSlider;
    }
}
