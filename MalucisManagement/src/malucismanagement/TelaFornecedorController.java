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
    private JFXComboBox<?> cbFiltro;
    @FXML
    private JFXTextField txPesquisar;
    @FXML
    private JFXButton btRemoverFornecedor;
    @FXML
    private TableView<?> tvFornecedores;

    Boolean flag = true; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        txCNPJ.clear();
        txEmail.clear();
        txIE.clear();
        txNomeForcenedor.clear();
        txTelefone.clear();
        txTipo.clear();
    }

    @FXML
    private void EditarFornecedor(ActionEvent event) {
        flag = false;
        /*Preencher campos com o item do tableviwe*/
    }

    @FXML
    private void CancelarFiltro(ActionEvent event) {
        txPesquisar.clear();
        cbFiltro.getSelectionModel().clearSelection();
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
