package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Cliente;

public class DALCliente {
    
    public boolean gravar(Cliente c) {
        
        String sql = "INSERT INTO Cliente(cli_cod, cli_nome, cli_cpf, cli_sexo, cli_datanasc, cli_email, cli_fone, "
                + "cli_cep, cli_rua, cli_numero, cli_bairro, cli_cidade, cli_uf) "
                + "VALUES (#1,#2,'#3',#4,'#5','#6','#7','#8','#9','#10','#11','#12','#13')";
        
        sql = sql.replaceAll("#1", "" + c.getCodigo());
        sql = sql.replaceAll("#2", "" + c.getNome());
        sql = sql.replaceAll("#3", "" + c.getCpf());
        sql = sql.replaceAll("#4", "" + c.getSexo());
        sql = sql.replaceAll("#5", "" + c.getDatanasc().toString());
        sql = sql.replaceAll("#6", "" + c.getEmail());
        sql = sql.replaceAll("#7", "" + c.getTelefone());
        sql = sql.replaceAll("#8", "" + c.getCep());
        sql = sql.replaceAll("#9", "" + c.getRua());
        sql = sql.replaceAll("#10", "" + c.getNumero());
        sql = sql.replaceAll("#11", "" + c.getBairro());
        sql = sql.replaceAll("#12", "" + c.getCidade());
        sql = sql.replaceAll("#13", "" + c.getUf());
        
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean alterar(Cliente c) {
        
        String sql = "UPDATE Cliente SET cli_nome='#1', cli_cpf='#2', cli_sexo='#3', cli_datanasc='#4', "
                + "cli_email='#5', cli_fone='#6', cli_cep='#7', cli_rua='#8', cli_numero='#9', cli_bairro='#10', "
                + "cli_cidade='#11', cli_uf='#12' WHERE cli_cod=" + c.getCodigo();
        
        sql = sql.replaceAll("#1", "" + c.getNome());
        sql = sql.replaceAll("#2", "" + c.getCpf());
        sql = sql.replaceAll("#3", "" + c.getSexo());
        sql = sql.replaceAll("#4", "" + c.getDatanasc().toString());
        sql = sql.replaceAll("#5", "" + c.getEmail());
        sql = sql.replaceAll("#6", "" + c.getTelefone());
        sql = sql.replaceAll("#7", "" + c.getCep());
        sql = sql.replaceAll("#8", "" + c.getRua());
        sql = sql.replaceAll("#9", "" + c.getNumero());
        sql = sql.replaceAll("#10", "" + c.getBairro());
        sql = sql.replaceAll("#11", "" + c.getCidade());
        sql = sql.replaceAll("#12", "" + c.getUf());
        
        return Banco.getCon().manipular(sql);
        
    }
    
    public boolean apagar(Cliente c) {
        
        return Banco.getCon().manipular("DELETE FROM Cliente WHERE cli_cod=" + c.getCodigo());
    }
    
//    public Cliente get(int cod) {
//        
//        Produto aux = null;
//        ResultSet rs = Banco.getCon().consultar("select * from produto where prod_id="+cod);
//        try 
//        {
//            if(rs.next())
//            {
//                aux = new Produto(rs.getInt("prod_id"),new DALCategoria().get(rs.getInt("cat_id")),new DALUnidade().get(rs.getInt("uni_id")),rs.getString("prod_nome"),rs.getDouble("prod_preco"),rs.getString("prod_descr"));
//            }
//        } 
//        catch (SQLException ex) 
//        {
//            
//        }
//        return aux;
//    }
//    
//    public List<Cliente> get(String filtro) {
//        
//        String sql="select * from produto";
//        if(!filtro.isEmpty())
//            sql+=" where "+filtro;
//        List <Cliente> aux = new ArrayList();
//        ResultSet rs = Banco.getCon().consultar(sql);
//        try 
//        {
//            while(rs.next())
//            {
//                aux.add(new Produto(rs.getInt("prod_id"),new DALCategoria().get(rs.getInt("cat_id")),new DALUnidade().get(rs.getInt("uni_id")),rs.getString("prod_nome"),rs.getDouble("prod_preco"),rs.getString("prod_descr")));
//            }
//        } 
//        catch (SQLException ex) 
//        {
//            
//        }
//        
//        return aux;
//    }
}