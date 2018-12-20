package resources;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import mp3player.Playlist;
import mp3player.Track;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class PlaylistBox {

    private ListView<Track> plList = new ListView<Track>();

    private Label playlistName = new Label("playlistname");
    //private Button playButton = new Button("Play");
    //private Button settingsButton = new Button("Einstellungen");
    private Button openPlaylistButton = new Button("");


    //Create the Playlist:
    public VBox create() {
        VBox vBox = new VBox();
        HBox topBox = new HBox();
        HBox playlistInfo = new HBox();

        playlistName.setPadding(new Insets(8));
        playlistName.setFont(Font.font("Century Gothic Regular", FontWeight.NORMAL, 15));


        //openPlaylist-Button:
        try {
            Image playImage = new Image(new FileInputStream("files/Icons/playListButton.png"));
            ImageView iv =new ImageView(playImage);
            iv.setFitWidth(30);
            iv.setFitHeight(30);
            openPlaylistButton.setGraphic(iv);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        openPlaylistButton.setId("openPlaylistButton");

        AnchorPane anchorPane = new AnchorPane();

        AnchorPane.setLeftAnchor(plList, 10.0);
        AnchorPane.setRightAnchor(plList, 10.0);
        AnchorPane.setTopAnchor(plList, 0.0);
        AnchorPane.setBottomAnchor(plList, 0.0);

        plList.setPrefWidth(300);
        anchorPane.getChildren().addAll(plList);


        playlistInfo.getChildren().addAll(openPlaylistButton,playlistName);
        playlistInfo.setSpacing(10);
        topBox.getChildren().addAll(playlistInfo);
        topBox.setSpacing(10);
        topBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(topBox, anchorPane);
        return vBox;
    }


    //Playlist in die Zellen speichern:
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


    //David Guetta:
    public ListView<Track> getPlList() {
        return plList;
    }

    public Label getPlaylistName() {
        return playlistName;
    }

    public Button getOpenPlaylistButton() {
        return openPlaylistButton;
    }
}
