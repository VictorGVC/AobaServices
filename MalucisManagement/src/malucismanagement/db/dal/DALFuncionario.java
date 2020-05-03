/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Funcionario;

/**
 *
 * @author HITRON
 */
public class DALFuncionario {
    public boolean gravar(Funcionario f, String senha) 
    {
        String sql = "INSERT INTO Cliente(cli_id, cli_nome, cli_sexo, cli_datanasc, cli_email, cli_fone, "
                + "cli_cep, cli_rua, cli_numero, cli_bairro, cli_cidade, cli_uf) "
                + "VALUES (#1,#2,'#3',#4,'#5','#6','#7','#8','#9','#10','#11','#12');";
        
        sql = sql.replaceAll("#1", "" + f.getCpf());
        sql = sql.replaceAll("#2", "" + f.getNome());
        sql = sql.replaceAll("#3", "" + f.getSexo());
        sql = sql.replaceAll("#4", "" + f.getDatanasc().toString());
        sql = sql.replaceAll("#5", "" + f.getEmail());
        sql = sql.replaceAll("#6", "" + f.getTelefone());
        sql = sql.replaceAll("#7", "" + f.getCep());
        sql = sql.replaceAll("#8", "" + f.getRua());
        sql = sql.replaceAll("#9", "" + f.getNumero());
        sql = sql.replaceAll("#10", "" + f.getBairro());
        sql = sql.replaceAll("#11", "" + f.getCidade());
        sql = sql.replaceAll("#12", "" + f.getUf());
        
        sql += "INSERT INTO Login(log_usuario, cli_id, log_nivel, log_senha, log_ativo)"
                + "VALUES ('#1','#2',#3,'#4', 'S');";
        sql = sql.replaceAll("#1", "" + f.getLogin());
        sql = sql.replaceAll("#2", "" + f.getCpf());
        sql = sql.replaceAll("#3", "" + f.getNivel());
        sql = sql.replaceAll("#3", "" + senha);
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Funcionario f,String senha) 
    {
        String sql = "UPDATE Login SET log_usuario='#1', log_nivel= #2, log_senha='#3'"
                + "WHERE log_usuario=" + f.getLogin();
        
        sql = sql.replaceAll("#1", "" + f.getLogin());
        sql = sql.replaceAll("#2", "" + f.getNivel());
        sql = sql.replaceAll("#3", senha);
        
        
        sql += "UPDATE Cliente SET cli_nome='#1', cli_sexo='#2', cli_datanasc='#3', "
                + "cli_email='#4', cli_fone='#5', cli_cep='#6', cli_rua='#7', cli_numero='#8', cli_bairro='#9', "
                + "cli_cidade='#10', cli_uf='#11' WHERE cli_id=" + f.getCpf();
        
        sql = sql.replaceAll("#1", "" + f.getNome());
        sql = sql.replaceAll("#2", "" + f.getSexo());
        sql = sql.replaceAll("#3", "" + f.getDatanasc().toString());
        sql = sql.replaceAll("#4", "" + f.getEmail());
        sql = sql.replaceAll("#5", "" + f.getTelefone());
        sql = sql.replaceAll("#6", "" + f.getCep());
        sql = sql.replaceAll("#7", "" + f.getRua());
        sql = sql.replaceAll("#8", "" + f.getNumero());
        sql = sql.replaceAll("#9", "" + f.getBairro());
        sql = sql.replaceAll("#10", "" + f.getCidade());
        sql = sql.replaceAll("#11", "" + f.getUf());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Funcionario f) 
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Login "
                + "WHERE log_usuario !=" + f.getLogin());
        int cont = 0;
        try{
            
            if(rs.next())
                cont++;
        } 
        catch(SQLException ex) 
        {
            System.out.println(ex);
        }
        if(cont > 0)
            return Banco.getCon().manipular("DELETE FROM Login WHERE log_usuario=" + f.getLogin());
        else 
            return false;
    }
    
    public boolean desativar(Funcionario f) 
    {    
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Login "
                + "WHERE log_usuario !=" + f.getLogin());
        int cont = 0;
        try{
            
            if(rs.next())
                cont++;
        } 
        catch(SQLException ex) 
        {
            System.out.println(ex);
        }
        if(cont > 0)
            return Banco.getCon().manipular("UPDATE Login SET log_ativo = 'N' WHERE log_usuario=" + f);
        else 
            return false;
    }
    
    public boolean ativar(Funcionario f) 
    {    
        return Banco.getCon().manipular("UPDATE Login SET log_ativo = 'S' WHERE log_usuario=" + f.getLogin());
    }
    
    public Funcionario get(String id) {
        
        Funcionario aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Cliente c INNER JOIN Login l"
                + "ON l.cli_id = c.cli_id WHERE l.log_usuario=" + id);
        
        try{
            
            if(rs.next())
                aux = new Funcionario(rs.getInt("c.cli_numero"), rs.getString("c.cli_sexo").charAt(0), 
                        rs.getString("c.cli_nome"), rs.getString("c.cli_id"), rs.getString("c.cli_email"),
                        rs.getString("c.cli_email"), rs.getString("c.cli_cep"), rs.getString("c.cli_rua"),
                        rs.getString("c.cli_bairro"), rs.getString("c.cli_cidade"), rs.getString("c.cli_uf"),
                        rs.getString("l.log_usuario"), rs.getDate("c.cli_datanasc").toLocalDate(), 
                        rs.getString("l.log_ativo").charAt(0), rs.getInt("l.log_nivel"));
        } 
        catch(SQLException ex) 
        {
            System.out.println(ex);
        }
        
        return aux;
    }
    
    public List<Funcionario> getL(String filtro) {
        
        String sql = "SELECT * FROM Cliente c RIGHT JOIN Login l "
                + "ON l.cli_id = c.cli_id";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Funcionario> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
                aux.add(new Funcionario(rs.getInt("c.cli_numero"), rs.getString("c.cli_sexo").charAt(0), 
                        rs.getString("c.cli_nome"), rs.getString("c.cli_id"), rs.getString("c.cli_email"),
                        rs.getString("c.cli_email"), rs.getString("c.cli_cep"), rs.getString("c.cli_rua"),
                        rs.getString("c.cli_bairro"), rs.getString("c.cli_cidade"), rs.getString("c.cli_uf"),
                        rs.getString("l.log_usuario"), rs.getDate("c.cli_datanasc").toLocalDate(), 
                        rs.getString("l.log_ativo").charAt(0), rs.getInt("l.log_nivel")));
        } 
        catch(SQLException ex) 
        {
            System.out.println(ex);
        }
        
        return aux;
    }
    
    public boolean valida(String login, String senha)
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Login "
                + "WHERE log_usuario = " + login + " AND log_senha = "+ senha);
        int cont = 0;
        try{
            
            if(rs.next())
                cont++;
        } 
        catch(SQLException ex) 
        {
            System.out.println(ex);
        }
        return cont == 1;
    }
    
}
