package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Prodmarcas;

public class DALProdmarcas {
    public boolean gravar(Prodmarcas pm) throws SQLException {
        
        
        String sql = "INSERT INTO prodmarcas (mar_cod,pro_cod,estoque) "
                + "VALUES (#1,#2,#3)";
        sql = sql.replaceAll("#1","" + pm.getMar_cod());
        sql = sql.replaceAll("#2","" + pm.getPro_cod());
        sql = sql.replaceAll("#3","" + pm.getEstoque());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Prodmarcas pm) throws SQLException {
        
        String sql = "UPDATE prodmarcas SET "
                + "estoque =#1, pro_cod = #2 WHERE mar_cod="+pm.getMar_cod();
        
        sql = sql.replaceAll("#1","" + pm.getEstoque());
        sql = sql.replaceAll("#2","" + pm.getPro_cod());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM prodmarcas WHERE mar_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Prodmarcas> getProdmarcas(){
        List <Prodmarcas> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM prodmarcas");
        
        try {
            while(rs.next()){
                lista.add(new Prodmarcas(Integer.parseInt(rs.getString("mar_cod")),Integer.parseInt(rs.getString("pro_cod")), Integer.parseInt(rs.getString("estoque"))));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
}
