package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Venda;

public class DALVenda {
    
    public boolean gravar(Venda v) {
        
        String sql = "INSERT INTO Vendas(ven_total, ven_dtvenda)"
                + "VALUES (#1,'#2')";
        
        sql = sql.replaceAll("#1", "" + v.getValortotal());
        sql = sql.replaceAll("#2", v.getDtvenda().toString());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Venda v) {
        
        String sql = "UPDATE Vendas SET ven_tota=#2, ven_datavenda='#3', cli_id='#4' "
                + "WHERE ven_cod='" + v.getCod()+ "'";
        
        sql = sql.replaceAll("#2", "" + v.getValortotal());
        sql = sql.replaceAll("#3", "" + v.getDtvenda().toString());
        sql = sql.replaceAll("#4", "" + v.getCli_id());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(Venda v) {
        
        return Banco.getCon().manipular("DELETE FROM Vendas WHERE ven_cod='" + v.getCod() + "'");
    }
    
    public Venda getVenda(String cod) {
        
        Venda aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Vendas WHERE ven_cod='" + cod + "'");
        
        try{
            
            if(rs.next())
                aux = new Venda(rs.getInt("ven_cod"),rs.getDouble("ven_total"),
                        rs.getDate("ven_dtvenda").toLocalDate(), rs.getString("cli_id"));
        } 
        catch(SQLException ex) {}
        
        return aux;
    }
    
    public Venda getUltima() {
        
        Venda aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Vendas WHERE ven_cod = (SELECT MAX(ven_cod) FROM Vendas)");
        
        try{
            
            if(rs.next())
                aux = new Venda(rs.getInt("ven_cod"),rs.getDouble("ven_total"),
                        rs.getDate("ven_dtvenda").toLocalDate(), rs.getString("cli_id"));
        } 
        catch(SQLException ex) {}
        
        return aux;
    }
    
    public List<Venda> getListVenda(String filtro) {
        
        String sql = "SELECT * FROM Vendas";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Venda> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
                aux.add(new Venda(rs.getInt("ven_cod"),rs.getDouble("ven_total"),
                        rs.getDate("ven_dtvenda").toLocalDate(), rs.getString("cli_id")));
        } 
        catch (SQLException ex) {}
        
        return aux;
    }
}