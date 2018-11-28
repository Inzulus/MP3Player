package mp3player;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

public class MP3Player {

    //TODO close thread?
    SimpleMinim minim = new SimpleMinim(true);
    SimpleAudioPlayer audioPlayer;

    int currentTrackNumber = 0;
    Playlist currentPlaylist;

    public MP3Player(String filename){
        audioPlayer = minim.loadMP3File(filename);
    }

    public MP3Player(){

    }

    public void play(){
        audioPlayer.play();
    }

    public void play(String filename){
        PlaylistManager plManager = new PlaylistManager();
        currentPlaylist = plManager.getPlaylist(filename);

        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());
        info();
        audioPlayer.play();

    }

    public void play(Playlist pl){
        currentPlaylist = pl;
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());
        info();
        audioPlayer.play();
    }

    /*public void play(String filename){
        audioPlayer = minim.loadMP3File(filename);
        audioPlayer.play();

    }*/
    //TODO loop & stop?
    public void next(){
        audioPlayer.pause();
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(++currentTrackNumber).getPath());
        info();
        audioPlayer.play();
    }
    //TODO loop
    public void prev(){
        audioPlayer.pause();
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(--currentTrackNumber).getPath());
        info();
        audioPlayer.play();
    }

    public void pause(){
        audioPlayer.pause();

    }

    public void volume(float value){
        value = value*60;
        audioPlayer.setGain(value);
    }

    public void stop(){
        audioPlayer.rewind();
        audioPlayer.pause();

    }

    public void info(){
        System.out.print(currentPlaylist.getTrack(currentTrackNumber).getArtist()+" - ");
        System.out.println(currentPlaylist.getTrack(currentTrackNumber).getName());
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public Track getCurrentTrack(){
        return currentPlaylist.getTrack(currentTrackNumber);
    }
}
