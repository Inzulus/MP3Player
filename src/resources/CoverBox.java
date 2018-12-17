package resources;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;
import java.io.File;

public class CoverBox {
    private Image image;
    private ImageView iV;
    private ImageViewPane viewPane;


    public AnchorPane create(){
        AnchorPane aPane = new AnchorPane();
        //TODO Cover passend zum Song
            File file = new File("files/cover.png");
            image = new Image(file.toURI().toString());
            iV = new ImageView(image);
            iV.preserveRatioProperty().setValue(true);
            viewPane = new ImageViewPane(iV);
            AnchorPane.setLeftAnchor(viewPane, 0.0);
            AnchorPane.setRightAnchor(viewPane, 0.0);
            AnchorPane.setTopAnchor(viewPane, 10.0);
            AnchorPane.setBottomAnchor(viewPane, 10.0);
            iV.preserveRatioProperty().setValue(true);

            aPane.getChildren().add(viewPane);
            aPane.setPadding(new Insets(10));

        return aPane;
    }

    public void changeImage(BufferedImage bufferedImage){
        if(bufferedImage!=null)
            iV.setImage(SwingFXUtils.toFXImage(bufferedImage,null));
        else{
            iV.setImage(image);
        }
    }
}
