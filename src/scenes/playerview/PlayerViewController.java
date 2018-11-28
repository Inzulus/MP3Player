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

    public void initialize(){
        view.getbBar().getPlayButton().addEventHandler(ActionEvent.ACTION, e -> player.play());
    }

    public PlayerView getView() {
        return view;
    }
}
