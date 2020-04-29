package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import malucismanagement.db.dal.DALCliente;
import malucismanagement.db.entidades.Cliente;
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
    private JFXTextField tcod;
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
    private TableColumn<Cliente, String> colcodigo;
    @FXML
    private TableColumn<Cliente, String> colnome;
    @FXML
    private TableColumn<Cliente, String> colcpf;
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
        initColTb();
        listaSexo();
        listaCategoria();
        estado(true);
    }    

    private void fadeout() {
        
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
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void initColTb() {
        
        colcodigo.setCellValueFactory(new PropertyValueFactory("cod"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        colcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colemail.setCellValueFactory(new PropertyValueFactory("email"));
        coltelefone.setCellValueFactory(new PropertyValueFactory("fone"));
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
        List<Cliente> res = dal.get(filtro);
        ObservableList<Cliente> modelo;
        
        modelo = FXCollections.observableArrayList(res);
        tvclientes.setItems(modelo);
    }
    
    private void listaSexo() {
        
        List<String> list = new ArrayList();
        
        list.add("Maculino");
        list.add("Feminino");
        
        cbsexo.setItems(FXCollections.observableArrayList(list));
    }
    
    private void listaCategoria() {
        
        List<String> list = new ArrayList();
        
        list.add("Codigo");
        list.add("Nome");
        list.add("CPF");
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
    
    @FXML
    private void clkBtNovo(ActionEvent event) {
        
        estado(false);
        limparCampos();
        pnpesquisa.setDisable(true);
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {
        
        estado(false);
        pnpesquisa.setDisable(false);
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tvclientes.getSelectionModel().getSelectedIndex() != -1){
            
            a.setHeaderText("Exclusão!");
            a.setTitle("Exclusão");
            a.setContentText("Confirma a exclusão");
            if (a.showAndWait().get() == ButtonType.OK){
                
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
    private void clkBtConfirmar(ActionEvent event) {
        
        int cod;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tnome.getText().isEmpty()){
            
            a.setContentText("Nome deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tnome.requestFocus();
        }
        else if(tcpf.getText().isEmpty()){
            
            a.setContentText("CPF deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tcpf.requestFocus();
        }
        else if(cbsexo.getSelectionModel().getSelectedIndex() == -1){
            
            a.setContentText("Sexo deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            cbsexo.requestFocus();
        }
        else if(dpdatanasc.getValue() == null){
            
            a.setContentText("Data de nascimento deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            dpdatanasc.requestFocus();
        }
        else if(temail.getText().isEmpty()){
            
            a.setContentText("E-Mail deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            temail.requestFocus();
        }
        else if(ttelefone.getText().isEmpty()){
            
            a.setContentText("Telefone deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            ttelefone.requestFocus();
        }
        else if(tcep.getText().isEmpty()){
            
            a.setContentText("CEP deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tcep.requestFocus();
        }
        else if(trua.getText().isEmpty()){
            
            a.setContentText("Rua deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            trua.requestFocus();
        }
        else if(tnumero.getText().isEmpty()){
            
            a.setContentText("Numero deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tnumero.requestFocus();
        }
        else if(tbairro.getText().isEmpty()){
            
            a.setContentText("Bairro deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tbairro.requestFocus();
        }
        else if(tcidade.getText().isEmpty()){
            
            a.setContentText("Cidade deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tcidade.requestFocus();
        }
        else if(tuf.getText().isEmpty()){
            
            a.setContentText("Estado deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            tuf.requestFocus();
        }
        else{
            
            try {
                cod = Integer.parseInt(tcod.getText());
            } 
            catch (NumberFormatException e) {
                cod = 0;
            }
            
            char sexo = ' ';
            
            if(cbsexo.getSelectionModel().getSelectedIndex() == 0)
                sexo = 'M';
            else if(cbsexo.getSelectionModel().getSelectedIndex() == 1)
                sexo = 'F';
            
            Cliente c = new Cliente(cod, Integer.parseInt(tnumero.getText()), sexo,
                                         tnome.getText(), 
                                         tcpf.getText(),
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
    private void evtBotaoDigitado(KeyEvent event) {
        
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
            
            if(cbcategoria.getSelectionModel().getSelectedIndex() == 0)
                carregaTabela("UPPER(cli_cod) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
            else if(cbcategoria.getSelectionModel().getSelectedIndex() == 1)
                carregaTabela("UPPER(cli_nome) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
            else if(cbcategoria.getSelectionModel().getSelectedIndex() == 2)
                carregaTabela("UPPER(cli_cpf) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
            else if(cbcategoria.getSelectionModel().getSelectedIndex() == 3)
                carregaTabela("UPPER(cli_email) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
            else if(cbcategoria.getSelectionModel().getSelectedIndex() == 4)
                carregaTabela("UPPER(cli_fone) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
            else if(cbcategoria.getSelectionModel().getSelectedIndex() == 5)
                carregaTabela("UPPER(cli_cep) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
            else if(cbcategoria.getSelectionModel().getSelectedIndex() == 6)
                carregaTabela("UPPER(cli_rua) LIKE '%" + tfiltro.getText().toUpperCase() + "%'");
        }
    } 

    @FXML
    private void clkTabela(MouseEvent event) {
        
        if(tvclientes.getSelectionModel().getSelectedIndex() >= 0){
            
            if(tvclientes.getSelectionModel().getSelectedItem() != null){
                
                Cliente c = (Cliente)tvclientes.getSelectionModel().getSelectedItem();
                
                tcod.setText("" + c.getCodigo());
                tnome.setText(c.getNome());
                tcpf.setText(c.getCpf());
                if(c.getSexo() == 'M')
                    cbsexo.getSelectionModel().selectFirst();
                else if(c.getSexo() == 'F')
                    cbsexo.getSelectionModel().selectLast();
                dpdatanasc.setValue(c.getDatanasc());
                temail.setText(c.getEmail());
                ttelefone.setText(c.getTelefone());
                tcep.setText(c.getCep());
                trua.setText(c.getRua());
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
        
        TelaPrincipalController.spnprincipal.setCenter(null);
        TelaPrincipalController.efeito(false);
    }
}