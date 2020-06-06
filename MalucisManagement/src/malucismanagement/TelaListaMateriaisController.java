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
import com.sun.javafx.scene.control.behavior.TabPaneBehavior;
import java.net.URL;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
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
import malucismanagement.db.entidades.CategoriaProduto;
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
    private TableColumn<ListaItens, Double> coltotal;
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
    private TableColumn<ListaItens, Double> colpreco;
    @FXML
    private Label lbltotal1;
    @FXML
    private AnchorPane pntabescolas;
    @FXML
    private AnchorPane pnescola;
    @FXML
    private JFXComboBox<String> cbescolas;
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
    private JFXTextField txtotal;

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
        List <String> l = new ArrayList<String>();
        for (Cliente c : dal.getEscolas("")) 
            l.add(c.getNome());
        
        cbescolas.setItems(FXCollections.observableArrayList(l));
        
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
        carregaTabelaE("c.cli_nome LIKE '%" + cbescolas.getValue() + "%'");
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
            txescola.setText(cbescolas.getValue());
            tablista.getContent().requestFocus();
            
            txturma.requestFocus();
        }
    }

    @FXML
    private void clkBtEditarEscola(MouseEvent event) {
        
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
    
    
    
}
