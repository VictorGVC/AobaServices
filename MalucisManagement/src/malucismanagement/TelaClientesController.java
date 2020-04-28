package malucismanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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
    private JFXComboBox<?> cbsexo;
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
    private Pane pnfiltros;
    @FXML
    private JFXComboBox<?> cbcategoria;
    @FXML
    private JFXTextField tfiltro;
    @FXML
    private TableView<?> tvclientes;
    @FXML
    private TableColumn<?, ?> colnome;
    @FXML
    private TableColumn<?, ?> colcpf;
    @FXML
    private TableColumn<?, ?> colemail;
    @FXML
    private TableColumn<?, ?> coltelefone;
    @FXML
    private TableColumn<?, ?> colrua;
    @FXML
    private TableColumn<?, ?> colnumero;
    @FXML
    private TableColumn<?, ?> colcidade;
    @FXML
    private VBox pnpesquisa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fadeout();
        initColTb();
        estado(true);
    }    

    private void fadeout() {
        
        MaskFieldUtil.cpfField(tcpf);
        MaskFieldUtil.foneField(ttelefone);
        MaskFieldUtil.cepField(tcep);
        MaskFieldUtil.maxField(tuf, 2);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pnprincipal);
        
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    
    private void initColTb() {
        
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        colcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colemail.setCellValueFactory(new PropertyValueFactory("email"));
        coltelefone.setCellValueFactory(new PropertyValueFactory("fone"));
        colrua.setCellValueFactory(new PropertyValueFactory("rua"));
        colnumero.setCellValueFactory(new PropertyValueFactory("num"));
        colcidade.setCellValueFactory(new PropertyValueFactory("cid"));
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
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
    }

    @FXML
    private void clkBtVoltar(ActionEvent event) {
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

                        tuf.setText(result.getUf());
                        trua.setText(result.getEnd());
                        tcidade.setText(result.getCidade());
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
    }
}