package scenes.playerview;

import javafx.event.ActionEvent;
import mp3player.MP3Player;


public class PlayerViewController {
    private PlayerView view;
    private MP3Player player;

    public PlayerViewController(MP3Player player){
        view = new PlayerView();
        this.player = player;
        initialize();
    }
    //TODO remove Playlist file
    public void initialize() {
        view.getbBar().getPlayButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.play("pl.m3u");
            setSongInfo();
        });
        view.getbBar().getNextButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.next();
            setSongInfo();
        });
        view.getbBar().getPrevButton().addEventHandler(ActionEvent.ACTION, event -> {
            player.prev();
            setSongInfo();
        });

    }

    public void setSongInfo(){
        view.getIbox().getlSongTitle().setText(player.getCurrentTrack().getName());
        view.getIbox().getlSongInterpret().setText(player.getCurrentTrack().getArtist());
        view.getcBox().changeImage(player.getCurrentTrack().getImage());

    }


    public PlayerView getView() {
        return view;
    }
}
