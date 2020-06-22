package malucismanagement;

import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private boolean pa;
    
    @FXML
    private VBox pnprincipal;
    @FXML
    private Pane pncabecalho;
    @FXML
    private Button btfechar;
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
    @FXML
    private Label lbconfig;
    @FXML
    private Label lblogo;
    @FXML
    private Label lbcp;
    @FXML
    private Label lbcs;
    @FXML
    private Label lbcf;
    @FXML
    private JFXButton btabririmg;
    @FXML
    private JFXButton btremoverimg;
    @FXML
    private JFXButton btsalvar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pa = false;
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        fadeout();
        if(p != null){
            
            try {
                retornaConfig();
            }
            catch (IOException ex) {}
            setParametros();
        }
        else
            pa = true;
        if(!pa)
            btfechar.setVisible(false);
        setMascaras();
        listaFontes();
    }    
    
    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void retornaConfig() throws IOException {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        BufferedImage bimg = null;
        InputStream is = dal.getFoto();
        
        cpprimaria.setValue(hex2Rgb(p.getCorprimaria()));
        cpsecundaria.setValue(hex2Rgb(p.getCorsecundaria()));
        cbfonte.setValue(p.getFonte());
        cpfonte.setValue(hex2Rgb(p.getCorfonte()));
        bimg = ImageIO.read(is);
        if(bimg != null)
            ivlogo.setImage(SwingFXUtils.toFXImage(bimg, null));
        tcep.setText(p.getCep());
        tcidade.setText(p.getCidade());
        tuf.setText(p.getUf());
        trua.setText(p.getRua());
        ttelefone.setText(p.getTelefone());
    }

    private void setParametros() {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p.getCorprimaria() != null){
            
            pndados.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            pncabecalho.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            pnrodape.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            lbconfig.setFont(new Font(p.getFonte(), 14));
            
            lbcp.setFont(new Font(p.getFonte(), 14));
            lblogo.setFont(new Font(p.getFonte(), 14));
            lbcs.setFont(new Font(p.getFonte(), 14));
            lbcf.setFont(new Font(p.getFonte(), 14));
            btabririmg.setFont(new Font(p.getFonte(), 12));
            btremoverimg.setFont(new Font(p.getFonte(), 12));
            tcep.setFont(new Font(p.getFonte(), 14));
            tcidade.setFont(new Font(p.getFonte(), 14));
            tuf.setFont(new Font(p.getFonte(), 14));
            trua.setFont(new Font(p.getFonte(), 14));
            ttelefone.setFont(new Font(p.getFonte(), 14));
            
            btsalvar.setFont(new Font(p.getFonte(), 14));
        }
        if(p.getCorfonte() != null){
            
            lbconfig.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            lbcp.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lblogo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbcs.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbcf.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btabririmg.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btremoverimg.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcep.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcidade.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tuf.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            trua.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            ttelefone.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            btsalvar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    } 
    
    private void setMascaras() {
        
        MaskFieldUtil.cepField(tcep);
        MaskFieldUtil.maxField(tcidade, 30);
        MaskFieldUtil.maxField(tuf, 2);
        MaskFieldUtil.maxField(trua, 60);
        MaskFieldUtil.foneField(ttelefone);
    }
    
    private void listaFontes() {
        
        List<String> list = new ArrayList();
        
        list.add("Arial");
        list.add("Times New Roman");
        list.add("Helvetica");
        list.add("Calibri");
        list.add("Tahoma");
        
        cbfonte.setItems(FXCollections.observableArrayList(list));
    }
    
    public static Color hex2Rgb(String colorStr) {
        
        return Color.rgb(Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
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
        
        String corp = "#" + cpprimaria.getValue().toString().substring(2, 8);
        String cors = "#" + cpsecundaria.getValue().toString().substring(2, 8);
        String corf = "#" + cpfonte.getValue().toString().substring(2, 8);
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = new Parametrizacao(corp, cors, cbfonte.getValue(), corf, 
                ttelefone.getText(), trua.getText(), tcep.getText(), tuf.getText(), tcidade.getText());
        Parametrizacao paux = dal.getConfig();
        BufferedImage bimg = null;
        
        bimg = SwingFXUtils.fromFXImage(ivlogo.getImage(), bimg);
        p.setLogo(ManipularImagem.getImgBytes(bimg));
        if(paux == null){
            
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
        setParametros();
    }
    
    @FXML
    private void clkBtFechar(ActionEvent event) {
        
        Stage stage = (Stage) btfechar.getScene().getWindow();
        
        stage.close();
        if(pa)
            try {
                chamaLogin();
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
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
}