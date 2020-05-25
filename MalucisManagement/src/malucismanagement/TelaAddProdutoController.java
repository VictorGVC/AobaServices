package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class TelaAddProdutoController implements Initializable {

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
    private Label lbobg;
    @FXML
    private JFXTextField txCompra;
    @FXML
    private JFXComboBox<?> cbMarca;
    @FXML
    private JFXComboBox<?> cbProduto;
    @FXML
    private JFXTextField txQuantidade;
    @FXML
    private JFXTextField txPreco;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXTextField txFiltro;
    @FXML
    private Label lbProduto;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private TableView<?> tvCompra;
    @FXML
    private TableColumn<?, ?> ColCod;
    @FXML
    private TableColumn<?, ?> ColData;
    @FXML
    private TableColumn<?, ?> ColFornecedor;
    @FXML
    private TableColumn<?, ?> ColTotal;
    @FXML
    private TableColumn<?, ?> ColTotal1;

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
    private void clkTabela(MouseEvent event) {
    }
    
}
