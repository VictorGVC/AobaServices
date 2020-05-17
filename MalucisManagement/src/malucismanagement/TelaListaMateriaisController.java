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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HITRON
 */
public class TelaListaMateriaisController implements Initializable {

    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private Pane pndados;
    @FXML
    private Label lbobg;
    @FXML
    private JFXTextField txescola;
    @FXML
    private JFXTextField txturma;
    @FXML
    private JFXComboBox<?> cbcategoria;
    @FXML
    private JFXTextField txqtde;
    @FXML
    private JFXComboBox<?> cbprodutos;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXButton btadicionar;
    @FXML
    private JFXButton btapagar;
    @FXML
    private JFXButton btalterar;
    @FXML
    private TableView<?> tvclientes;
    @FXML
    private TableColumn<?, ?> coldescricao;
    @FXML
    private TableColumn<?, ?> colquantidade;
    @FXML
    private TableColumn<?, ?> coltotal;
    @FXML
    private Label lbltotal;

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
    private void clkBtNovo(ActionEvent event) {
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
    
}
