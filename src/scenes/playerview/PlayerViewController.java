package scenes.playerview;

import javafx.event.ActionEvent;
import mp3player.InfoEvent;
import mp3player.InfoListener;
import mp3player.MP3Player;


public class PlayerViewController {
    private PlayerView view;
    private  MP3Player player;
    private InfoListener il = new InfoListener() {
        @Override
        //TODO RUNLLATER
        public void infoReceived(InfoEvent event) {
            view.getIbox().getlSongTitle().setText(event.getTrack().getName());
            view.getIbox().getlSongInterpret().setText(event.getTrack().getArtist());
            view.getcBox().changeImage(event.getTrack().getImage());

            view.getpSlider().getSlider().setValue(0);
            view.getpSlider().getSlider().setMax(event.getTrack().getLength());
            view.getpSlider().getrTime().setText(Long.toString(event.getTrack().getLength()));
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
                //setSongInfo();
                //player.pause();
        });
        view.getPlaylistBox().loadPlaylist(player.getCurrentPlaylist());
        view.getbBar().getNextButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.next();
            //setSongInfo();
        });
        view.getbBar().getPrevButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.prev();
            //setSongInfo();
        });
        //TODO changelistener oder notifier in mp3player implementieren
        view.getpSlider().getSlider().valueProperty().addListener((observable,oldValue,newValue)->{
            player.skip(newValue.intValue());
        });

        view.getbBar().getShuffleButton().addEventHandler(ActionEvent.ACTION,event -> {
            player.shuffle();
            //setSongInfo();
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
