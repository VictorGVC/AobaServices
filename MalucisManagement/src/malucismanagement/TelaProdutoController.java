package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import malucismanagement.db.dal.DALCategoriaProduto;
import malucismanagement.db.dal.DALProduto;
import malucismanagement.db.entidades.CategoriaProduto;
import malucismanagement.db.entidades.Produto;

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
    private JFXComboBox<?> cbCategoria;
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
    private TableColumn<CategoriaProduto, String> ColCat;
    @FXML
    private TableColumn<Produto, Integer> ColCod;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarregaTabela();
        CarregaCBFiltro();
    }    

    @FXML
    private void SalvarProduto(ActionEvent event) throws SQLException {
        DALCategoriaProduto dalct = new DALCategoriaProduto();
        Produto p = new Produto(Integer.parseInt(txQtdEstoque.getText()),dalct.getCategoriaProduto(cbCategoria.getValue().toString()),Double.parseDouble(txPreco.getText()),
        txNomeProduto.getText());
        
        DALProduto dal = new DALProduto();
        if(flag)
            dal.gravar(p);
        else{
            dal.alterar(p);
            flag = true;
        }
        CarregaTabela();
    }
    
    private void CarregaTabela(){
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = dal.getProdutos();
           tvProdutos.setItems(lista); 
    }
    
    private void CarregaTabelaProduto(){
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = dal.getProdutosNome(txPesquisar.getText());
        tvProdutos.setItems(lista); 
    }
    
    private void CarregaTabelaPreco(){
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = dal.getProdutosPreco(Double.parseDouble(txPesquisar.getText()));
        tvProdutos.setItems(lista); 
    }
    
    private void CarregaTabelaQtd(){
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = dal.getProdutosQtd(Integer.parseInt(txPesquisar.getText()));
        tvProdutos.setItems(lista); 
    }
    
    private void CarregaTabelaCategoria(){
        tvProdutos.getItems().clear();
        DALProduto dal = new DALProduto();
        ObservableList<Produto> lista = dal.getProdutosCategoria(dal.getCodCat(txPesquisar.getText()));
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
        txNomeProduto.clear();
        txPreco.clear();
        txQtdEstoque.clear();
        cbCategoria.getSelectionModel().clearSelection();
    }

    @FXML
    private void EditarProduto(ActionEvent event) {
        flag = false;
        Produto linha = tvProdutos.getSelectionModel().getSelectedItem();
        txNomeProduto.setText(linha.getPro_nome());
        txPreco.setText(""+linha.getPro_preco());
        txQtdEstoque.setText(""+linha.getPro_quantidade());
        cbCategoria.getSelectionModel().select(linha.getCat_cod());
    }

    @FXML
    private void CancelarFiltro(ActionEvent event) {
        txPesquisar.clear();
        cbFiltro.getSelectionModel().clearSelection();
    }

    @FXML
    private void FiltrarProduto(ActionEvent event) {
        DALProduto dal = new DALProduto();
        if(cbFiltro.getSelectionModel().getSelectedItem() == "Produto")
            CarregaTabelaProduto();
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Preco")
            CarregaTabelaPreco();
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Quantidade")
            CarregaTabelaQtd();
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Categoria")
            CarregaTabelaCategoria();
            
        
    }

    @FXML
    private void RemoverProduto(ActionEvent event) {
        DALProduto dal = new DALProduto();
        Produto linha = tvProdutos.getSelectionModel().getSelectedItem();
        dal.excluir(linha.getPro_cod());
        CarregaTabela();
    }
    
}
