package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mp3player.MP3Player;
import scenes.playerview.PlayerViewController;
import scenes.playerview.PlaylistViewController;

import java.io.File;

import static javafx.application.Application.launch;

public class Main extends Application {

    //Startet einfach ALLES:
    public static void main(String[] args) {
        launch(args);
    }
    //ALLES wieder vorbei:


    MP3Player player;
    PlaylistViewController plvc;
    PlayerViewController pvc;
    Stage pStage;
    boolean plView = false;
    Scene scenePlayer;
    Scene scenePlaylist;

    public void init(){
        player = new MP3Player();
        plvc = new PlaylistViewController(player,this);
        pvc = new PlayerViewController(player,this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pStage = primaryStage;
        pvc.getView().start(primaryStage);
        plvc.getView().start(primaryStage);
        //TODO
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
        /*if(!plView){
            pStage.setScene(scene);
            plView=true;
        }
        else{

            pStage.setScene(pvc.getView().getScene());
            plView=false;
        }
        */
        if(!plView) {
            pStage.setScene(scenePlaylist);
            pStage.show();
            plView = true;
        }
        else{
            pStage.setScene(scenePlayer);
            pStage.show();
            plView = false;
        }
    }
}
