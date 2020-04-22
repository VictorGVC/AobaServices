package malucismanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TelaConfigController implements Initializable {

    @FXML
    private ImageView ivLogo;
    @FXML
    private VBox painel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 

    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), painel);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}