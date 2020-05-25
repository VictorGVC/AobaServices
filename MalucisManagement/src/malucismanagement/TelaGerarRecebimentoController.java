package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
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
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;
import malucismanagement.db.dal.DALCliente;
import malucismanagement.db.dal.DALContasReceber;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Cliente;
import malucismanagement.db.entidades.ContasReceber;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.ManipularCpfCnpj;
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
        cbtipo.setFocusColor(Paint.valueOf("RED"));
        cbtipo.setUnFocusColor(Paint.valueOf("RED"));
        if(op){
            
            setCorAlert(tcliente, cor);
            dpvencimento.setDefaultColor(Paint.valueOf("RED"));
        }
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
        
        int parcela = 1, parcelas;
        boolean flag = false, flag2 = true;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        setCorAlert("BLACK");
        if(tparcelas.getText().isEmpty()){
            
            flag = true;
            setCorAlert(tparcelas, "RED");
        }
        if(cbtipo.getSelectionModel().getSelectedIndex() == -1){
            
            flag = true;
            cbtipo.setFocusColor(Paint.valueOf("RED"));
            cbtipo.setUnFocusColor(Paint.valueOf("RED"));
        }
        if(op){
            
            if(tcliente.getText().isEmpty()){

                flag = true;
                setCorAlert(tcliente, "RED");
            }
            if(!ManipularCpfCnpj.isCpf(tcliente.getText())){
            
                setCorAlert(tcliente, "RED");
                a.setContentText("CPF inválido!");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
            if(dpvencimento.getValue() == null){
                
                flag = true;
                dpvencimento.setDefaultColor(Paint.valueOf("RED"));
            }
        }
        if(flag){
            
            a.setContentText("Campos obrigatórios não preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else if((ManipularCpfCnpj.isCpf(tcliente.getText()) && op) || !op){
            
            parcelas = Integer.parseInt(tparcelas.getText());
            DALCliente dal = new DALCliente();
            Cliente c = dal.getCli(cliente);
            while(parcela <= parcelas){
                
                ContasReceber cr = null;
                if(op)
                    cr = new ContasReceber(parcela, venda, (double)(valortotal / parcelas), 
                        dpvencimento.getValue(), null, cbtipo.getSelectionModel().getSelectedItem(), c.getTelefone());
                else
                    cr = new ContasReceber(parcela, venda, (double)(valortotal / parcelas), 
                        LocalDate.now(), LocalDate.now(), cbtipo.getSelectionModel().getSelectedItem(), c.getTelefone());
                DALContasReceber dalcr = new DALContasReceber();
                
//                if(!dal.gravar(cr))
//                    flag2 = false;
                parcela++;
            }
            if(flag2){
                
                JFXSnackbar sb = new JFXSnackbar(pnprincipal); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Salvo com Sucesso!")));
            }
            else{
                
                a.setContentText("Problemas ao Gravar!");
                a.showAndWait();
            }
        }
    }
}