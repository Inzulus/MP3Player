package resources;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VerticalDirection;
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


    //Create:
    public HBox create(){
        HBox hbox = new HBox();

        //Wertebereich:
        volumeSlider.setMin(0);
        volumeSlider.setMax(2);
        volumeSlider.adjustValue(1);
        //volumeSlider.setMaxHeight(50);
        //volumeSlider.setMinHeight(50);
        //volumeSlider.setMinWidth(100);
        //volumeSlider.setMaxWidth(100);

        //MuteButton:
        try {
            Image playImage = new Image(new FileInputStream("files/Icons/muteButton.png"));
            ImageView iv =new ImageView(playImage);
            iv.setFitWidth(15);
            iv.setFitHeight(15);
            muteButton.setGraphic(iv);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        hbox.getChildren().addAll(muteButton,volumeSlider);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setPadding(new Insets(10,0,0,0));
        return hbox;
    }


    //GETTER:
    public Slider getVolumeSlider(){
        return volumeSlider;
    }

    public ToggleButton getMuteButton(){
        return muteButton;
    }
}
