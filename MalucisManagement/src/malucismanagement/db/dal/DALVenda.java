package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Venda;

public class DALVenda {
    
    public List<Venda> getListVenda(String filtro) {
        
        String sql = "SELECT * FROM ItensVenda";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Venda> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
                aux.add(new Venda(rs.getInt("ven_cod"),rs.getDouble("ven_total"),
                        rs.getDate("ven_datavenda").toLocalDate(), rs.getString("cli_id")));
        } 
        catch (SQLException ex) {}
        
        return aux;
    }
}
