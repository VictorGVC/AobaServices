package malucismanagement;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.ManipularImagem;
import malucismanagement.util.MaskFieldUtil;
import malucismanagement.util.SQLException_Exception;
import malucismanagement.util.SigepClienteException;

public class TelaConfigController implements Initializable {

    private File arq;
    private FileInputStream file;
    
    @FXML
    private VBox pnprincipal;
    @FXML
    private Pane pncabecalho;
    @FXML
    private Pane pndados;
    @FXML
    private JFXTextField trua;
    @FXML
    private JFXTextField ttelefone;
    @FXML
    private ImageView ivlogo;
    @FXML
    private JFXColorPicker cpsecundaria;
    @FXML
    private JFXColorPicker cpprimaria;
    @FXML
    private JFXTextField tcep;
    @FXML
    private JFXTextField tuf;
    @FXML
    private JFXTextField tcidade;
    @FXML
    private JFXComboBox<String> cbfonte;
    @FXML
    private JFXColorPicker cpfonte;
    @FXML
    private Pane pnrodape;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
        /*try {
            retornaConfig();
        }
        catch (IOException ex) {}*/
        listaFontes();
    } 

    private void fadeout() {
        
        MaskFieldUtil.cepField(tcep);
        MaskFieldUtil.maxField(tcidade, 30);
        MaskFieldUtil.maxField(tuf, 2);
        MaskFieldUtil.maxField(trua, 60);
        MaskFieldUtil.foneField(ttelefone);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void listaFontes() {
        
        List<String> list = new ArrayList();
        
        list.add("Arial");
        list.add("Calibri");
        list.add("Times New Roman");
        
        cbfonte.setItems(FXCollections.observableArrayList(list));
    }
    
    private void retornaConfig() throws IOException {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        BufferedImage bimg = null;
        InputStream is = dal.getFoto();
        
        cpprimaria.setValue(Color.web(p.getCorprimaria()));
        cpsecundaria.setValue(Color.web(p.getCorsecundaria()));
        cbfonte.setValue(p.getFonte());
        cpfonte.setValue(Color.web(p.getFonte()));
        bimg = ImageIO.read(is);
        if(bimg != null)
            ivlogo.setImage(SwingFXUtils.toFXImage(bimg, null));
        tcep.setText(p.getCep());
        tcidade.setText(p.getCidade());
        tuf.setText(p.getUf());
        trua.setText(p.getRua());
        ttelefone.setText(p.getTelefone());
    }
    
    @FXML
    private void clkBtAbrirImg(ActionEvent event) throws FileNotFoundException {
        
        Image img;
        FileChooser fc = new FileChooser();
        
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        arq = fc.showOpenDialog(null);
        file = new FileInputStream(arq);
        if(arq != null)
            ivlogo.setImage(new Image(file));
    }

    @FXML
    private void clkBtRemoverImg(ActionEvent event) {
        
        ivlogo.setImage(null);
    }
    
    @FXML
    private void evtBotaoDigitado(KeyEvent event) {
        
        if(tcep.getText().length() == 8){
            
            Task task = new Task<Void>() {
                
                @Override
                protected Void call() {
                    
                    String cep = tcep.getText().replaceAll("\\-", "");
                    malucismanagement.util.AtendeClienteService service = new malucismanagement.util.AtendeClienteService();
                    malucismanagement.util.AtendeCliente port = service.getAtendeClientePort();

                    try {

                        malucismanagement.util.EnderecoERP result = port.consultaCEP(cep);

                        tuf.setText(result.getUf());
                        trua.setText(result.getEnd());
                        tcidade.setText(result.getCidade());
                    }
                    catch (SQLException_Exception | SigepClienteException e) {}
                    
                    return null;
                }
            };
            new Thread(task).start();
        }
    }
    
    @FXML
    private void clkBtSalvar(ActionEvent event) {
        
        Parametrizacao p = new Parametrizacao(cpprimaria.toString(), cpsecundaria.toString(), 
                cbfonte.getValue(), cpfonte.toString(), ttelefone.getText(), trua.getText(), 
                tcep.getText(), tuf.getText(), tcidade.getText());
        DALParametrizacao dal = new DALParametrizacao();
        BufferedImage bimg = null;
        
        bimg = SwingFXUtils.fromFXImage(ivlogo.getImage(), bimg);
        p.setLogo(ManipularImagem.getImgBytes(bimg));
        if(true){
            
            if (dal.gravar(p)){
                
                if(dal.gravarFoto(p)){
                    
                    JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Salvo com Sucesso!")));
                }
                else{
                    
                    JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Erro ao Salvar!")));
                }
            }
        }
        else{
            
            if (dal.alterar(p)){
                
                if(dal.gravarFoto(p)){
                    
                    JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Alterado com Sucesso!")));
                }
                else{
                    
                    JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
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