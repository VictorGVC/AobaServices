package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import malucismanagement.db.dal.DALFuncionario;
import malucismanagement.db.dal.DALParametrizacao;
import malucismanagement.db.entidades.Funcionario;
import malucismanagement.db.entidades.Parametrizacao;
import malucismanagement.util.MaskFieldUtil;
import malucismanagement.util.SQLException_Exception;
import malucismanagement.util.SigepClienteException;

public class TelaFuncionariosController implements Initializable {

    private String Usuario;
    private String cpf;
    private char ativo;
    
    @FXML
    private SplitPane pnprincipal;
    @FXML
    private HBox pnbotoes;
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
    private JFXTextField tcpf;
    @FXML
    private JFXTextField tcep;
    @FXML
    private JFXTextField temail;
    @FXML
    private JFXTextField tcidade;
    @FXML
    private JFXTextField tuf;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private Pane pnfiltros;
    @FXML
    private JFXComboBox<String> cbcategoria;
    @FXML
    private JFXTextField tfiltro;
    @FXML
    private TableView<Funcionario> tvclientes;
    @FXML
    private TableColumn<Funcionario, String> colnome;
    @FXML
    private TableColumn<Funcionario, String> colcpf;
    @FXML
    private TableColumn<Funcionario, String> coltelefone;
    @FXML
    private TableColumn<Funcionario, String> collogin;
    @FXML
    private TableColumn<Funcionario, String> colemail;
    @FXML
    private TableColumn<Funcionario, String> colativo;
    @FXML
    private JFXComboBox<String> cbCargo;
    @FXML
    private JFXPasswordField txsenha;
    @FXML
    private JFXTextField txlogin;
    @FXML
    private JFXPasswordField txsenhan;
    @FXML
    private JFXButton btativdesativ;
    @FXML
    private JFXButton btnovo;
    
    private boolean pa;

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        pa = false;
        fadeout();
        setMascaras();
        initializeSexo();
        initializeCategoria();
        initializeCargo();
        initializeColunas();
        estado(true);
        setParametros();
        DALFuncionario dalf = new DALFuncionario();
        if(dalf.getL("").isEmpty())
            pa = true;
    }   
    
    private void fadeout() 
    {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void initializeColunas() {
        
        colcpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        coltelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colativo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
        collogin.setCellValueFactory(new PropertyValueFactory<>("login"));
    }
    
    private void initializeSexo()
    {
        List<String> list = new ArrayList();
        
        list.add("Masculino");
        list.add("Feminino");
        
        cbsexo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void initializeCategoria() 
    {
        List<String> list = new ArrayList();
        
        list.add("Usuário");
        list.add("Nome");
        list.add("CPF");
        list.add("E-Mail");
        list.add("Telefone");
        list.add("Ativo");
        
        cbcategoria.setItems(FXCollections.observableArrayList(list));
    }
    
    private void initializeCargo()
    {
        List<String> list = new ArrayList();
        
        list.add("Administrador");
        list.add("Vendedor");
        
        cbCargo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void limparCampos() 
    {
        ObservableList <Node> componentes = pndados.getChildren();
        
        for(Node n : componentes) 
        {
            if (n instanceof TextInputControl)
                ((TextInputControl)n).setText("");
            cbsexo.getSelectionModel().select(-1);
            cbCargo.getSelectionModel().select(-1);
            cbcategoria.getSelectionModel().select(-1);
        }
    }
    
    private void estado(boolean b) 
    {
        pndados.setDisable(b);
        btconfirmar.setDisable(b);
        btcancelar.setDisable(b);
        btapagar.setDisable(!b);
        btalterar.setDisable(!b);
        btativdesativ.setDisable(!b);
                
        carregaTabela("");
    }
    
    private void setParametros() 
    {
//        DALParametrizacao dal = new DALParametrizacao();
//        Parametrizacao p = dal.getConfig();
//        
//        if(p.getCorprimaria() != null)
//        {
//            pndados.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
//            tvclientes.setStyle("-fx-background-color: " + p.getCorprimaria() + ";");
//        }
//        if(p.getCorsecundaria()!= null)
//        {
//            pnbotoes.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
//            pnfiltros.setStyle("-fx-background-color: " + p.getCorsecundaria()+ ";");
//        }
//        if(p.getFonte() != null)
//        {
//            btalterar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            btapagar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            btconfirmar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            btcancelar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            btvoltar.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            
//            tcpf.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            tnome.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            cbsexo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            dpdatanasc.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            temail.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            ttelefone.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            tcep.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            trua.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            tnumero.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            tbairro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            tcidade.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            tuf.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txlogin.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            txsenha.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            
//            cbcategoria.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            tfiltro.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            cbCargo.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//            
//            tvclientes.setStyle("-fx-font-family: " + p.getFonte()+ ";");
//        }
//        if(p.getCorfonte() != null)
//        {
//            btalterar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            btapagar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            btconfirmar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            btcancelar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            btvoltar.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            
//            tcpf.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            tnome.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            cbsexo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            dpdatanasc.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            temail.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            ttelefone.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            tcep.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            trua.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            tnumero.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            tbairro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            tcidade.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            tuf.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            txsenha.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            txlogin.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            
//            cbcategoria.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            tfiltro.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            cbCargo.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//            
//            tvclientes.setStyle("-fx-text-fill: " + p.getCorfonte()+ ";");
//        }
    }
    
    private void setMascaras() 
    {
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
    }
    
    private void carregaTabela(String filtro) 
    {
        DALFuncionario dal = new DALFuncionario();
        List<Funcionario> res = dal.getL(filtro);
        ObservableList<Funcionario> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvclientes.setItems(modelo);
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) 
    {
        if(tvclientes.getSelectionModel().getSelectedIndex() != -1)
        {
            estado(false);
            tcpf.setDisable(true);
            txsenhan.setVisible(true);
            pnpesquisa.setDisable(false);
        }
        else
        {
            JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
            sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Selecione algum funcionario!")));
        }
        
    }

    @FXML
    private void clkBtApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvclientes.getSelectionModel().getSelectedIndex() != -1){
            
            a.setHeaderText("Exclusão!");
            a.setTitle("Exclusão");
            a.setContentText("Confirma a exclusão");
            if (a.showAndWait().get() == ButtonType.OK){
                
                DALFuncionario dal = new DALFuncionario();
                Funcionario f;
                f = tvclientes.getSelectionModel().getSelectedItem();
                if(dal.apagar(f)){
                    
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Excluído com Sucesso!")));
                }
                else{
                    
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setHeaderText("ERRO");
                    a.setTitle("ERRO!");
                    a.setContentText("Exclusão não realizada!");
                    a.showAndWait();
                }
                carregaTabela("");
                limparCampos();
            }
        }
        else{
            
            a.setTitle("Selecionar");
            a.setHeaderText("Selecionar");
            a.setContentText("Nenhum produto selecionado");
            a.showAndWait();
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) 
    {
        String id;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tcpf.getText().isEmpty())
        {
            a.setContentText("CPF deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tcpf.requestFocus();
        }
        else if(tnome.getText().isEmpty())
        {
            a.setContentText("Nome deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tnome.requestFocus();
        }
        else if(cbsexo.getSelectionModel().getSelectedIndex() == -1)
        {
            a.setContentText("Sexo deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbsexo.requestFocus();
        }
        else if(dpdatanasc.getValue() == null)
        {
            a.setContentText("Data de nascimento deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            dpdatanasc.requestFocus();
        }
        else if(temail.getText().isEmpty())
        {
            a.setContentText("E-Mail deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            temail.requestFocus();
        }
        else if(ttelefone.getText().isEmpty())
        {  
            a.setContentText("Telefone deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            ttelefone.requestFocus();
        }
        else if(tcep.getText().isEmpty())
        {
            a.setContentText("CEP deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tcep.requestFocus();
        }
        else if(tcidade.getText().isEmpty())
        {
            a.setContentText("Cidade deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tcidade.requestFocus();
        }
        else if(tuf.getText().isEmpty())
        {    
            a.setContentText("Estado deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tuf.requestFocus();
        }
        //começa aqui
        else if(txlogin.getText().isEmpty())
        {
            a.setContentText("Login deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txlogin.requestFocus();
        }
        else if(txsenha.getText().isEmpty())
        {   
            a.setContentText("Senha deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txsenha.requestFocus();
        }
        else if(cbCargo.getSelectionModel().getSelectedIndex() == -1)
        {
            a.setContentText("Estado deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbCargo.requestFocus();
        }
        else
        {    
            try 
            {
                id = tcpf.getText();
            } 
            catch (NumberFormatException e) 
            {
                id = "";
            }
            
            char sexo = ' ';
            
            if(cbsexo.getSelectionModel().getSelectedIndex() == 0)
                sexo = 'M';
            else if(cbsexo.getSelectionModel().getSelectedIndex() == 1)
                sexo = 'F';
                    
            Funcionario f = new Funcionario(Integer.parseInt(tnumero.getText()), sexo, 
                    tnome.getText(), id, temail.getText(), ttelefone.getText(), tcep.getText(),
                    trua.getText(), tbairro.getText(), tcidade.getText(), tuf.getText(), txlogin.getText(), 
                    dpdatanasc.getValue(), 
                    'l',
                    cbCargo.getSelectionModel().getSelectedIndex());
            DALFuncionario dal = new DALFuncionario();
            if(pnpesquisa.isDisable())
            {
                if (dal.gravar(f,txsenha.getText()))
                {
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Salvo com Sucesso!")));
                    estado(true);
                    limparCampos();
                    pnpesquisa.setDisable(false);
                    carregaTabela("");
                }
                else{
                    
                    a.setContentText("Problemas ao Gravar!");
                    a.showAndWait();
                }
            }
            else
                if (dal.alterar(f,txsenha.getText(),txsenhan.getText(),getUsuario(),getCpf()))
                {
                    JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
                    sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Alterado com Sucesso!")));
                    estado(true);
                    limparCampos();
                    pnpesquisa.setDisable(false);
                    carregaTabela("");
                }
                else
                {
                    a.setContentText("Problemas ao Alterar!");
                    a.showAndWait();
                }
        }
        
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) 
    {
         if (!pndados.isDisabled()){
            
            estado(true);
            limparCampos();
        }
        pnpesquisa.setDisable(false);
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) 
    {
        Stage stage = (Stage) btvoltar.getScene().getWindow();
        stage.close();
        DALParametrizacao dalp = new DALParametrizacao();
        if(dalp.getConfig() == null)
            try 
            {
                chamaConfig();
            } 
            catch (IOException ex) 
            {
                System.out.println(ex);
            }
        else if(pa)
            try 
            {
                chamaLogin();
            } 
            catch (IOException ex) 
            {
                System.out.println(ex);
            }
    }

    @FXML
    private void evtBotaoDigitado(KeyEvent event) 
    {
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
    private void clkTFiltro(KeyEvent event) 
    {
        if(cbcategoria.getSelectionModel().getSelectedIndex() != -1){
            
            switch (cbcategoria.getSelectionModel().getSelectedIndex()) {
                
                case 0:
                    carregaTabela("UPPER(l.log_usuario) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 1:
                    carregaTabela("UPPER(c.cli_nome) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 2:
                    carregaTabela("UPPER(c.cli_id) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 3:
                    carregaTabela("UPPER(c.cli_email) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 4:
                    carregaTabela("UPPER(c.cli_fone) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                case 5:
                    carregaTabela("UPPER(l.log_ativo) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    private void clkTabela(MouseEvent event) 
    {
        if(tvclientes.getSelectionModel().getSelectedIndex() >= 0){
            
            if(tvclientes.getSelectionModel().getSelectedItem() != null){
                
                Funcionario f = (Funcionario)tvclientes.getSelectionModel().getSelectedItem();
                
                tcpf.setText(f.getCpf());
                tnome.setText(f.getNome());
                if(f.getSexo() == 'M')
                    cbsexo.getSelectionModel().selectFirst();
                else if(f.getSexo() == 'F')
                    cbsexo.getSelectionModel().selectLast();
                dpdatanasc.setValue(f.getDatanasc());
                temail.setText(f.getEmail());
                ttelefone.setText(f.getTelefone());
                tcep.setText(f.getCep());
                trua.setText(f.getRua());
                tnumero.setText("" + f.getNumero());
                tbairro.setText(f.getBairro());
                tcidade.setText(f.getCidade());
                tuf.setText(f.getUf());
                cbCargo.getSelectionModel().select(f.getNivel());
                pndados.setDisable(false); 
                if(btconfirmar.isDisable())
                    pndados.setDisable(true);
                txlogin.setText(f.getLogin());
                setUsuario(collogin.getCellData(tvclientes.getSelectionModel().getSelectedIndex()));
                setCpf(colcpf.getCellData(tvclientes.getSelectionModel().getSelectedIndex()));
                setAtivo(tvclientes.getSelectionModel().getSelectedItem().getAtivo());
            }
        }
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    @FXML
    private void clkBtAtivar(ActionEvent event) 
    {
        if(tvclientes.getSelectionModel().getSelectedIndex() != -1)
        {
            DALFuncionario dal = new DALFuncionario();
            if(getAtivo() == 'S')
                dal.desativar(tvclientes.getSelectionModel().getSelectedItem());
            else
                dal.ativar(tvclientes.getSelectionModel().getSelectedItem());
            carregaTabela("");
        }
        else
        {
            JFXSnackbar sb = new JFXSnackbar(pnpesquisa); 
            sb.enqueue(new JFXSnackbar.SnackbarEvent(new Label("Selecione algum funcionário!")));
        }
    }  

    @FXML
    private void clkBtNovo(ActionEvent event) 
    {
        estado(false);
        tcpf.setDisable(false);
        txsenhan.setVisible(false);
        limparCampos();
        pnpesquisa.setDisable(true);
    }
    
    private void chamaLogin() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin_Cadastro.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    private void chamaConfig() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaConfig.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    
}
