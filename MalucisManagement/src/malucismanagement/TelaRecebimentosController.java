package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Tab;
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
import javafx.scene.text.Font;
import javafx.util.Duration;
import malucismanagement.db.dal.DALContasReceber;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.ContasReceber;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.MaskFieldUtil;

public class TelaRecebimentosController implements Initializable {

    @FXML
    private Tab tabreceber;
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
    @FXML
    private JFXButton btquitar;
    @FXML
    private JFXButton btestornar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXTextField tparcelas;
    @FXML
    private JFXTextField tcodigo;
    @FXML
    private JFXDatePicker dpdatavenc;
    @FXML
    private Label lbobg;
    @FXML
    private JFXTextField tcodvenda;
    @FXML
    private JFXTextField tvalor;
    @FXML
    private JFXComboBox<String> cbtipo;
    @FXML
    private JFXTextField tcontato;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXComboBox<String> cbcategoria;
    @FXML
    private JFXTextField tfiltro;
    @FXML
    private TableView<ContasReceber> tvrecebimentos;
    @FXML
    private TableColumn<ContasReceber, Integer> colcod;
    @FXML
    private TableColumn<ContasReceber, Integer> colvenda;
    @FXML
    private TableColumn<ContasReceber, Integer> colparcela;
    @FXML
    private TableColumn<ContasReceber, Double> colvalor;
    @FXML
    private TableColumn<ContasReceber, LocalDate> coldatavenc;
    @FXML
    private TableColumn<ContasReceber, String> coltipo;
    @FXML
    private TableColumn<ContasReceber, String> colcontato;
    @FXML
    private Tab tabrecebidas;
    @FXML
    private SplitPane pnprincipal1;
    @FXML
    private HBox pnbotoes1;
    @FXML
    private JFXButton btcancelar1;
    @FXML
    private Pane pndados1;
    @FXML
    private JFXTextField tparcelas1;
    @FXML
    private JFXTextField tcodigo1;
    @FXML
    private Label lbobg1;
    @FXML
    private JFXTextField tcodvenda1;
    @FXML
    private JFXTextField tvalor1;
    @FXML
    private JFXComboBox<String> cbtipo1;
    @FXML
    private JFXTextField tcontato1;
    @FXML
    private JFXDatePicker dpdatapag1;
    @FXML
    private VBox pnpesquisa1;
    @FXML
    private Pane pnfiltros1;
    @FXML
    private JFXComboBox<String> cbcategoria1;
    @FXML
    private JFXTextField tfiltro1;
    @FXML
    private TableView<ContasReceber> tvrecebidas;
    @FXML
    private TableColumn<ContasReceber, Integer> colcod1;
    @FXML
    private TableColumn<ContasReceber, Integer> colvenda1;
    @FXML
    private TableColumn<ContasReceber, Integer> colparcela1;
    @FXML
    private TableColumn<ContasReceber, Double> colvalor1;
    @FXML
    private TableColumn<ContasReceber, LocalDate> coldatapag1;
    @FXML
    private TableColumn<ContasReceber, String> coltipo1;
    @FXML
    private TableColumn<ContasReceber, String> colcontato1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
        fixaDivider();
        setParametros();
        setMascaras();
        initColT1();
        initColT2();
        listaTipo();
        listaCategoria();
        estado1(true);
        estado2(true);
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
                
                divider.setPosition(0.37);
            }
        });
        
        SplitPane.Divider divider2 = pnprincipal1.getDividers().get(0);
        divider2.positionProperty().addListener(new ChangeListener<Number>() {
            
            @Override 
            public void changed(ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue) {
                
                divider2.setPosition(0.37);
            }
        });
    }
    
    private void setParametros() {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p.getCorprimaria() != null){
            
            pndados.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvrecebimentos.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            
            pndados1.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvrecebidas.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            pnbotoes.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            pnfiltros.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            
            pnbotoes1.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            pnfiltros1.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            btquitar.setFont(new Font(p.getFonte(), 12));
            btcancelar.setFont(new Font(p.getFonte(), 12));
            
            tcodigo.setFont(new Font(p.getFonte(), 14));
            tcodvenda.setFont(new Font(p.getFonte(), 14));
            tparcelas.setFont(new Font(p.getFonte(), 14));
            tvalor.setFont(new Font(p.getFonte(), 14));
            tcontato.setFont(new Font(p.getFonte(), 14));
            lbobg.setFont(new Font(p.getFonte(), 12));
            
            tfiltro.setFont(new Font(p.getFonte(), 14));
            
            btestornar.setFont(new Font(p.getFonte(), 12));
            btcancelar1.setFont(new Font(p.getFonte(), 12));
            
            tcodigo1.setFont(new Font(p.getFonte(), 14));
            tcodvenda1.setFont(new Font(p.getFonte(), 14));
            tparcelas1.setFont(new Font(p.getFonte(), 14));
            tvalor1.setFont(new Font(p.getFonte(), 14));
            tcontato1.setFont(new Font(p.getFonte(), 14));
            lbobg1.setFont(new Font(p.getFonte(), 12));
            
            tfiltro1.setFont(new Font(p.getFonte(), 14));
        }
        if(p.getCorfonte() != null){
            
            btquitar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tcodigo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcodvenda.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tparcelas.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tvalor.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcontato.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tfiltro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            btestornar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tcodigo1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcodvenda1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tparcelas1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tvalor1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcontato1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tfiltro1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void setMascaras() {
        
        MaskFieldUtil.numericField(tcodigo);
        MaskFieldUtil.numericField(tcodvenda);
        MaskFieldUtil.numericField(tparcelas);
        MaskFieldUtil.monetaryField(tvalor);
        MaskFieldUtil.foneField(tcontato);
        
        MaskFieldUtil.numericField(tcodigo1);
        MaskFieldUtil.numericField(tcodvenda1);
        MaskFieldUtil.numericField(tparcelas1);
        MaskFieldUtil.monetaryField(tvalor1);
        MaskFieldUtil.foneField(tcontato1);
    }
    
    private void initColT1() {
        
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colvenda.setCellValueFactory(new PropertyValueFactory("ven_cod"));
        colparcela.setCellValueFactory(new PropertyValueFactory("par"));
        colvalor.setCellValueFactory(new PropertyValueFactory("valor"));
        coldatavenc.setCellValueFactory(new PropertyValueFactory("datavenc"));
        coltipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontato.setCellValueFactory(new PropertyValueFactory("contato"));
    }
    
    private void initColT2() {
        
        colcod1.setCellValueFactory(new PropertyValueFactory("cod"));
        colvenda1.setCellValueFactory(new PropertyValueFactory("ven_cod"));
        colparcela1.setCellValueFactory(new PropertyValueFactory("par"));
        colvalor1.setCellValueFactory(new PropertyValueFactory("valor"));
        coldatapag1.setCellValueFactory(new PropertyValueFactory("datavenc"));
        coltipo1.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontato1.setCellValueFactory(new PropertyValueFactory("contato"));
    }
    
    private void estado1(boolean b) {
        
        pndados.setDisable(b);
        btquitar.setDisable(b);
        btcancelar.setDisable(b);
      
        //carregaTabelaReceber("");
    }
    
    private void estado2(boolean b) {
        
        pndados1.setDisable(b);
        btcancelar1.setDisable(b);
        btestornar.setDisable(b);
      
        //carregaTabelaRecebidas("");
    }
    
    private void carregaTabelaReceber(String filtro) {
        
        DALContasReceber dal = new DALContasReceber();
        List<ContasReceber> res = dal.getListRec(filtro);
        ObservableList<ContasReceber> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvrecebimentos.setItems(modelo);
    }
    
    private void carregaTabelaRecebidas(String filtro) {
        
        DALContasReceber dal = new DALContasReceber();
        List<ContasReceber> res = dal.getListRec(filtro);
        ObservableList<ContasReceber> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvrecebidas.setItems(modelo);
    }
    
    private void listaTipo() {
        
        List<String> list = new ArrayList();
        
        list.add("");
        list.add("Cartão de Crédito");
        list.add("Cartão de Débito");
        list.add("Dinheiro");
        
        cbtipo.setItems(FXCollections.observableArrayList(list));
        cbtipo1.setItems(FXCollections.observableArrayList(list));
    }
    
    private void listaCategoria() {
        
        List<String> list = new ArrayList();
        
        list.add("");
        list.add("Código");
        list.add("Venda");
        list.add("Status");
        list.add("Tipo");
        list.add("Cliente");
        list.add("Contato");
        
        cbcategoria.setItems(FXCollections.observableArrayList(list));
        cbcategoria1.setItems(FXCollections.observableArrayList(list));
    }
    
    private void limparCampos1() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
            cbtipo.getSelectionModel().select(-1);
            cbcategoria.getSelectionModel().select(-1);
        }
    }
    
    private void limparCampos2() {
        
        ObservableList <Node> componentes = pndados1.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
            cbtipo1.getSelectionModel().select(-1);
            cbcategoria1.getSelectionModel().select(-1);
        }
    }
    
    private void setCorAlert(String cor){
        
        if(tabreceber.isSelected()){
            
            setCorAlert(tvalor, cor);
            setCorAlert(tcontato, cor);
        }
        
        if(tabrecebidas.isSelected()){
            
            setCorAlert(tvalor1, cor);
            setCorAlert(tcontato1, cor);
        }
    }
    
    private void setCorAlert(JFXTextField tf, String cor){
        
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }

    @FXML
    private void clkBtQuitar(ActionEvent event) {
    }

    @FXML
    private void clkBtEstonar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        
        if(tabreceber.isSelected()){
            
            if (!pndados.isDisabled()){

                estado1(true);
                limparCampos1();
            }
            pnpesquisa.setDisable(false);
        }
        
        if(tabrecebidas.isSelected()){
            
            if (!pndados1.isDisabled()){

                estado2(true);
                limparCampos2();
            }
            pnpesquisa1.setDisable(false);
        }
    }
    
    @FXML
    private void clkTFiltro1(KeyEvent event) {
    }

    @FXML
    private void clkTFiltro2(KeyEvent event) {
    }

    @FXML
    private void clkTabela(MouseEvent event) {
        
        estado1(false);
        limparCampos1();
        pnpesquisa.setDisable(true);
    }

    @FXML
    private void clkTabelaRecebidas(MouseEvent event) {
        
        estado2(false);
        limparCampos2();
        pnpesquisa.setDisable(true);
    }
}