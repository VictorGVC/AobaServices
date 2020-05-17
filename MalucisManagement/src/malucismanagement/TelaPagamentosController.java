package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TelaPagamentosController implements Initializable {

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
    private JFXComboBox<?> cbTipo;
    @FXML
    private JFXTextField txContato;
    @FXML
    private JFXTextField txValor;
    @FXML
    private JFXComboBox<?> cbFornecedores;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXComboBox<?> cbcategoria;
    @FXML
    private JFXTextField tfiltro;
    @FXML
    private JFXButton btQuitar;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private TableView<?> tvclientes;
    @FXML
    private TableColumn<?, ?> collogin;
    @FXML
    private TableColumn<?, ?> colnome;
    @FXML
    private TableColumn<?, ?> colcpf;
    @FXML
    private TableColumn<?, ?> colemail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clkBtNovo(ActionEvent event) {
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
    }

    @FXML
    private void clkTFiltro(KeyEvent event) {
    }

    @FXML
    private void clkbtQuitar(ActionEvent event) {
    }

    @FXML
    private void clkTabela(MouseEvent event) {
    }
    
}
