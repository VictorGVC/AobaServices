package malucismanagement;

import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import malucismanagement.db.banco.Banco;
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
    private JFXButton btfornecedores;
    @FXML
    private JFXButton btprodutos;
    @FXML
    private HBox pnrodape;
    @FXML
    private Label lbnome;
    @FXML
    private Label lbrua;
    @FXML
    private Label lbcep;
    @FXML
    private Label lbcidade;
    @FXML
    private Label lbuf;
    @FXML
    private Label lbtelefone;
    @FXML
    private VBox pntotal;
    @FXML
    private VBox pnbotoes2;
    @FXML
    private JFXButton btvendas;
    @FXML
    private JFXButton btrecebimentos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sessao = TelaLogin_CadastroController.getLogin();
        verificanivel();
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
            pnbotoes2.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            pnbotoes.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            tbatalhos.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            
            btvendas.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            btfuncionarios.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            btclientes.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            btfornecedores.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            btprodutos.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            
            pnrodape.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            btrecebimentos.setFont(new Font(p.getFonte(), 14));
            btvendas.setFont(new Font(p.getFonte(), 14));
            btfuncionarios.setFont(new Font(p.getFonte(), 14));
            btclientes.setFont(new Font(p.getFonte(), 14));
            btfornecedores.setFont(new Font(p.getFonte(), 14));
            btprodutos.setFont(new Font(p.getFonte(), 14));
            
            lbnome.setFont(new Font(p.getFonte(), 12));
            lbrua.setFont(new Font(p.getFonte(), 12));
            lbcep.setFont(new Font(p.getFonte(), 12));
            lbcidade.setFont(new Font(p.getFonte(), 12));
            lbuf.setFont(new Font(p.getFonte(), 12));
            lbtelefone.setFont(new Font(p.getFonte(), 12));
        }
        if(p.getCorfonte() != null){
            
            btrecebimentos.setStyle(btfuncionarios.getStyle() + "-fx-text-fill: " + p.getCorfonte()+ ";");
            btvendas.setStyle(btfuncionarios.getStyle() + "-fx-text-fill: " + p.getCorfonte()+ ";");
            btfuncionarios.setStyle(btfuncionarios.getStyle() + "-fx-text-fill: " + p.getCorfonte()+ ";");
            btclientes.setStyle(btclientes.getStyle() + "-fx-text-fill: " + p.getCorfonte()+ ";");
            btfornecedores.setStyle(btfornecedores.getStyle() + "-fx-text-fill: " + p.getCorfonte()+ ";");
            btprodutos.setStyle(btprodutos.getStyle() + "-fx-text-fill: " + p.getCorfonte()+ ";");
            
            lbnome.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbrua.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbcep.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbcidade.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbuf.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbtelefone.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
        if(bimg != null)
            ivlogo.setImage(SwingFXUtils.toFXImage(bimg, null));
        if(p.getCidade() != null)
            lbrua.setText("Endereço: " + p.getRua());
        if(p.getCep() != null)
            lbcep.setText("CEP: " + p.getCep());
        if(p.getCidade() != null)
            lbcidade.setText("Cidade: " + p.getCidade());
        if(p.getUf() != null)
            lbcep.setText(p.getUf());
        if(p.getTelefone() != null)
            lbtelefone.setText("Fone: " + p.getTelefone());
    }
    
    private void verificanivel() {
        
        if(sessao.getNivel() == 1){
            
            mifuncionario.setVisible(false);
            mifornecedores.setVisible(false);
            btfuncionarios.setVisible(false);
            btfornecedores.setVisible(false);
        }
    }
    
    @FXML
    private void clkChamaLogin(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) mnbar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin_Cadastro.fxml"));
        Scene scene = new Scene(root);
        
        stage.close();
        stage = new Stage();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkConfiguracoes(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaConfig.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Configurações");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent t) {
                
                t.consume();
                stage.close();
                try {
                    setParametros();
                } 
                catch (IOException ex) {}
            }
        });
    }

    @FXML
    private void clkOpenVendas(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaVendas.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Vendas");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    private void clkOpenRecebimentos(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaRecebimentos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Recebimentos");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    private void clkOpenFuncionarios(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Funcionários");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    private void clkOpenClientes(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaClientes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Clientes");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    private void clkOpenFornecedores(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Fornecedores");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    @FXML
    private void clkOpenProduto(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Produtos");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
    
    @FXML
    private void clkBackup(ActionEvent event) {
        
        Banco.realizaBackupRestauracao("backup.bat");
    }

    @FXML
    private void clkRestore(ActionEvent event) {
        
        Banco.realizaBackupRestauracao("restore.bat");
    }
}