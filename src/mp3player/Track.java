package mp3player;

public class Track {
    String name;
    String artist;
    String path;
    //int length;

    public Track(String name, String artist, String path){
        this.artist = artist;
        this.name = name;
        //this.length = length;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }



}
