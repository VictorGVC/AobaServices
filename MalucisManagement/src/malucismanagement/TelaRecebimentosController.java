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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import malucismanagement.db.dal.DALContasReceber;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.ContasReceber;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.MaskFieldUtil;

public class TelaRecebimentosController implements Initializable {

    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btquitar;
    @FXML
    private JFXButton btestornar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private JFXButton btvoltar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXTextField tparcelas;
    @FXML
    private JFXTextField tcodigo;
    @FXML
    private JFXDatePicker dpdatavenc;
    @FXML
    private Label lbobg;
    @FXML
    private JFXTextField tcodvenda;
    @FXML
    private JFXTextField tvalor;
    @FXML
    private JFXComboBox<String> cbtipo;
    @FXML
    private JFXTextField tcontato;
    @FXML
    private JFXDatePicker dpdatapag;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXComboBox<String> cbcategoria;
    @FXML
    private JFXTextField tfiltro;
    @FXML
    private TableView<ContasReceber> tvrecebimentos;
    @FXML
    private TableColumn<ContasReceber, Integer> colcod;
    @FXML
    private TableColumn<ContasReceber, Integer> colvenda;
    @FXML
    private TableColumn<ContasReceber, Integer> colparcela;
    @FXML
    private TableColumn<ContasReceber, Double> colvalor;
    @FXML
    private TableColumn<ContasReceber, LocalDate> coldatavenc;
    @FXML
    private TableColumn<ContasReceber, LocalDate> coldatapag;
    @FXML
    private TableColumn<ContasReceber, String> coltipo;
    @FXML
    private TableColumn<ContasReceber, String> colcontato;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
        fixaDivider();
        setParametros();
        setMascaras();
        initColTb();
        listaTipo();
        listaCategoria();
        estado(true);
    }    

    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void fixaDivider() {
        
        SplitPane.Divider divider = pnprincipal.getDividers().get(0);
        divider.positionProperty().addListener(new ChangeListener<Number>() {
            
            @Override 
            public void changed(ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue) {
                
                divider.setPosition(0.37);
            }
        });
    }
    
    private void setParametros() {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p.getCorprimaria() != null){
            
            pndados.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvrecebimentos.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            pnbotoes.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            pnfiltros.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            btquitar.setFont(new Font(p.getFonte(), 12));
            btestornar.setFont(new Font(p.getFonte(), 12));
            btcancelar.setFont(new Font(p.getFonte(), 12));
            btvoltar.setFont(new Font(p.getFonte(), 12));
            
            tcodigo.setFont(new Font(p.getFonte(), 14));
            tcodvenda.setFont(new Font(p.getFonte(), 14));
            tparcelas.setFont(new Font(p.getFonte(), 14));
            tvalor.setFont(new Font(p.getFonte(), 14));
            tcontato.setFont(new Font(p.getFonte(), 14));
            lbobg.setFont(new Font(p.getFonte(), 12));
            
            tfiltro.setFont(new Font(p.getFonte(), 14));
        }
        if(p.getCorfonte() != null){
            
            btquitar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btestornar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btvoltar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tcodigo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcodvenda.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tparcelas.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tvalor.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcontato.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tfiltro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void setMascaras() {
        
        MaskFieldUtil.numericField(tcodigo);
        MaskFieldUtil.numericField(tcodvenda);
        MaskFieldUtil.numericField(tparcelas);
        MaskFieldUtil.monetaryField(tvalor);
        MaskFieldUtil.foneField(tcontato);
    }
    
    private void initColTb() {
        
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colvenda.setCellValueFactory(new PropertyValueFactory("ven_cod"));
        colparcela.setCellValueFactory(new PropertyValueFactory("par"));
        colvalor.setCellValueFactory(new PropertyValueFactory("valor"));
        coldatavenc.setCellValueFactory(new PropertyValueFactory("datavenc"));
        coldatapag.setCellValueFactory(new PropertyValueFactory("datapag"));
        coltipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontato.setCellValueFactory(new PropertyValueFactory("contato"));
    }
    
    private void estado(boolean b) {
        
        pndados.setDisable(b);
        btquitar.setDisable(b);
        btcancelar.setDisable(b);
        btestornar.setDisable(!b);
      
        //carregaTabela("");
    }
    
    private void carregaTabela(String filtro) {
        
        DALContasReceber dal = new DALContasReceber();
        List<ContasReceber> res = dal.getListRec(filtro);
        ObservableList<ContasReceber> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvrecebimentos.setItems(modelo);
    }
    
    private void listaTipo() {
        
        List<String> list = new ArrayList();
        
        list.add("");
        list.add("Cartão de Crédito");
        list.add("Cartão de Débito");
        list.add("Dinheiro");
        
        cbtipo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void listaCategoria() {
        
        List<String> list = new ArrayList();
        
        list.add("");
        list.add("Código");
        list.add("Venda");
        list.add("Data de Vencimento");
        list.add("Status");
        list.add("Tipo");
        list.add("Cliente");
        list.add("Contato");
        
        cbcategoria.setItems(FXCollections.observableArrayList(list));
    }
    
    private void limparCampos() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
            cbtipo.getSelectionModel().select(-1);
            cbcategoria.getSelectionModel().select(-1);
        }
    }
    
    private void setCorAlert(String cor){
        
        setCorAlert(tvalor, cor);
        setCorAlert(tcontato, cor);
    }
    
    private void setCorAlert(JFXTextField tf, String cor){
        
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }

    @FXML
    private void clkBtQuitar(ActionEvent event) {
    }

    @FXML
    private void clkBtEstonar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        
        if (!pndados.isDisabled()){
            
            estado(true);
            limparCampos();
        }
        pnpesquisa.setDisable(false);
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
        
        Stage stage = (Stage) btvoltar.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void clkTFiltro(KeyEvent event) {
    }

    @FXML
    private void clkTabela(MouseEvent event) {
        
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
    }
}