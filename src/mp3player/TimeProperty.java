package mp3player;

import javafx.beans.property.SimpleIntegerProperty;

public class TimeProperty {
    private SimpleIntegerProperty time = new SimpleIntegerProperty();

    public void setTime(int time) {
        this.time.set(time);
    }

    public int getTime() {
        return time.get();
    }

    public SimpleIntegerProperty timeProperty() {
        return time;
    }
}
