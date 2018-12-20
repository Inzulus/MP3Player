package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mp3player.MP3Player;
import scenes.playerview.PlayerViewController;
import scenes.playerview.PlaylistViewController;

import java.io.*;
import java.util.Properties;

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

        try (InputStream inputStream = new FileInputStream("settings.xml")) {
            Properties prop = new Properties();
            prop.loadFromXML(inputStream);

            primaryStage.setWidth(Double.parseDouble(
                    prop.getProperty("width")));
            primaryStage.setHeight(Double.parseDouble(
                    prop.getProperty("height")));
            player.loadPlaylist(prop.getProperty("lastPlaylist"));
            player.setCurrentTrackNumber(Integer.parseInt(prop.getProperty("lastSong")));
            plView=Boolean.parseBoolean(prop.getProperty("playListView"));
            if(!Boolean.parseBoolean(prop.getProperty("playListView"))){
                primaryStage.setScene(scenePlayer);
            }
            else{
                primaryStage.setScene(scenePlaylist);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }






        primaryStage.setTitle("MP3Player");

        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {

        try (OutputStream out = new FileOutputStream("settings.xml")) {
            Properties properties = new Properties();
            properties.setProperty("lastPlaylist", player.getCurrentPlaylist().getName());
            properties.setProperty("lastSong", String.valueOf(player.getCurrentTrackNumber()));
            properties.setProperty("playListView", String.valueOf(plView));
            properties.setProperty("width", String.valueOf(pStage.getWidth()));
            properties.setProperty("height",String.valueOf(pStage.getHeight()));
            properties.storeToXML(out,"Settings XML File for MP3-Player");
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.stop();
        player.stop();

    }

    public void changeView(){
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
