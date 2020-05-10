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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
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
    private VBox painel;
    @FXML
    private JFXPasswordField txsenha;
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private JFXTextField txusuario;
    @FXML
    private FlowPane pnlogin;
    @FXML
    private JFXButton btlogin;

    /**
     * Initializes the controller class.
     */
    static Funcionario sessao;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMascaras();
        sessao = new Funcionario();
    }    
    
    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void setParametros() 
    {
//        DALParametrizacao dal = new DALParametrizacao();
//        Parametrizacao p = dal.getConfig();
//        
//        if(p.getCorprimaria() != null)
//        {
//            btcadastro.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
//            btlogin.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
//        }
//        if(p.getCorsecundaria()!= null)
//        {
//            pnlogin.setStyle("-fx-background-color: " + p.getCorsecundaria() + ";");
//            pntab.setStyle("-fx-background-color: " + p.getCorsecundaria() + ";");
//        }
//        if(p.getFonte() != null)
//        {
//            txbairro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txcep.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txcidade.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txcpf.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txemail.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txnome.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txnumero.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txrua.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txsenha.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txsenhac.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txtelefone.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txusuario.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txusuarioc.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txuf.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            
//            cbcargo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            cbsexo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//        }
//        if(p.getCorfonte() != null)
//        {
//            txbairro.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txcep.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txcidade.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txcpf.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txemail.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txnome.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txnumero.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txrua.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txsenha.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txsenhac.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txtelefone.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txusuario.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txusuarioc.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            txuf.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            
//            cbcargo.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//            cbsexo.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
//        }
    }
    
    private void setMascaras()
    {
        MaskFieldUtil.maxField(txusuario, 15);
        MaskFieldUtil.maxField(txsenha, 15);
    }

    public static Funcionario getLogin()
    {
        return sessao;
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
                Stage stage = (Stage) btlogin.getScene().getWindow();
                stage.close();
                try 
                {
                    chamaPrincipal();
                } 
                catch (IOException ex) 
                {
                    System.out.println(ex);
                }
            }
            else
            {
                JFXSnackbar sb = new JFXSnackbar(pnlogin); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Login e/ou senha invalido(s)!")));
            }
        }
    }
    
    private void chamaPrincipal() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setMaximized(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setScene(scene);
        stage.show();
    }
}
