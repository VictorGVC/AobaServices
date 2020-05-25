package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import malucismanagement.db.dal.DALFuncionario;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Funcionario;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.MaskFieldUtil;

public class TelaLogin_CadastroController implements Initializable {

    static Funcionario sessao;
    
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
        setParametros();
        setMascaras();
        sessao = new Funcionario();
        teclaEnter();
    }    
    
    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void setParametros() {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p != null){
            
            if(p.getCorprimaria() != null)
            {
                
            }
            if(p.getCorsecundaria()!= null)
            {
                btlogin.setStyle("-fx-background-color: " + p.getCorsecundaria() + ";");
                pnlogin.setStyle("-fx-background-color: " + p.getCorsecundaria() + ";");
            }
            if(p.getFonte() != null)
            {
                txsenha.setStyle("-fx-font-family: " + p.getFonte()+ ";");
                txusuario.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            }
            if(p.getCorfonte() != null)
            {
                txsenha.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
                txusuario.setStyle("-fx-font-family: " + p.getCorfonte()+ ";");
            }
        }
    }
    
    private void setMascaras() {
        
        MaskFieldUtil.maxField(txusuario, 15);
        MaskFieldUtil.maxField(txsenha, 15);
    }
    
    private void teclaEnter(){
        
        txusuario.setOnKeyPressed(k ->{
            
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if(ENTER.match(k)) {
                txsenha.requestFocus();
            }
        });
        txsenha.setOnKeyPressed(k ->{
            
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if(ENTER.match(k)) {
                clkBtLogin(null);
            }
        });
    }

    public static Funcionario getLogin() {
        
        return sessao;
    }
    
    @FXML
    private void clkBtLogin(ActionEvent event) {
        
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
    
    private void chamaPrincipal() throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setMaximized(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Papelaria Maluci");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent t) {
                
                t.consume();
                stage.close();
                Platform.exit();
                System.exit(0);
            }
        });
    }
}