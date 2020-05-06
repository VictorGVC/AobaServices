package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import malucismanagement.db.dal.DALFornecedores;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Fornecedor;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.MaskFieldUtil;

public class TelaFornecedorController implements Initializable {

    @FXML
    private JFXButton btSalvarFonecedor;
    @FXML
    private JFXTextField txNomeForcenedor;
    @FXML
    private JFXTextField txCNPJ;
    @FXML
    private JFXTextField txIE;
    @FXML
    private JFXTextField txTelefone;
    @FXML
    private JFXTextField txEmail;
    @FXML
    private JFXTextField txTipo;
    @FXML
    private JFXButton btCancelarFornecedor;
    @FXML
    private JFXButton btEditarFornecedor;
    @FXML
    private JFXButton btCancelarFiltro;
    @FXML
    private JFXButton btFiltrarFornecedor;
    @FXML
    private JFXComboBox<String> cbFiltro;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private JFXButton btRemoverFornecedor;
    @FXML
    private TableView<Fornecedor> tvFornecedores;
    

    Boolean flag = true; 
    @FXML
    private TableColumn<Fornecedor, String> ColFornecedor;
    @FXML
    private TableColumn<Fornecedor, String>ColCNPJ;
    @FXML
    private TableColumn<Fornecedor, String> ColIE;
    @FXML
    private TableColumn<Fornecedor, String> ColTelefone;
    @FXML
    private TableColumn<Fornecedor, String> ColEmail;
    @FXML
    private TableColumn<Fornecedor, String> ColTipo;
    @FXML
    private Button btExit;
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private AnchorPane pnsecundario;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.cnpjField(txCNPJ);
        MaskFieldUtil.foneField(txTelefone);
        MaskFieldUtil.maxField(txIE, 13);
        MaskFieldUtil.maxField(txCNPJ, 19);
        MaskFieldUtil.maxField(txNomeForcenedor, 50);
        MaskFieldUtil.maxField(txTipo, 20);
        MaskFieldUtil.numericField(txIE);
        initColumn();
        if(!CarregaTabelaFornecedor()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Impossível Carregar Fornecedores");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        CarregaCBFiltro();
        LimpaTelaCadastro();
    }
    
    private void initColumn(){
        ColCNPJ.setCellValueFactory(new PropertyValueFactory("for_cnpj"));
        ColEmail.setCellValueFactory(new PropertyValueFactory("for_email"));
        ColFornecedor.setCellValueFactory(new PropertyValueFactory("for_nome"));
        ColIE.setCellValueFactory(new PropertyValueFactory("for_inscestadual"));
        ColTelefone.setCellValueFactory(new PropertyValueFactory("for_telefone"));
        ColTipo.setCellValueFactory(new PropertyValueFactory("for_tipo"));
    }
    
    private void setParametros() {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p.getCorprimaria() != null){
            
            pnprincipal.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            pnsecundario.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            
        }
        if(p.getFonte() != null){
            
            txCNPJ.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txEmail.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txIE.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txNomeForcenedor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txPesquisar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txTelefone.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txTipo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbFiltro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            btCancelarFiltro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btCancelarFornecedor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btEditarFornecedor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btFiltrarFornecedor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btRemoverFornecedor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btSalvarFonecedor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
        }
        if(p.getCorfonte() != null){
           
            txCNPJ.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txEmail.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txIE.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txNomeForcenedor.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txPesquisar.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txTelefone.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txTipo.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            cbFiltro.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            
            btCancelarFiltro.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btCancelarFornecedor.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btEditarFornecedor.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btFiltrarFornecedor.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btRemoverFornecedor.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btSalvarFonecedor.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
        }
    } 

    private void LimpaTelaCadastro(){
        txCNPJ.clear();
        txEmail.clear();
        txIE.clear();
        txNomeForcenedor.clear();
        txTelefone.clear();
        txTipo.clear();
        
        flag = true;
    }
    
    private void LimpaTelaTabela(){
        txPesquisar.clear();
        cbFiltro.getSelectionModel().clearSelection();
    }
    
    private void CarregaCBFiltro(){
        ObservableList<String> itens;
        itens = FXCollections.observableArrayList();

        itens.add("Fornecedor");
        itens.add("CNPJ");
        itens.add("IE");
        itens.add("Telefone");
        itens.add("Email");
        itens.add("Tipo");
        
        cbFiltro.setItems(itens);
    }

    @FXML
    private void SalvarFornecedor(ActionEvent event) {
        Fornecedor novo = new Fornecedor(txTipo.getText(),txNomeForcenedor.getText(),txEmail.getText(),txIE.getText(),
                txCNPJ.getText(),txTelefone.getText());
        String auxCNPJ = txCNPJ.getText();
        DALFornecedores dal = new DALFornecedores();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
         if(txNomeForcenedor.getText().isEmpty())
        {
            a.setContentText("Nome deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txNomeForcenedor.requestFocus();
        }
        else if(txCNPJ.getText().isEmpty() || txCNPJ.getLength() != 18)
        {
            a.setContentText("CNPJ deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txCNPJ.requestFocus();
        } 
        else if(txIE.getText().isEmpty() || txIE.getLength() != 13)
        {
            a.setContentText("Inscrição Estadual deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txIE.requestFocus();
        }
        else if(txEmail.getText().isEmpty() || !txEmail.getText().contains("@"))
        {
            a.setContentText("EMAIL deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txEmail.requestFocus();
        } 
        else{
            if(flag)
            {
                if(dal.gravar(novo)){
                    LimpaTelaCadastro();
                    if(!CarregaTabelaFornecedor()){
                        a.setContentText("Impossível Carregar Fornecedores");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
                }
                else
                {
                    a.setContentText("Impossível Salvar novo Fornecedor");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }  
            else
            {
                if(dal.alterar(novo,auxCNPJ)){
                    LimpaTelaCadastro();
                    if(!CarregaTabelaFornecedor()){
                        a.setContentText("Impossível Carregar Fornecedores");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
                    flag = true;
                }
                else{
                    a.setContentText("Impossível Editar Fornecedor");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
            
        }
    }

    @FXML
    private void CancelarFornecedor(ActionEvent event) {
        LimpaTelaCadastro();
    }

    @FXML
    private void EditarFornecedor(ActionEvent event) {
        flag = false;
        Fornecedor linha = tvFornecedores.getSelectionModel().getSelectedItem();
        txNomeForcenedor.setText(linha.getFor_nome());
        txCNPJ.setText(""+linha.getFor_cnpj());
        txEmail.setText(""+linha.getFor_email());
        txIE.setText(""+linha.getFor_inscestadual());
        txTelefone.setText(""+linha.getFor_telefone());
        txTipo.setText(""+linha.getFor_tipo());
    }

    @FXML
    private void CancelarFiltro(ActionEvent event) {
        LimpaTelaTabela();
        if(!CarregaTabelaFornecedor()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Impossível Filtrar Fornecedor");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
    }
    
    private boolean CarregaTabelaFornecedor(){
        boolean executar = true;
       
        try {
            tvFornecedores.getItems().clear();
            DALFornecedores dal = new DALFornecedores();
            ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedores());
            tvFornecedores.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaFornecedorNome(){
        boolean executar = true;
       
        try {
                tvFornecedores.getItems().clear();
                DALFornecedores dal = new DALFornecedores();
                ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresNome(txPesquisar.getText()));
                tvFornecedores.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaCNPJ(){
        boolean executar = true;
        
        try {
            tvFornecedores.getItems().clear();
            DALFornecedores dal = new DALFornecedores();
            ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresCNPJ(txPesquisar.getText()));
            tvFornecedores.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaIE(){
        boolean executar = true;
        
        try {
            tvFornecedores.getItems().clear();
            DALFornecedores dal = new DALFornecedores();
            ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresIE(txPesquisar.getText()));
            tvFornecedores.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaTelefone(){
        boolean executar = true;
        
        try {
            tvFornecedores.getItems().clear();
            DALFornecedores dal = new DALFornecedores();
            ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresTelefone(txPesquisar.getText()));
            tvFornecedores.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaEmail(){
        boolean executar = true;
        
        try {
            tvFornecedores.getItems().clear();
            DALFornecedores dal = new DALFornecedores();
            ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresEmail(txPesquisar.getText()));
            tvFornecedores.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaTipo(){
        boolean executar = true;
        
        try {
            tvFornecedores.getItems().clear();
            DALFornecedores dal = new DALFornecedores();
            ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresTipo(txPesquisar.getText()));
            tvFornecedores.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }

    @FXML
    private void FiltrarFornecedor(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALFornecedores dal = new DALFornecedores();
        if(cbFiltro.getSelectionModel().getSelectedItem() == "Fornecedor")
        {
            if(CarregaTabelaFornecedorNome())
                LimpaTelaTabela();
            else{
                    a.setContentText("Impossível Filtrar Fornecedor");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }            
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "CNPJ")
            {
                if(CarregaTabelaCNPJ())
                    LimpaTelaTabela();
                else{
                        a.setContentText("Impossível Filtrar Fornecedor");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "IE")
            {
                if(CarregaTabelaIE())
                    LimpaTelaTabela();
                else{
                        a.setContentText("Impossível Filtrar Fornecedor");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Telefone")
            {
                if(CarregaTabelaTelefone())
                    LimpaTelaTabela();
                else{
                        a.setContentText("Impossível Filtrar Fornecedor");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Email")
            {
                if(CarregaTabelaEmail())
                    LimpaTelaTabela();
                else{
                        a.setContentText("Impossível Filtrar Fornecedor");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Tipo")
            {
                if(CarregaTabelaTipo())
                    LimpaTelaTabela();
                else{
                        a.setContentText("Impossível Filtrar Fornecedor");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
            }
    }

    @FXML
    private void RemoverFornecedor(ActionEvent event) {
        DALFornecedores dal = new DALFornecedores();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Fornecedor linha = tvFornecedores.getSelectionModel().getSelectedItem();
        if(dal.excluir(linha.getFor_cnpj()))
        {
           if(!CarregaTabelaFornecedor()){
                a.setContentText("Impossível Carregar Fornecedores");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
                } 
        }
        else{
                a.setContentText("Impossível Remover Fornecedor");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
    }

    @FXML
    private void clkbtExit(ActionEvent event) {
        Stage stage = (Stage) btExit.getScene().getWindow();
        stage.close();
    }
    
}
