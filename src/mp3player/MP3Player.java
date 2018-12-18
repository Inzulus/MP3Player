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
    private List listener = new ArrayList();

    private Thread playThread;
    private Thread timeThread;
    private TimeProperty currentTime = new TimeProperty();

    private boolean isPlaying = false;
    private boolean shuffle;

    /*public enum playStatus{
        isPlaying,paused,stopped
    };*/


    //Konstruktor:
    public MP3Player(String filename){
        audioPlayer = minim.loadMP3File(filename);
    }

    public MP3Player(){
    }


    //Synchronized:
    private synchronized void fireInfoEvent(){
        InfoEvent ie = new InfoEvent(this,getCurrentTrack());
        Iterator listener = this.listener.iterator();
        while(listener.hasNext()){
            ((InfoListener)(listener.next())).infoReceived(ie);
        }
    }

    public synchronized void addInfoListener(InfoListener il){ listener.add(il); }

    public synchronized void removeInfoListener(InfoListener il){ listener.remove(il); }


    //Playlist laden:
    public void loadPlaylist(String filename) {
        PlaylistManager plManager = new PlaylistManager();
        currentPlaylist = plManager.getPlaylist(filename);
        audioPlayer = minim.loadMP3File(currentPlaylist.getTrack(currentTrackNumber).getPath());

        info();
    }


    //Start_Methoden zum abspielen eines Songs:
    public void startTimer(){
        currentTime.setTime(0);
        if(timeThread!=null)
            timeThread.interrupt();
        timeThread = new Thread() {
            public void run() {
                /*for (int i = 0; i < getCurrentTrack().getLength() && !timeThread.isInterrupted(); i++) {
                    System.out.println(i);
                    currentTime.setTime(audioPlayer.position()/1000);
                    i = audioPlayer.position()/1000;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        this.interrupt();
                        //e.printStackTrace();
                    }
                }*/
                while(true){
                    System.out.println(audioPlayer.position());
                    currentTime.setTime(audioPlayer.position()/1000);
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        this.interrupt();
                        //e.printStackTrace();
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
                if(isPlaying)
                    onCompletion();
            }
        };
        playThread.setDaemon(true);
        playThread.start();
        isPlaying = true;
    }


    //Play-Methoden:
    public void play(){
        info();
        isPlaying = true;
        playThread();
        //audioPlayer.play();
    }

    public void play(Track track,int currentTrackNumber){
        isPlaying = true;
        this.currentTrackNumber = currentTrackNumber;
        minim.stop();
        audioPlayer = minim.loadMP3File(track.getPath());
        playThread();
    }


    //Überspringen:
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
        if(isPlaying)
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
        if(isPlaying)
            playThread();
    }

    public void skip(int seconds){ audioPlayer.skip(seconds*1000); }


    //normaler Loop:
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


    //Lautstärke:
    public void volume(float value){
        if(audioPlayer.isPlaying()){
            if(value>=0 && value <=2){
                float v = 20*(float)Math.log10(value);
                audioPlayer.setGain(v);
            }
        }
    }

    public void mute(){
        if(!audioPlayer.isMuted())
            audioPlayer.mute();
        else{
            audioPlayer.unmute();
        }
    }


    //Pause/Stop:
    public void pause(){
        isPlaying = false;
        audioPlayer.pause();
    }

    public void unpause(){
        isPlaying = true;
        audioPlayer.play();
    }

    public void stop(){
        audioPlayer.rewind();
        audioPlayer.pause();
        minim.stop();
    }

    //Artist und Sontitel INFO:
    public void info(){
        System.out.print(currentPlaylist.getTrack(currentTrackNumber).getArtist()+" - ");
        System.out.println(currentPlaylist.getTrack(currentTrackNumber).getName());
    }


    //DAVID SUETTA
    public void setShuffle(boolean shuffle) { this.shuffle = shuffle; }

    //DAVID GUETTA
    public TimeProperty getCurrentTimeProperty(){ return currentTime; }

    public Playlist getCurrentPlaylist() { return currentPlaylist; }

    public Track getCurrentTrack(){ return currentPlaylist.getTrack(currentTrackNumber); }

    public int getCurrentSec(){ return audioPlayer.position()/1000; }

    public boolean isShuffle() { return shuffle; }

    public boolean isPlaying(){ return isPlaying; }

}
