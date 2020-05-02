package malucismanagement.db.dal;

import java.sql.ResultSet;
import javafx.collections.ObservableList;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Fornecedor;

public class DALFornecedores {
    
     public boolean gravar(Fornecedor f) {
        
        String sql = "INSERT INTO Fornecedores"
                + "(for_nome, for_cnpj, for_ie, for_telefone, for_email, for_tipo)"
                + "VALUES(,'#2','#3','#4','#5','#6','#7')";
        sql = sql.replaceAll("#1", f.getFor_nome());
        sql = sql.replaceAll("#2", "" + f.getFor_cnpj());
        sql = sql.replaceAll("#3", "" + f.getFor_inscestadual());
        sql = sql.replaceAll("#4", "" + f.getFor_telefone());
        sql = sql.replaceAll("#5", f.getFor_email());
        sql = sql.replaceAll("#6", f.getFor_tipo());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Fornecedor f) {
        
         String sql = "UPDATE Fornecedores SET "
                + "for_nome='#1', for_cnpj='#2', for_ie='#3', for_telefone='#4', for_email='#5', for_tipo='#6' WHERE for_cnpj = "+f.getFor_cnpj();
        
        sql = sql.replaceAll("#1",f.getFor_nome());
        sql = sql.replaceAll("#2", "" + f.getFor_cnpj());
        sql = sql.replaceAll("#3", "" + f.getFor_inscestadual());
        sql = sql.replaceAll("#4", "" + f.getFor_telefone());
        sql = sql.replaceAll("#5", "" + f.getFor_email());
        sql = sql.replaceAll("#6", "" + f.getFor_tipo());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(String cnpj){
        String sql = "DELETE FROM Fornecedores f WHERE f.for_cnpj ="+cnpj;
        
        return Banco.getCon().manipular(sql);
    }
    
    public ObservableList<Fornecedor> getFornecedores(){
        Fornecedor f = null;
        ObservableList<Fornecedor> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Fornecedor");
        
        try {
            while(rs.next()){
                f = new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_ie"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone"));
                lista.add(f);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Fornecedor> getFornecedoresNome(String nome){
        Fornecedor f = null;
        ObservableList<Fornecedor> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Fornecedor WHERE for_nome ="+nome);
        
        try {
            while(rs.next()){
                f = new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_ie"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone"));
                lista.add(f);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Fornecedor> getFornecedoresCNPJ(int cnpj){
        Fornecedor f = null;
        ObservableList<Fornecedor> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Fornecedor WHERE for_cnpj ="+cnpj);
        
        try {
            while(rs.next()){
                f = new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_ie"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone"));
                lista.add(f);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Fornecedor> getFornecedoresIE(int ie){
        Fornecedor f = null;
        ObservableList<Fornecedor> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Fornecedor WHERE for_ie ="+ie);
        
        try {
            while(rs.next()){
                f = new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_ie"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone"));
                lista.add(f);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Fornecedor> getFornecedoresTelefone(int telefone){
        Fornecedor f = null;
        ObservableList<Fornecedor> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Fornecedor WHERE for_telefone ="+telefone);
        
        try {
            while(rs.next()){
                f = new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_ie"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone"));
                lista.add(f);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Fornecedor> getFornecedoresEmail(String email){
        Fornecedor f = null;
        ObservableList<Fornecedor> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Fornecedor WHERE for_email ="+email);
        
        try {
            while(rs.next()){
                f = new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_ie"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone"));
                lista.add(f);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Fornecedor> getFornecedoresTipo(String tipo){
        Fornecedor f = null;
        ObservableList<Fornecedor> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Fornecedor WHERE for_tipo ="+tipo);
        
        try {
            while(rs.next()){
                f = new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_ie"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone"));
                lista.add(f);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
        
}
