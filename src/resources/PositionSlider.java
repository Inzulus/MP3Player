package resources;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

//TODO vllt vorspulen
public class PositionSlider {

    private Slider slider = new Slider();
    private Label lTime = new Label("0:00");
    private Label rTime = new Label("0:00");


    //Create:
    public HBox create(){
        HBox sliderBox = new HBox();

        slider.setMin(0);
        slider.setMax(0);
        slider.setValue(0);
        slider.setId("positionSlider");
        slider.setMaxWidth(200);
        slider.setMinWidth(200);


        sliderBox.getChildren().addAll(lTime,slider,rTime);
        sliderBox.setAlignment(Pos.BASELINE_CENTER);
        sliderBox.setSpacing(5);

        return sliderBox;
    }


    //GETTER:
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
