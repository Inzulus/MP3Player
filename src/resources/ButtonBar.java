package resources;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonBar {

    private Button playButton = new Button("Play");
    private Button nextButton = new Button("Next");
    private Button prevButton = new Button("Prev");
    private Button shuffleButton = new Button("Shuffle");



    public HBox create(){
        HBox bBar = new HBox();
        bBar.getChildren().addAll(prevButton,playButton,nextButton,shuffleButton);

        bBar.setPadding(new Insets(10));
        bBar.setSpacing(10);
        bBar.setAlignment(Pos.BASELINE_CENTER);
        bBar.setStyle("-fx-background-color: lightgrey;");

        return bBar;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getPrevButton() {
        return prevButton;
    }

    public Button getShuffleButton() {
        return shuffleButton;
    }
}
