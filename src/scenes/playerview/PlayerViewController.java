package scenes.playerview;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mp3player.InfoEvent;
import mp3player.InfoListener;
import mp3player.MP3Player;


public class PlayerViewController {
    private PlayerView view;
    private MP3Player player;
    private SimpleIntegerProperty currentTime = new SimpleIntegerProperty();
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
                    view.getpSlider().getrTime().setText(String.format("%02d:%02d",event.getTrack().getLength()/60,event.getTrack().getLength()));
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
        view.getbBar().getPlayButton().addEventHandler(ActionEvent.ACTION, event -> {
                playPause();
        });
        view.getPlaylistBox().loadPlaylist(player.getCurrentPlaylist());
        view.getbBar().getNextButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.next();
        });
        view.getbBar().getPrevButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.prev();
        });
        view.getpSlider().getSlider().valueProperty().addListener((observable,oldValue,newValue)->{
            //player.skip(newValue.intValue());
        });

        player.getCurrentTimeProperty().timeProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //System.out.println(newValue+"aaaaaaaaaaaaaaaaaaaaaaah!!!!!");
                Platform.runLater(() -> view.getpSlider().getlTime().setText(String.format("%02d:%02d",newValue.intValue()/60,newValue.intValue())));
                Platform.runLater(() -> view.getpSlider().getSlider().valueProperty().bindBidirectional(
                        player.getCurrentTimeProperty().timeProperty()
                ));
            }
        });

        view.getbBar().getShuffleButton().addEventHandler(ActionEvent.ACTION,event -> {
            player.setShuffle(view.getbBar().getShuffleButton().isSelected());
        });

        view.getPlaylistBox().getPlList().setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                player.play(view.getPlaylistBox().getPlList().getSelectionModel().getSelectedItem(),
                            view.getPlaylistBox().getPlList().getSelectionModel().getSelectedIndex());
            }
        });

    }

    public void playPause(){
        if(!player.isPlaying()){
            player.play();
        }
        else
            player.pause();
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
