package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import malucismanagement.db.dal.DALFornecedores;
import malucismanagement.db.entidades.Fornecedor;

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
    private TableView<?> tvFornecedores;
    

    Boolean flag = true; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarregaCBFiltro();
        LimpaTelaCadastro();
    }

    private void LimpaTelaCadastro(){
        txCNPJ.clear();
        txEmail.clear();
        txIE.clear();
        txNomeForcenedor.clear();
        txTelefone.clear();
        txTipo.clear();
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
        Fornecedor novo = new Fornecedor(Integer.parseInt(txIE.getText()),Integer.parseInt(txCNPJ.getText()),Integer.parseInt(txTelefone.getText()),
                txTipo.getText(),txNomeForcenedor.getText(),txEmail.getText());
        DALFornecedores dal = new DALFornecedores();
        if(flag)
            dal.gravar(novo);
        else
        {
            dal.alterar(novo);
            flag = true;
        }
            
    }

    @FXML
    private void CancelarFornecedor(ActionEvent event) {
        LimpaTelaCadastro();
    }

    @FXML
    private void EditarFornecedor(ActionEvent event) {
        flag = false;
        /*Preencher campos com o item do tableviwe*/
    }

    @FXML
    private void CancelarFiltro(ActionEvent event) {
        LimpaTelaTabela();
    }

    @FXML
    private void FiltrarFornecedor(ActionEvent event) {
        /* Recarregar tableview de acordo com os filtros*/
    }

    @FXML
    private void RemoverFornecedor(ActionEvent event) {
        DALFornecedores dal = new DALFornecedores();
        int cod = 0;
        /*Recuperar codigo dos produtos selecionados*/
        dal.excluir(cod);
    }
    
}
