package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.MaskFieldUtil;

public class TelaGerarRecebimentoController implements Initializable {

    private static int venda;
    private double valortotal;
    private boolean op;
    private static String cliente;
    
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXComboBox<String> cbtipo;
    @FXML
    private JFXTextField tvalor;
    @FXML
    private JFXTextField tparcelas;
    @FXML
    private JFXDatePicker dpvencimento;
    @FXML
    private JFXTextField tcliente;

    public static void setVenda(int v) {
        venda = v;
    }

    public static String getCliente() {
        return cliente;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        
        a.getButtonTypes().setAll(btsim,btnao);
        a.setTitle("Pagamento");
        a.setContentText("Conta fiada?");
        op = a.showAndWait().get() == btsim;
        fadeout();
        setParametros();
        setMascaras();
        listaTipo();
        visivel();
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
        
        if(p.getCorsecundaria()!= null){
            
            pnprincipal.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            tvalor.setFont(new Font(p.getFonte(), 14));
            tparcelas.setFont(new Font(p.getFonte(), 14));
            tcliente.setFont(new Font(p.getFonte(), 14));
            
            btconfirmar.setFont(new Font(p.getFonte(), 12));
        }
        if(p.getCorfonte() != null){
            
            tvalor.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tparcelas.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcliente.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            btconfirmar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void setMascaras() {
        
        MaskFieldUtil.numericField(tparcelas);
        MaskFieldUtil.cpfField(tcliente);
        dpvencimento.setValue(LocalDate.now());
    }
    
    private void visivel() {
        
        tcliente.setVisible(op);
        dpvencimento.setVisible(op);
    }
    
    private void listaTipo() {
        
        List<String> list = new ArrayList();
        
        list.add("");
        list.add("Cartão de Crédito");
        list.add("Cartão de Débito");
        list.add("Dinheiro");
        
        cbtipo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void setCorAlert(String cor){
        
        setCorAlert(tparcelas, cor);
        if(op)
            setCorAlert(tcliente, cor);
    }
    
    private void setCorAlert(JFXTextField tf, String cor){
        
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    @FXML
    private void evtCpfDigitado(KeyEvent event) {
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
    }
}