package malucismanagement;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.ManipularImagem;
import malucismanagement.util.MaskFieldUtil;

public class TelaConfigController implements Initializable {

    private File arq;
    private FileInputStream file;
    
    @FXML
    private ImageView ivLogo;
    @FXML
    private VBox painel;
    @FXML
    private JFXTextField tTelefone;
    @FXML
    private JFXColorPicker cpSecundaria;
    @FXML
    private JFXColorPicker cpPrimaria;
    @FXML
    private JFXTextField tCep;
    @FXML
    private JFXTextField tUf;
    @FXML
    private JFXTextField tCidade;
    @FXML
    private JFXTextField tRua;
    @FXML
    private JFXComboBox<String> cbFonte;
    @FXML
    private JFXColorPicker cpFonte;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<String> list = new ArrayList();
        
        fadeout();
        list = listaFontes();
        cbFonte.setItems(FXCollections.observableArrayList(list));
    } 

    private void fadeout() {
        
        MaskFieldUtil.cepField(tCep);
        MaskFieldUtil.maxField(tUf, 2);
        MaskFieldUtil.foneField(tTelefone);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), painel);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private List<String> listaFontes() {
        
        List<String> list = new ArrayList();
        
        list.add("Arial");
        list.add("Calibri");
        list.add("Times New Roman");
        
        return list;
    }
    
    @FXML
    private void evtBotaoDigitado(KeyEvent event) {
        
        if(tCep.getText().length() == 8){
            
            Task task = new Task<Void>() {
                
                @Override
                protected Void call() {
                    
                    String cep = tCep.getText().replaceAll("\\-", "");
                    malucismanagement.util.AtendeClienteService service = new malucismanagement.util.AtendeClienteService();
                    malucismanagement.util.AtendeCliente port = service.getAtendeClientePort();

                    try {

                        malucismanagement.util.EnderecoERP result = port.consultaCEP(cep);

                        tUf.setText(result.getUf());
                        tRua.setText(result.getEnd());
                        tCidade.setText(result.getCidade());
                    }
                    catch (Exception e) {}
                    
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    @FXML
    private void clkAddLogo(MouseEvent event) throws FileNotFoundException {
        
        Image img;
        FileChooser fc = new FileChooser();
        
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        arq = fc.showOpenDialog(null);
        file = new FileInputStream(arq);
        if(arq != null)
            ivLogo.setImage(new Image(file));
    }
    
    @FXML
    private void clkBtSalvar(ActionEvent event) {
        
        Parametrizacao p = new Parametrizacao(cpPrimaria.toString(), cpSecundaria.toString(), 
                cbFonte.getValue(), cpFonte.toString(), tTelefone.getText(), tRua.getText(), 
                tCep.getText(), tUf.getText(), tCidade.getText());
        DALParametrizacao dal = new DALParametrizacao();
        BufferedImage bimg = null;
        
        bimg = SwingFXUtils.fromFXImage(ivLogo.getImage(), bimg);
        p.setLogo(ManipularImagem.getImgBytes(bimg));
        if(true){
            
            if (dal.gravar(p)){
                
                if(dal.gravarFoto(p)){
                    
                    JFXSnackbar sb = new JFXSnackbar(painel); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Salvo com Sucesso!")));
                }
                else{
                    
                    JFXSnackbar sb = new JFXSnackbar(painel); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Erro ao Salvar!")));
                }
            }
        }
        else{
            
            if (dal.alterar(p)){
                
                if(dal.gravarFoto(p)){
                    
                    JFXSnackbar sb = new JFXSnackbar(painel); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Alterado com Sucesso!")));
                }
                else{
                    
                    JFXSnackbar sb = new JFXSnackbar(painel); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Erro ao Alterar!")));
                }
            }
        }
    }
    
    @FXML
    private void clkBtFechar(ActionEvent event) {
        
        TelaPrincipalController.spnprincipal.setCenter(null);
        TelaPrincipalController.efeito(false);
    }
}