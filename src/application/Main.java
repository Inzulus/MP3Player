package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mp3player.MP3Player;
import scenes.playerview.PlayerViewController;
import scenes.playerview.PlaylistViewController;

import java.io.File;

public class Main extends Application {

    //Startet einfach ALLES:
    public static void main(String[] args) {
        launch(args);
    }
    //ALLES wieder vorbei:


    private MP3Player player;
    private PlaylistViewController plvc;
    private PlayerViewController pvc;
    private Stage pStage;
    private boolean plView = false;
    private Scene scenePlayer;
    private Scene scenePlaylist;

    public void init(){
        player = new MP3Player();
        plvc = new PlaylistViewController(player,this);
        pvc = new PlayerViewController(player,this);
    }

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        pvc.getView().start(primaryStage);
        plvc.getView().start(primaryStage);

        scenePlayer = new Scene(pvc.getView().getBigHBox());
        scenePlaylist = new Scene(plvc.getView().getBigHBox());
        File file = new File("files/style.css");
        scenePlayer.getStylesheets().add(file.toURI().toString());
        scenePlaylist.getStylesheets().add(file.toURI().toString());

        primaryStage.setTitle("MP3Player");
        primaryStage.setScene(scenePlayer);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        player.stop();
    }

    public void changeView(){
        if(!plView) {
            /*if(player.isPlaying()) {
                plvc.getView().getbBar().getHBox().getChildren().remove(2);
                plvc.getView().getbBar().getHBox().getChildren().add(2, plvc.getView().getbBar().getPauseButton());
            }
            */
            pStage.setScene(scenePlaylist);
            pStage.show();
            plView = true;
        }
        else{
            pStage.setScene(scenePlayer);
            /*if(player.isPlaying()) {
                pvc.getView().getbBar().getHBox().getChildren().remove(2);
                pvc.getView().getbBar().getHBox().getChildren().add(2, pvc.getView().getbBar().getPauseButton());
            }*/
            pStage.show();
            plView = false;
        }
    }
}
