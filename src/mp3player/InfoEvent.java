package mp3player;

import java.util.EventObject;

public class InfoEvent extends EventObject {
    private Track track;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public InfoEvent(Object source, Track track) {
        super(source);
        this.track = track;
    }

    public Track getTrack(){
        return track;
    }
}
