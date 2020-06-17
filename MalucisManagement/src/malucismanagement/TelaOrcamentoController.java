/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import malucismanagement.db.dal.DALMarcas;
import malucismanagement.db.entidades.CategoriaProduto;
import malucismanagement.db.entidades.Cliente;
import malucismanagement.db.entidades.ListaEscola;
import malucismanagement.db.entidades.ListaItens;
import malucismanagement.db.entidades.Marcas;
import malucismanagement.db.entidades.Produto;

/**
 * FXML Controller class
 *
 * @author HITRON
 */
public class TelaOrcamentoController implements Initializable {

    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private JFXTextField txturma;
    @FXML
    private JFXComboBox<CategoriaProduto> cbcategoria;
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
    private TableColumn<ListaItens, String> coldescprod;
    @FXML
    private VBox pnpesquisa1;
    @FXML
    private AnchorPane pntabescolas;
    @FXML
    private AnchorPane pnescola;
    @FXML
    private JFXComboBox<Cliente> cbescolas;
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
    private TableView<ListaItens> tvproduto;
    @FXML
    private TableView<ListaItens> tvlista;
    @FXML
    private JFXTabPane pntab;
    @FXML
    private TableColumn<ListaItens, String> colcodprod;
    @FXML
    private TableColumn<ListaItens, Integer> colcod;
    @FXML
    private JFXTextField txano;
    @FXML
    private TableColumn<ListaItens, String> colmarca;
    @FXML
    private TableColumn<ListaItens, Double> colprecop;
    @FXML
    private TableColumn<ListaItens, Double> colpreco;
    @FXML
    private TableColumn<ListaItens, Double> coltotal;
    @FXML
    private TableColumn<ListaEscola, String> collisnome;
    @FXML
    private TableColumn<ListaEscola, Date> collisdata;
    @FXML
    private TableColumn<ListaEscola, String> collisserie;
    @FXML
    private TableView<ListaEscola> tvlisclientes;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private JFXComboBox<Marcas> cbmarcas;
    @FXML
    private JFXDatePicker dpate;
    @FXML
    private JFXDatePicker dpde;
    
    private ListaEscola escolaatual;
    
    private List<ListaItens> itens;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablista.setDisable(true);
        initCb();
        initColunas();
    }    
    
    private void initColunas()
    {
        colcod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colcodprod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        coldescprod.setCellValueFactory(new PropertyValueFactory<>("nome"));
        coldescricao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colescola.setCellValueFactory(new PropertyValueFactory<>("escola"));
        collisdata.setCellValueFactory(new PropertyValueFactory<>("data"));
        collisnome.setCellValueFactory(new PropertyValueFactory<>("escola"));
        collisserie.setCellValueFactory(new PropertyValueFactory<>("serie"));
        colmarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colpreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colprecop.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colquantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colturma.setCellValueFactory(new PropertyValueFactory<>("serie"));
    }

    private void initCb()
    {
        DALCliente dal = new DALCliente();
        cbescolas.setItems(FXCollections.observableArrayList(dal.getEscolas("")));
        
        DALCategoriaProduto dalc = new DALCategoriaProduto();
        cbcategoria.setItems(FXCollections.observableArrayList(dalc.getCategoriaProdutoItens("")));
        
        DALMarcas dalm = new DALMarcas();
        cbmarcas.setItems(FXCollections.observableArrayList(dalm.getMarcas()));
    }
    
    private void carregaTabelaE(String filtro) {
        
        DALListaMateriais dal = new DALListaMateriais();
        List<ListaEscola> res = dal.get(filtro);
        ObservableList<ListaEscola> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvescolas.setItems(modelo);
    }
    
    @FXML
    private void clkFiltraEscola(ActionEvent event) 
    {
        if(txano.getText().isEmpty())
            carregaTabelaE("c.cli_id = '" + cbescolas.getValue().getCpf() + "'");
        else
            carregaTabelaE("c.cli_id = '" + cbescolas.getValue().getCpf() + "' AND lis_anoreferencia LIKE '%" +txano.getText()+ "%'");
    }

    @FXML
    private void clkBtEditarEscola(MouseEvent event) {
        tablista.setDisable(false);
        pntab.getSelectionModel().selectLast();
        DALListaMateriais dal = new DALListaMateriais();
        
        tvescolas.getSelectionModel().getSelectedIndex();
        escolaatual = dal.getEscolaProdutos(tvescolas.getSelectionModel().getSelectedItem().getCodigo());
        txcpf.setText(escolaatual.getCnpj());
        txturma.setText(escolaatual.getSerie());
        
        ObservableList<ListaItens> modelo;
        
        modelo = FXCollections.observableArrayList((List)escolaatual.getProdutos());
        tvlista.setItems(modelo);
        itens = escolaatual.getProdutos();
    }

    @FXML
    private void clkFiltraAno(KeyEvent event) {
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
    }

    @FXML
    private void clkEditarProduto(ActionEvent event) {
    }

    @FXML
    private void clkBtAdd(ActionEvent event) {
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
