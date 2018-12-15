package mp3player;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MP3Player {

    private SimpleMinim minim = new SimpleMinim();
    private SimpleAudioPlayer audioPlayer;

    private int currentTrackNumber = 0;
    private Playlist currentPlaylist;
    private boolean isPlaying = false;
    private List listener = new ArrayList();
    private Thread playThread;
    private Thread timeThread;
    private TimeProperty currentTime = new TimeProperty();


    private boolean shuffle;
    public enum playStatus{
        isPlaying,paused,stopped
    };

    public TimeProperty getCurrentTimeProperty(){
        return currentTime;
    }


    public MP3Player(String filename){
        audioPlayer = minim.loadMP3File(filename);
    }

    public MP3Player(){

    }

    private synchronized void fireInfoEvent(){
        InfoEvent ie = new InfoEvent(this,getCurrentTrack());
        Iterator listener = this.listener.iterator();
        while(listener.hasNext()){
            ((InfoListener)(listener.next())).infoReceived(ie);
        }
    }

    public synchronized void addInfoListener(InfoListener il){
        listener.add(il);
    }

    public synchronized void removeInfoListener(InfoListener il){
        listener.remove(il);
    }

    public void startTimer(){
        currentTime.setTime(0);
        if(timeThread!=null)
            timeThread.interrupt();
        timeThread = new Thread() {
            public void run() {
                for (int i = 0; i < getCurrentTrack().getLength(); i++) {
                    currentTime.setTime(i);
                    System.out.println(currentTime.getTime());
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timeThread.start();

    }


    public void playThread(){
        startTimer();
        fireInfoEvent();
        playThread = new Thread(){
            public void run(){
                audioPlayer.play();
                onCompletion();
            }
        };
        playThread.setDaemon(true);
        playThread.start();
        isPlaying = true;
    }


    public void play(){
        info();
        playThread();
    }

    public void play(Track track,int currentTrackNumber){
        this.currentTrackNumber = currentTrackNumber;
        minim.stop();
        audioPlayer = minim.loadMP3File(track.getPath());
        playThread();
    }

    public void loadPlaylist(String filename) {
        PlaylistManager plManager = new PlaylistManager();
        currentPlaylist = plManager.getPlaylist(filename);
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());

        info();
    }

    public void next() {
        minim.stop();
        if(shuffle){
            currentTrackNumber = (int) (Math.random()*currentPlaylist.getTrackCount());
        }
        else if(currentTrackNumber < currentPlaylist.getTrackCount() - 1){
            currentTrackNumber++;
        }
        else{
            currentTrackNumber=0;

        }
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());
        info();
        fireInfoEvent();
        playThread();
    }

    public void onCompletion(){
        if(shuffle){
            currentTrackNumber = (int) (Math.random()*currentPlaylist.getTrackCount());
        }
        else if(currentTrackNumber < currentPlaylist.getTrackCount() - 1){
            currentTrackNumber++;
        }
        else{
            currentTrackNumber=0;

        }
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());
        info();
        fireInfoEvent();
        playThread();
    }

    public void prev(){
        minim.stop();
        if(shuffle){
            currentTrackNumber = (int) (Math.random()*currentPlaylist.getTrackCount());
        }
        else if(currentTrackNumber==0) {
            currentTrackNumber = currentPlaylist.getTrackCount()-1;
        }
        else{
            currentTrackNumber--;
        }
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());
        info();
        playThread();
    }

    public void pause(){
        audioPlayer.pause();
        isPlaying = false;
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
        minim.stop();

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


    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

}
