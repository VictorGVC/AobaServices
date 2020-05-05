package malucismanagement;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import malucismanagement.db.dal.DALFornecedores;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Parametrizacao;

public class TelaPrincipalController implements Initializable {

    public static BorderPane spnprincipal = null;
    
    @FXML
    private BorderPane pnprincipal;
    @FXML
    private ImageView ivLogo;
    @FXML
    private JFXButton btclientes;
    @FXML
    private HBox pndados;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btclientes1;
    @FXML
    private MenuItem miFornecedores;
    @FXML
    private MenuItem miProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        spnprincipal = pnprincipal;
        chamaLogin();
    }    

    public void chamaLogin()
    {
        try 
        {    
            Parent root = FXMLLoader.load(getClass().getResource("TelaLogin_Cadastro.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
            
    public static void efeito(boolean on)
    {
        if(on){
            
            FadeTransition ft = new FadeTransition(Duration.millis(500), spnprincipal);
        }
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

    @FXML
    private void clkBtClientes(ActionEvent event) {
        
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("TelaClientes.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);
        }
        catch (IOException ex){
            
            System.out.println(ex);
        }
    }

    @FXML
    private void clkBtFuncionarios(ActionEvent event) throws IOException 
    {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);
        }
        catch (IOException ex){
            
            System.out.println(ex);
        }
    }


    @FXML
    private void clkChamaLogin(ActionEvent event) 
    {
        try 
        {    
            Parent root = FXMLLoader.load(getClass().getResource("TelaLogin_Cadastro.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkOpenFornecedores(ActionEvent event) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("TelaFornecedor.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);
        }
        catch (IOException ex){
            
            System.out.println(ex);
        }
    }

    @FXML
    private void clkOpenProduto(ActionEvent event) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));
            efeito(true);
            pnprincipal.setCenter(root);
        }
        catch (IOException ex){
            
            System.out.println(ex);
        }
    }
}