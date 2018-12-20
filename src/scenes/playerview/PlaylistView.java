package scenes.playerview;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.*;

import java.io.File;

//TODO Playlist in eine neue View packen:
public class PlaylistView {

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
    public void start(Stage primaryStage){
        stage = primaryStage;


        bigHBox.getStyleClass().add("hbox");
        BorderPane root = new BorderPane();
        VBox playBox = new VBox();
        playBox.getChildren().addAll(pSlider.create(),bBar.create());
        playBox.setPadding(new Insets(5));

        root.setBottom(playBox);
        root.setCenter(cBox.create());
        root.setTop(ibox.create());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(plView.create(),vSlider.create());


        bigHBox.getChildren().addAll(root,vBox);

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
