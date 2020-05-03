/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import malucismanagement.util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author HITRON
 */
public class TelaLogin_CadastroController implements Initializable {

    @FXML
    private VBox painel1;
    @FXML
    private Tab tablogin;
    @FXML
    private FlowPane flplogin;
    @FXML
    private VBox painel;
    @FXML
    private JFXComboBox<String> cbcargo;
    @FXML
    private JFXComboBox<String> cbsexo;
    @FXML
    private JFXTextField txtelefone;
    @FXML
    private JFXDatePicker dtpnascimento;
    @FXML
    private JFXTextField txcep;
    @FXML
    private JFXPasswordField txsenha;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private JFXTextField txusuario;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txrua;
    @FXML
    private JFXTextField txbairro;
    @FXML
    private JFXTextField txcidade;
    @FXML
    private JFXTextField txnumero;
    @FXML
    private JFXPasswordField txsenhac;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMascaras();
    }    
    
    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void setMascaras()
    {
        MaskFieldUtil.foneField(txtelefone);
        MaskFieldUtil.cpfField(txcpf);
        MaskFieldUtil.cepField(txcep);
        MaskFieldUtil.maxField(txnome, 60);
        MaskFieldUtil.maxField(txusuario, 15);
        MaskFieldUtil.maxField(txsenha, 15);
        MaskFieldUtil.maxField(txbairro, 30);
        MaskFieldUtil.maxField(txrua, 30);
        MaskFieldUtil.maxField(txnumero, 7);
        MaskFieldUtil.maxField(txcidade, 20);
    }

    @FXML
    private void clkBtCadastro(ActionEvent event) {
        
    }

    @FXML
    private void clkBtLogin(ActionEvent event) {
    }
    
}
