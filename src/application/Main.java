package application;

import javafx.application.Application;
import javafx.stage.Stage;
import mp3player.MP3Player;
import scenes.playerview.PlayerViewController;

import static javafx.application.Application.launch;

public class Main extends Application {

    //Startet einfach ALLES:
    public static void main(String[] args) {
        launch(args);
    }
    //ALLES wieder vorbei:


    MP3Player player;
    PlayerViewController pvc;

    public void init(){
        player = new MP3Player();
        pvc = new PlayerViewController(player);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pvc.getView().start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        player.stop();
    }
}
