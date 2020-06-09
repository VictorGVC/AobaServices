package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Fornecedor;

public class DALFornecedores {
    
     public boolean gravar(Fornecedor f) {
        
        String sql = "INSERT INTO fornecedores(for_cnpj, for_nome, for_inscestadual, for_email, for_tipo, for_telefone) "
                + "VALUES ('#1','#2','#3','#4','#5','#6')";
        
        sql = sql.replaceAll("#1", f.getFor_cnpj());
        sql = sql.replaceAll("#2", f.getFor_nome());
        sql = sql.replaceAll("#3", f.getFor_inscestadual());
        sql = sql.replaceAll("#4", f.getFor_email());
        sql = sql.replaceAll("#5", f.getFor_tipo());
        sql = sql.replaceAll("#6", f.getFor_telefone());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Fornecedor f, String CNPJ) {
        
         String sql = "UPDATE fornecedores SET for_cnpj='#1', for_nome='#2', for_inscestadual='#3', for_email='#4', "
                + "for_tipo='#5', for_telefone='#6' WHERE for_cnpj like '" +CNPJ+"'";
        
        sql = sql.replaceAll("#1", f.getFor_cnpj());
        sql = sql.replaceAll("#2", f.getFor_nome());
        sql = sql.replaceAll("#3", f.getFor_inscestadual());
        sql = sql.replaceAll("#4", f.getFor_email());
        sql = sql.replaceAll("#5", f.getFor_tipo());
        sql = sql.replaceAll("#6", f.getFor_telefone());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(String cnpj){
        String sql = "DELETE FROM fornecedores WHERE for_cnpj like '"+cnpj+"'";
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Fornecedor> getFornecedores(){
        List <Fornecedor> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedores");
        
        try {
            while(rs.next()){
                lista.add(new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_inscestadual"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public List<String> getFornecedoresNomes(String nome){
        List <String> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedores WHERE Lower(for_nome) like '%"+nome+"%'");
        
        try {
            while(rs.next()){
                lista.add(rs.getString("for_cnpj"));
            }
        } catch (Exception e) {
            int i = 0;
            i++;
        }
        
        return lista;
    }
    
    public String getFornecedorNome(String cnpj) throws SQLException{
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedores WHERE for_cnpj like '"+cnpj+"'");
        
        try {
            rs.next();
            
        } catch (Exception e) {
            int i = 0;
            i++;
        }
        
        return rs.getString("for_nome");
    }
    
    public List<Fornecedor> getFornecedoresCNPJ(String cnpj){
        List <Fornecedor> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedores");
        
        try {
            while(rs.next()){
                String auxcnpj = rs.getString("for_cnpj");
                auxcnpj = auxcnpj.replace(".", "");
                auxcnpj = auxcnpj.replace("/", "");
                auxcnpj = auxcnpj.replace("-", "");
                if(auxcnpj.contains(cnpj))
                    lista.add(new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_inscestadual"),
                            rs.getString("for_cnpj"),rs.getString("for_telefone")));
            }
        } catch (Exception e) {
            int i = 0;
            i++;
        }
        
        return lista;
    }
    
    public List<Fornecedor> getFornecedoresIE(String ie){
        List <Fornecedor> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedores");
        
        try {
            while(rs.next()){
                String auxcnpj = rs.getString("for_inscestadual");
                auxcnpj = auxcnpj.replace(".", "");
                if(auxcnpj.contains(ie))
                    lista.add(new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_inscestadual"),
                            rs.getString("for_cnpj"),rs.getString("for_telefone")));
            }
        } catch (Exception e) {
            int i = 0;
            i++;
        }
        
        return lista;
    }
    
    public List<Fornecedor> getFornecedoresTelefone(String telefone){
        List <Fornecedor> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedores");
        
        try {
            while(rs.next()){
                String auxcnpj = rs.getString("for_telefone");
                auxcnpj = auxcnpj.replace("(", "");
                auxcnpj = auxcnpj.replace(")", "");
                if(auxcnpj.contains(telefone))
                    lista.add(new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_inscestadual"),
                            rs.getString("for_cnpj"),rs.getString("for_telefone")));
            }
        } catch (Exception e) {
            int i = 0;
            i++;
        }
        
        return lista;
    }
    
    public List<Fornecedor> getFornecedoresEmail(String email){
        List <Fornecedor> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedores WHERE Lower(for_email) like '%"+email.toLowerCase()+"%'");
        
        try {
            while(rs.next()){
                lista.add(new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_inscestadual"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone")));
            }
        } catch (Exception e) {
            int i = 0;
            i++;
        }
        
        return lista;
    }
    
    public List<Fornecedor> getFornecedoresTipo(String tipo){
        List <Fornecedor> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedores WHERE Lower(for_tipo) like '%"+tipo.toLowerCase()+"%'");
        
        try {
            while(rs.next()){
                lista.add(new Fornecedor(rs.getString("for_tipo"),rs.getString("for_nome"),rs.getString("for_email"),rs.getString("for_inscestadual"),
                        rs.getString("for_cnpj"),rs.getString("for_telefone")));
            }
        } catch (Exception e) {
            int i = 0;
            i++;
        }
        
        return lista;
    }
        
}
