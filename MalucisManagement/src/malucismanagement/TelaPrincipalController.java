package malucismanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class TelaPrincipalController implements Initializable {

    public static BorderPane spnprincipal = null;
    
    @FXML
    private BorderPane pnprincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        spnprincipal = pnprincipal;
    }    

    public static void efeito(boolean on)
    {
        if(on){
            
            FadeTransition ft = new FadeTransition(Duration.millis(500), spnprincipal); 
            spnprincipal.setStyle("-fx-background-image: url('icons/pub.jpg');-fx-background-position: center center;"
                    + "-fx-background-repeat: no-repeat;");
        }
        else
            spnprincipal.setStyle("-fx-background-image: url('icons/pub.jpg');-fx-background-position: center center;"
                    + "-fx-background-repeat: no-repeat;");
    }
    
    @FXML
    private void clkBackup(ActionEvent event) {
    }

    @FXML
    private void clkRestore(ActionEvent event) {
    }

    @FXML
    private void clkConfiguracoes(ActionEvent event) {
        
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("TelaConfig.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);
        }
        catch (IOException ex){
            
            System.out.println(ex);
        }
    }
}