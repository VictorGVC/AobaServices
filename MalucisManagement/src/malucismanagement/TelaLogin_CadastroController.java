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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import static malucismanagement.TelaPrincipalController.efeito;
import malucismanagement.db.dal.DALFuncionario;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Funcionario;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.MaskFieldUtil;
import malucismanagement.util.SQLException_Exception;
import malucismanagement.util.SigepClienteException;

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
    private JFXTabPane pntab;
    @FXML
    private JFXTextField txuf;
    @FXML
    private FlowPane pncadastro;

    /**
     * Initializes the controller class.
     */
    static Funcionario sessao;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMascaras();
        initializeCargo();
        initializeSexo();
        sessao = new Funcionario();
    }    
    
    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    public static Funcionario getlogin()
    {
        return sessao;
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
            txuf.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
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
            txuf.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            
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
        String id;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txcpf.getText().isEmpty())
        {
            a.setContentText("CPF deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txcpf.requestFocus();
        }
        else if(txnome.getText().isEmpty())
        {
            a.setContentText("Nome deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txnome.requestFocus();
        }
        else if(cbsexo.getSelectionModel().getSelectedIndex() == -1)
        {
            a.setContentText("Sexo deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbsexo.requestFocus();
        }
        else if(dtpnascimento.getValue() == null)
        {
            a.setContentText("Data de nascimento deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            dtpnascimento.requestFocus();
        }
        else if(txemail.getText().isEmpty())
        {
            a.setContentText("E-Mail deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txemail.requestFocus();
        }
        else if(txtelefone.getText().isEmpty())
        {  
            a.setContentText("Telefone deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txtelefone.requestFocus();
        }
        else if(txcep.getText().isEmpty())
        {
            a.setContentText("CEP deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txcep.requestFocus();
        }
        else if(txrua.getText().isEmpty())
        {  
            a.setContentText("Rua deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txrua.requestFocus();
        }
        else if(txnumero.getText().isEmpty())
        {   
            a.setContentText("Numero deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txnumero.requestFocus();
        }
        else if(txbairro.getText().isEmpty())
        {
            a.setContentText("Bairro deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txbairro.requestFocus();
        }
        else if(txcidade.getText().isEmpty())
        {
            a.setContentText("Cidade deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txcidade.requestFocus();
        }
        else if(txuf.getText().isEmpty())
        {    
            a.setContentText("Estado deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txuf.requestFocus();
        }
        //começa aqui
        else if(txusuarioc.getText().isEmpty())
        {
            a.setContentText("Login deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txusuarioc.requestFocus();
        }
        else if(txsenhac.getText().isEmpty())
        {   
            a.setContentText("Senha deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txsenhac.requestFocus();
        }
        else if(cbcargo.getSelectionModel().getSelectedIndex() == -1)
        {
            a.setContentText("Estado deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbcargo.requestFocus();
        }
        else
        {    
            try 
            {
                id = txcpf.getText();
            } 
            catch (NumberFormatException e) 
            {
                id = "";
            }
            
            char sexo = ' ';
            
            if(cbsexo.getSelectionModel().getSelectedIndex() == 0)
                sexo = 'M';
            else if(cbsexo.getSelectionModel().getSelectedIndex() == 1)
                sexo = 'F';
                    
            Funcionario f = new Funcionario(Integer.parseInt(txnumero.getText()), sexo, 
                    txnome.getText(), id, txemail.getText(), txtelefone.getText(), txcep.getText(),
                    txrua.getText(), txbairro.getText(), txcidade.getText(), txuf.getText(), txusuarioc.getText(), 
                    dtpnascimento.getValue(), 
                    'l',
                    cbcargo.getSelectionModel().getSelectedIndex());
            DALFuncionario dal = new DALFuncionario();
            
            if (dal.gravar(f,txsenhac.getText()))
            {
                JFXSnackbar sb = new JFXSnackbar(pncadastro); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Gravado com Sucesso!")));
                TelaPrincipalController.spnprincipal.setCenter(null);
                TelaPrincipalController.efeito(false);
                sessao.setNivel(cbcargo.getSelectionModel().getSelectedIndex());
                sessao.setLogin(id);
            }
            else
            {
                a.setContentText("Problemas ao Alterar!");
                a.showAndWait();
            }
        }
        
    }

    @FXML
    private void evtBotaoDigitado(KeyEvent event) 
    {
        if(txcep.getText().length() == 8){
            
            Task task = new Task<Void>() {
                
                @Override
                protected Void call() {
                    
                    String cep = txcep.getText().replaceAll("\\-", "");
                    malucismanagement.util.AtendeClienteService service = new malucismanagement.util.AtendeClienteService();
                    malucismanagement.util.AtendeCliente port = service.getAtendeClientePort();

                    try {

                        malucismanagement.util.EnderecoERP result = port.consultaCEP(cep);

                        txrua.setText(result.getEnd());
                        txbairro.setText(result.getBairro());
                        txcidade.setText(result.getCidade());
                        txuf.setText(result.getUf());
                    }
                    catch (SQLException_Exception | SigepClienteException e) {}
                    
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    @FXML
    private void clkBtLogin(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALFuncionario dal = new DALFuncionario();
        
        if(txusuario.getText().isEmpty())
        {
            a.setContentText("Usuário deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txusuario.requestFocus();
        }
        else if(txsenha.getText().isEmpty())
        {
            a.setContentText("Senha deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txsenha.requestFocus();
        }
        else
        {
            if(dal.valida(txusuario.getText(), txsenha.getText()))
            {
                DALParametrizacao dalp = new DALParametrizacao();
                Parametrizacao p = dalp.getConfig();
                JFXSnackbar sb = new JFXSnackbar(pnlogin); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Logado com sucesso!")));
                sessao.setLogin(txusuario.getText());
                Funcionario f = dal.get(txusuario.getText());
                sessao.setNivel(f.getNivel());
                
                if(p == null)
                {
                    try 
                    {
                        Parent root = FXMLLoader.load(getClass().getResource("TelaConfig.fxml"));
                        efeito(true);
                        TelaPrincipalController.spnprincipal.setCenter(root);
                    }
                    catch (IOException ex){

                        System.out.println(ex);
                    }
                }
                else
                {
                    
                    TelaPrincipalController.spnprincipal.setCenter(null);
                    TelaPrincipalController.efeito(false);
                }
            }
            else
            {
                JFXSnackbar sb = new JFXSnackbar(pnlogin); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Login e/ou senha invalido(s)!")));
            }
        }
    }
    
}
