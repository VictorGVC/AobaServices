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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private JFXTextField tcodvenda;
    @FXML
    private JFXTextField tvalor;
    @FXML
    private JFXTextField ttipo;
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
    private JFXDatePicker dpdatainicial;
    @FXML
    private JFXDatePicker dpdatafinal;
    @FXML
    private JFXButton btlimpar;
    @FXML
    private JFXButton btfiltrar;
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
    private JFXTextField tcodvenda1;
    @FXML
    private JFXTextField tvalor1;
    @FXML
    private JFXTextField ttipo1;
    @FXML
    private JFXDatePicker dpdatapag;
    @FXML
    private JFXTextField tcontato1;
    @FXML
    private VBox pnpesquisa1;
    @FXML
    private Pane pnfiltros1;
    @FXML
    private JFXComboBox<String> cbcategoria1;
    @FXML
    private JFXTextField tfiltro1;
    @FXML
    private JFXDatePicker dpdatainicial1;
    @FXML
    private JFXDatePicker dpdatafinal1;
    @FXML
    private JFXButton btlimpar1;
    @FXML
    private JFXButton btfiltrar1;
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
    private TableColumn<ContasReceber, LocalDate> coldatapag;
    @FXML
    private TableColumn<ContasReceber, String> coltipo1;
    @FXML
    private TableColumn<ContasReceber, String> colcontato1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
        fixaDivider();
        setParametros();
        initColT1();
        initColT2();
        listaCategoria();
        estado1(true);
        estado2(true);
        esperaLista();
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
            ttipo.setFont(new Font(p.getFonte(), 14));
            tcontato.setFont(new Font(p.getFonte(), 14));
            
            tfiltro.setFont(new Font(p.getFonte(), 14));
            btlimpar.setFont(new Font(p.getFonte(), 12));
            btfiltrar.setFont(new Font(p.getFonte(), 12));
            
            btestornar.setFont(new Font(p.getFonte(), 12));
            btcancelar1.setFont(new Font(p.getFonte(), 12));
            
            tcodigo1.setFont(new Font(p.getFonte(), 14));
            tcodvenda1.setFont(new Font(p.getFonte(), 14));
            tparcelas1.setFont(new Font(p.getFonte(), 14));
            tvalor1.setFont(new Font(p.getFonte(), 14));
            ttipo1.setFont(new Font(p.getFonte(), 14));
            tcontato1.setFont(new Font(p.getFonte(), 14));
            
            tfiltro1.setFont(new Font(p.getFonte(), 14));
            btlimpar1.setFont(new Font(p.getFonte(), 12));
            btfiltrar1.setFont(new Font(p.getFonte(), 12));
        }
        if(p.getCorfonte() != null){
            
            btquitar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tcodigo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcodvenda.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tparcelas.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tvalor.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            ttipo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcontato.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tfiltro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btlimpar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btfiltrar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            btestornar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tcodigo1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcodvenda1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tparcelas1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tvalor1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            ttipo1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcontato1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tfiltro1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btlimpar1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btfiltrar1.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void initColT1() {
        
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colvenda.setCellValueFactory(new PropertyValueFactory("ven_cod"));
        colparcela.setCellValueFactory(new PropertyValueFactory("parcela"));
        colvalor.setCellValueFactory(new PropertyValueFactory("valor"));
        coldatavenc.setCellValueFactory(new PropertyValueFactory("dtvencimento"));
        coltipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontato.setCellValueFactory(new PropertyValueFactory("contato"));
    }
    
    private void initColT2() {
        
        colcod1.setCellValueFactory(new PropertyValueFactory("cod"));
        colvenda1.setCellValueFactory(new PropertyValueFactory("ven_cod"));
        colparcela1.setCellValueFactory(new PropertyValueFactory("parcela"));
        colvalor1.setCellValueFactory(new PropertyValueFactory("valor"));
        coldatapag.setCellValueFactory(new PropertyValueFactory("dtvencimento"));
        coltipo1.setCellValueFactory(new PropertyValueFactory("tipo"));
        colcontato1.setCellValueFactory(new PropertyValueFactory("contato"));
    }
    
    private void estado1(boolean b) {
        
        btquitar.setDisable(b);
        btcancelar.setDisable(b);
        if(b)
            carregaTabelaReceber("");
    }
    
    private void estado2(boolean b) {
        
        btestornar.setDisable(b);
        btcancelar1.setDisable(b);
        if(b)
            carregaTabelaRecebidas("");
    }
    
    private void carregaTabelaReceber(String filtro) {
        
        DALContasReceber dal = new DALContasReceber();
        List<ContasReceber> res = dal.getListRec1(filtro);
        ObservableList<ContasReceber> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvrecebimentos.setItems(modelo);
    }
    
    private void carregaTabelaRecebidas(String filtro) {
        
        DALContasReceber dal = new DALContasReceber();
        List<ContasReceber> res = dal.getListRec2(filtro);
        ObservableList<ContasReceber> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvrecebidas.setItems(modelo);
    }
    
    private void listaCategoria() {
        
        List<String> list = new ArrayList();
        
        list.add("");
        list.add("Código");
        list.add("Contato");
        list.add("Valor");
        list.add("Venda");
        
        cbcategoria.setItems(FXCollections.observableArrayList(list));
        cbcategoria1.setItems(FXCollections.observableArrayList(list));
    }
    
    private void esperaLista(){
        
        cbcategoria.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                switch (cbcategoria.getSelectionModel().getSelectedIndex()) {

                    case 0:
                        carregaTabelaReceber("");
                        break;
                    case 1:
                        MaskFieldUtil.numericField(tfiltro);
                        break;
                    case 2:
                        MaskFieldUtil.foneField(tfiltro);
                        break;
                    case 3:
                        MaskFieldUtil.numericField(tfiltro);
                        break;
                    case 4:
                        MaskFieldUtil.numericField(tfiltro);
                        break;    
                    default:
                        break;
                }
            }
        });
    }
    
    private void limparCampos1() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
            cbcategoria.getSelectionModel().select(-1);
        }
    }
    
    private void limparCampos2() {
        
        ObservableList <Node> componentes = pndados1.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
            cbcategoria1.getSelectionModel().select(-1);
        }
    }
    
    private String auxSql(int op){
        
        String sql = "";
        
        if(cbcategoria.getSelectionModel().getSelectedIndex() != -1 && op == 1){

            switch (cbcategoria.getSelectionModel().getSelectedIndex()) {

                case 1:
                    if(!"".equals(tfiltro.getText()))
                        sql = "(rec_cod = " + Integer.parseInt(tfiltro.getText()) + " OR rec_cod >= " + (Integer.parseInt(tfiltro.getText()) * 10) + " AND cod_cod <= " + (Integer.parseInt(tfiltro.getText()) * 10 + 9) + ")";
                    else
                        sql = "";
                    break;
                case 2:
                    sql = "UPPER(rec_contato) LIKE '%" + tfiltro.getText().toUpperCase() + "%'";
                    break;
                case 3:
                    if(!"".equals(tfiltro.getText()))
                        sql = "(rec_valor = " + Double.parseDouble(tfiltro.getText()) + " OR rec_valor >= " + (Double.parseDouble(tfiltro.getText()) * 10) + " AND rec_valor <= " + (Double.parseDouble(tfiltro.getText()) * 10 + 9) + ")";
                    else
                        sql = "";
                    break;
                case 4:
                    if(!"".equals(tfiltro.getText()))
                        sql = "(ven_cod = " + Integer.parseInt(tfiltro.getText()) + " OR ven_cod >= " + (Integer.parseInt(tfiltro.getText()) * 10) + " AND ven_cod <= " + (Integer.parseInt(tfiltro.getText()) * 10 + 9) + ")";
                    else
                        sql = "";
                    break;    
                default:
                    break;
            }
        }
        else if(cbcategoria1.getSelectionModel().getSelectedIndex() != -1 && op == 2){

            switch (cbcategoria1.getSelectionModel().getSelectedIndex()) {

                case 1:
                    if(!"".equals(tfiltro1.getText()))
                        sql = "(rec_cod = " + Integer.parseInt(tfiltro1.getText()) + " OR rec_cod >= " + (Integer.parseInt(tfiltro1.getText()) * 10) + " AND cod_cod <= " + (Integer.parseInt(tfiltro1.getText()) * 10 + 9) + ")";
                    else
                        sql = "";
                    break;
                case 2:
                    sql = "UPPER(rec_contato) LIKE '%" + tfiltro1.getText().toUpperCase() + "%'";
                    break;
                case 3:
                    if(!"".equals(tfiltro1.getText()))
                        sql = "(rec_valor = " + Double.parseDouble(tfiltro1.getText()) + " OR rec_valor >= " + (Double.parseDouble(tfiltro1.getText()) * 10) + " AND rec_valor <= " + (Double.parseDouble(tfiltro1.getText()) * 10 + 9) + ")";
                    else
                        sql = "";
                    break;
                case 4:
                    if(!"".equals(tfiltro1.getText()))
                        sql = "(ven_cod = " + Integer.parseInt(tfiltro1.getText()) + " OR ven_cod >= " + (Integer.parseInt(tfiltro1.getText()) * 10) + " AND ven_cod <= " + (Integer.parseInt(tfiltro1.getText()) * 10 + 9) + ")";
                    else
                        sql = "";
                    break;    
                default:
                    break;
            }
        }
        
        return sql;
    }

    @FXML
    private void clkBtQuitar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        ButtonType btok = new ButtonType("Ok");

        a.getButtonTypes().setAll(btsim,btnao);
        if(tvrecebimentos.getSelectionModel().getSelectedIndex() != -1){
            
            a.setHeaderText("Quitar!");
            a.setTitle("Quitar Conta");
            a.setContentText("Confirma a quitação da conta?");
            if (a.showAndWait().get() == btsim){
                
                DALContasReceber dal = new DALContasReceber();
                ContasReceber cr;
                
                cr = dal.getCon1(tvrecebimentos.getSelectionModel().getSelectedItem().getCod());
                if(dal.quitar(cr)){
                    
                    carregaTabelaReceber("");
                    carregaTabelaRecebidas("");
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Conta quitada com Sucesso!")));
                }
                else{

                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Conta não quitada!");
                    a.getButtonTypes().setAll(btok);
                    a.showAndWait();
                }
            }
        }
        else{
            
            a.setAlertType(Alert.AlertType.WARNING);
            a.setTitle("Selecionar");
            a.setHeaderText("Selecionar");
            a.setContentText("Nenhuma conta selecionada");
            a.showAndWait();
        }
    }

    @FXML
    private void clkTFiltro(KeyEvent event) {
        
        if(cbcategoria.getSelectionModel().getSelectedIndex() != -1){
            
            switch (cbcategoria.getSelectionModel().getSelectedIndex()) {
                
                case 1:
                    if(!"".equals(tfiltro.getText()))
                        carregaTabelaReceber("(rec_cod = " + Integer.parseInt(tfiltro.getText()) + " OR rec_cod >= " + (Integer.parseInt(tfiltro.getText()) * 10) + " AND rec_cod <= " + (Integer.parseInt(tfiltro.getText()) * 10 + 9) + ")");
                    else
                        carregaTabelaReceber("");
                    break;
                case 2:
                    carregaTabelaReceber("UPPER(rec_contato) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 3:
                    if(!"".equals(tfiltro.getText()))
                        carregaTabelaReceber("(rec_valor = " + Double.parseDouble(tfiltro.getText()) + " OR rec_valor >= " + (Double.parseDouble(tfiltro.getText()) * 10) + " AND rec_valor <= " + (Double.parseDouble(tfiltro.getText()) * 10 + 9) + ")");
                    else
                        carregaTabelaReceber("");
                    break;
                case 4:
                    if(!"".equals(tfiltro.getText()))
                        carregaTabelaReceber("(ven_cod = " + Integer.parseInt(tfiltro.getText()) + " OR ven_cod >= " + (Integer.parseInt(tfiltro.getText()) * 10) + " AND ven_cod <= " + (Integer.parseInt(tfiltro.getText()) * 10 + 9) + ")");
                    else
                        carregaTabelaReceber("");
                    break;    
                default:
                    break;
            }
        }
    }

    @FXML
    private void clkBtLimpar(ActionEvent event) {
        
        String sql = auxSql(1);
        
        dpdatainicial.setValue(null);
        dpdatafinal.setValue(null);
        carregaTabelaReceber(sql);
    }

    @FXML
    private void clkBtFiltrar(ActionEvent event) {
        
        String sql = auxSql(1);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(dpdatafinal.getValue() == null && dpdatainicial.getValue() != null){
            
            if("".equals(sql))
                carregaTabelaReceber("rec_dtvencimento >= '" + dpdatainicial.getValue() + "'");
            else
                carregaTabelaReceber(sql + " AND rec_dtvencimento >= '" + dpdatainicial.getValue() + "'");
        } 
        else if(dpdatainicial.getValue() == null && dpdatafinal.getValue() != null){
            
            if("".equals(sql))
                carregaTabelaReceber("rec_dtvencimento <= '" + dpdatafinal.getValue() + "'");
            else
                carregaTabelaReceber(sql + "AND rec_dtvencimento <= '" + dpdatafinal.getValue() + "'");
        }  
        else if(dpdatainicial.getValue() != null && dpdatafinal.getValue() != null){
            
            if("".equals(sql))
                carregaTabelaReceber("rec_dtvencimento >= '" + dpdatainicial.getValue() + "' AND rec_dtvencimento <= '" + dpdatafinal.getValue() + "'");    
            else
                carregaTabelaReceber(sql + "AND rec_dtvencimento >= '" + dpdatainicial.getValue() + "' AND rec_dtvencimento <= '" + dpdatafinal.getValue() + "'");
        }  
        else{
            
            a.setContentText("Selecione um período!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
    }

    @FXML
    private void clkTabela(MouseEvent event) {
        
        estado1(false);
        if(tvrecebimentos.getSelectionModel().getSelectedIndex() >= 0){
            
            if(tvrecebimentos.getSelectionModel().getSelectedItem() != null){
                
                ContasReceber cr = (ContasReceber)tvrecebimentos.getSelectionModel().getSelectedItem();
                
                tcodigo.setText("" + cr.getCod());
                tcodvenda.setText("" + cr.getVen_cod());
                tparcelas.setText("" + cr.getParcela());
                tvalor.setText("" + cr.getValor());
                ttipo.setText(cr.getTipo());
                dpdatavenc.setValue(cr.getDtvencimento());
                tcontato.setText(cr.getContato());
            }
        }
    }

    @FXML
    private void clkBtEstonar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        ButtonType btok = new ButtonType("Ok");

        a.getButtonTypes().setAll(btsim,btnao);
        if(tvrecebidas.getSelectionModel().getSelectedIndex() != -1){
            
            a.setHeaderText("Estornar!");
            a.setTitle("Estornar Conta");
            a.setContentText("Confirma o estorno da conta?");
            if (a.showAndWait().get() == btsim){
                
                DALContasReceber dal = new DALContasReceber();
                ContasReceber cr;
                
                cr = dal.getCon2(tvrecebidas.getSelectionModel().getSelectedItem().getCod());
                if(dal.estornar(cr)){
                    
                    carregaTabelaReceber("");
                    carregaTabelaRecebidas("");
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Conta quitada com Sucesso!")));
                }
                else{

                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Conta não estornada!");
                    a.getButtonTypes().setAll(btok);
                    a.showAndWait();
                }
            }
        }
        else{
            
            a.setAlertType(Alert.AlertType.WARNING);
            a.setTitle("Selecionar");
            a.setHeaderText("Selecionar");
            a.setContentText("Nenhuma conta selecionada");
            a.showAndWait();
        }
    }

    @FXML
    private void clkTFiltro1(KeyEvent event) {
        
        if(cbcategoria1.getSelectionModel().getSelectedIndex() != -1){
            
            switch (cbcategoria1.getSelectionModel().getSelectedIndex()) {
                
                case 1:
                    if(!"".equals(tfiltro1.getText()))
                        carregaTabelaReceber("(rec_cod = " + Integer.parseInt(tfiltro1.getText()) + " OR rec_cod >= " + (Integer.parseInt(tfiltro1.getText()) * 10) + " AND rec_cod <= " + (Integer.parseInt(tfiltro1.getText()) * 10 + 9) + ")");
                    else
                        carregaTabelaReceber("");
                    break;
                case 2:
                    carregaTabelaReceber("UPPER(rec_contato) LIKE '%" + tfiltro1.getText().toUpperCase() + "%'");
                    break;
                case 3:
                    if(!"".equals(tfiltro1.getText()))
                        carregaTabelaReceber("(rec_valor = " + Double.parseDouble(tfiltro1.getText()) + " OR rec_valor >= " + (Double.parseDouble(tfiltro1.getText()) * 10) + " AND rec_valor <= " + (Double.parseDouble(tfiltro1.getText()) * 10 + 9) + ")");
                    else
                        carregaTabelaReceber("");
                    break;
                case 4:
                    if(!"".equals(tfiltro1.getText()))
                        carregaTabelaReceber("(ven_cod = " + Integer.parseInt(tfiltro1.getText()) + " OR ven_cod >= " + (Integer.parseInt(tfiltro1.getText()) * 10) + " AND ven_cod <= " + (Integer.parseInt(tfiltro1.getText()) * 10 + 9) + ")");
                    else
                        carregaTabelaReceber("");
                    break;    
                default:
                    break;
            }
        }
    }

    @FXML
    private void clkBtLimpar1(ActionEvent event) {
        
        String sql = auxSql(2);
        
        dpdatainicial1.setValue(null);
        dpdatafinal1.setValue(null);
        carregaTabelaReceber(sql);
    }

    @FXML
    private void clkBtFiltrar1(ActionEvent event) {
        
        String sql = auxSql(2);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(dpdatafinal1.getValue() == null && dpdatainicial1.getValue() != null){
            
            if("".equals(sql))
                carregaTabelaReceber("rec_dtpagamento >= '" + dpdatainicial1.getValue() + "'");
            else
                carregaTabelaReceber(sql + " AND rec_dtpagamento >= '" + dpdatainicial1.getValue() + "'");
        } 
        else if(dpdatainicial1.getValue() == null && dpdatafinal1.getValue() != null){
            
            if("".equals(sql))
                carregaTabelaReceber("rec_dtpagamento <= '" + dpdatafinal1.getValue() + "'");
            else
                carregaTabelaReceber(sql + "AND rec_dtpagamento <= '" + dpdatafinal1.getValue() + "'");
        }  
        else if(dpdatainicial1.getValue() != null && dpdatafinal1.getValue() != null){
            
            if("".equals(sql))
                carregaTabelaReceber("rec_dtpagamento >= '" + dpdatainicial1.getValue() + "' AND rec_dtpagamento <= '" + dpdatafinal1.getValue() + "'");    
            else
                carregaTabelaReceber(sql + "AND rec_dtpagamento >= '" + dpdatainicial1.getValue() + "' AND rec_dtpagamento <= '" + dpdatafinal1.getValue() + "'");
        }  
        else{
            
            a.setContentText("Selecione um período!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
    }

    @FXML
    private void clkTabelaRecebidas(MouseEvent event) {
        
        estado2(false);
        if(tvrecebidas.getSelectionModel().getSelectedIndex() >= 0){
            
            if(tvrecebidas.getSelectionModel().getSelectedItem() != null){
                
                ContasReceber cr = (ContasReceber)tvrecebidas.getSelectionModel().getSelectedItem();
                
                tcodigo1.setText("" + cr.getCod());
                tcodvenda1.setText("" + cr.getVen_cod());
                tparcelas1.setText("" + cr.getParcela());
                tvalor1.setText("" + cr.getValor());
                ttipo1.setText(cr.getTipo());
                dpdatapag.setValue(cr.getDtvencimento());
                tcontato1.setText(cr.getContato());
            }
        }
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        
        if(tabreceber.isSelected()){
            
            estado1(true);
            limparCampos1();
            pnpesquisa.setDisable(false);
        }
        
        if(tabrecebidas.isSelected()){
            
            estado2(true);
            limparCampos2();
            pnpesquisa1.setDisable(false);
        }
    }
}