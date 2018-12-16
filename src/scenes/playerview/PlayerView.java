package scenes.playerview;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.*;

import java.io.File;


public class PlayerView extends Application {

    private InfoBox ibox = new InfoBox();
    private ButtonBar bBar = new ButtonBar();
    private CoverBox cBox = new CoverBox();
    private PositionSlider pSlider = new PositionSlider();
    private PlaylistBox plView = new PlaylistBox();
    private Stage stage;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;

        HBox bigHBox = new HBox();
        BorderPane root = new BorderPane();
        VBox playBox = new VBox();
        playBox.getChildren().addAll(bBar.create(),pSlider.create());
        playBox.setPadding(new Insets(5));

        root.setBottom(playBox);
        root.setCenter(cBox.create());
        //root.setRight(plView.create());
        root.setTop(ibox.create());

        bigHBox.getChildren().addAll(root,plView.create());



        Scene scene = new Scene(bigHBox);
        //TODO FILE URL SHIT
        File file = new File("files/style.css");
        scene.getStylesheets().add(file.toURI().toString());

        primaryStage.setTitle("MP3Player");
        primaryStage.setScene(scene);
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public Stage getStage(){
        return stage;
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

    public PlaylistBox getPlaylistBox(){
        return plView;
    }
}
