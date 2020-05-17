package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import malucismanagement.db.dal.DALItensVenda;
import malucismanagement.db.dal.DALVenda;
import malucismanagement.db.entidades.ItensVenda;
import malucismanagement.db.entidades.Venda;
import malucismanagement.util.MaskFieldUtil;

public class TelaVendasController implements Initializable {

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
    private JFXButton btcancelar;
    @FXML
    private JFXButton btvoltar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXTextField tcodigodebarras;
    @FXML
    private JFXTextField tproduto;
    @FXML
    private JFXTextField tpreco;
    @FXML
    private JFXTextField tmarca;
    @FXML
    private JFXTextField tqtde;
    @FXML
    private JFXButton btadicionar;
    @FXML
    private JFXButton btremover;
    @FXML
    private TableView<ItensVenda> tvprodutos;
    @FXML
    private TableColumn<ItensVenda, String> colprod;
    @FXML
    private TableColumn<ItensVenda, Double> colpreco;
    @FXML
    private TableColumn<ItensVenda, Integer> colqtde;
    @FXML
    private TableColumn<ItensVenda, Double> coltotalprod;
    @FXML
    private Label lbobg;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXComboBox<?> cbcategoria;
    @FXML
    private JFXTextField tfiltro;
    @FXML
    private TableView<Venda> tvvendas;
    @FXML
    private TableColumn<Venda, Integer> colcod;
    @FXML
    private TableColumn<Venda, String> colcliente;
    @FXML
    private TableColumn<Venda, LocalDate> coldata;
    @FXML
    private TableColumn<Venda, Double> coltotalven;
    @FXML
    private Pane pndados2;
    @FXML
    private JFXTextField tcliente;
    @FXML
    private JFXDatePicker dpdatavenda;
    @FXML
    private JFXButton btconfirmar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
        fixaDivider();
        setParametros();
        setMascaras();
        initColProd();
        initColVenda();
        listaCategoria();
        estado(true);
    }    

    private void fadeout() {
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void fixaDivider() {
        
        SplitPane.Divider divider = pnprincipal.getDividers().get(0);
        divider.positionProperty().addListener(new ChangeListener<Number>() {
            
            @Override 
            public void changed(ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue) {
                
                divider.setPosition(0.52);
            }
        });
    }
    
    private void setParametros() {}
    
    private void setMascaras() {
        
        MaskFieldUtil.maxField(tcodigodebarras, 20);
        MaskFieldUtil.maxField(tproduto, 50);
        MaskFieldUtil.monetaryField(tpreco);
        MaskFieldUtil.maxField(tmarca, 50);
        MaskFieldUtil.numericField(tpreco);
        MaskFieldUtil.cpfField(tcliente);
        dpdatavenda.setValue(LocalDate.now());
    }
    
    private void initColProd(){
        
        colprod.setCellValueFactory(new PropertyValueFactory("pro_nome"));
        colpreco.setCellValueFactory(new PropertyValueFactory("pro_preco"));
        colqtde.setCellValueFactory(new PropertyValueFactory("pro_quantidade"));
        coltotalprod.setCellValueFactory(new PropertyValueFactory("pro_total"));
    }
    
    private void initColVenda(){
        
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colcliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        coldata.setCellValueFactory(new PropertyValueFactory("data"));
        coltotalven.setCellValueFactory(new PropertyValueFactory("total"));
    }
    
    private void estado(boolean b) {
        
        pndados.setDisable(b);
        pndados2.setDisable(b);
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btapagar.setDisable(!b);
        btalterar.setDisable(!b);
        btnovo.setDisable(!b);
      
        carregaTabelaVendas("");
    }
    
    private void carregaTabelaVendas(String filtro){
        
        DALVenda dal = new DALVenda();
        List<Venda> res = dal.getListVenda(filtro);
        ObservableList<Venda> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvvendas.setItems(modelo);
    }
    
    private void carregaTabelaProdutos(String filtro){
        
        DALItensVenda dal = new DALItensVenda();
        List<ItensVenda> res = dal.getListItVenda(filtro);
        ObservableList<ItensVenda> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvprodutos.setItems(modelo);
    }
    
    private void listaCategoria() {
        
        /*List<String> list = new ArrayList();
        
        list.add("");
        list.add("Código");
        list.add("Cliente");
        list.add("Data");
        list.add("Valor");
        
        cbcategoria.setItems(FXCollections.observableArrayList(list));*/
    }
    
    private void limparCampos() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
    }
    
    private void setCorAlert(String cor){
        
        setCorAlert(tcodigodebarras, cor);
        setCorAlert(tpreco, cor);
        setCorAlert(tqtde, cor);
    }
    
    private void setCorAlert(JFXTextField tf, String cor){
        
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    @FXML
    private void clkBtNovo(ActionEvent event) {
        
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {
        
        if(tvvendas.getSelectionModel().getSelectedIndex() != -1){
            
            estado(false);
            pnpesquisa.setDisable(false);
        }
        else{
            
            JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
            sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Selecione algum cliente!")));
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        
        if (!pndados.isDisabled() && !pndados2.isDisabled()){
            
            estado(true);
            limparCampos();
        }
        pnpesquisa.setDisable(false);
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
        
        Stage stage = (Stage) btvoltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkBtAdicionar(ActionEvent event) {
    }

    @FXML
    private void clkBtRemover(ActionEvent event) {
    }
    
    @FXML
    private void clkTabelaProdutos(MouseEvent event) {
        
        if(tvprodutos.getSelectionModel().getSelectedIndex() >= 0){
            
            if(tvprodutos.getSelectionModel().getSelectedItem() != null){
                
                ItensVenda i = (ItensVenda)tvprodutos.getSelectionModel().getSelectedItem();
                
                tcodigodebarras.setText(i.getPro_cod());
                tproduto.setText("");
                tpreco.setText("");
                tmarca.setText("");
                tqtde.setText("" + i.getQtde());
                pndados.setDisable(false); 
                pndados2.setDisable(false); 
                if(btconfirmar.isDisable()){
                    
                    pndados.setDisable(true);
                    pndados2.setDisable(true);
                }
            }
        }
    }
    
    @FXML
    private void clkTFiltro(KeyEvent event) {
        
        if(cbcategoria.getSelectionModel().getSelectedIndex() != -1){
            
            switch (cbcategoria.getSelectionModel().getSelectedIndex()) {
                
                case 1:
                    carregaTabelaVendas("UPPER(ven_cod) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 2:
                    carregaTabelaVendas("UPPER(cli_id) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 3:
                    carregaTabelaVendas("UPPER(ven_data) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 4:
                    carregaTabelaVendas("UPPER(ven_valor) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                default:
                    break;
            }
        }
    }
    
    @FXML
    private void clkTabelaVendas(MouseEvent event) {
        
        if(tvvendas.getSelectionModel().getSelectedIndex() >= 0){
            
            if(tvvendas.getSelectionModel().getSelectedItem() != null){
                
                Venda v = (Venda)tvvendas.getSelectionModel().getSelectedItem();
                
                tcliente.setText(v.getCli_id());
                dpdatavenda.setValue(v.getDtvenda());
                carregaTabelaProdutos("UPPER(ven_cod) LIKE '%" + tvvendas.getSelectionModel().getSelectedItem().getCod() + "%'");
                pndados.setDisable(false); 
                pndados2.setDisable(false); 
                if(btconfirmar.isDisable()){
                    
                    pndados.setDisable(true);
                    pndados2.setDisable(true);
                }
            }
        }
    }
    
    @FXML
    private void evtCpfDigitado(KeyEvent event) {
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
    }
}