package db.dal;

import db.util.Conexao;
import db.entidades.Servicos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DALServicos {
    public boolean salvar(Servicos s){
        
        String sql = "INSERT INTO servicos(ser_cod,ser_descricao) VALUES(#1,'#2')";
        
        sql = sql.replaceAll("#1", "" + s.getSer_cod());
        sql = sql.replaceAll("#2", "" + s.getSer_descricao());
        
        return new Conexao().manipular(sql);
    }
    
    public boolean alterar(Servicos s){
        
        String sql = "UPDATE servicos SET "
                + "ser_cod='#1', ser_descricao='#2'";
        
        sql = sql.replaceAll("#1", "" + s.getSer_cod());
        sql = sql.replaceAll("#2", "" + s.getSer_descricao());
        
        return new Conexao().manipular(sql);
    }
    
    public boolean apagar(Servicos s){
        
        String sql = "DELETE FROM servicos WHERE cat_cod ="+s.getSer_cod();
        
        return new Conexao().manipular(sql);
    }
    
    public List<Servicos> getProdutos(){
        
        List<Servicos> lista = new ArrayList();
        ResultSet rs = new Conexao().consultar("SELECT * FROM servicos");
        
        try {
            while(rs.next()){
                lista.add(new Servicos(Integer.parseInt(rs.getString("ser_cod")),rs.getString("ser_descricao")));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
}
