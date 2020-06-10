package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        String sql = "SELECT * FROM CategoriaProduto WHERE Lower(cat_nome) like '%"+nome.toLowerCase()+"%'";
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            if(rs.next())
                return Integer.parseInt(rs.getString("cat_cod"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Integer.parseInt(rs.getString("cat_cod"));
    }
    
    public String getCategoriaProduto(int cod) throws SQLException{
        String sql = "SELECT * FROM CategoriaProduto ct WHERE ct.cat_cod = "+cod;
        ResultSet rs = Banco.getCon().consultar(sql);
        
        return rs.getString("cat_nome");
    }
    
    public List<String> getCategoriaProduto(){
        List <String> lista = new ArrayList();
        String sql = "SELECT * FROM CategoriaProduto";
        CategoriaProduto ct = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            while(rs.next()){
                lista.add(rs.getString("cat_nome"));
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    /**
     *
     * @param filtro
     * @return
     */
    public List<CategoriaProduto> getCategoriaProdutoItens(String filtro)
    {
        List <CategoriaProduto> lista = new ArrayList();
        String sql = "SELECT * FROM CategoriaProduto";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            while(rs.next()){
                lista.add(new CategoriaProduto(rs.getInt("cat_cod"),rs.getString("cat_nome")));
            }
        } catch (SQLException e) {
        }
        
        return lista;
    }
}
