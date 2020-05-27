package dal;

import util.Conexao;
import entidades.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALCategoria {
    
    public boolean salvar(Categoria c){
        
        String sql = "INSERT INTO categoria(cat_cod,cat_descricao) VALUES(#1,'#2')";
        
        sql = sql.replaceAll("#1", "" + c.getCat_cod());
        sql = sql.replaceAll("#2", "" + c.getCat_descricao());
        
        return new Conexao().manipular(sql);
    }
    
    public boolean alterar(Categoria c){
        
        String sql = "UPDATE categoria SET "
                + "cat_cod='#1', cat_descricao='#2'";
        
        sql = sql.replaceAll("#1", "" + c.getCat_cod());
        sql = sql.replaceAll("#2", "" + c.getCat_descricao());
        
        return new Conexao().manipular(sql);
    }
    
    public boolean apagar(Categoria c){
        
        String sql = "DELETE FROM categoria WHERE cat_cod ="+c.getCat_cod();
        
        return new Conexao().manipular(sql);
    }
    
    public List<Categoria> getProdutos(){
        
        List<Categoria> lista = new ArrayList();
        ResultSet rs = new Conexao().consultar("SELECT * FROM categoria");
        
        try {
            while(rs.next()){
                lista.add(new Categoria(rs.getString("cat_descricao"), Integer.parseInt(rs.getString("cat_cod"))));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
}
