package mp3player;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

public class MP3Player {

    //TODO close thread?
    private SimpleMinim minim = new SimpleMinim();
    private SimpleAudioPlayer audioPlayer;

    private int currentTrackNumber = 0;
    private Playlist currentPlaylist;
    private boolean isPlaying = false;

    public MP3Player(String filename){
        audioPlayer = minim.loadMP3File(filename);
    }

    public MP3Player(){

    }

    public void playThread(){
        Thread playThread = new Thread(){
            public void run(){
                audioPlayer.play();
                next();
            }
        };
        playThread.start();
        isPlaying = true;
    }

    public void play(){
        info();
        playThread();
    }

    public void loadPlaylist(String filename) {
        PlaylistManager plManager = new PlaylistManager();
        currentPlaylist = plManager.getPlaylist(filename);
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());

        info();
    }

    public void next() {
        audioPlayer.pause();
        if (currentTrackNumber < currentPlaylist.getTrackCount() - 1){
            audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(++currentTrackNumber).getPath());
            info();
            playThread();
        }
        else{
            currentTrackNumber=-1;
            next();
        }
    }

    public void prev(){
        audioPlayer.pause();
        if(currentTrackNumber==0) {
            currentTrackNumber = currentPlaylist.getTrackCount();
            prev();
        }
        else{
            audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(--currentTrackNumber).getPath());
            info();
            playThread();
        }
    }

    public void pause(){
        audioPlayer.pause();
        isPlaying = false;
    }

    public void shuffle(){
        audioPlayer.pause();
        currentTrackNumber = (int)((currentPlaylist.getTrackCount())*Math.random());
        System.out.println(currentTrackNumber);
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());
        info();
        playThread();
    }

    public boolean isPlaying(){
        return isPlaying;
    }

    public void skip(int seconds){
        audioPlayer.skip(seconds*1000);
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

    public int getCurrentSec(){
        return audioPlayer.position()/1000;
    }

    public long getTrackLength(){
        return currentPlaylist.getTrack(currentTrackNumber).getLength();
    }
}
