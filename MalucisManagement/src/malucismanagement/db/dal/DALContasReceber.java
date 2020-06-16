package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.ContasReceber;

public class DALContasReceber {
    
    public boolean gravar1(ContasReceber c) {
        
        String sql = "INSERT INTO ContasReceber(rec_parcela, rec_dtvencimento, rec_tipo, rec_contato, "
                + "rec_valor, ven_cod) VALUES (#1,'#2','#3','#4',#5,#6)";
        
        sql = sql.replaceAll("#1", "" + c.getParcela());
        sql = sql.replaceAll("#2", c.getDtvencimento().toString());
        sql = sql.replaceAll("#3", c.getTipo());
        sql = sql.replaceAll("#4", c.getContato());
        sql = sql.replaceAll("#5", "" + c.getValor());
        sql = sql.replaceAll("#6", "" + c.getVen_cod());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean gravar2(ContasReceber c) {
        
        String sql = "INSERT INTO ContasReceber(rec_parcela, rec_dtvencimento, rec_dtpagamento, rec_tipo, "
                + "rec_valor, ven_cod) VALUES (#1,'#2','#3','#4',#5,'#6')";
        
        sql = sql.replaceAll("#1", "" + c.getParcela());
        sql = sql.replaceAll("#2", "" + c.getDtvencimento().toString());
        sql = sql.replaceAll("#3", "" + c.getDtpagamento().toString());
        sql = sql.replaceAll("#4", c.getTipo());
        sql = sql.replaceAll("#5", "" + c.getValor());
        sql = sql.replaceAll("#6", "" + c.getVen_cod());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(ContasReceber c) {
        
        String sql = "UPDATE ContasReceber SET rec_parcela=#1, rec_dtvencimento='#2', rec_dtpagamento='#3', "
                + "rec_tipo='#4', rec_contato='#5', rec_valor=#6, ven_cod='#7' WHERE rec_cod=" + c.getCod();
        
        sql = sql.replaceAll("#1", "" + c.getParcela());
        sql = sql.replaceAll("#2", "" + c.getDtvencimento().toString());
        sql = sql.replaceAll("#3", "" + c.getDtpagamento().toString());
        sql = sql.replaceAll("#4", c.getTipo());
        sql = sql.replaceAll("#5", c.getContato());
        sql = sql.replaceAll("#6", "" + c.getValor());
        sql = sql.replaceAll("#7", "" + c.getVen_cod());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar(int v) {
        
        return Banco.getCon().manipular("DELETE FROM ContasReceber WHERE ven_cod=" + v);
    }
    
     public boolean manter(int v) {
        
        return Banco.getCon().manipular("UPDATE ContasReceber SET ven_cod=null WHERE ven_cod=" + v);
    }
    
    public ContasReceber getCon(int cod) {
        
        ContasReceber aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM ContasReceber WHERE rec_cod=" + cod);
        
        try{
            
            if(rs.next())
                aux = new ContasReceber(rs.getInt("rec_cod"),rs.getInt("rec_parcela"),rs.getInt("ven_cod"),
                        rs.getDouble("rec_valor"),rs.getDate("rec_datavencimento").toLocalDate(), 
                        rs.getDate("rec_datapagamento").toLocalDate(),rs.getString("rec_tipo"),rs.getString("rec_contato"));
        } 
        catch(SQLException ex) {}
        
        return aux;
    }
    
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