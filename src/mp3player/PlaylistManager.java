package mp3player;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import de.hsrm.mi.prog.util.StaticScanner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaylistManager {
    Playlist aktuellePlaylist;

    public PlaylistManager(){

    }

    public Playlist getPlaylist(String name){
        Playlist pl = new Playlist(name);
        Path file = Paths.get(name);
        try {
            BufferedReader reader = Files.newBufferedReader(file);
            String zeile = null;
            while ((zeile = reader.readLine()) != null) {
                try {
                    Mp3File mp3file = new Mp3File(zeile);
                    ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                    //TODO exception für nicht vorhandenes Bild
                    BufferedImage trackImage = ImageIO.read(new ByteArrayInputStream(mp3file.getId3v2Tag().getAlbumImage()));
                    Track newTrack = new Track(id3v1Tag.getTitle(), id3v1Tag.getArtist(), zeile,mp3file.getLengthInSeconds(),trackImage);
                    pl.addTrack(newTrack);

                } catch (InvalidDataException e) {
                    System.out.println("Invalid Mp3File");
                } catch (UnsupportedTagException e) {
                    System.out.println("Unsupported Tags");
                }
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return pl;
    }
}
