package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import malucismanagement.db.dal.DALCategoriaProduto;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.dal.DALProduto;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.db.entidades.Produto;
import malucismanagement.util.MaskFieldUtil;

public class TelaProdutoController implements Initializable {
    
    int CodAux;
    @FXML
    private JFXButton btSalvarProduto;
    @FXML
    private JFXTextField txNomeProduto;
    @FXML
    private JFXTextField txPreco;
    @FXML
    private JFXTextField txQtdEstoque;
    @FXML
    private JFXButton btCancelarProduto;
    @FXML
    private JFXComboBox<String> cbCategoria;
    @FXML
    private JFXButton btEditarFornecedor;
    private JFXButton btCancelarFiltro;
    private JFXButton btFiltrarProduto;
    @FXML
    private JFXComboBox<String> cbFiltro;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private JFXButton btRemoverProduto;
    @FXML
    private TableView<Produto> tvProdutos;
    
    Boolean flag = true; 
    @FXML
    private TableColumn<Produto, String> ColProduto;
    @FXML
    private TableColumn<Produto, Double> ColPreco;
    @FXML
    private TableColumn<Produto, Integer> ColQtd;
    @FXML
    private TableColumn<Produto, String> ColCat;
    @FXML
    private TableColumn<Produto, Integer> ColCod;
    @FXML
    private Button btExit;
    @FXML
    private AnchorPane pnprincipal;
    @FXML
    private AnchorPane pnsecundario;
    @FXML
    private JFXButton btaddProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.monetaryField(txPreco);
        MaskFieldUtil.numericField(txQtdEstoque);
        MaskFieldUtil.maxField(txNomeProduto, 50);
        initColumn();
        try {
            CarregaCBCategoria();
            if(!CarregaTabela()){
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("Impossível Carregar Fornecedores");
                        a.setHeaderText("Alerta");
                        a.setTitle("Alerta");
                        a.showAndWait();
                    }
        } catch (SQLException ex) {
            Logger.getLogger(TelaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        adcProd(true);
        CarregaCBFiltro();
        CodAux = 0;
    }    
    
    private void CarregaCBCategoria(){
        tvProdutos.getItems().clear();
        DALCategoriaProduto dal = new DALCategoriaProduto();
        ObservableList<String> lista = FXCollections.observableArrayList(dal.getCategoriaProduto());
        cbCategoria.setItems(lista);
    }

    @FXML
    private void SalvarProduto(ActionEvent event) throws SQLException {
        DALCategoriaProduto dalct = new DALCategoriaProduto();
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txNomeProduto.getText().isEmpty())
        {
            a.setContentText("Nome deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txNomeProduto.requestFocus();
        }
        else if(txPreco.getText().isEmpty() || txPreco.getLength() > 6)
        {
            if(txPreco.getLength() > 6)
                a.setContentText("Preço Inválido");
            else
                a.setContentText("Preço deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txPreco.requestFocus();
        }
        else if(txQtdEstoque.getText().isEmpty())
        {
            a.setContentText("Quantidade deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txQtdEstoque.requestFocus();
        }
        else if(cbCategoria.getSelectionModel().isEmpty())
        {
            a.setContentText("Categoria deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbCategoria.requestFocus();
        }
        else{
            String cat = cbCategoria.getValue().toString();
            Produto p = new Produto(Integer.parseInt(txQtdEstoque.getText()),cat,Double.parseDouble(txPreco.getText().replace(",", ".")),
        txNomeProduto.getText());
            DALProduto dal = new DALProduto();
            if(flag)
            {
                if(dal.gravar(p))
                {
                    LimpaTelaCadastro();
                    if(!CarregaTabela()){
                        a.setContentText("Impossível Carregar Fornecedores");
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
                p.setPro_cod(CodAux);
                if(dal.alterar(p)){
                    LimpaTelaCadastro();
                    if(!CarregaTabela()){
                        a.setContentText("Impossível Carregar Fornecedores");
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
        
    }
    
    private void LimpaTelaCadastro(){
        txNomeProduto.clear();
        txPreco.clear();
        txQtdEstoque.clear();
        cbCategoria.getSelectionModel().clearSelection();
        
        flag = true;
    }
    
    private void LimpaTelaTabela(){
        txPesquisar.clear();
        cbFiltro.getSelectionModel().clearSelection();
    }
    
    private void initColumn(){
        ColCat.setCellValueFactory(new PropertyValueFactory("cat_cod"));
        ColCod.setCellValueFactory(new PropertyValueFactory("pro_cod"));
        ColPreco.setCellValueFactory(new PropertyValueFactory("pro_preco"));
        ColProduto.setCellValueFactory(new PropertyValueFactory("pro_nome"));
        ColQtd.setCellValueFactory(new PropertyValueFactory("pro_quantidade"));
    }
    
    private boolean CarregaTabela() throws SQLException{
        boolean executar = true;
       
        try {
            tvProdutos.getItems().clear();
            DALProduto dal = new DALProduto();
            ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutos());
            tvProdutos.setItems(lista);
            } catch (Exception e) {
            executar = false;
        }
        
        return executar;
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
            
            txNomeProduto.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txPesquisar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txPreco.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            txQtdEstoque.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbCategoria.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            cbFiltro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
            btCancelarFiltro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btCancelarProduto.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btEditarFornecedor.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btFiltrarProduto.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btRemoverProduto.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            btSalvarProduto.setStyle("-fx-font-family: " + p.getFonte()+ ";");
            
        }
        if(p.getCorfonte() != null){
           
            txNomeProduto.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txPreco.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txPreco.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            txPesquisar.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            cbCategoria.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            cbFiltro.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            
            btCancelarFiltro.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btCancelarProduto.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btEditarFornecedor.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btFiltrarProduto.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btRemoverProduto.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
            btSalvarProduto.setStyle("-fx-fill: " + p.getCorfonte()+ ";");
        }
    } 
    
    private boolean CarregaTabelaProduto(){
        boolean executar = true;
       
        try {
            tvProdutos.getItems().clear();
            DALProduto dal = new DALProduto();
            ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutosNome(txPesquisar.getText()));
            tvProdutos.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaPreco(){
        boolean executar = true;
       
        try {
            tvProdutos.getItems().clear();
            DALProduto dal = new DALProduto();
            ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutosPreco(Double.parseDouble(txPesquisar.getText().replace(",", "."))));
            tvProdutos.setItems(lista); 
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaQtd(){
        boolean executar = true;
       
        try {
            tvProdutos.getItems().clear();
            DALProduto dal = new DALProduto();
            ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutosQtd(Integer.parseInt(txPesquisar.getText())));
            tvProdutos.setItems(lista); 
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private boolean CarregaTabelaCategoria() throws SQLException{
        boolean executar = true;
       
        try {
            tvProdutos.getItems().clear();
            DALProduto dal = new DALProduto();
            ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutosCategoria(txPesquisar.getText()));
            tvProdutos.setItems(lista);
        } catch (Exception e) {
            executar = false;
        }
        
        return executar;
    }
    
    private void CarregaCBFiltro(){
        ObservableList<String> itens;
        itens = FXCollections.observableArrayList();
        
        itens.add("Filtro");
        itens.add("Produto");
        itens.add("Preço");
        itens.add("Quantidade");
        itens.add("Categoria");
        
        cbFiltro.setItems(itens);
    }

    @FXML
    private void CancelarProduto(ActionEvent event) {
        LimpaTelaCadastro();
        adcProd(true);
    }

    @FXML
    private void EditarProduto(ActionEvent event) {
        flag = false;
        Produto linha = tvProdutos.getSelectionModel().getSelectedItem();
        CodAux = linha.getPro_cod();
        txNomeProduto.setText(linha.getPro_nome());
        txPreco.setText(""+linha.getPro_preco());
        txQtdEstoque.setText(""+linha.getPro_quantidade());
        int index = cbCategoria.getItems().indexOf(linha.getCat_cod());
        cbCategoria.getSelectionModel().select(index);
        adcProd(false);
    }

    private void CancelarFiltro(ActionEvent event) throws SQLException {
        LimpaTelaTabela();
        if(!CarregaTabela()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Impossível Carregar Produtos");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
    }

    @FXML
    private void RemoverProduto(ActionEvent event) throws SQLException {
        DALProduto dal = new DALProduto();
        Produto linha = tvProdutos.getSelectionModel().getSelectedItem();
        Alert opcao = new Alert(Alert.AlertType.CONFIRMATION);
        opcao.setContentText("Você deseja remover este fornecedor?");
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        opcao.getButtonTypes().setAll(btnSim, btnNao);
        Optional<ButtonType> result = opcao.showAndWait();
        
        if(result.get() == btnSim){
            if(dal.excluir(linha.getPro_cod()))
            {
                if(!CarregaTabela()){
                     Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setContentText("Impossível Carregar Fornecedores");
                            a.setHeaderText("Alerta");
                            a.setTitle("Alerta");
                            a.showAndWait();
                        }
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Impossível Remover Produto");
                a.setHeaderText("Alerta");
                a.setTitle("Alerta");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void clkbtExit(ActionEvent event) {
        
        Stage stage = (Stage) btExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void FiltrarProduto(KeyEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DALProduto dal = new DALProduto();
        if(cbFiltro.getSelectionModel().getSelectedItem() == "Produto")
        {
            if(!CarregaTabelaProduto()){
                    a.setContentText("Impossível Filtrar Fornecedor");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
        }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Preço")
            {
                if(!CarregaTabelaPreco())
                {
                    a.setContentText("Impossível Filtrar Fornecedor");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Quantidade")
            {
                if(!CarregaTabelaQtd())
                {
                    a.setContentText("Impossível Filtrar Fornecedor");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Categoria")
            {
                if(!CarregaTabelaCategoria())
                {
                    a.setContentText("Impossível Filtrar Fornecedor");
                    a.setHeaderText("Alerta");
                    a.setTitle("Alerta");
                    a.showAndWait();
                }
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Filtro")
            CarregaTabela();
            
    }
    
    private void adcProd(boolean b){
        txNomeProduto.setDisable(b);
        txPreco.setDisable(b);
        txQtdEstoque.setDisable(b);
        cbCategoria.setDisable(b);
        
        btSalvarProduto.setDisable(!b);
        btRemoverProduto.setDisable(!b);
        btEditarFornecedor.setDisable(!b);
        
        btaddProduto.setDisable(b);
        btCancelarProduto.setDisable(b);        
    }

    @FXML
    private void NovoProd(ActionEvent event) {
        adcProd(false);
    }
}