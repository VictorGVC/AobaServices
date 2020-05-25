package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import malucismanagement.db.dal.DALItensVenda;
import malucismanagement.db.dal.DALMarcas;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.dal.DALProdmarcas;
import malucismanagement.db.dal.DALProduto;
import malucismanagement.db.dal.DALVenda;
import malucismanagement.db.entidades.ItensVenda;
import malucismanagement.db.entidades.Marcas;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.db.entidades.Prodmarcas;
import malucismanagement.db.entidades.Produto;
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
    private JFXComboBox<String> cbcategoria;
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
    private JFXTextField tcodigo;
    @FXML
    private JFXTextField ttotal;
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
        codBarrasEnter();
        qtdeEnter();
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
                
                divider.setPosition(0.45);
            }
        });
    }
    
    private void setParametros() {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p.getCorprimaria() != null){
            
            pndados.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            pndados2.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvprodutos.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvvendas.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
        }
        if(p.getCorsecundaria()!= null){
            
            pnbotoes.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
            pnfiltros.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
        }
        if(p.getFonte() != null){
            
            btnovo.setFont(new Font(p.getFonte(), 12));
            btalterar.setFont(new Font(p.getFonte(), 12));
            btapagar.setFont(new Font(p.getFonte(), 12));
            btconfirmar.setFont(new Font(p.getFonte(), 12));
            btcancelar.setFont(new Font(p.getFonte(), 12));
            btvoltar.setFont(new Font(p.getFonte(), 12));
            
            tcodigodebarras.setFont(new Font(p.getFonte(), 14));
            tproduto.setFont(new Font(p.getFonte(), 14));
            tpreco.setFont(new Font(p.getFonte(), 14));
            tmarca.setFont(new Font(p.getFonte(), 14));
            tqtde.setFont(new Font(p.getFonte(), 14));
            btadicionar.setFont(new Font(p.getFonte(), 12));
            btremover.setFont(new Font(p.getFonte(), 12));
            lbobg.setFont(new Font(p.getFonte(), 12));
            
            tfiltro.setFont(new Font(p.getFonte(), 14));
            
            tcodigo.setFont(new Font(p.getFonte(), 14));
            ttotal.setFont(new Font(p.getFonte(), 14));
        }
        if(p.getCorfonte() != null){
            
            btnovo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btalterar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btapagar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btconfirmar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btvoltar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tcodigodebarras.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tproduto.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tpreco.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tmarca.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tqtde.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btadicionar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btremover.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tfiltro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tcodigo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            ttotal.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void setMascaras() {
        
        MaskFieldUtil.maxField(tcodigodebarras, 20);
        MaskFieldUtil.maxField(tproduto, 50);
        MaskFieldUtil.monetaryField(tpreco);
        MaskFieldUtil.maxField(tmarca, 50);
        MaskFieldUtil.numericField(tpreco);
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
      
        //carregaTabelaVendas("");
    }
    
    private void codBarrasEnter(){
        
        tcodigodebarras.setOnKeyPressed(k ->{
            
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if(ENTER.match(k)) {
                try {
                    buscaProduto();
                    tqtde.requestFocus();
                } 
                catch (IOException ex) {}
            }
        });
    }
    
    private void qtdeEnter(){
        
        tqtde.setOnKeyPressed(k ->{
            
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if(ENTER.match(k)) {
                clkBtAdicionar(null);
            }
        });
    }
    
    private void buscaProduto() throws IOException{
        
        DALProduto dal = new DALProduto();
        DALProdmarcas dalpm = new DALProdmarcas();
        DALMarcas dalm = new DALMarcas();
        Produto p = dal.getProdutoCod(tcodigodebarras.getText());
        Prodmarcas pm = null;
        Marcas m = null;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        
        a.getButtonTypes().setAll(btsim,btnao);
        if(p != null){
            
            pm = dalpm.getProdEMarca(tcodigodebarras.getText());
            m = dalm.getMarca(pm.getMar_cod());
            tproduto.setText(p.getPro_nome());
            tpreco.setText("" + p.getPro_preco());
            tmarca.setText(m.getMar_nome());
        }
        else{
            
            a.setHeaderText("Atenção!");
            a.setTitle("Atenção");
            a.setContentText("Produto não encontrado, deseja cadastrar-lo?");
            if (a.showAndWait().get() == btsim){
                
                Parent root = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
                stage.setTitle("Produtos");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            }
        }
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
        
        List<String> list = new ArrayList();
        
        list.add("");
        list.add("Código");
        list.add("Cliente");
        list.add("Data");
        list.add("Valor");
        
        cbcategoria.setItems(FXCollections.observableList(list));
    }
    
    private void limparCampos1() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
    }
    
    private void limparCampos2() {
        
        ObservableList <Node> componentes = pndados2.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
        }
    }
    
    private void setCorAlert(String cor){
        
        setCorAlert(tcodigodebarras, cor);
        setCorAlert(tqtde, cor);
    }
    
    private void setCorAlert(JFXTextField tf, String cor){
        
        tf.setFocusColor(Paint.valueOf(cor));
        tf.setUnFocusColor(Paint.valueOf(cor));
    }
    
    @FXML
    private void clkBtNovo(ActionEvent event) {
        
        estado(false);
        pnfiltros.setDisable(true);
        tvvendas.setDisable(true);
        tcodigodebarras.requestFocus();
        limparCampos1();
        limparCampos2();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {
        
        if(tvvendas.getSelectionModel().getSelectedIndex() != -1){
            
            estado(false);
            pnfiltros.setDisable(false);
            tvvendas.setDisable(false);
        }
        else{
            
            JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
            sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Selecione algum cliente!")));
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        ButtonType btok = new ButtonType("Ok");

        a.getButtonTypes().setAll(btsim,btnao);
        if(tvvendas.getSelectionModel().getSelectedIndex() != -1){
            
            a.setHeaderText("Exclusão!");
            a.setTitle("Exclusão");
            a.setContentText("Confirma a exclusão?");
            if (a.showAndWait().get() == btsim){
                
                DALItensVenda dali = new DALItensVenda();
                ItensVenda i;
                
                i = dali.getVenda("" + tvvendas.getSelectionModel().getSelectedItem().getCod());
                if(dali.apagarItens(i)){
                 
                    DALVenda dal = new DALVenda();
                    Venda v;

                    v = tvvendas.getSelectionModel().getSelectedItem();
                    if(dal.apagar(v)){

                        JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                        sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Excluído com Sucesso!")));
                    }
                    else{

                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setHeaderText("ERRO");
                        a.setTitle("ERRO!");
                        a.setContentText("Exclusão da venda não realizada!");
                        a.getButtonTypes().setAll(btok);
                        a.showAndWait();
                    }
                    carregaTabelaVendas("");
                    limparCampos1();
                    limparCampos2();
                }
                else{

                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Exclusão de itens não realizada!");
                    a.getButtonTypes().setAll(btok);
                    a.showAndWait();
                }
            }
        }
        else{
            
            a.setAlertType(Alert.AlertType.WARNING);
            a.setTitle("Selecionar");
            a.setHeaderText("Selecionar");
            a.setContentText("Nenhuma venda selecionada");
            a.showAndWait();
        }
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        
        if (!pndados.isDisabled() && !pndados2.isDisabled()){
            
            estado(true);
            limparCampos1();
            limparCampos2();
        }
        pnfiltros.setDisable(false);
        tvvendas.setDisable(false);
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
        
        Stage stage = (Stage) btvoltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkBtAdicionar(ActionEvent event) {
        
        int cod;
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        setCorAlert("BLACK");
        if(tcodigodebarras.getText().isEmpty()){
            
            flag = true;
            setCorAlert(tcodigodebarras, "RED");
        }
        if(tqtde.getText().isEmpty()){
            
            flag = true;
            setCorAlert(tqtde, "RED");
        }
        if(flag){
            
            a.setContentText("Campos obrigatórios não preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else{
            
            try {
                cod = Integer.parseInt(tcodigo.getText());
            } 
            catch (NumberFormatException e) {
                cod = -1;
            }
            
            DALMarcas dalm = new DALMarcas();
            Marcas m = dalm.getMarca(tmarca.getText());
            ItensVenda i = new ItensVenda(cod, m.getMar_cod(), tcodigodebarras.getText(), Integer.parseInt(tqtde.getText()), Double.parseDouble(tpreco.getText()));
            DALItensVenda dal = new DALItensVenda();

            if (dal.gravar(i)){

                JFXSnackbar sb = new JFXSnackbar(pndados); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Adicionado!")));
            }
            else{

                a.setContentText("Problemas ao Adicionar!");
                a.showAndWait();
            }
            limparCampos1();
            tcodigodebarras.requestFocus();
        }
    }

    @FXML
    private void clkBtRemover(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btsim = new ButtonType("Sim");
        ButtonType btnao = new ButtonType("Não");
        ButtonType btok = new ButtonType("Ok");

        a.getButtonTypes().setAll(btsim,btnao);
        if(tvprodutos.getSelectionModel().getSelectedIndex() != -1){
            
            a.setHeaderText("Remoção!");
            a.setTitle("Remoção");
            a.setContentText("Confirma a remoção?");
            if (a.showAndWait().get() == btsim){
                
                DALItensVenda dal = new DALItensVenda();
                ItensVenda i;
                
                i = tvprodutos.getSelectionModel().getSelectedItem();
                if(dal.apagarProduto(i)){
                    
                    JFXSnackbar sb = new JFXSnackbar(pndados); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Removido com Sucesso!")));
                }
                else{
                    
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Remoção não realizada!");
                    a.getButtonTypes().setAll(btok);
                    a.showAndWait();
                }
                carregaTabelaProdutos("");
                limparCampos1();
            }
        }
        else{
            
            a.setAlertType(Alert.AlertType.WARNING);
            a.setTitle("Selecionar");
            a.setHeaderText("Selecionar");
            a.setContentText("Nenhum produto selecionado");
            a.showAndWait();
        }
    }
    
    @FXML
    private void clkTabelaProdutos(MouseEvent event) {
        
        DALProduto dal = new DALProduto();
        DALProdmarcas dalpm = new DALProdmarcas();
        DALMarcas dalm = new DALMarcas();
        Produto p = dal.getProdutoCod(tcodigodebarras.getText());
        Prodmarcas pm = dalpm.getProdEMarca(tcodigodebarras.getText());
        Marcas m = dalm.getMarca(pm.getMar_cod());
        
        if(tvprodutos.getSelectionModel().getSelectedIndex() >= 0){
            
            if(tvprodutos.getSelectionModel().getSelectedItem() != null){
                
                ItensVenda i = (ItensVenda)tvprodutos.getSelectionModel().getSelectedItem();
                
                tcodigodebarras.setText(i.getPro_cod());
                tproduto.setText(p.getPro_nome());
                tpreco.setText("" + p.getPro_preco());
                tmarca.setText(m.getMar_nome());
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
                
                tcodigo.setText("" + v.getCod());
                ttotal.setText("" + v.getValortotal());
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
    
    private void gravarVenda(){
        
        int cod;
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        try {
            cod = Integer.parseInt(tcodigo.getText());
        } 
        catch (NumberFormatException e) {
            cod = -1;
        }

        Venda v = new Venda(cod, Double.parseDouble(ttotal.getText()), dpdatavenda.getValue(), TelaGerarRecebimentoController.getCliente());
        DALVenda dal = new DALVenda();

        if(pnfiltros.isDisable()){

            if (dal.gravar(v)){

                JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Salvo com Sucesso!")));
            }
            else{

                a.setContentText("Problemas ao Gravar!");
                a.showAndWait();
            }
        }
        else
        {
            if (dal.alterar(v)){

                JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Alterado com Sucesso!")));
            }
            else{

                a.setContentText("Problemas ao Alterar!");
                a.showAndWait();
            }
        }
        estado(true);
        limparCampos1();
        limparCampos2();
        pnfiltros.setDisable(false);
        tvvendas.setDisable(false);
    }
    
    @FXML
    private void clkBtConfirmar(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("TelaGerarRecebimento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        TelaGerarRecebimentoController.setVenda(Integer.parseInt(tcodigo.getText()));
        stage.initStyle(StageStyle.UTILITY);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setTitle("Pagamento");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent t) {
                
                t.consume();
                stage.close();
                gravarVenda();
            }
        });
    }
}