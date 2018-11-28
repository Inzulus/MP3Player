package mp3player;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Track {
    private String name;
    private String artist;
    private String path;
    private BufferedImage image;
    private long length;

    public Track(String name, String artist, String path,long length, BufferedImage image){
        this.artist = artist;
        this.name = name;
        this.length = length;
        this.path = path;
        this.image = image;
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

    public BufferedImage getImage() {
        return image;
    }

    public long getLength(){
        return length;
    }



}
