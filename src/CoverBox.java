import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CoverBox {

    public AnchorPane create(){
        AnchorPane aPane = new AnchorPane();
        Image image = new Image(getClass().getResourceAsStream("Small-mario.png"));
        ImageView iV = new ImageView(image);
        ImageViewPane viewPane = new ImageViewPane(iV);
        AnchorPane.setLeftAnchor(viewPane, 0.0);
        AnchorPane.setRightAnchor(viewPane, 0.0);
        AnchorPane.setTopAnchor(viewPane, 10.0);
        AnchorPane.setBottomAnchor(viewPane, 10.0);

        aPane.getChildren().add(viewPane);
        aPane.setPadding(new Insets(10));

        return aPane;
    }
}
