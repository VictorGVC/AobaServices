package malucismanagement;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import malucismanagement.util.ConsultaAPI;
import malucismanagement.util.MaskFieldUtil;
import org.json.JSONObject;

public class TelaConfigController implements Initializable {

    File arq;
    FileInputStream file;
    
    @FXML
    private ImageView ivLogo;
    @FXML
    private VBox painel;
    @FXML
    private JFXTextField tTelefone;
    @FXML
    private JFXColorPicker cpSecundaria;
    @FXML
    private JFXColorPicker cpPrimaria;
    @FXML
    private JFXTextField tCep;
    @FXML
    private JFXTextField tUf;
    @FXML
    private JFXTextField tCidade;
    @FXML
    private JFXTextField tRua;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
    } 

    private void fadeout() {
        
        MaskFieldUtil.cepField(tCep);
        MaskFieldUtil.maxField(tUf, 2);
        MaskFieldUtil.foneField(tTelefone);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), painel);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    private void evtBotaoDigitado(KeyEvent event) {
        
        if(tCep.getText().length() == 8){
            
            Task task = new Task<Void>() {
            @Override
            protected Void call() {
                
                String json_str = ConsultaAPI.consultaCep(tCep.getText());
                JSONObject my_obj = new JSONObject(json_str);
                
                tRua.setText(my_obj.getString("logradouro"));
                tCidade.setText(my_obj.getString("localidade"));
                tUf.setText(my_obj.getString("uf"));
                    
                return null;
            }
        };
        new Thread(task).start();
        }
    }

    @FXML
    private void clkAddLogo(MouseEvent event) throws FileNotFoundException {
        
        Image img;
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        arq = fc.showOpenDialog(null);
        file = new FileInputStream(arq);
        if(arq != null)
            ivLogo.setImage(new Image(file));
    }
    
    @FXML
    private void clkBtSalvar(ActionEvent event) {
        
    }
    
    @FXML
    private void clkBtFechar(ActionEvent event) {
        
        TelaPrincipalController.spnprincipal.setCenter(null);
        TelaPrincipalController.efeito(false);
    }
}