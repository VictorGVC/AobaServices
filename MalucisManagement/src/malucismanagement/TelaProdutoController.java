package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import malucismanagement.db.dal.DALCategoriaProduto;
import malucismanagement.db.dal.DALProduto;
import malucismanagement.db.entidades.CategoriaProduto;
import malucismanagement.db.entidades.Produto;
import malucismanagement.util.MaskFieldUtil;

public class TelaProdutoController implements Initializable {

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
    @FXML
    private JFXButton btCancelarFiltro;
    @FXML
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.monetaryField(txPreco);
        MaskFieldUtil.numericField(txQtdEstoque);
        MaskFieldUtil.maxField(txNomeProduto, 50);
        initColumn();
        try {
            CarregaCBCategoria();
            CarregaTabela();
        } catch (SQLException ex) {
            Logger.getLogger(TelaProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CarregaCBFiltro();
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
        String cat = cbCategoria.getValue().toString();
        Produto p = new Produto(Integer.parseInt(txQtdEstoque.getText()),cat,Double.parseDouble(txPreco.getText().replace(",", ".")),
        txNomeProduto.getText());
        
        DALProduto dal = new DALProduto();
        if(flag)
        {
            if(dal.gravar(p))
                LimpaTelaCadastro();
        }
        else{
            if(dal.alterar(p)){
                LimpaTelaCadastro();
                flag = true;
            }
        }
        CarregaTabela();
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
    
    private void CarregaTabela() throws SQLException{
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutos());
        tvProdutos.setItems(lista);
    }
    
    private void CarregaTabelaProduto(){
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutosNome(txPesquisar.getText()));
        tvProdutos.setItems(lista);
    }
    
    private void CarregaTabelaPreco(){
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutosPreco(Double.parseDouble(txPesquisar.getText().replace(",", "."))));
        tvProdutos.setItems(lista); 
    }
    
    private void CarregaTabelaQtd(){
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutosQtd(Integer.parseInt(txPesquisar.getText())));
        tvProdutos.setItems(lista); 
    }
    
    private void CarregaTabelaCategoria() throws SQLException{
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = FXCollections.observableArrayList(dal.getProdutosCategoria(txPesquisar.getText()));
        tvProdutos.setItems(lista); 
    }
    
    private void CarregaCBFiltro(){
        ObservableList<String> itens;
        itens = FXCollections.observableArrayList();

        itens.add("Produto");
        itens.add("Preço");
        itens.add("Quantidade");
        itens.add("Categoria");
        
        cbFiltro.setItems(itens);
    }

    @FXML
    private void CancelarProduto(ActionEvent event) {
        LimpaTelaCadastro();
    }

    @FXML
    private void EditarProduto(ActionEvent event) {
        flag = false;
        Produto linha = tvProdutos.getSelectionModel().getSelectedItem();
        txNomeProduto.setText(linha.getPro_nome());
        txPreco.setText(""+linha.getPro_preco());
        txQtdEstoque.setText(""+linha.getPro_quantidade());
        int index = cbCategoria.getItems().indexOf(linha.getCat_cod());
        cbCategoria.getSelectionModel().select(index);
    }

    @FXML
    private void CancelarFiltro(ActionEvent event) throws SQLException {
        LimpaTelaTabela();
        CarregaTabela();
    }
    @FXML
    private void FiltrarProduto(ActionEvent event) throws SQLException {
        DALProduto dal = new DALProduto();
        if(cbFiltro.getSelectionModel().getSelectedItem() == "Produto")
        {
            CarregaTabelaProduto();
            LimpaTelaTabela();
        }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Preco")
            {
                CarregaTabelaPreco();
                LimpaTelaTabela();
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Quantidade")
            {
                CarregaTabelaQtd();
                LimpaTelaTabela();
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Categoria")
            {
                CarregaTabelaCategoria();
                LimpaTelaTabela();
            }
            
    }

    @FXML
    private void RemoverProduto(ActionEvent event) throws SQLException {
        DALProduto dal = new DALProduto();
        Produto linha = tvProdutos.getSelectionModel().getSelectedItem();
        dal.excluir(linha.getPro_cod());
        CarregaTabela();
    }
    
}
