import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonBar {

    public HBox create(){
        HBox bBar = new HBox();
        Button playButton = new Button("Play");
        Button nextButton = new Button("Next");
        Button prevButton = new Button("Prev");
        Button shuffleButton = new Button("Shuffle");
        bBar.getChildren().addAll(prevButton,playButton,nextButton,shuffleButton);

        bBar.setPadding(new Insets(10));
        bBar.setSpacing(10);
        bBar.setAlignment(Pos.BASELINE_CENTER);
        bBar.setStyle("-fx-background-color: lightgrey;");

        return bBar;
    }
}
