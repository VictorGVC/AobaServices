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
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import malucismanagement.db.dal.DALFuncionario;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Parametrizacao;
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
    @FXML
    private JFXTextField txusuarioc;
    @FXML
    private Tab tabcadastro;
    @FXML
    private JFXTextField txemail;
    @FXML
    private Label lblpessoal;
    @FXML
    private Label lblendereco;
    @FXML
    private Label lblcadastro;
    @FXML
    private JFXButton btcadastro;
    @FXML
    private FlowPane pnlogin;
    @FXML
    private JFXButton btlogin;
    @FXML
    private JFXTabPane pntab;

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
    
    private void initializeSexo()
    {
        List<String> list = new ArrayList();
        
        list.add("Masculino");
        list.add("Feminino");
        
        cbsexo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void initializeCargo()
    {
        List<String> list = new ArrayList();
        
        list.add("Administrador");
        list.add("Vendedor");
        
        cbcargo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void setParametros() 
    {
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p.getCorprimaria() != null)
        {
            btcadastro.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            btlogin.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null)
        {
            pnlogin.setStyle("-fx-background-color: " + p.getCorsecundaria() + ";");
            pntab.setStyle("-fx-background-color: " + p.getCorsecundaria() + ";");
        }
        if(p.getFonte() != null)
        {
            txbairro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txcep.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txcidade.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txcpf.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txemail.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txnome.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txnumero.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txrua.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txsenha.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txsenhac.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txtelefone.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txusuario.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txusuarioc.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            cbcargo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbsexo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
        }
        if(p.getCorfonte() != null)
        {
            txbairro.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txcep.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txcidade.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txcpf.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txemail.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txnome.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txnumero.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txrua.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txsenha.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txsenhac.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txtelefone.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txusuario.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            txusuarioc.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            
            cbcargo.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            cbsexo.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
        }
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
    private void clkBtCadastro(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkBtLogin(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALFuncionario dal = new DALFuncionario();
        
        if(txusuario.getText().isEmpty())
        {
            a.setContentText("CPF deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txusuario.requestFocus();
        }
        else if(txsenha.getText().isEmpty())
        {
            a.setContentText("Nome deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txsenha.requestFocus();
        }
        else
        {
            if(dal.valida(txusuario.getText(), txsenha.getText()))
            {
                
            }
            else
            {
                JFXSnackbar sb = new JFXSnackbar(pnlogin); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Login e/ou senha invalido(s)!")));
            }
        }
    }
    
}
