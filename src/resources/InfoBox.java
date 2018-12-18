package resources;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoBox {

    private Label lSongTitle = new Label("SongTitle");
    private Label lSongInterpret = new Label("SongInterpret");


    //Create the Info:
    public VBox create(){
        VBox ibox = new VBox();
        ibox.setAlignment(Pos.BASELINE_CENTER);
        ibox.setStyle("-fx-background-color: rgb(240,240,240);");
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
