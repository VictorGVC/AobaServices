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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
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
    private TableColumn<Produto, String> coldescprod;
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
    private TableView<ListaItens> tvlista;
    @FXML
    private JFXTabPane pntab;
    @FXML
    private TableColumn<Produto, String> colcodprod;
    @FXML
    private TableColumn<ListaItens, Integer> colcod;
    @FXML
    private JFXTextField txano;
    
    private ListaEscola escolaatual;
    
    private List<ListaItens> itens;
    
    private ListaItens itematual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCb();
        initColunas();
        itens = new ArrayList<>();
    }   
    
    private void initColunas()
    {
        colcod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        coldescricao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colquantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colescola.setCellValueFactory(new PropertyValueFactory<>("escola"));
        colturma.setCellValueFactory(new PropertyValueFactory<>("serie"));
        colcodprod.setCellValueFactory(new PropertyValueFactory<>("pro_cod"));
        coldescprod.setCellValueFactory(new PropertyValueFactory<>("pro_nome"));
    }
    
    
    
    private void initCb()
    {
        DALCliente dal = new DALCliente();
        
        cbescolas.setItems(FXCollections.observableArrayList(dal.getEscolas("")));
        
        DALCategoriaProduto dalc = new DALCategoriaProduto();
        cbcategoria.setItems(FXCollections.observableArrayList(dalc.getCategoriaProdutoItens("")));
        
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
        
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        ObservableList <Node> componentes = pntablista.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
            cbcategoria.getSelectionModel().select(-1);
            itens.clear();
        }
    }


    @FXML
    private void clkBtApagar(ActionEvent event) {
        itens.remove(itematual);
        
        ObservableList<ListaItens> modelo;

        modelo = FXCollections.observableArrayList(itens);
        tvlista.setItems(modelo);
        tvlista.refresh();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {
        txqtde.setText(""+itens.get(itens.indexOf(itematual)).getQuantidade());
        
        itens.remove(itematual);
        
        ObservableList<ListaItens> modelo;

        modelo = FXCollections.observableArrayList(itens);
        tvlista.setItems(modelo);
        tvlista.refresh();
    }

    @FXML
    private void clkTabela(MouseEvent event) {
        itematual = tvlista.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void clkFiltraEscola(ActionEvent event) {
        if(txano.getText().isEmpty())
            carregaTabelaE("c.cli_id = '" + cbescolas.getValue().getCpf() + "'");
        else
            carregaTabelaE("c.cli_id = '" + cbescolas.getValue().getCpf() + "' AND lis_anoreferencia LIKE '%" +txano.getText()+ "%'");
    }
    
    private void carregaTabelaP(CategoriaProduto c) throws SQLException {
        
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
    private void clkBtNovaLista(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(cbescolas.getSelectionModel().getSelectedIndex() == -1)
        {
            a.setContentText("escola deve ser selecionada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbescolas.requestFocus();
        }
        else
        {
            pntab.getSelectionModel().selectNext();
            txescola.setText(cbescolas.getValue().getNome());
            
            txturma.requestFocus();
        }
    }

    @FXML
    private void clkBtEditarEscola(MouseEvent event) {
        pntab.getSelectionModel().selectNext();
        DALListaMateriais dal = new DALListaMateriais();
        
        escolaatual = dal.getEscolaProdutos(tvescolas.getSelectionModel().getSelectedItem().getCodigo());
        txescola.setText(escolaatual.getEscola());
        txturma.setText(escolaatual.getSerie());
        
        ObservableList<ListaItens> modelo;
        
        modelo = FXCollections.observableArrayList((List)escolaatual.getProdutos());
        tvlista.setItems(modelo);
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
            boolean b = true;
            ListaItens aux = new ListaItens(Integer.parseInt(txqtde.getText()), Integer.parseInt(tvproduto.getSelectionModel().getSelectedItem().getPro_cod()), tvproduto.getSelectionModel().getSelectedItem().getPro_nome());
            for (ListaItens i : itens) 
            {
                if(i.getCodigo() == aux.getCodigo())
                {
                    i.setQuantidade(i.getQuantidade() + aux.getQuantidade());
                    b = false;
                }
            }
            
            if(b)
                itens.add(aux);
                        
            ObservableList<ListaItens> modelo;
        
            modelo = FXCollections.observableArrayList(itens);
            tvlista.setItems(modelo);
            tvlista.refresh();
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
