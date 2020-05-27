package dal;

import entidades.Anuncio;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

public class DALAnuncio {
     public boolean salvar(Anuncio a){
        
        String sql = "INSERT INTO anuncio(anu_cod,anu_titulo,anu_telefone,anu_preco,anu_desc,fot_cod,cat_cod) VALUES(#1,'#2','#3',#4,'#5',#6,#7)";
        
        sql = sql.replaceAll("#1", "" + a.getAnu_cod());
        sql = sql.replaceAll("#2", "" + a.getAnu_titulo());
        sql = sql.replaceAll("#3", "" + a.getAnu_telefone());
        sql = sql.replaceAll("#4", "" + a.getAnu_preco());
        sql = sql.replaceAll("#5", "" + a.getAnu_desc());
        sql = sql.replaceAll("#6", "" + a.getFot_cod());
        sql = sql.replaceAll("#7", "" + a.getCat_cod());
        
        return new Conexao().manipular(sql);
    }
    
    public boolean alterar(Anuncio a){
        
        String sql = "UPDATE canuncio SET "
                + "anu_titulo='#1', anu_telefone='#2',anu_preco=#3,anu_desc='#4',fot_cod=#5,cat_cod=#6 WHERE anu_cod="+a.getAnu_cod();
        
        sql = sql.replaceAll("#1", "" + a.getAnu_titulo());
        sql = sql.replaceAll("#2", "" + a.getAnu_telefone());
        sql = sql.replaceAll("#3", "" + a.getAnu_preco());
        sql = sql.replaceAll("#4", "" + a.getAnu_desc());
        sql = sql.replaceAll("#5", "" + a.getFot_cod());
        sql = sql.replaceAll("#6", "" + a.getCat_cod());
        
        return new Conexao().manipular(sql);
    }
    
    public boolean apagar(Anuncio a){
        
        String sql = "DELETE FROM categoria WHERE cat_cod ="+a.getAnu_cod();
        
        return new Conexao().manipular(sql);
    }
    
    public List<Anuncio> getProdutos(){
        
        List<Anuncio> lista = new ArrayList();
        ResultSet rs = new Conexao().consultar("SELECT * FROM anuncio");
        
        try {
            while(rs.next()){
                lista.add(new Anuncio(Integer.parseInt(rs.getString("anu_cod")),Integer.parseInt(rs.getString("fot_cd")),Integer.parseInt(rs.getString("cat_cod")),
                        rs.getString("anu_titulo"), rs.getString("anu_telefone"),rs.getString("anu_desc"), Double.parseDouble(rs.getString("anu_preco"))));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
}
