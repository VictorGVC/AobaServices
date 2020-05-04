package malucismanagement;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import malucismanagement.db.banco.Banco;

public class MalucisManagement extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setScene(scene);
        stage.setTitle("Papelaria Maluci");
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        if(Banco.conectar())
        {
            JOptionPane.showMessageDialog(null, "Conexão criada com sucesso, o sistema será reiniciado");
            launch(args);
        }
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