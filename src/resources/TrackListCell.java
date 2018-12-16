package resources;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mp3player.Track;

import java.io.File;

public class TrackListCell extends ListCell<Track> {
    private Pane view;
    private Label titleLabel;
    private Label artistLabel;
    private ImageView iV;

    public TrackListCell(){
        VBox trackPane = new VBox();
        titleLabel = new Label();
        artistLabel = new Label();
        trackPane.getChildren().addAll(titleLabel,artistLabel);
        iV = new ImageView();
        ImageViewPane viewPane = new ImageViewPane(iV);

        view = new HBox();
        view.getChildren().addAll(viewPane,trackPane);
        this.setGraphic(view);
    }

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
