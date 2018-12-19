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

//TODO Ausgewählt anzeigen bei Shuffle
public class ButtonBar {

    private Button playButton = new Button("");
    private Button nextButton = new Button("");
    private Button prevButton = new Button("");
    private Button pauseButton = new Button("");
    private Button viewButton = new Button("");
    private ToggleButton shuffleButton = new ToggleButton("");
    private HBox bBar = new HBox();


    //Create the Bar full of Buttons:
    public HBox create(){
        createButtons();

        bBar.getStyleClass().add("hbox");

        bBar.getChildren().addAll(viewButton,prevButton,playButton,nextButton,shuffleButton);
        bBar.setPadding(new Insets(10));
        bBar.setSpacing(10);
        bBar.setAlignment(Pos.BASELINE_CENTER);
        //bBar.setStyle("-fx-background-color: lightgrey;");

        return bBar;
    }

    public void createButtons(){

        //View-Button:
        try{
            Image viewImage = new Image(new FileInputStream("files/Icons/viewPlaylistButton.png"));
            ImageView ivPlaylist =new ImageView(viewImage);
            ivPlaylist.setFitWidth(20);
            ivPlaylist.setFitHeight(20);
            viewButton.setGraphic(ivPlaylist);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Play-Button:
        try {
            Image playImage = new Image(new FileInputStream("files/Icons/playButton.png"));
            ImageView ivPlay =new ImageView(playImage);
            ivPlay.setFitWidth(45);
            ivPlay.setFitHeight(45);
            playButton.setGraphic(ivPlay);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        playButton.setId("playButton");

        //Next-Button:
        try {
            Image nextImage = new Image(new FileInputStream("files/Icons/skipButton.png"));
            ImageView ivNext =new ImageView(nextImage);
            ivNext.setFitWidth(30);
            ivNext.setFitHeight(30);
            nextButton.setGraphic(ivNext);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nextButton.setId("nextButton");

        //Prev-Button:
        try {
            Image prevImage = new Image(new FileInputStream("files/Icons/prevButton.png"));
            ImageView ivPrev =new ImageView(prevImage);
            ivPrev.setFitWidth(30);
            ivPrev.setFitHeight(30);
            prevButton.setGraphic(ivPrev);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prevButton.setId("prevButton");

        //pauseButton:
        try {
            Image pauseImage = new Image(new FileInputStream("files/Icons/pauseButton.png"));
            ImageView iv =new ImageView(pauseImage);
            iv.setFitWidth(45);
            iv.setFitHeight(45);
            pauseButton.setGraphic(iv);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pauseButton.setId("pauseButton");

        //Shuffle-Button:
        try {
            Image shuffleImage = new Image(new FileInputStream("files/Icons/shuffleButton.png"));
            ImageView iv =new ImageView(shuffleImage);
            iv.setFitWidth(15);
            iv.setFitHeight(15);
            shuffleButton.setGraphic(iv);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        shuffleButton.setId("shuffleButton");
    }


    //David Guetta:
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

    public Button getViewButton(){
        return viewButton;
    }
}
