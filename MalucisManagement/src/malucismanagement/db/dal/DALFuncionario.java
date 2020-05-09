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
                + "VALUES ('#1','#2','#3','#4','#5','#6','#7','#8','#9','&1','&2','&3'); ";
        
        sql = sql.replaceAll("#1", "" + f.getCpf());
        sql = sql.replaceAll("#2", "" + f.getNome());
        sql = sql.replaceAll("#3", "" + f.getSexo());
        sql = sql.replaceAll("#4", "" + f.getDatanasc().toString());
        sql = sql.replaceAll("#5", "" + f.getEmail());
        sql = sql.replaceAll("#6", "" + f.getTelefone());
        sql = sql.replaceAll("#7", "" + f.getCep());
        sql = sql.replaceAll("#8", "" + f.getRua());
        sql = sql.replaceAll("#9", "" + f.getNumero());
        sql = sql.replaceAll("&1", "" + f.getBairro());
        sql = sql.replaceAll("&2", "" + f.getCidade());
        sql = sql.replaceAll("&3", "" + f.getUf());
        
        sql += "INSERT INTO Login(log_usuario, cli_id, log_nivel, log_senha, log_ativo)"
                + "VALUES ('#1','#2',#3,'#4', 'S');";
        sql = sql.replaceAll("#1", "" + f.getLogin());
        sql = sql.replaceAll("#2", "" + f.getCpf());
        sql = sql.replaceAll("#3", "" + f.getNivel());
        sql = sql.replaceAll("#4", "" + senha);
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Funcionario f,String senhaa, String senhan,String usua, String cpfa) 
    {
        String sql = null;
        if(valida(usua,senhaa))
        {
            if(!senhan.isEmpty())
                sql = "UPDATE Login SET log_usuario='#1', log_nivel= #2, log_senha='#3', cli_id = '#4'"
                    + "WHERE cli_id = '" + cpfa + "'; ";
            else
                sql = "UPDATE Login SET log_usuario='#1',  cli_id = '#4', log_nivel= #2 "
                    + "WHERE cli_id ='" + cpfa + "'; ";

            sql = sql.replaceAll("#1", "" + f.getLogin());
            sql = sql.replaceAll("#2", "" + f.getNivel());
            sql = sql.replaceAll("#4", "" + f.getCpf());
            sql = sql.replaceAll("#3", senhan);
            
            sql += "UPDATE Cliente SET cli_nome='#1', cli_sexo='#2', cli_datanasc='#3', "
                    + "cli_email='#4', cli_fone='#5', cli_cep='#6', cli_rua='#7', cli_numero='#8', cli_bairro='#9', "
                    + "cli_cidade='&1', cli_uf='&2', cli_id='&3' WHERE cli_id='" + f.getCpf() + "'; ";

            sql = sql.replaceAll("#1", "" + f.getNome());
            sql = sql.replaceAll("#2", "" + f.getSexo());
            sql = sql.replaceAll("#3", "" + f.getDatanasc().toString());
            sql = sql.replaceAll("#4", "" + f.getEmail());
            sql = sql.replaceAll("#5", "" + f.getTelefone());
            sql = sql.replaceAll("#6", "" + f.getCep());
            sql = sql.replaceAll("#7", "" + f.getRua());
            sql = sql.replaceAll("#8", "" + f.getNumero());
            sql = sql.replaceAll("#9", "" + f.getBairro());
            sql = sql.replaceAll("&1", "" + f.getCidade());
            sql = sql.replaceAll("&2", "" + f.getUf());
            sql = sql.replaceAll("&3", "" + f.getCpf());
            
            
        }
        
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Funcionario f) 
    {
        if(f.getNivel() == 0)
        {
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM Login "
                + "WHERE log_usuario !='" + f.getLogin() + "' AND log_ativo = 'S'");
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
                return Banco.getCon().manipular("DELETE FROM Login WHERE log_usuario='" + f.getLogin() + "'");
            else 
                return false;
        }
        else
            return Banco.getCon().manipular("DELETE FROM Login WHERE log_usuario='" + f.getLogin() + "'");
    }
    
    public boolean desativar(Funcionario f) 
    {    
        if(f.getNivel() == 0)
        {
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM Login "
                + "WHERE log_usuario !='" + f.getLogin()+"' AND log_ativo = 'S'");
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
                return Banco.getCon().manipular("UPDATE Login SET log_ativo = 'N' WHERE log_usuario='" + f.getLogin()+"'");
            else 
                return false;
        }
        else
            return Banco.getCon().manipular("UPDATE Login SET log_ativo = 'N' WHERE log_usuario='" + f.getLogin()+"'");
    }
    
    public boolean ativar(Funcionario f) 
    {    
        return Banco.getCon().manipular("UPDATE Login SET log_ativo = 'S' WHERE log_usuario='" + f.getLogin()+"'");
    }
    
    public Funcionario get(String id) {
        
        Funcionario aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Cliente c INNER JOIN Login l "
                + "ON l.cli_id = c.cli_id WHERE l.log_usuario='" + id+"'");
        
        try{
            
            if(rs.next())
                aux = new Funcionario(rs.getInt("cli_numero"), rs.getString("cli_sexo").charAt(0), 
                        rs.getString("cli_nome"), rs.getString("cli_id"), rs.getString("cli_email"),
                        rs.getString("cli_email"), rs.getString("cli_cep"), rs.getString("cli_rua"),
                        rs.getString("cli_bairro"), rs.getString("cli_cidade"), rs.getString("cli_uf"),
                        rs.getString("log_usuario"), rs.getDate("cli_datanasc").toLocalDate(), 
                        rs.getString("log_ativo").charAt(0), rs.getInt("log_nivel"));
        } 
        catch(SQLException ex) 
        {
            System.out.println(ex);
        }
        
        return aux;
    }
    
    public List<Funcionario> getL(String filtro) {
        
        String sql = "SELECT * FROM CLIENTE c RIGHT JOIN Login l "
                + "ON l.cli_id = c.cli_id";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Funcionario> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
                aux.add(new Funcionario(rs.getInt("cli_numero"), rs.getString("cli_sexo").charAt(0), 
                        rs.getString("cli_nome"), rs.getString("cli_id"), rs.getString("cli_email"),
                        rs.getString("cli_fone"), rs.getString("cli_cep"), rs.getString("cli_rua"),
                        rs.getString("cli_bairro"), rs.getString("cli_cidade"), rs.getString("cli_uf"),
                        rs.getString("log_usuario"), rs.getDate("cli_datanasc").toLocalDate(), 
                        rs.getString("log_ativo").charAt(0), rs.getInt("log_nivel")));
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
                + "WHERE log_usuario = '" + login + "' AND log_senha = '"+ senha +"'");
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
