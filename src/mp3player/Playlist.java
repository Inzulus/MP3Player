package mp3player;

import java.util.ArrayList;

public class Playlist {
    ArrayList<Track> songs = new ArrayList<Track>();
    String name;

    public Playlist(String name){
        this.name = name;
    }

    public Track getTrack(int n){
        return songs.get(n);
    }

    public void addTrack(Track track){
        songs.add(track);
    }

}
