/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import malucismanagement.db.entidades.Cliente;
import malucismanagement.db.entidades.Produto;

/**
 * FXML Controller class
 *
 * @author HITRON
 */
public class TelaListaMateriaisController implements Initializable {

    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private JFXTextField txescola;
    @FXML
    private JFXTextField txturma;
    @FXML
    private JFXComboBox<String> cbcategoria;
    @FXML
    private JFXTextField txqtde;
    @FXML
    private JFXButton btadicionar;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btalterar;
    @FXML
    private TableColumn<?, ?> coldescricao;
    @FXML
    private TableColumn<?, ?> colquantidade;
    @FXML
    private TableColumn<?, ?> coltotal;
    @FXML
    private Tab tabescolas;
    @FXML
    private Tab tablista;
    @FXML
    private SplitPane pnprincipal1;
    @FXML
    private HBox pnbotoes1;
    @FXML
    private Pane pndados1;
    @FXML
    private Label lbobg1;
    @FXML
    private TableColumn<?, ?> coldescprod;
    @FXML
    private TableColumn<?, ?> colprecoprod;
    @FXML
    private VBox pnpesquisa1;
    @FXML
    private TableColumn<?, ?> colpreco;
    @FXML
    private Label lbltotal1;
    @FXML
    private AnchorPane pntabescolas;
    @FXML
    private AnchorPane pnescola;
    @FXML
    private JFXComboBox<?> cbescolas;
    @FXML
    private JFXButton btnovo;
    @FXML
    private TableView<Cliente> tvescolas;
    @FXML
    private TableColumn<?, ?> colescola;
    @FXML
    private TableColumn<?, ?> colturma;
    @FXML
    private BorderPane pntablista;
    @FXML
    private Label lblescola;
    @FXML
    private Label lblproduto;
    @FXML
    private TableView<Produto> tvproduto;
    @FXML
    private TableView<?> tvlista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
    }


    @FXML
    private void clkBtApagar(ActionEvent event) {
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {
    }

    @FXML
    private void clkTabela(MouseEvent event) {
    }

    @FXML
    private void clkFiltraEscola(ActionEvent event) {
    }

    @FXML
    private void clkBtNovaLista(ActionEvent event) {
    }

    @FXML
    private void clkBtEditarEscola(MouseEvent event) {
    }

    @FXML
    private void clkBtAdd(ActionEvent event) {
    }
    
}
