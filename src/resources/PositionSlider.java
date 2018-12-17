package resources;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class PositionSlider {

    private Slider slider = new Slider();
    private Label lTime = new Label("0:00");
    private Label rTime = new Label("0:00");
    //TODO 63
    public HBox create(){
        HBox sliderBox = new HBox();

        slider.setMin(0);
        slider.setMax(0);
        slider.setValue(0);


        sliderBox.getChildren().addAll(lTime,slider,rTime);
        sliderBox.setAlignment(Pos.BASELINE_CENTER);
        return sliderBox;

    }

    public Slider getSlider() {
        return slider;
    }

    public Label getlTime() {
        return lTime;
    }

    public Label getrTime() {
        return rTime;
    }
}
