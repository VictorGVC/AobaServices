package malucismanagement;

import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Funcionario;
import malucismanagement.db.entidades.Parametrizacao;

public class TelaPrincipalController implements Initializable {

    static Funcionario sessao = new Funcionario();
    
    @FXML
    private MenuBar mnbar;
    @FXML
    private MenuItem mifornecedores;
    @FXML
    private MenuItem miproduto;
    @FXML
    private MenuItem mifuncionario;
    @FXML
    private MenuItem miclientes;
    @FXML
    private ToolBar tbatalhos;
    @FXML
    private VBox pnimg;
    @FXML
    private ImageView ivlogo;
    @FXML
    private VBox pnbotoes;
    @FXML
    private JFXButton btfuncionarios;
    @FXML
    private JFXButton btclientes;
    @FXML
    private HBox pnrodape;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //chamaLogin();
        try {
            setParametros();
        } 
        catch (IOException ex) {}
    }    

    private void setParametros() throws IOException {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        BufferedImage bimg = null;
        InputStream is = dal.getFoto();

        bimg = ImageIO.read(is);
        if(p.getCorprimaria() != null){
            
            pnimg.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            pnbotoes.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            tbatalhos.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            pnrodape.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            btclientes.setFont(new Font(p.getFonte(), 14));
            btfuncionarios.setFont(new Font(p.getFonte(), 14));
        }
        if(p.getCorfonte() != null){
            
            btclientes.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btfuncionarios.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
        if(bimg != null)
            ivlogo.setImage(SwingFXUtils.toFXImage(bimg, null));
    }
    
    private void verificanivel()
    {
        if(sessao.getNivel() == 1)
        {
            mifuncionario.setDisable(true);
            mifornecedores.setDisable(true);
        }
    }
    
//    private void chamaLogin()
//    {
//        mnbar.setDisable(true);
//        try 
//        {    
//            Parent root = FXMLLoader.load(getClass().getResource("TelaLogin_Cadastro.fxml"));
//            efeito(true);
//            DALFuncionario df = new DALFuncionario();
//            sessao = TelaLogin_CadastroController.getlogin();
//            pnprincipal.setCenter(root);
//        }
//        catch (IOException ex)
//        {
//            System.out.println(ex);
//        }
//        
//        mnbar.setDisable(false);
//    }

    @FXML
    private void clkChamaLogin(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin_Cadastro.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Gerenciar Clientes");
        stage.show();
    }

    @FXML
    private void clkConfiguracoes(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaConfig.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Gerenciar Clientes");
        stage.show();
    }

    @FXML
    private void clkOpenFuncionarios(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Gerenciar Clientes");
        stage.show();
    }

    @FXML
    private void clkOpenClientes(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaClientes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Gerenciar Clientes");
        stage.show();
    }

    @FXML
    private void clkOpenFornecedores(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Gerenciar Clientes");
        stage.show();
    }

    @FXML
    private void clkOpenProduto(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Gerenciar Clientes");
        stage.show();
    }
    
    @FXML
    private void clkBackup(ActionEvent event) {
    }

    @FXML
    private void clkRestore(ActionEvent event) {
    }
}