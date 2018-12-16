package resources;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mp3player.Playlist;
import mp3player.Track;


public class PlaylistBox {

    private ListView<Track> plList = new ListView<Track>();

    private Label playlistName = new Label("playlistname");
    private Button playButton = new Button("Play");
    private Button settingsButton = new Button("Einstellungen");
    private Button openPlaylistButton = new Button("open");

    public VBox create() {
        VBox vBox = new VBox();
        HBox topBox = new HBox();
        HBox playlistInfo = new HBox();


        playlistInfo.getChildren().addAll(playlistName, openPlaylistButton);
        playlistInfo.setSpacing(10);
        topBox.getChildren().addAll(playButton, playlistInfo, settingsButton);
        topBox.setSpacing(10);
        topBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(topBox, plList);
        return vBox;
    }

    public void loadPlaylist(Playlist pl ){
        ObservableList<Track> observableList = FXCollections.observableArrayList();
        for(int i = 0;i<pl.getTrackCount();i++){
            observableList.add(pl.getTrack(i));
        }
        plList.setItems(observableList);
        plList.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {
            @Override
            public ListCell<Track> call(ListView<Track> param) {
                return new TrackListCell();
            }
        });



    }

    public ListView<Track> getPlList() {
        return plList;
    }

    public Label getPlaylistName() {
        return playlistName;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getOpenPlaylistButton() {
        return openPlaylistButton;
    }
}
