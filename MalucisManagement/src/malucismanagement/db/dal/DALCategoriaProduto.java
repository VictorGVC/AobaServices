package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.CategoriaProduto;

public class DALCategoriaProduto {
    public boolean gravar(CategoriaProduto ct) {
        
        String sql = "INSERT INTO CategoriaProduto"
                + "(cat_cod, cat_nome)"
                + "VALUES('#1','#2')";
        sql = sql.replaceAll("#1",""+ct.getCat_cod());
        sql = sql.replaceAll("#2", ct.getCat_nome());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(CategoriaProduto ct) {
        
        String sql = "UPDATE CategoriaProduto SET "
                + "cat_cod='#1', cat_nome='#2'";
        
        sql = sql.replaceAll("#1",""+ct.getCat_cod());
        sql = sql.replaceAll("#2", ct.getCat_nome());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM CategoriaProduto c WHERE c.cat_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public int getCategoriaProduto(String nome) throws SQLException{
        String sql = "SELECT * FROM CategoriaProduto ct WHERE ct.cat_nome = "+nome;
        CategoriaProduto ct = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        
        return Integer.parseInt(rs.getString("cat_cod"));
    }
    
    public String getCategoriaProduto(int cod) throws SQLException{
        String sql = "SELECT * FROM CategoriaProduto ct WHERE ct.cat_cod = "+cod;
        CategoriaProduto ct = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        
        return rs.getString("cat_nome");
    }
}
