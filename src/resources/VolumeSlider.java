package resources;

import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VolumeSlider {
    private Slider volumeSlider = new Slider();
    private ToggleButton muteButton = new ToggleButton();

    public HBox create(){
        HBox hbox = new HBox();

        volumeSlider.setMin(0);
        volumeSlider.setMax(2);
        volumeSlider.adjustValue(1);

        try {
            Image playImage = new Image(new FileInputStream("files/Icons/muteButton.png"));
            muteButton.setGraphic(new ImageView(playImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        muteButton.setId("muteButton");


        hbox.getChildren().addAll(muteButton,volumeSlider);
        return hbox;
    }

    public Slider getVolumeSlider(){
        return volumeSlider;
    }

    public ToggleButton getMuteButton(){
        return muteButton;
    }
}
