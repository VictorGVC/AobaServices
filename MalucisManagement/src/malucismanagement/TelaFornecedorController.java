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
import javafx.scene.control.cell.PropertyValueFactory;
import malucismanagement.db.dal.DALFornecedores;
import malucismanagement.db.entidades.Fornecedor;
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
        CarregaTabelaFornecedor();
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
        if(flag)
        {
            if(dal.gravar(novo))
                LimpaTelaCadastro();
        }  
        else
        {
            if(dal.alterar(novo,auxCNPJ)){
                flag = true;
                LimpaTelaCadastro();
            }
        }
        
        LimpaTelaCadastro();
        CarregaTabelaFornecedor();
        
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
        CarregaTabelaFornecedor();
    }
    
    private void CarregaTabelaFornecedor(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedores());
        tvFornecedores.setItems(lista);
    }
    
    private void CarregaTabelaFornecedorNome(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresNome(txPesquisar.getText()));
        tvFornecedores.setItems(lista);
    }
    
    private void CarregaTabelaCNPJ(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresCNPJ(txPesquisar.getText()));
        tvFornecedores.setItems(lista);
    }
    
    private void CarregaTabelaIE(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresIE(txPesquisar.getText()));
        tvFornecedores.setItems(lista);
    }
    
    private void CarregaTabelaTelefone(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresTelefone(txPesquisar.getText()));
        tvFornecedores.setItems(lista);
    }
    
    private void CarregaTabelaEmail(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresEmail(txPesquisar.getText()));
        tvFornecedores.setItems(lista);
    }
    
    private void CarregaTabelaTipo(){
        tvFornecedores.getItems().clear();
        DALFornecedores dal = new DALFornecedores();
        ObservableList<Fornecedor> lista = FXCollections.observableArrayList(dal.getFornecedoresTipo(txPesquisar.getText()));
        tvFornecedores.setItems(lista);
    }

    @FXML
    private void FiltrarFornecedor(ActionEvent event) {
        DALFornecedores dal = new DALFornecedores();
        if(cbFiltro.getSelectionModel().getSelectedItem() == "Fornecedor")
        {
            CarregaTabelaFornecedorNome();
            LimpaTelaTabela();
            }            
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "CNPJ")
            {
            CarregaTabelaCNPJ();
            LimpaTelaTabela();
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "IE")
            {
                CarregaTabelaIE();
            LimpaTelaTabela();
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Telefone")
            {
            CarregaTabelaTelefone();
            LimpaTelaTabela();
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Email")
            {
            CarregaTabelaEmail();
            LimpaTelaTabela();
            }
        else if(cbFiltro.getSelectionModel().getSelectedItem() == "Tipo")
            {
            CarregaTabelaTipo();
            LimpaTelaTabela();
            }
    }

    @FXML
    private void RemoverFornecedor(ActionEvent event) {
        DALFornecedores dal = new DALFornecedores();
        Fornecedor linha = tvFornecedores.getSelectionModel().getSelectedItem();
        dal.excluir(linha.getFor_cnpj());
        CarregaTabelaFornecedor();
    }
    
}
