package malucismanagement;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.dal.DALFuncionario;
import malucismanagement.db.dal.DALParametrizacao;

public class MalucisManagement extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
        Parent root = null;
        
        DALFuncionario dalf = new DALFuncionario();
        DALParametrizacao dalp = new DALParametrizacao();
        
        if(dalf.getL("").isEmpty()){
            
            root = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Funcionários");
        }
        else{
            
            root = FXMLLoader.load(getClass().getResource("TelaLogin_Cadastro.fxml"));
            stage.resizableProperty().setValue(false);       
            stage.setTitle("Login");
        }
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setScene(scene);
        stage.show();
    }
    
    private void chamaLogin() throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin_Cadastro.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    
    private void chamaConfig() throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaConfig.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Configurações");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    private void chamaCadastroF() throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Funcionários");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        
        if(Banco.conectar())
            launch(args);
        else
        {
            JOptionPane.showMessageDialog(null, "Erro: " + Banco.getCon().getMensagemErro());
            
            if(JOptionPane.showConfirmDialog(null, "Deseja criar uma nova conexão?") == JOptionPane.YES_OPTION)
            {
                if(!Banco.criarBD("malucidb"))
                    JOptionPane.showMessageDialog(null, "Não foi possivel criar uma nova conexão");
                else{
                    
                    JOptionPane.showMessageDialog(null, "Conexão criada com sucesso, o sistema será reiniciado");
                    
                    Banco.realizaBackupRestauracao("dbbanco\\restore.bat");
                }
            }
            System.exit(0);
        }
    }
}