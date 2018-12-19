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

//TODO Playlist in eine neue View packen:
public class PlaylistView extends Application {

    private InfoBox ibox = new InfoBox();
    private ButtonBar bBar = new ButtonBar();
    private CoverBox cBox = new CoverBox();
    private PositionSlider pSlider = new PositionSlider();
    private PlaylistBox plView = new PlaylistBox();
    private VolumeSlider vSlider = new VolumeSlider();
    private Stage stage;
    private Scene scene;

    private HBox bigHBox = new HBox();


    //Zusammenbauen und erzeugen der PlaylistView:
    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;


        bigHBox.getStyleClass().add("hbox");
        BorderPane root = new BorderPane();
        VBox playBox = new VBox();
        HBox allSliders = new HBox();
        allSliders.getChildren().addAll(vSlider.create(),pSlider.create());
        playBox.getChildren().addAll(allSliders,bBar.create());
        playBox.setPadding(new Insets(5));

        root.setBottom(playBox);
        root.setCenter(cBox.create());
        root.setTop(ibox.create());

        bigHBox.getChildren().addAll(root,plView.create());

        /*Scene scene = new Scene(bigHBox);
        File file = new File("files/style.css");
        scene.getStylesheets().add(file.toURI().toString());*/

        /*primaryStage.setTitle("MP3Player");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }


    //GET them all:
    public HBox getBigHBox(){
        return bigHBox;
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

    public VolumeSlider getvSlider() {
        return vSlider;
    }

    public Scene getScene() {
        return scene;
    }
}
