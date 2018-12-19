package resources;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InfoBox {

    private Label lSongTitle = new Label("SongTitle");
    private Label lSongInterpret = new Label("SongInterpret");


    //Create the Info:
    public VBox create(){
        VBox ibox = new VBox();

        lSongTitle.setFont(Font.font("Century Gothic Regular", FontWeight.NORMAL, 18));
        ibox.getStyleClass().add("hbox");
        ibox.setAlignment(Pos.BASELINE_CENTER);
        ibox.getChildren().addAll(lSongTitle,lSongInterpret);
        ibox.setPadding(new Insets(5));

        return ibox;
    }


    //GETTER:
    public Label getlSongTitle() {
        return lSongTitle;
    }

    public Label getlSongInterpret() {
        return lSongInterpret;
    }
}
