package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Cliente;

public class DALCliente {
    
    public boolean gravar(Cliente c) {
        
        String sql = "INSERT INTO Cliente(cli_id, cli_nome, cli_sexo, cli_datanasc, cli_email, cli_fone, "
                + "cli_cep, cli_rua, cli_numero, cli_bairro, cli_cidade, cli_uf) "
                + "VALUES ('#1','#2','#3','#4','#5','#6','#7','#8',#9,'#a','#b','#c')";
        
        sql = sql.replaceAll("#1", "" + c.getCpf());
        sql = sql.replaceAll("#2", "" + c.getNome());
        sql = sql.replaceAll("#3", "" + c.getSexo());
        sql = sql.replaceAll("#4", "" + c.getDatanasc().toString());
        sql = sql.replaceAll("#5", "" + c.getEmail());
        sql = sql.replaceAll("#6", "" + c.getTelefone());
        sql = sql.replaceAll("#7", "" + c.getCep());
        sql = sql.replaceAll("#8", "" + c.getRua());
        sql = sql.replaceAll("#9", "" + c.getNumero());
        sql = sql.replaceAll("#a", "" + c.getBairro());
        sql = sql.replaceAll("#b", "" + c.getCidade());
        sql = sql.replaceAll("#c", "" + c.getUf());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Cliente c) {
        
        String sql = "UPDATE Cliente SET cli_id='#1', cli_nome='#2', cli_sexo='#3', cli_datanasc='#4', "
                + "cli_email='#5', cli_fone='#6', cli_cep='#7', cli_rua='#8', cli_numero=#9, cli_bairro='#a', "
                + "cli_cidade='#b', cli_uf='#c' WHERE cli_id='" + c.getCpf()+ "'";
        
        sql = sql.replaceAll("#1", "" + c.getCpf());
        sql = sql.replaceAll("#2", "" + c.getNome());
        sql = sql.replaceAll("#3", "" + c.getSexo());
        sql = sql.replaceAll("#4", "" + c.getDatanasc().toString());
        sql = sql.replaceAll("#5", "" + c.getEmail());
        sql = sql.replaceAll("#6", "" + c.getTelefone());
        sql = sql.replaceAll("#7", "" + c.getCep());
        sql = sql.replaceAll("#8", "" + c.getRua());
        sql = sql.replaceAll("#9", "" + c.getNumero());
        sql = sql.replaceAll("#a", "" + c.getBairro());
        sql = sql.replaceAll("#b", "" + c.getCidade());
        sql = sql.replaceAll("#c", "" + c.getUf());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Cliente c) {
        
        return Banco.getCon().manipular("DELETE FROM Cliente WHERE cli_id='" + c.getCpf() + "'");
    }
    
    public Cliente getCli(String id) {
        
        Cliente aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Cliente WHERE cli_id='" + id + "'");
        
        try{
            
            if(rs.next())
                aux = new Cliente(rs.getInt("cli_numero"),rs.getString("cli_sexo").charAt(0),
                        rs.getString("cli_nome"),rs.getString("cli_id"),rs.getString("cli_email"),
                        rs.getString("cli_fone"),rs.getString("cli_cep"),rs.getString("cli_rua"),
                        rs.getString("cli_bairro"),rs.getString("cli_cidade"),rs.getString("cli_uf"),
                        rs.getDate("cli_datanasc").toLocalDate());
        } 
        catch(SQLException ex) {}
        
        return aux;
    }
    
    public List<Cliente> getListCli(String filtro) {
        
        String sql = "SELECT * FROM Cliente";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Cliente> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
                aux.add(new Cliente(rs.getInt("cli_numero"),rs.getString("cli_sexo").charAt(0),
                        rs.getString("cli_nome"),rs.getString("cli_id"),rs.getString("cli_email"),
                        rs.getString("cli_fone"),rs.getString("cli_cep"),rs.getString("cli_rua"),
                        rs.getString("cli_bairro"),rs.getString("cli_cidade"),rs.getString("cli_uf"),
                        rs.getDate("cli_datanasc").toLocalDate()));
        } 
        catch (SQLException ex) {}
        
        return aux;
    }
}