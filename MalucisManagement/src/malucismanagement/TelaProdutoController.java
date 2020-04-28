package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import malucismanagement.db.dal.DALProduto;
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
    private JFXComboBox<?> cbFiltro;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private JFXButton btRemoverProduto;
    @FXML
    private TableView<?> tvProdutos;
    
    Boolean flag = true; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void SalvarProduto(ActionEvent event) {
        Produto p = new Produto(0/*Pegar o cod do produto*/,Integer.parseInt(txQtdEstoque.getText()),Double.parseDouble(txPreco.getText()),
        txNomeProduto.getText(),Integer.parseInt("1"/*Pegar o cod da categoria selecionada*/));
        
        DALProduto dal = new DALProduto();
        if(flag)
            dal.gravar(p);
        else{
            dal.alterar(p);
            flag = true;
        }
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
        /*Preencher campos com o item do tableviwe*/
    }

    @FXML
    private void CancelarFiltro(ActionEvent event) {
        txPesquisar.clear();
        cbFiltro.getSelectionModel().clearSelection();
    }

    @FXML
    private void FiltrarProduto(ActionEvent event) {
        /*Recarregar tableview de acordo com o filtro*/
    }

    @FXML
    private void RemoverProduto(ActionEvent event) {
        DALProduto dal = new DALProduto();
        int cod = 0;
        /*Recuperar codigo dos produtos selecionados*/
        dal.excluir(cod);
    }
    
}
