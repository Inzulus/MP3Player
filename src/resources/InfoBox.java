package resources;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoBox {

    public VBox create(){
        VBox ibox = new VBox();
        ibox.setAlignment(Pos.BASELINE_CENTER);
        Label lSongTitle = new Label("SongTitle");
        Label lSongInterpret = new Label("SongInterpret");
        ibox.setStyle("-fx-background-color: rgb(240,240,240);");
        ibox.getChildren().addAll(lSongTitle,lSongInterpret);

        ibox.setPadding(new Insets(5));
        return ibox;
    }
}
