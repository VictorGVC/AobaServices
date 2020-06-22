package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import javafx.scene.input.InputMethodEvent;
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
    private TableColumn<Contaspagar, String> colVencimento;
    @FXML
    private CheckBox ckVencidos;
    @FXML
    private Label lbFornecedores;
    @FXML
    private JFXButton btLimpar;
    @FXML
    private TableColumn<Contaspagar, String> colPag;
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
    private TableColumn<Contaspagar, String> colVencimentoPag;
    @FXML
    private TableColumn<Contaspagar, String> colPagPag;
    @FXML
    private TableColumn<Contaspagar, Character> colTipoPag;
    @FXML
    private TableColumn<Contaspagar, String> colContatoPag;
    @FXML
    private DatePicker dtVencimentoPag;
    @FXML
    private Label lbDTVencimentoPag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeout();
        //setParametros();
        initColumn();
        
        try {
            if(!CarregaTabela()){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Impossível Carregar Contas não pagas");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(!CarregaTabelaPag()){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Impossível Carregar Contas pagas");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CodAux = 0;
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
            pnbotoes.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            pnbotoes1.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            pndados1.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            pnpesquisa1.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            pnpesquisa.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvContas.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvContasPag.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            pnprincipal.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            ckVencidos.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            dtVencimentoPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
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
            lbDTVencimentoPag.setStyle("-fx-font-family: " + p.getFonte()+ ";");
        }
        if(p.getCorfonte() != null){
           
            ckVencidos.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            dtVencimentoPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
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
            lbDTVencimentoPag.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void LimpaTelaNaoPag() throws SQLException {
        dtPagamentos.getEditor().clear();
        ckVencidos.setSelected(false);
        txfiltro.clear();
        CarregaTabela();
    }
    
    private void LimpaTelaPag() throws SQLException {
        dtVencimentoPag.getEditor().clear();
        dtPagamentosPag.getEditor().clear();
        txfiltroPag.clear();
        CarregaTabelaPag();
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
            tvContasPag.getItems().clear();
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
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagarFornecedorNaoPag(txfiltro.getText()));
            tvContas.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaFornecedorPag() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvContasPag.getItems().clear();
            DALContaspagar dal = new DALContaspagar();
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagarFornecedorPag(txfiltroPag.getText()));
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
                    a.setContentText("Impossível Carregar Contas Não Pagas");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            if(!CarregaTabelaPag()){
                    a.setContentText("Impossível Carregar Contas Pagas");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
    }

    public void Filtrar() throws SQLException{
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(txfiltro.getText().compareTo("") > 0)
        {
            if(!CarregaTabelaFornecedor()){
                    a.setContentText("Impossível Filtrar Contas Não Pagas");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
        }
        else 
            CarregaTabela();
    }
    @FXML
    private void clkTFiltro(KeyEvent event) throws SQLException {
       Filtrar();
    }

    @FXML
    private void LimparTelaNaoPag(ActionEvent event) throws SQLException {
        LimpaTelaNaoPag();
    }

    @FXML
    private void LimparTelaPag(ActionEvent event) throws SQLException {
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
            if(!CarregaTabela()){
                    a.setContentText("Impossível Carregar Contas");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
    }


    @FXML
    private void clickCKVenc(ActionEvent event) throws SQLException, ParseException {
        Calendar hoje = Calendar.getInstance();
        String mes;
        if(hoje.get(hoje.MONTH)+1 > 9)
            mes = ""+(hoje.get(hoje.MONTH)+1);
        else
            mes = "0"+(hoje.get(hoje.MONTH)+1);
        String today = hoje.get(hoje.YEAR)+"-"+mes+"-"+hoje.get(hoje.DAY_OF_MONTH);
        if(ckVencidos.isSelected()){
            ObservableList<Contaspagar> lista = tvContas.getItems();
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getPag_dtvencimento().compareTo(today) > 0 ){
                    lista.remove(i);
                    i--;
                }
            }
            try {
                tvContas.setItems(lista);
            } catch (Exception e) {
                System.out.println(e);
                }
        }
        else{
            CarregaTabela();
            Filtrar();
        }     
    }

    @FXML
    private void clkDTVencimento(ActionEvent event) throws SQLException {
            LocalDate localDate = dtPagamentos.getValue();
            ObservableList<Contaspagar> lista = tvContas.getItems();
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getPag_dtvencimento().compareTo(localDate+"") != 0 ){
                    lista.remove(i);
                    i--;
                }
            }
            try {
                tvContas.setItems(lista);
            } catch (Exception e) {
                System.out.println(e);
                }
    }
    
    public void FiltrarPag() throws SQLException{
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(txfiltroPag.getText().compareTo("") > 0)
        {
            if(!CarregaTabelaFornecedorPag()){
                    a.setContentText("Impossível Filtrar Contas Pagas");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
        }
        else 
            CarregaTabelaPag();
    }

    @FXML
    private void clkTFiltroPag(KeyEvent event) throws SQLException {
        FiltrarPag();
    }

    @FXML
    private void clkDTPagamento(ActionEvent event) {
        LocalDate localDate = dtPagamentosPag.getValue();
            ObservableList<Contaspagar> lista = tvContasPag.getItems();
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getPag_dtpagamento().compareTo(localDate+"") != 0 ){
                    lista.remove(i);
                    i--;
                }
            }
            try {
                tvContasPag.setItems(lista);
            } catch (Exception e) {
                System.out.println(e);
                }
    }

    @FXML
    private void clkDTVencimentoPag(ActionEvent event) {
        LocalDate localDate = dtVencimentoPag.getValue();
            ObservableList<Contaspagar> lista = tvContasPag.getItems();
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getPag_dtvencimento().compareTo(localDate+"") != 0 ){
                    lista.remove(i);
                    i--;
                }
            }
            try {
                tvContasPag.setItems(lista);
            } catch (Exception e) {
                System.out.println(e);
                }
    }


    
}
