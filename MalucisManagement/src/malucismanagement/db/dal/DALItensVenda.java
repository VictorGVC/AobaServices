package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.ItensVenda;

public class DALItensVenda {
    
    public List<ItensVenda> getListItVenda(String filtro) {
        
        String sql = "SELECT * FROM Venda";
        
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
