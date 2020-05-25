package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.ContasReceber;

public class DALContasReceber {
    
    public List<ContasReceber> getListRec(String filtro) {
        
        String sql = "SELECT * FROM ContasReceber";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <ContasReceber> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
                aux.add(new ContasReceber(rs.getInt("rec_cod"),rs.getInt("rec_parcela"),rs.getInt("ven_cod"),
                        rs.getDouble("rec_valor"),rs.getDate("rec_datavencimento").toLocalDate(), 
                        rs.getDate("rec_datapagamento").toLocalDate(),rs.getString("rec_tipo"),rs.getString("rec_contato")));
        } 
        catch (SQLException ex) {}
        
        return aux;
    }
}
