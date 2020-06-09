/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import malucismanagement.db.dal.DALCategoriaProduto;
import malucismanagement.db.dal.DALCliente;
import malucismanagement.db.dal.DALListaMateriais;
import malucismanagement.db.dal.DALProduto;
import malucismanagement.db.entidades.Cliente;
import malucismanagement.db.entidades.ListaEscola;
import malucismanagement.db.entidades.ListaItens;
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
    private TableColumn<ListaItens, String> coldescricao;
    @FXML
    private TableColumn<ListaItens, Integer> colquantidade;
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
    private TableColumn<Produto, String> coldescprod;
    @FXML
    private TableColumn<Produto, Double> colprecoprod;
    @FXML
    private VBox pnpesquisa1;
    @FXML
    private AnchorPane pntabescolas;
    @FXML
    private AnchorPane pnescola;
    @FXML
    private JFXComboBox<Cliente> cbescolas;
    @FXML
    private JFXButton btnovo;
    @FXML
    private TableView<ListaEscola> tvescolas;
    @FXML
    private TableColumn<ListaEscola, String> colescola;
    @FXML
    private TableColumn<ListaEscola, String> colturma;
    @FXML
    private BorderPane pntablista;
    @FXML
    private Label lblescola;
    @FXML
    private Label lblproduto;
    @FXML
    private TableView<Produto> tvproduto;
    @FXML
    private TableView<ListaEscola> tvlista;
    @FXML
    private JFXTabPane pntab;
    @FXML
    private TableColumn<Produto, String> colcodprod;
    @FXML
    private TableColumn<ListaItens, String> colcod;
    @FXML
    private JFXTextField txano;
    
    private ListaEscola escolaatual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCb();
        initColunas();
        
    }   
    
    private void initColunas()
    {
        coldescricao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colescola.setCellValueFactory(new PropertyValueFactory<>("escola"));
        colturma.setCellValueFactory(new PropertyValueFactory<>("serie"));
        colcodprod.setCellValueFactory(new PropertyValueFactory<>("pro_cod"));
        coldescprod.setCellValueFactory(new PropertyValueFactory<>("pro_nome"));
        colprecoprod.setCellValueFactory(new PropertyValueFactory<>("pro_preco"));
    }
    
    
    
    private void initCb()
    {
        DALCliente dal = new DALCliente();
        
        cbescolas.setItems(FXCollections.observableArrayList(dal.getEscolas("")));
        
        DALCategoriaProduto dalc = new DALCategoriaProduto();
        cbcategoria.setItems(FXCollections.observableArrayList(dalc.getCategoriaProduto()));
        
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
        if(txano.getText().isEmpty())
            carregaTabelaE("c.cli_id = '" + cbescolas.getValue().getCpf() + "'");
        else
            carregaTabelaE("c.cli_id = '" + cbescolas.getValue().getCpf() + "' AND lis_anoreferencia LIKE '%" +txano.getText()+ "%'");
    }
    
    private void carregaTabelaP(String c) throws SQLException {
        
        DALProduto dal = new DALProduto();
        List<Produto> res = dal.getProdutosCategoria(c);
        ObservableList<Produto> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvproduto.setItems(modelo);
    }
    
    private void carregaTabelaE(String filtro) {
        
        DALListaMateriais dal = new DALListaMateriais();
        List<ListaEscola> res = dal.get(filtro);
        ObservableList<ListaEscola> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvescolas.setItems(modelo);
    }

    @FXML
    private void clkBtNovaLista(ActionEvent event) {
        if(cbescolas.getSelectionModel().getSelectedIndex() == -1)
        {
            
        }
        else
        {
            pntab.getSelectionModel().selectNext();
            txescola.setText(cbescolas.getValue().getNome());
            tablista.getContent().requestFocus();
            
            txturma.requestFocus();
        }
    }

    @FXML
    private void clkBtEditarEscola(MouseEvent event) {
        pntab.getSelectionModel().selectNext();
        txescola.setText(tvescolas.getSelectionModel().getSelectedItem().getEscola());
        txturma.setText(tvescolas.getSelectionModel().getSelectedItem().getSerie());
        
        DALListaMateriais dal = new DALListaMateriais();
        
        escolaatual = dal.getEscolaProdutos(tvescolas.getSelectionModel().getSelectedItem().getCodigo());
        
    }

    @FXML
    private void clkBtAdd(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txqtde.getText().isEmpty())
        {
            a.setContentText("quantidade deve ser informada!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txqtde.requestFocus();
        }
        else if(tvproduto.getSelectionModel().getSelectedIndex() == -1)
        {
            a.setContentText("Selecione um produto!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txqtde.requestFocus();
        }
        else
        {
            
        }
    }

    @FXML
    private void clkEditarProduto(ActionEvent event) throws SQLException {
        carregaTabelaP(cbcategoria.getValue());
    } 

    @FXML
    private void clkFiltraAno(KeyEvent event) 
    {
        if(!txano.getText().isEmpty() && cbescolas.getSelectionModel().getSelectedIndex() == -1)
            carregaTabelaE("lis_anoreferencia LIKE '%" +txano.getText()+ "%'");
        else if(!txano.getText().isEmpty() && cbescolas.getSelectionModel().getSelectedIndex() != -1)
            carregaTabelaE("c.cli_id = '" + cbescolas.getValue().getCpf() + "' AND lis_anoreferencia LIKE '%" +txano.getText()+ "%'");
    }
    
    
    
}
