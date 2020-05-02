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
import javafx.scene.control.TableColumn;
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
    private TableView<Fornecedor> tvFornecedores;
    

    Boolean flag = true; 
    @FXML
    private TableColumn<?, ?> ColCodigo;
    @FXML
    private TableColumn<?, ?> ColFornecedor;
    @FXML
    private TableColumn<?, ?> ColCNPJ;
    @FXML
    private TableColumn<?, ?> ColIE;
    @FXML
    private TableColumn<?, ?> ColTelefone;
    @FXML
    private TableColumn<?, ?> ColEmail;
    @FXML
    private TableColumn<?, ?> ColTipo;
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
    }
    
    private void CarregaTabelaFornecedor(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = dal.getFornecedoresNome(txPesquisar.getText());
        for (int i = 0; i < lista.size(); i++) {
           tvFornecedores.setItems(lista); 
        }
    }
    
    private void CarregaTabelaCNPJ(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = dal.getFornecedoresCNPJ(Integer.parseInt(txPesquisar.getText()));
        for (int i = 0; i < lista.size(); i++) {
           tvFornecedores.setItems(lista); 
        }
    }
    
    private void CarregaTabelaIE(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = dal.getFornecedoresIE(Integer.parseInt(txPesquisar.getText()));
        for (int i = 0; i < lista.size(); i++) {
           tvFornecedores.setItems(lista); 
        }
    }
    
    private void CarregaTabelaTelefone(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = dal.getFornecedoresTelefone(Integer.parseInt(txPesquisar.getText()));
        for (int i = 0; i < lista.size(); i++) {
           tvFornecedores.setItems(lista); 
        }
    }
    
    private void CarregaTabelaEmail(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = dal.getFornecedoresEmail(txPesquisar.getText());
        for (int i = 0; i < lista.size(); i++) {
           tvFornecedores.setItems(lista); 
        }
    }
    
    private void CarregaTabelaTipo(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = dal.getFornecedoresTipo(txPesquisar.getText());
        for (int i = 0; i < lista.size(); i++) {
           tvFornecedores.setItems(lista); 
        }
    }

    @FXML
    private void FiltrarFornecedor(ActionEvent event) {
        DALFornecedores dal = new DALFornecedores();
        if(cbFiltro.getSelectionModel().getSelectedItem() == "Fornecedor")
            CarregaTabelaFornecedor();
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "CNPJ")
            CarregaTabelaCNPJ();
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "IE")
            CarregaTabelaIE();
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Telefone")
            CarregaTabelaTelefone();
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Email")
            CarregaTabelaEmail();
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Tipo")
            CarregaTabelaTipo();
    }

    @FXML
    private void RemoverFornecedor(ActionEvent event) {
        DALFornecedores dal = new DALFornecedores();
        Fornecedor linha = tvFornecedores.getSelectionModel().getSelectedItem();
        dal.excluir(linha.getFor_cod());
    }
    
}
