package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import malucismanagement.util.MaskFieldUtil;

public class TelaConfigController implements Initializable {

    @FXML
    private ImageView ivLogo;
    @FXML
    private VBox painel;
    @FXML
    private JFXTextField tEndereco;
    @FXML
    private JFXTextField tTelefone;
    @FXML
    private JFXColorPicker cpSecundaria;
    @FXML
    private JFXColorPicker cpPrimaria;
    @FXML
    private JFXButton btSalvar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
    } 

    private void fadeout() {
        
        MaskFieldUtil.foneField(tTelefone);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), painel);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    private void clkBtSalvar(ActionEvent event) {
        
    }
}