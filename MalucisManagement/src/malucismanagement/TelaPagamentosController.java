package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import malucismanagement.db.dal.DALContaspagar;
import malucismanagement.db.dal.DALFornecedores;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Contaspagar;
import malucismanagement.db.entidades.Parametrizacao;

public class TelaPagamentosController implements Initializable {

    int CodAux;
    
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btvoltar;
    @FXML
    private Pane pndados;
    private Label lbobg;
    private JFXComboBox<String> cbcategoria;
    @FXML
    private JFXButton btQuitar;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField txfiltro;
    @FXML
    private TableView<Contaspagar> tvContas;
    @FXML
    private TableColumn<Contaspagar, Integer> colCod;
    @FXML
    private TableColumn<Contaspagar, Integer> colParcela;
    @FXML
    private TableColumn<Contaspagar, String> colFornecedor;
    @FXML
    private TableColumn<Contaspagar, Double> colValor;
    @FXML
    private TableColumn<Contaspagar, Character> colTipo;
    @FXML
    private TableColumn<Contaspagar, String> colContato;
    @FXML
    private TableColumn<Contaspagar, Date> colVencimento;
    @FXML
    private CheckBox ckVencidos;
    @FXML
    private Label lbFornecedores;
    @FXML
    private JFXButton btLimpar;
    @FXML
    private TableColumn<Contaspagar, Date> colPag;
    @FXML
    private Tab tabNaoPago;
    @FXML
    private DatePicker dtPagamentos;
    @FXML
    private Tab tabPago;
    @FXML
    private HBox pnbotoes1;
    @FXML
    private JFXButton btvoltarPag;
    @FXML
    private Pane pndados1;
    @FXML
    private CheckBox ckVencidosPag;
    @FXML
    private JFXTextField txfiltroPag;
    @FXML
    private Label lbFornecedoresPag;
    @FXML
    private DatePicker dtPagamentosPag;
    @FXML
    private Label lbDtPagamentoPag;
    @FXML
    private JFXButton btLimparPag;
    @FXML
    private JFXButton btExtornar;
    @FXML
    private VBox pnpesquisa1;
    @FXML
    private TableView<Contaspagar> tvContasPag;
    @FXML
    private TableColumn<Contaspagar, Integer> colCodPag;
    @FXML
    private TableColumn<Contaspagar, Integer> colParcelaPag;
    @FXML
    private TableColumn<Contaspagar, String> colFornecedorPag;
    @FXML
    private TableColumn<Contaspagar, Double> colValorPag;
    @FXML
    private TableColumn<Contaspagar, Date> colVencimentoPag;
    @FXML
    private TableColumn<Contaspagar, Date> colPagPag;
    @FXML
    private TableColumn<Contaspagar, Character> colTipoPag;
    @FXML
    private TableColumn<Contaspagar, String> colContatoPag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*fadeout();
        //setParametros();
        initColumn();
        
        /*try {
            if(!CarregaTabela()){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Impossível Carregar Contas");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CodAux = 0;*/
    }    
    
    private void initColumn() {
        
        colCod.setCellValueFactory(new PropertyValueFactory("pag_cod"));
        colContato.setCellValueFactory(new PropertyValueFactory("pag_contato"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory("for_cnpj"));
        colParcela.setCellValueFactory(new PropertyValueFactory("pag_parcela"));
        colPag.setCellValueFactory(new PropertyValueFactory("pag_dtpagamento"));
        colValor.setCellValueFactory(new PropertyValueFactory("pag_valor"));
        colTipo.setCellValueFactory(new PropertyValueFactory("pag_tipo"));
        colVencimento.setCellValueFactory(new PropertyValueFactory("pag_dtvencimento"));
        
        colCodPag.setCellValueFactory(new PropertyValueFactory("pag_cod"));
        colContatoPag.setCellValueFactory(new PropertyValueFactory("pag_contato"));
        colFornecedorPag.setCellValueFactory(new PropertyValueFactory("for_cnpj"));
        colParcelaPag.setCellValueFactory(new PropertyValueFactory("pag_parcela"));
        colPagPag.setCellValueFactory(new PropertyValueFactory("pag_dtpagamento"));
        colValorPag.setCellValueFactory(new PropertyValueFactory("pag_valor"));
        colTipoPag.setCellValueFactory(new PropertyValueFactory("pag_tipo"));
        colVencimentoPag.setCellValueFactory(new PropertyValueFactory("pag_dtvencimento"));
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
        
        if(p.getCorprimaria() != null){
            
            pndados.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvContas.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvContasPag.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            pnprincipal.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            ckVencidos.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            ckVencidosPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txfiltroPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txfiltro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            dtPagamentos.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            dtPagamentosPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            btQuitar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btvoltar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btExtornar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btLimpar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btLimparPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btvoltarPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            lbobg.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            lbFornecedores.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            lbDtPagamentoPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            lbFornecedoresPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
        }
        if(p.getCorfonte() != null){
           
            ckVencidos.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            ckVencidosPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            txfiltroPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            txfiltro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            dtPagamentos.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            dtPagamentosPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            btQuitar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btvoltar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btExtornar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btLimpar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btLimparPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btvoltarPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            lbobg.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbFornecedores.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbDtPagamentoPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbFornecedoresPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void LimpaTelaNaoPag() {
        ckVencidos.setSelected(false);
        dtPagamentos.getEditor().clear();
        txfiltro.clear();
    }
    
    private void LimpaTelaPag() {
        
        ckVencidosPag.setSelected(false);
        dtPagamentosPag.getEditor().clear();
        txfiltroPag.clear();
    }
    
    private boolean CarregaTabela() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvContas.getItems().clear();
            DALContaspagar dal = new DALContaspagar();
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagarNaoPagas());
            tvContas.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaPag() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvContas.getItems().clear();
            DALContaspagar dal = new DALContaspagar();
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagarPagas());
            tvContasPag.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaFornecedor() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvContas.getItems().clear();
            DALContaspagar dal = new DALContaspagar();
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagarFornecedor(txfiltro.getText()));
            tvContas.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaFornecedorPag() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvContas.getItems().clear();
            DALContaspagar dal = new DALContaspagar();
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagarFornecedor(txfiltro.getText()));
            tvContasPag.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
        Stage stage = (Stage) btvoltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkbtQuitar(ActionEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALContaspagar dal = new DALContaspagar();
        if(dal.QuitarConta(tvContas.getSelectionModel().getSelectedItem().getPag_cod())){
            LimpaTelaNaoPag();
            if(!CarregaTabela()){
                a.setContentText("Impossível Carregar Contas");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
                }
            }
    }


    @FXML
    private void clkTabela(MouseEvent event) {
    }

    @FXML
    private void clkTFiltro(KeyEvent event) {
    }

    @FXML
    private void LimparTelaNaoPag(ActionEvent event) {
        LimpaTelaNaoPag();
    }

    @FXML
    private void LimparTelaPag(ActionEvent event) {
        LimpaTelaPag();
    }

    @FXML
    private void clkbtExtornar(ActionEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALContaspagar dal = new DALContaspagar();
        if(dal.ExtornarConta(tvContasPag.getSelectionModel().getSelectedItem().getPag_cod())){
            LimpaTelaPag();
            if(!CarregaTabelaPag()){
                a.setContentText("Impossível Carregar Contas");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
                }
            }
    }

    @FXML
    private void clkckVencidosPag(ActionEvent event) {
    }
    
}
