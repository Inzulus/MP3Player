package resources;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mp3player.MP3Player;

public class PlayHandler implements EventHandler<ActionEvent> {
    private MP3Player player;
    public PlayHandler(MP3Player player){
        this.player = player;
    }

    public void handle(ActionEvent event){
        player.play();
    }

}
