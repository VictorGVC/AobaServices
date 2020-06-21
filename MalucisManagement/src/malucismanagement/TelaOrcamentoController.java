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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import malucismanagement.db.dal.DALMarcas;
import malucismanagement.db.entidades.CategoriaProduto;
import malucismanagement.db.entidades.Cliente;
import malucismanagement.db.entidades.ListaEscola;
import malucismanagement.db.entidades.ListaItens;
import malucismanagement.db.entidades.Marcas;

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
    private TableColumn<ListaItens, String> colcod;
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
    @FXML
    private TableColumn<ListaItens, String> colmarcaprod;
    @FXML
    private Label txtotal;

    private ListaItens itematual;
    
    double total;
    @FXML
    private JFXTextField txnome;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initCb();
        initColunas();
        dpde.setValue(LocalDate.ofEpochDay(2018));
        dpate.setValue(LocalDate.now());
        itens = new ArrayList<ListaItens>();
        total = 0;
        escolaatual = new ListaEscola();
        escolaatual.setCodigo(-1);
    }    
    
    private void initColunas()
    {
        colcod.setCellValueFactory(new PropertyValueFactory<>("codigobarras"));
        colcodprod.setCellValueFactory(new PropertyValueFactory<>("codigobarras"));
        coldescprod.setCellValueFactory(new PropertyValueFactory<>("nome"));
        coldescricao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colescola.setCellValueFactory(new PropertyValueFactory<>("escola"));
        collisdata.setCellValueFactory(new PropertyValueFactory<>("data"));
        collisnome.setCellValueFactory(new PropertyValueFactory<>("escola"));
        collisserie.setCellValueFactory(new PropertyValueFactory<>("serie"));
        colmarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colmarcaprod.setCellValueFactory(new PropertyValueFactory<>("marca"));
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
    
    private void carregaTabelaP(String filtro) throws SQLException {
        
        DALListaMateriais dal = new DALListaMateriais();
        List<ListaItens> res = dal.getListaOrcamento(filtro);
        ObservableList<ListaItens> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvproduto.setItems(modelo);
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
        String aoba;
        aoba = txcpf.getText();
        pntab.getSelectionModel().selectLast();
        DALListaMateriais dal = new DALListaMateriais();
        
        tvescolas.getSelectionModel().getSelectedIndex();
        escolaatual = dal.getEscolaProdutos(tvescolas.getSelectionModel().getSelectedItem().getCodigo());
        txcpf.setText(escolaatual.getCnpj());
        
        ObservableList<ListaItens> modelo;
        
        modelo = FXCollections.observableArrayList((List)escolaatual.getProdutos());
        tvlista.setItems(modelo);
        itens = escolaatual.getProdutos();
    }

    @FXML
    private void clkFiltraAno(KeyEvent event) {
        if(!txano.getText().isEmpty() && cbescolas.getSelectionModel().getSelectedIndex() == -1)
            carregaTabelaE("lis_anoreferencia LIKE '%" +txano.getText()+ "%'");
        else if(!txano.getText().isEmpty() && cbescolas.getSelectionModel().getSelectedIndex() != -1)
            carregaTabelaE("c.cli_id = '" + cbescolas.getValue().getCpf() + "' AND lis_anoreferencia LIKE '%" +txano.getText()+ "%'");
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) throws SQLException 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(txnome.getText().isEmpty())
        {
            a.setContentText("Turma deve ser informada!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txnome.requestFocus();
        }
        else
        {
            if(escolaatual.getCodigo() == -1)
            {
                escolaatual = new ListaEscola();
                escolaatual.setCnpj(txcpf.getText());
                escolaatual.setEscola(txnome.getText());
                escolaatual.setProdutos(itens);
                DALListaMateriais dal = new DALListaMateriais();

                if(dal.salvar(escolaatual))
                {

                }
                else
                {

                }
            }
            else 
            {
                escolaatual.setEscola(txnome.getText());
                escolaatual.setCnpj(txcpf.getText());
                escolaatual.setProdutos(itens);
                DALListaMateriais dal = new DALListaMateriais();

                if(dal.alterar(escolaatual))
                {

                }
                else
                {

                }
            }
            
        }
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) 
    {
        pntab.getSelectionModel().selectFirst();
    }

    @FXML
    private void clkEditarProduto(ActionEvent event) throws SQLException 
    {
        if(cbmarcas.getSelectionModel().getSelectedIndex() == -1)
            carregaTabelaP("p.cat_cod = "+cbcategoria.getSelectionModel().getSelectedItem().getCat_cod());
        else
            carregaTabelaP("p.cat_cod = "+cbcategoria.getSelectionModel().getSelectedItem().getCat_cod()+
                    " AND pm.mar_cod = "+ cbmarcas.getSelectionModel().getSelectedItem().getMar_cod());
    }

    @FXML
    private void clkBtAdd(ActionEvent event) 
    {
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
            ListaItens aux = new ListaItens(Integer.parseInt(txqtde.getText()), tvproduto.getSelectionModel().getSelectedItem().getPreco(), 
                    tvproduto.getSelectionModel().getSelectedItem().getCodigobarras(), tvproduto.getSelectionModel().getSelectedItem().getNome(), 
                    tvproduto.getSelectionModel().getSelectedItem().getPreco() * Integer.parseInt(txqtde.getText()), 
                    tvproduto.getSelectionModel().getSelectedItem().getMarca());
            total += aux.getTotal();
            txtotal.setText(""+(total));
            for (ListaItens i : itens) 
            {
                if(i.getCodigo() == aux.getCodigo())
                {
                    i.setQuantidade(i.getQuantidade() + aux.getQuantidade());
                    i.setTotal(i.getPreco() * i.getQuantidade());
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
    private void clkBtApagar(ActionEvent event) 
    {
        total -= itematual.getTotal();
        itens.remove(itematual);
        txtotal.setText(""+total);
        ObservableList<ListaItens> modelo;
        itematual = null;
        modelo = FXCollections.observableArrayList(itens);
        tvlista.setItems(modelo);
        tvlista.refresh();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) 
    {
        txqtde.setText(""+itens.get(itens.indexOf(itematual)).getQuantidade());
        total -= itematual.getTotal();
        itens.remove(itematual);
        txtotal.setText(""+total);
        itens.remove(itematual);
        itematual = null;
        ObservableList<ListaItens> modelo;

        modelo = FXCollections.observableArrayList(itens);
        tvlista.setItems(modelo);
        tvlista.refresh();
    }

    @FXML
    private void clkTabela(MouseEvent event) 
    {
        itematual = tvlista.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void clkEditarProdutoM(ActionEvent event) throws SQLException 
    {
        if(cbcategoria.getSelectionModel().getSelectedIndex() == -1)
            carregaTabelaP("pm.mar_cod = "+ cbmarcas.getSelectionModel().getSelectedItem().getMar_cod());
        else
            carregaTabelaP("p.cat_cod = "+cbcategoria.getSelectionModel().getSelectedItem().getCat_cod()+
                " AND pm.mar_cod = "+ cbmarcas.getSelectionModel().getSelectedItem().getMar_cod());
    }
    
}
