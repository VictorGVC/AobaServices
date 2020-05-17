package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
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
import malucismanagement.db.dal.DALCompras;
import malucismanagement.db.dal.DALFornecedores;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.db.entidades.Compras;
import malucismanagement.db.entidades.Fornecedor;
import malucismanagement.util.MaskFieldUtil;

public class TelaCompraProdutoController implements Initializable {

    int CodAux;
    Boolean flag = true; 
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private JFXButton btvoltar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXDatePicker dtCompra;
    @FXML
    private Label lbobg;
    @FXML
    private JFXComboBox<String> cbFornecedores;
    @FXML
    private Pane pnfiltros;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField txTotal;
    private JFXComboBox<String> cbCategoria;
    @FXML
    private JFXTextField txFiltro;
    private JFXButton cbAddItens;
    @FXML
    private TableColumn<Compras, Integer> ColCod;
    @FXML
    private TableColumn<Compras, Date> ColData;
    @FXML
    private TableColumn<Compras, String> ColFornecedor;
    @FXML
    private TableColumn<Compras, Double> ColTotal;
    @FXML
    private TableView<Compras> tvCompra;
    @FXML
    private JFXButton btAddItens;
    @FXML
    private Label lbForncedor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeout();
        setParametros();
        initColumn();
        
        try {
            CarregaCBFornecedores();
            if(!CarregaTabela()){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Impossível Carregar Compras");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        adcProd(true);
        CodAux = 0;
    }
    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }    

    private void initColumn() {
        
        ColCod.setCellValueFactory(new PropertyValueFactory("com_cod"));
        ColData.setCellValueFactory(new PropertyValueFactory("com_dtcompra"));
        ColFornecedor.setCellValueFactory(new PropertyValueFactory("for_cnpj"));
        ColTotal.setCellValueFactory(new PropertyValueFactory("com_total"));
    }
    
    private void setParametros() {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p.getCorprimaria() != null){
            
            pndados.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvCompra.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            pnprincipal.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            txTotal.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbAddItens.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbCategoria.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbFornecedores.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            dtCompra.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            btalterar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btapagar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btcancelar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btconfirmar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btnovo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btvoltar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            lbobg.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            lbForncedor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
        }
        if(p.getCorfonte() != null){
           
            txTotal.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            cbAddItens.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            cbCategoria.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            cbFornecedores.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            dtCompra.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            btalterar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btapagar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btconfirmar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btnovo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btvoltar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            lbForncedor.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            lbobg.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void adcProd(boolean b) {
        
        txTotal.setDisable(b);
        cbAddItens.setDisable(b);
        cbFornecedores.setDisable(b);
        dtCompra.setDisable(b);
        
        btnovo.setDisable(!b);
        btapagar.setDisable(!b);
        btalterar.setDisable(!b);
        
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btvoltar.setDisable(b);
    }
    
    private void LimpaTelaCadastro() {
        
        txTotal.clear();
        dtCompra.getEditor().clear();
        cbFornecedores.getSelectionModel().clearSelection();
        
        flag = true;
    }
    
    private void CarregaCBFornecedores(){
        
        cbFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<String> lista = FXCollections.observableArrayList(dal.getNomesFornecedores());
        cbFornecedores.setItems(lista);
    }
    
    @FXML
    private void clkBtNovo(ActionEvent event) {
        adcProd(false);
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {
        flag = false;
        Compras linha = tvCompra.getSelectionModel().getSelectedItem();
        CodAux = linha.getCom_cod();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(linha.getCom_dtcompra()+"",formatter);
        dtCompra.setValue(date);
        txTotal.setText(""+linha.getCom_total());
        int index = cbFornecedores.getItems().indexOf(linha.getFor_cnpj());
        cbCategoria.getSelectionModel().select(index);
        adcProd(false);
    }

    @FXML
    private void clkBtApagar(ActionEvent event) throws SQLException {
        DALCompras dal = new DALCompras();
        Compras linha = tvCompra.getSelectionModel().getSelectedItem();
        Alert opcao = new Alert(Alert.AlertType.CONFIRMATION);
        opcao.setContentText("Você deseja remover esta compra?");
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        opcao.getButtonTypes().setAll(btnSim, btnNao);
        Optional<ButtonType> result = opcao.showAndWait();
        
        if(result.get() == btnSim){
            if(dal.excluir(linha.getCom_cod()))
            {
                if(!CarregaTabela()){
                     Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setContentText("Impossível Carregar Compras");
                            a.setHeaderText("Alerta");
                            a.setTitle("Alerta");
                            a.showAndWait();
                        }
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Impossível Remover Compras");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) throws SQLException {
        boolean aceito = true;
        LimpaCB();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Date hoje = new Date(System.currentTimeMillis());
        Date dt = (Date) Date.from(dtCompra.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if( dt.after(hoje))
        {
            dtCompra.setStyle("-fx-background-color: red;");
            aceito = false;
            a.setContentText("Data inválida");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            dtCompra.requestFocus();
        }
        else if(cbFornecedores.getSelectionModel().getSelectedItem().compareTo("") == 0)
        {
            cbFornecedores.setStyle("-fx-background-color: red;");
            aceito = false;
            a.setContentText("Fornecedor deve ser informado inválido");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbFornecedores.requestFocus();
        }
        if(aceito){
            String cat = cbCategoria.getValue().toString();
            Compras p = new Compras(cbFornecedores.getSelectionModel().getSelectedItem(),Double.parseDouble(txTotal.getText()),dt);
            DALCompras dal = new DALCompras();
            if(flag)
            {
                if(dal.gravar(p))
                {
                    LimpaTelaCadastro();
                    if(!CarregaTabela()){
                        a.setContentText("Impossível Carregar Produtos");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
                }
                else
                {
                    a.setContentText("Impossível Salvar Produto");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
                adcProd(true);
            }
            else{
                p.setCom_cod(CodAux);
                if(dal.alterar(p)){
                    LimpaTelaCadastro();
                    if(!CarregaTabela()){
                        a.setContentText("Impossível Carregar Produtos");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
                    flag = true;
                }
                else
                {
                    a.setContentText("Impossível Editar Produto");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
                adcProd(true);
            }
        }
        LimpaTelaCadastro();
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        LimpaTelaCadastro();
        LimpaCB();
        adcProd(true);
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
        Stage stage = (Stage) btvoltar.getScene().getWindow();
        stage.close();
    }
    
    private void LimpaCB() {
        
        cbAddItens.setStyle("-fx-background-color: none;");
        cbFornecedores.setStyle("-fx-background-color: none;");
    }

    @FXML
    private void clkbtAddItem(ActionEvent event) {
    }
    
    private boolean CarregaTabela() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvCompra.getItems().clear();
            DALCompras dal = new DALCompras();
            ObservableList<Compras> lista = FXCollections.observableArrayList(dal.getCompras());
            tvCompra.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaFornecedor() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvCompra.getItems().clear();
            DALCompras dal = new DALCompras();
            ObservableList<Compras> lista = FXCollections.observableArrayList(dal.getComprasFornecedor(txFiltro.getText()));
            tvCompra.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }

    @FXML
    private void clkTFiltro(KeyEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALCompras dal = new DALCompras();
        if(!(txFiltro.getText().compareTo("") == 1))
            if(!CarregaTabelaFornecedor())
            {
                a.setContentText("Impossível Filtrar Compra");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
        else
           CarregaTabela();
    }

    @FXML
    private void clkTabela(MouseEvent event) {
    }
    
}
