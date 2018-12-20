package resources;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mp3player.Track;

import java.io.File;

//TODO gehört das zu Ressourcen? AND Bild neben den Infos?
public class TrackListCell extends ListCell<Track> {

    private Pane view;
    private Label titleLabel;
    private Label artistLabel;
    private ImageView iV;


    //Kontruktor:
    public TrackListCell(){
        VBox trackPane = new VBox();
        titleLabel = new Label();
        artistLabel = new Label();
        iV = new ImageView();

        titleLabel.setFont(Font.font("Century Gothic Regular", FontWeight.NORMAL, 14));
        titleLabel.setId("titleLabelCell");
        artistLabel.setFont(Font.font("Century Gothic Regular", FontWeight.NORMAL, 10));
        artistLabel.setId("artistLabelCell");

        trackPane.setPadding(new Insets(0, 0, 0, 3));
        trackPane.getChildren().addAll(titleLabel,artistLabel);

        ImageViewPane viewPane = new ImageViewPane(iV);

        view = new HBox();
        view.getChildren().addAll(viewPane,trackPane);
        this.setGraphic(view);
    }


    //Schon erzeugte und benutzte Zellen wiederverwenden:
    protected void updateItem(Track item, boolean empty){
        super.updateItem(item,empty);

        if(item!=null){
            titleLabel.setText(item.getName());
            artistLabel.setText(item.getArtist());
            iV.setImage(SwingFXUtils.toFXImage(item.getImage(),null));
            iV.setFitHeight(50);
            iV.setFitWidth(50);
            setGraphic(view);
        }
        else{
            setGraphic(null);
        }
    }
}
