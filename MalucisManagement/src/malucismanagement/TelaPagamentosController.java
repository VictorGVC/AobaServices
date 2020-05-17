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
import malucismanagement.db.dal.DALContaspagar;
import malucismanagement.db.dal.DALFornecedores;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Contaspagar;
import malucismanagement.db.entidades.Parametrizacao;

public class TelaPagamentosController implements Initializable {

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
    private JFXTextField txParcela;
    @FXML
    private JFXDatePicker dtVencimento;
    @FXML
    private Label lbobg;
    @FXML
    private JFXComboBox<String> cbTipo;
    @FXML
    private JFXTextField txContato;
    @FXML
    private JFXTextField txValor;
    @FXML
    private JFXComboBox<String> cbFornecedores;
    @FXML
    private Pane pnfiltros;
    @FXML
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
    private TableColumn<Contaspagar, Character> colStatus;
    @FXML
    private TableColumn<Contaspagar, Character> colTipo;
    @FXML
    private TableColumn<Contaspagar, String> colContato;
    @FXML
    private TableColumn<Contaspagar, Date> colVencimento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeout();
        setParametros();
        initColumn();
        
        try {
            CarregaCBFornecedores();
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
        
        adcProd(true);
        CodAux = 0;
    }    
    
    private void initColumn() {
        
        colCod.setCellValueFactory(new PropertyValueFactory("pag_cod"));
        colContato.setCellValueFactory(new PropertyValueFactory("pag_contato"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory("for_cnpj"));
        colParcela.setCellValueFactory(new PropertyValueFactory("pag_parcela"));
        colStatus.setCellValueFactory(new PropertyValueFactory("pag_status"));
        colValor.setCellValueFactory(new PropertyValueFactory("pag_valor"));
        colTipo.setCellValueFactory(new PropertyValueFactory("pag_tipo"));
        colVencimento.setCellValueFactory(new PropertyValueFactory("pag_dtvencimento"));
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
        }
        if(p.getCorsecundaria()!= null){
            
            pnprincipal.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            txContato.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbFornecedores.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbTipo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txParcela.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txValor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txfiltro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            dtVencimento.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            btalterar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btapagar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btcancelar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btconfirmar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btnovo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btvoltar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            lbobg.setStyle("-fx-font-family: " + p.getFonte()+ ";");
        }
        if(p.getCorfonte() != null){
           
            txContato.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            txParcela.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            cbTipo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            cbFornecedores.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            txValor.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            txfiltro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            dtVencimento.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            btalterar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btapagar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btconfirmar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btnovo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btvoltar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            lbobg.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void adcProd(boolean b) {
        
        txContato.setDisable(b);
        cbTipo.setDisable(b);
        cbFornecedores.setDisable(b);
        txParcela.setDisable(b);
        txValor.setDisable(b);
        txfiltro.setDisable(b);
        dtVencimento.setDisable(b);
        
        btnovo.setDisable(!b);
        btapagar.setDisable(!b);
        btalterar.setDisable(!b);
        
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btvoltar.setDisable(b);
    }
    
    private void LimpaTelaCadastro() {
        
        txContato.clear();
        dtVencimento.getEditor().clear();
        cbFornecedores.getSelectionModel().clearSelection();
        txParcela.clear();
        txValor.clear();
        txfiltro.clear();
        cbTipo.getSelectionModel().clearAndSelect(CodAux);
        
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
        Contaspagar linha = tvContas.getSelectionModel().getSelectedItem();
        CodAux = linha.getPag_cod();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(linha.getPag_dtvencimento()+"",formatter);
        dtVencimento.setValue(date);
        txContato.setText(""+linha.getPag_contato());
        txParcela.setText(""+linha.getPag_parcela());
        txValor.setText(""+linha.getPag_valor());
        int index = cbFornecedores.getItems().indexOf(linha.getFor_cnpj());
        cbFornecedores.getSelectionModel().select(index);
        index = cbTipo.getItems().indexOf(linha.getPag_tipo());
        cbTipo.getSelectionModel().select(index);
        adcProd(false);
    }

    @FXML
    private void clkBtApagar(ActionEvent event) throws SQLException {
        DALContaspagar dal = new DALContaspagar();
        Contaspagar linha = tvContas.getSelectionModel().getSelectedItem();
        Alert opcao = new Alert(Alert.AlertType.CONFIRMATION);
        opcao.setContentText("Você deseja remover esta conta");
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        opcao.getButtonTypes().setAll(btnSim, btnNao);
        Optional<ButtonType> result = opcao.showAndWait();
        
        if(result.get() == btnSim){
            if(dal.excluir(linha.getPag_cod()))
            {
                if(!CarregaTabela()){
                     Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setContentText("Impossível Carregar Contas");
                            a.setHeaderText("Alerta");
                            a.setTitle("Alerta");
                            a.showAndWait();
                        }
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Impossível Remover Contas");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
        }
    }
    
    private boolean CarregaTabela() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvContas.getItems().clear();
            DALContaspagar dal = new DALContaspagar();
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagar());
            tvContas.setItems(lista);
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
    
    private boolean CarregaTabelaTipo() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvContas.getItems().clear();
            DALContaspagar dal = new DALContaspagar();
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagarTipo(txfiltro.getText()));
            tvContas.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaStatus() throws SQLException {
        
        boolean executar = true;
       
        try {
            tvContas.getItems().clear();
            DALContaspagar dal = new DALContaspagar();
            ObservableList<Contaspagar> lista = FXCollections.observableArrayList(dal.getContapagarStatus(txfiltro.getText()));
            tvContas.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) throws SQLException {
        boolean aceito = true;
        LimpaCores();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Date hoje = new Date(System.currentTimeMillis());
        Date dt = (Date) Date.from(dtVencimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(txParcela.getText().isEmpty() || Integer.parseInt(txParcela.getText()) < 1)
        {
            txParcela.setStyle("-fx-background-color: red;");
            aceito = false;
            a.setContentText("Parcela Inválida");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txParcela.requestFocus();
        }
        else if( dt.after(hoje))
        {
            dtVencimento.setStyle("-fx-background-color: red;");
            aceito = false;
            a.setContentText("Data inválida");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            dtVencimento.requestFocus();
        }
        else if(cbTipo.getSelectionModel().getSelectedItem().compareTo("") == 0)
        {
            cbTipo.setStyle("-fx-background-color: red;");
            aceito = false;
            a.setContentText("Tipo Inválido");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbTipo.requestFocus();
        }
        else if(txValor.getText().isEmpty() || Double.parseDouble(txValor.getText()) <= 0)
        {
            txValor.setStyle("-fx-background-color: red;");
            aceito = false;
            a.setContentText("Valor Inválido");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txValor.requestFocus();
        }
        else if(cbFornecedores.getSelectionModel().getSelectedItem().compareTo("") == 0)
        {
            cbFornecedores.setStyle("-fx-background-color: red;");
            aceito = false;
            a.setContentText("Fornecedor Inválido");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbFornecedores.requestFocus();
        }
        if(aceito){
            DALFornecedores dlfor = new DALFornecedores();
            String forn = dlfor.getFornecedoresCNPJ(cbFornecedores.getSelectionModel().getSelectedItem()).get(0).getFor_nome();
            Contaspagar cp = new Contaspagar(Integer.parseInt(txParcela.getText()),Double.parseDouble(txValor.getText()),
                    txContato.getText(),forn,dt,cbTipo.getSelectionModel().getSelectedItem().charAt(0),'A');
            DALContaspagar dal = new DALContaspagar();
            if(flag)
            {
                if(dal.gravar(cp))
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
                cp.setPag_cod(CodAux);
                if(dal.alterar(cp)){
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
        LimpaTelaCadastro();
        adcProd(true);
    }
    
    private void LimpaCores() {
        
        cbTipo.setStyle("-fx-background-color: none;");
        cbFornecedores.setStyle("-fx-background-color: none;");
        dtVencimento.setStyle("-fx-background-color: none;");
        txContato.setStyle("-fx-background-color: none;");
        txParcela.setStyle("-fx-background-color: none;");
        txValor.setStyle("-fx-background-color: none;");
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
        Stage stage = (Stage) btvoltar.getScene().getWindow();
        stage.close();
    }

    private void CarregaCBFiltro() {
        
        ObservableList<String> itens;
        itens = FXCollections.observableArrayList();
        
        itens.add("Filtro");
        itens.add("Fornecedor");
        itens.add("Tipo");
        itens.add("Status");
        
        cbcategoria.setItems(itens);
    }
    
    @FXML
    private void clkTFiltro(KeyEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALContaspagar dal = new DALContaspagar();
        if(cbcategoria.getSelectionModel().getSelectedItem() == "Fornecedor")
        {
            if(!CarregaTabelaFornecedor()){
                    a.setContentText("Impossível Filtrar Conta");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
        }
        else if(cbcategoria.getSelectionModel().getSelectedItem() == "Tipo")
            {
                if(!CarregaTabelaTipo())
                {
                    a.setContentText("Impossível Filtrar Conta");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
        else if(cbcategoria.getSelectionModel().getSelectedItem() == "Status")
            {
                if(!CarregaTabelaStatus())
                {
                    a.setContentText("Impossível Filtrar Conta");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
        else if(cbcategoria.getSelectionModel().getSelectedItem() == "Filtro")
            CarregaTabela();
    }

    @FXML
    private void clkbtQuitar(ActionEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALContaspagar dal = new DALContaspagar();
        if(dal.QuitarConta(tvContas.getSelectionModel().getSelectedItem().getPag_cod())){
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
    }

    @FXML
    private void clkTabela(MouseEvent event) {
    }
    
}
