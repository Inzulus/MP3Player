package application;

import javafx.application.Application;
import javafx.stage.Stage;
import mp3player.MP3Player;
import scenes.playerview.PlayerViewController;

import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        MP3Player player = new MP3Player("brain.mp3");
        PlayerViewController pvc = new PlayerViewController(player);
        pvc.getView().start(primaryStage);
    }
}
