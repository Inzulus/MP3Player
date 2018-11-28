package resources;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class SongSlider {
    //TODO 63
    public HBox create(){
        HBox sliderBox = new HBox();

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(240);
        slider.setValue(0);

        Label lTime = new Label("0:00");
        Label rTime = new Label("4:00");

        sliderBox.getChildren().addAll(lTime,slider,rTime);
        sliderBox.setAlignment(Pos.BASELINE_CENTER);
        return sliderBox;

    }
}
