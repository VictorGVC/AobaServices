package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.ItensVenda;

public class DALItensVenda {
    
    public boolean gravar(ItensVenda i) {
        
        String sql = "INSERT INTO ItensVenda(ven_cod, mar_cod, it_qtde, it_preco, pro_cod"
                + "VALUES (#1,#2,#3,#4,#5)";
        
        sql = sql.replaceAll("#1", "" + i.getVen_cod());
        sql = sql.replaceAll("#2", "" + i.getMar_cod());
        sql = sql.replaceAll("#3", "" + i.getQtde());
        sql = sql.replaceAll("#4", "" + i.getPreco());
        sql = sql.replaceAll("#4", "" + i.getPro_cod());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagarProduto(ItensVenda i) {
        
        return Banco.getCon().manipular("DELETE FROM ItensVenda WHERE pro_cod='" + i.getPro_cod() + "'");
    }
    
    public boolean apagarItens(ItensVenda i) {
        
        return Banco.getCon().manipular("DELETE FROM ItensVenda WHERE ven_cod='" + i.getVen_cod() + "'");
    }
    
    public ItensVenda getVenda(String cod) {
        
        ItensVenda aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM ItensVenda WHERE ven_cod='" + cod + "'");
        
        try{
            
            if(rs.next())
                aux = new ItensVenda(rs.getInt("ven_cod"),rs.getInt("mar_cod"),rs.getString("cli_id"),
                        rs.getInt("it_qtde"), rs.getDouble("it_preco"));
        } 
        catch(SQLException ex) {}
        
        return aux;
    }
    
    public List<ItensVenda> getListItVenda(String filtro) {
        
        String sql = "SELECT * FROM ItensVenda";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <ItensVenda> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
                aux.add(new ItensVenda(rs.getInt("ven_cod"),rs.getInt("mar_cod"),rs.getString("cli_id"),
                        rs.getInt("it_qtde"), rs.getDouble("it_preco")));
        } 
        catch (SQLException ex) {}
        
        return aux;
    }
}
