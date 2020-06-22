package db.dal;

import db.util.Conexao;
import db.entidades.Categoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALCategoria {
    
    public boolean salvar(Categoria c){
        
        String sql = "INSERT INTO categoria(cat_desc) VALUES('#2')";
        
        sql = sql.replaceAll("#2", "" + c.getCat_descricao());
        
        return new Conexao().manipular(sql);
    }
    
    public boolean alterar(Categoria c){
        
        String sql = "UPDATE categoria SET "
                + "cat_cod='#1', cat_desc='#2'";
        
        sql = sql.replaceAll("#1", "" + c.getCat_cod());
        sql = sql.replaceAll("#2", "" + c.getCat_descricao());
        
        return new Conexao().manipular(sql);
    }
    
    public boolean apagar(String c){
        
        String sql = "DELETE FROM categoria WHERE cat_cod ="+c;
        
        return new Conexao().manipular(sql);
    }
    
    public List<Categoria> getCategorias(){
        
        List<Categoria> lista = new ArrayList();
        Conexao con = new Conexao();
        ResultSet rs = con.consultar("SELECT * FROM categoria");
        
        try {
            while(rs.next()){
                lista.add(new Categoria(rs.getString("cat_desc"), Integer.parseInt(rs.getString("cat_cod"))));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
}
