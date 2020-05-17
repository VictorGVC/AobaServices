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
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPane.Divider;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import malucismanagement.db.dal.DALCliente;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Cliente;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.ManipularCpfCnpj;
import malucismanagement.util.MaskFieldUtil;
import malucismanagement.util.SQLException_Exception;
import malucismanagement.util.SigepClienteException;

public class TelaClientesController implements Initializable {

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
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private JFXButton btvoltar;
    @FXML
    private Pane pndados;
    @FXML
    private JFXTextField tcpf;
    @FXML
    private JFXTextField tnome;
    @FXML
    private JFXTextField ttelefone;
    @FXML
    private JFXTextField trua;
    @FXML
    private JFXComboBox<String> cbsexo;
    @FXML
    private JFXTextField tnumero;
    @FXML
    private JFXTextField tbairro;
    @FXML
    private JFXDatePicker dpdatanasc;
    @FXML
    private JFXTextField tcep;
    @FXML
    private JFXTextField temail;
    @FXML
    private JFXTextField tcidade;
    @FXML
    private JFXTextField tuf;
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
    private TableView<Cliente> tvclientes;
    @FXML
    private TableColumn<Cliente, String> colcpf;
    @FXML
    private TableColumn<Cliente, String> colnome;
    @FXML
    private TableColumn<Cliente, String> colemail;
    @FXML
    private TableColumn<Cliente, String> coltelefone;
    @FXML
    private TableColumn<Cliente, String> colcep;
    @FXML
    private TableColumn<Cliente, String> colrua;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
        fixaDivider();
        setParametros();
        setMascaras();
        initColTb();
        listaSexo();
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
        
        Divider divider = pnprincipal.getDividers().get(0);
        divider.positionProperty().addListener(new ChangeListener<Number>() {
            
            @Override 
            public void changed(ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue) {
                
                divider.setPosition(0.52);
            }
        });
    }
    
    private void setParametros() {
        
        DALParametrizacao dal = new DALParametrizacao();
        Parametrizacao p = dal.getConfig();
        
        if(p.getCorprimaria() != null){
            
            pndados.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
            tvclientes.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
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
            
            tcpf.setFont(new Font(p.getFonte(), 14));
            tnome.setFont(new Font(p.getFonte(), 14));
            temail.setFont(new Font(p.getFonte(), 14));
            ttelefone.setFont(new Font(p.getFonte(), 14));
            tcep.setFont(new Font(p.getFonte(), 14));
            trua.setFont(new Font(p.getFonte(), 14));
            tnumero.setFont(new Font(p.getFonte(), 14));
            tbairro.setFont(new Font(p.getFonte(), 14));
            tcidade.setFont(new Font(p.getFonte(), 14));
            tuf.setFont(new Font(p.getFonte(), 14));
            lbobg.setFont(new Font(p.getFonte(), 12));
            
            tfiltro.setFont(new Font(p.getFonte(), 14));
        }
        if(p.getCorfonte() != null){
            
            btnovo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btalterar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btapagar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btconfirmar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btcancelar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            btvoltar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tcpf.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tnome.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            temail.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            ttelefone.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcep.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            trua.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tnumero.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tbairro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tcidade.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            tuf.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
            
            tfiltro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
        }
    }
    
    private void setMascaras() {
        
        MaskFieldUtil.maxField(tnome, 30);
        MaskFieldUtil.cpfField(tcpf);
        MaskFieldUtil.maxField(temail, 50);
        MaskFieldUtil.foneField(ttelefone);
        MaskFieldUtil.cepField(tcep);
        MaskFieldUtil.maxField(trua, 60);
        MaskFieldUtil.numericField(tnumero);
        MaskFieldUtil.maxField(tbairro, 40);
        MaskFieldUtil.maxField(tcidade, 30);
        MaskFieldUtil.maxField(tuf, 2);
        dpdatanasc.setValue(LocalDate.now());
    }
    
    private void initColTb() {
        
        colcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        colemail.setCellValueFactory(new PropertyValueFactory("email"));
        coltelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colcep.setCellValueFactory(new PropertyValueFactory("cep"));
        colrua.setCellValueFactory(new PropertyValueFactory("rua"));
    }
    
    private void estado(boolean b) {
        
        pndados.setDisable(b);
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btapagar.setDisable(!b);
        btalterar.setDisable(!b);
        btnovo.setDisable(!b);
      
        carregaTabela("");
    }
    
    private void carregaTabela(String filtro) {
        
        DALCliente dal = new DALCliente();
        List<Cliente> res = dal.getListCli(filtro);
        ObservableList<Cliente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvclientes.setItems(modelo);
    }
    
    private void listaSexo() {
        
        List<String> list = new ArrayList();
        
        list.add("Masculino");
        list.add("Feminino");
        
        cbsexo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void listaCategoria() {
        
        List<String> list = new ArrayList();
        
        list.add("");
        list.add("CPF");
        list.add("Nome");
        list.add("E-Mail");
        list.add("Telefone");
        list.add("CEP");
        list.add("Rua");
        
        cbcategoria.setItems(FXCollections.observableArrayList(list));
    }
    
    private void limparCampos() {
        
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) {
            
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
            cbsexo.getSelectionModel().select(-1);
            cbcategoria.getSelectionModel().select(-1);
        }
    }
    
    private void setCorAlert(String cor){
        
        setCorAlert(tcpf, cor);
        setCorAlert(tnome, cor);
        setCorAlert(ttelefone, cor);
        setCorAlert(tcep, cor);
        setCorAlert(tcidade, cor);
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
        
        if(tvclientes.getSelectionModel().getSelectedIndex() != -1){
            
            estado(false);
            tcpf.setDisable(true);
            pnpesquisa.setDisable(false);
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
        if(tvclientes.getSelectionModel().getSelectedIndex() != -1){
            
            a.setHeaderText("Exclusão!");
            a.setTitle("Exclusão");
            a.setContentText("Confirma a exclusão?");
            if (a.showAndWait().get() == btsim){
                
                DALCliente dal = new DALCliente();
                Cliente c;
                c = tvclientes.getSelectionModel().getSelectedItem();
                if(dal.apagar(c)){
                    
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Excluído com Sucesso!")));
                }
                else{
                    
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Exclusão não permitida, esse cliente é um funcionário!");
                    a.getButtonTypes().setAll(btok);
                    a.showAndWait();
                }
                carregaTabela("");
                limparCampos();
            }
        }
        else{
            
            a.setAlertType(Alert.AlertType.WARNING);
            a.setTitle("Selecionar");
            a.setHeaderText("Selecionar");
            a.setContentText("Nenhum cliente selecionado");
            a.showAndWait();
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
        
        String id;
        boolean flag = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        setCorAlert("BLACK");
        if(tcpf.getText().isEmpty()){
            
            flag = true;
            setCorAlert(tcpf, "RED");
        }
        if(!ManipularCpfCnpj.isCpf(tcpf.getText())){
            
            setCorAlert(tcpf, "RED");
            a.setContentText("CPF inválido!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        if(tnome.getText().isEmpty()){
            
            flag = true;
            setCorAlert(tnome, "RED");
        }
        if(ttelefone.getText().isEmpty()){
            
            flag = true;
            setCorAlert(ttelefone, "RED");
        }
        if(tcep.getText().isEmpty()){
            
            flag = true;
            setCorAlert(tcep, "RED");
        }
        if(tcidade.getText().isEmpty()){
            
            flag = true;
            setCorAlert(tcidade, "RED");
        }
        if(tuf.getText().isEmpty()){
            
            flag = true;
            setCorAlert(tuf, "RED");
        }
        if(flag){
            
            a.setContentText("Campos obrigatórios não preenchidos!");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
        }
        else if(ManipularCpfCnpj.isCpf(tcpf.getText())){
            
            try {
                id = tcpf.getText();
            } 
            catch (NumberFormatException e) {
                id = "";
            }
            
            char sexo = ' ';
            
            if(cbsexo.getSelectionModel().getSelectedIndex() == 0)
                sexo = 'M';
            else if(cbsexo.getSelectionModel().getSelectedIndex() == 1)
                sexo = 'F';
            int num = -1;
            if(!tnumero.getText().isEmpty())
                num = Integer.parseInt(tnumero.getText());
            Cliente c = new Cliente(num, sexo,
                                    tnome.getText(),
                                    id,
                                    temail.getText(),
                                    ttelefone.getText(),
                                    tcep.getText(),
                                    trua.getText(),
                                    tbairro.getText(),
                                    tcidade.getText(),
                                    tuf.getText(),
                                    dpdatanasc.getValue());
            DALCliente dal = new DALCliente();

            if(pnpesquisa.isDisable()){
                
                if (dal.gravar(c)){
                    
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
                if (dal.alterar(c)){
                    
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Alterado com Sucesso!")));
                }
                else{
                    
                    a.setContentText("Problemas ao Alterar!");
                    a.showAndWait();
                }
            }
            estado(true);
            limparCampos();
            pnpesquisa.setDisable(false);
        }
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        
        if (!pndados.isDisabled()){
            
            estado(true);
            limparCampos();
        }
        pnpesquisa.setDisable(false);
    }

    @FXML
    private void evtCpfDigitado(KeyEvent event) {
        
        Alert a = new Alert(Alert.AlertType.WARNING);
        
        setCorAlert(tcpf, "BLACK");
        if(tcpf.getText().length() >= 13){
            
            Task task = new Task<Void>() {
                
                @Override
                protected Void call() {
                    
                    if(!ManipularCpfCnpj.isCpf(tcpf.getText())){
                        
                        setCorAlert(tcpf, "RED");
                        a.setTitle("Atenção!");
                        a.setHeaderText("CPF");
                        a.setContentText("CPF inválido!");
                    }
                    
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    @FXML
    private void evtCepDigitado(KeyEvent event) {
        
        if(tcep.getText().length() == 8){
            
            Task task = new Task<Void>() {
                
                @Override
                protected Void call() {
                    
                    String cep = tcep.getText().replaceAll("\\-", "");
                    malucismanagement.util.AtendeClienteService service = new malucismanagement.util.AtendeClienteService();
                    malucismanagement.util.AtendeCliente port = service.getAtendeClientePort();

                    try {

                        malucismanagement.util.EnderecoERP result = port.consultaCEP(cep);

                        trua.setText(result.getEnd());
                        tbairro.setText(result.getBairro());
                        tcidade.setText(result.getCidade());
                        tuf.setText(result.getUf());
                    }
                    catch (SQLException_Exception | SigepClienteException e) {}
                    
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    @FXML
    private void clkTFiltro(KeyEvent event) {
        
        if(cbcategoria.getSelectionModel().getSelectedIndex() != -1){
            
            switch (cbcategoria.getSelectionModel().getSelectedIndex()) {
                
                case 1:
                    carregaTabela("UPPER(cli_id) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 2:
                    carregaTabela("UPPER(cli_nome) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 3:
                    carregaTabela("UPPER(cli_email) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 4:
                    carregaTabela("UPPER(cli_fone) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 5:
                    carregaTabela("UPPER(cli_cep) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 6:
                    carregaTabela("UPPER(cli_rua) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                default:
                    break;
            }
        }
    } 

    @FXML
    private void clkTabela(MouseEvent event) {
        
        if(tvclientes.getSelectionModel().getSelectedIndex() >= 0){
            
            if(tvclientes.getSelectionModel().getSelectedItem() != null){
                
                Cliente c = (Cliente)tvclientes.getSelectionModel().getSelectedItem();
                
                tcpf.setText(c.getCpf());
                tnome.setText(c.getNome());
                if(c.getSexo() == 'M')
                    cbsexo.getSelectionModel().selectFirst();
                else if(c.getSexo() == 'F')
                    cbsexo.getSelectionModel().selectLast();
                dpdatanasc.setValue(c.getDatanasc());
                temail.setText(c.getEmail());
                ttelefone.setText(c.getTelefone());
                tcep.setText(c.getCep());
                trua.setText(c.getRua());
                if(c.getNumero() != -1)
                    tnumero.setText("" + c.getNumero());
                tbairro.setText(c.getBairro());
                tcidade.setText(c.getCidade());
                tuf.setText(c.getUf());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
            }
        }
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
        
        Stage stage = (Stage) btvoltar.getScene().getWindow();
        stage.close();
    }
}