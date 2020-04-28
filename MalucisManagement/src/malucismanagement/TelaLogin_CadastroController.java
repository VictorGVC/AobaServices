/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HITRON
 */
public class TelaLogin_CadastroController implements Initializable {

    @FXML
    private VBox painel1;
    @FXML
    private JFXButton btcadastro;
    @FXML
    private Tab tablogin;
    @FXML
    private FlowPane flplogin;
    @FXML
    private VBox painel;
    @FXML
    private JFXButton btlogin;
    @FXML
    private JFXComboBox<?> cbcargo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkBtSalvar(ActionEvent event) {
        
    }
    
}
