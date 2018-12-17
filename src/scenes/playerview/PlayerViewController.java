package scenes.playerview;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import mp3player.InfoEvent;
import mp3player.InfoListener;
import mp3player.MP3Player;

import java.io.File;


public class PlayerViewController {
    private PlayerView view;
    private MP3Player player;
    //private SimpleIntegerProperty currentTime = new SimpleIntegerProperty();
    private FileChooser fileChooser = new FileChooser();
    private InfoListener il = new InfoListener() {
        @Override
        public void infoReceived(InfoEvent event) {
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    view.getIbox().getlSongTitle().setText(event.getTrack().getName());
                    view.getIbox().getlSongInterpret().setText(event.getTrack().getArtist());
                    view.getcBox().changeImage(event.getTrack().getImage());

                    view.getpSlider().getSlider().setValue(0);
                    view.getpSlider().getSlider().setMax(event.getTrack().getLength());
                    view.getpSlider().getrTime().setText(String.format("%02d:%02d",event.getTrack().getLength()/60,event.getTrack().getLength()%60));
                }
            });
        }
    };

    public PlayerViewController(MP3Player player){
        view = new PlayerView();
        this.player = player;
        initialize();
    }
    //TODO remove Playlist file
    public void initialize() {
        player.loadPlaylist("pl.m3u");
        player.addInfoListener(il);
        view.getPlaylistBox().loadPlaylist(player.getCurrentPlaylist());

        view.getPlaylistBox().getOpenPlaylistButton().addEventHandler(ActionEvent.ACTION,event -> {
            File file = fileChooser.showOpenDialog(view.getStage());
            player.loadPlaylist(file.getPath());
            view.getPlaylistBox().loadPlaylist(player.getCurrentPlaylist());

        });

        view.getbBar().getPlayButton().addEventHandler(ActionEvent.ACTION, event -> {
            play();
        });
        view.getbBar().getPauseButton().addEventHandler(ActionEvent.ACTION,event -> {
            pause();
        });
        view.getbBar().getNextButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.next();
        });
        view.getbBar().getPrevButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.prev();
        });
        view.getpSlider().getSlider().valueProperty().addListener((observable,oldValue,newValue)->{
            //player.skip(newValue.intValue());
        });

        view.getvSlider().getVolumeSlider().valueProperty().addListener((observable,oldValue,newValues)->{
            player.volume(newValues.floatValue());
        });

        view.getPlaylistBox().getOpenPlaylistButton().addEventHandler(ActionEvent.ACTION,event -> {
            //player.loadPlaylist(fileChooser.showOpenDialog());
        });

        player.getCurrentTimeProperty().timeProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //System.out.println(newValue+"aaaaaaaaaaaaaaaaaaaaaaah!!!!!");
                Platform.runLater(() -> view.getpSlider().getlTime().setText(String.format("%02d:%02d",newValue.intValue()/60,newValue.intValue()%60)));
                Platform.runLater(() -> view.getpSlider().getSlider().valueProperty().bindBidirectional(
                        player.getCurrentTimeProperty().timeProperty()
                ));
            }
        });

        view.getbBar().getShuffleButton().addEventHandler(ActionEvent.ACTION,event -> {
            player.setShuffle(view.getbBar().getShuffleButton().isSelected());
        });

        view.getvSlider().getMuteButton().addEventHandler(ActionEvent.ACTION,event -> {
            player.mute();
        });

        view.getPlaylistBox().getPlList().setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if(!player.isPlaying()) {
                    view.getbBar().getHBox().getChildren().remove(1);
                    view.getbBar().getHBox().getChildren().add(1, view.getbBar().getPauseButton());
                }
                    player.play(view.getPlaylistBox().getPlList().getSelectionModel().getSelectedItem(),
                            view.getPlaylistBox().getPlList().getSelectionModel().getSelectedIndex());
            }
        });

    }

    public void play(){
        if(!player.isPlaying()){
            view.getbBar().getHBox().getChildren().remove(1);
            view.getbBar().getHBox().getChildren().add(1,view.getbBar().getPauseButton());
            player.play();
        }
        else {
            //pause();
        }
    }

    public void pause(){
        player.pause();
        view.getbBar().getHBox().getChildren().remove(1);
        view.getbBar().getHBox().getChildren().add(1,view.getbBar().getPlayButton());
    }

    public void setSongInfo(){
        view.getIbox().getlSongTitle().setText(player.getCurrentTrack().getName());
        view.getIbox().getlSongInterpret().setText(player.getCurrentTrack().getArtist());
        view.getcBox().changeImage(player.getCurrentTrack().getImage());

        view.getpSlider().getSlider().setValue(0);
        view.getpSlider().getSlider().setMax(player.getCurrentTrack().getLength());
        view.getpSlider().getrTime().setText(Long.toString(player.getCurrentTrack().getLength()));

    }
    public PlayerView getView() {
        return view;
    }


}
