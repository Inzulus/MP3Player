package mp3player;

import java.util.ArrayList;

public class Playlist {
    private ArrayList<Track> songs = new ArrayList<Track>();
    private String name;

    public Playlist(String name){
        this.name = name;
    }

    public Track getTrack(int n){
        return songs.get(n);
    }

    public void addTrack(Track track){
        songs.add(track);
    }

    public int getTrackCount(){
        return songs.size();
    }


}
