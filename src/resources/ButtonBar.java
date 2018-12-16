package resources;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ButtonBar {


    private Button playButton = new Button("");
    private Button nextButton = new Button("");
    private Button prevButton = new Button("");
    private Button pauseButton = new Button("");
    private ToggleButton shuffleButton = new ToggleButton("");
    private HBox bBar = new HBox();



    public HBox create(){
        createButtons();

        bBar.getChildren().addAll(prevButton,playButton,nextButton,shuffleButton);
        //bBar.getChildren().remove(playButton);
        bBar.setPadding(new Insets(10));
        bBar.setSpacing(10);
        bBar.setAlignment(Pos.BASELINE_CENTER);
        bBar.setStyle("-fx-background-color: lightgrey;");

        return bBar;
    }

    public void createButtons(){
        //Play-Button:
        try {
            Image playImage = new Image(new FileInputStream("files/Icons/playButton.png"));
            playButton.setGraphic(new ImageView(playImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        playButton.setId("playButton");

        //Next-Button:
        try {
            Image nextImage = new Image(new FileInputStream("files/Icons/skipButton.png"));
            nextButton.setGraphic(new ImageView(nextImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nextButton.setId("nextButton");

        //Prev-Button:
        try {
            Image prevImage = new Image(new FileInputStream("files/Icons/prevButton.png"));
            prevButton.setGraphic(new ImageView(prevImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prevButton.setId("prevButton");

        //pauseButton:
        try {
            Image pauseImage = new Image(new FileInputStream("files/Icons/pauseButton.png"));
            pauseButton.setGraphic(new ImageView(pauseImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pauseButton.setId("pauseButton");

        //Shuffle-Button:
        try {
            Image shuffleImage = new Image(new FileInputStream("files/Icons/shuffleButton.png"));
            shuffleButton.setGraphic(new ImageView(shuffleImage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        shuffleButton.setId("shuffleButton");
    }

    public HBox getHBox(){
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

    public ToggleButton getShuffleButton() {
        return shuffleButton;
    }

    public Button getPauseButton(){
        return pauseButton;
    }
}
