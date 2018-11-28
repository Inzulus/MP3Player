package resources;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class CoverBox {

    public AnchorPane create(){
        AnchorPane aPane = new AnchorPane();
        //try {
            //Image image = new Image(new FileInputStream(getClass().getResource("Small-mario.png").toExternalForm()));
            File file = new File("cover.png");
            Image image = new Image(file.toURI().toString());
            ImageView iV = new ImageView(image);
            ImageViewPane viewPane = new ImageViewPane(iV);
            AnchorPane.setLeftAnchor(viewPane, 0.0);
            AnchorPane.setRightAnchor(viewPane, 0.0);
            AnchorPane.setTopAnchor(viewPane, 10.0);
            AnchorPane.setBottomAnchor(viewPane, 10.0);

            aPane.getChildren().add(viewPane);
            aPane.setPadding(new Insets(10));
        //}
        //catch(FileNotFoundException fnf){
        //    System.out.println("File not found");
        //}

        return aPane;
    }
}
