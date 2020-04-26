package malucismanagement.db.dal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Parametrizacao;

public class DALParametrizacao {
    
    public boolean gravar(Parametrizacao p) {
        
        String sql = "INSERT INTO Parametrizacao() VALUES()";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Parametrizacao p) {
        
        String sql = "UPDATE Parametrizacao SET";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean gravarFoto(Parametrizacao p) {
        
        try {
            
            PreparedStatement st = Banco.con.getConnect().prepareStatement("UPDATE Parametrizacao SET par_logo = ?");
            
            st.setBytes(1, p.getLogo());
            st.execute();
            
            return true;
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        
        return false;
    }
    
    public Parametrizacao getConfig() {
        
        String sql = "SELECT * FROM parametrizacao";
        Parametrizacao p = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            p = new Parametrizacao(rs.getString(""),rs.getString(""),rs.getString(""),
                    rs.getString(""),rs.getString(""),rs.getString(""),rs.getString(""),
                    rs.getString(""),rs.getString(""));
        } 
        catch (SQLException ex) {}
        
        return p;
    }
    
    public InputStream getFoto() {
        
        InputStream is = null;
        
        try{
            
            PreparedStatement ps = Banco.getCon().getConnect().
                    prepareStatement("SELECT par_logo FROM parametrizacao");
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                byte[] bytes = rs.getBytes("par_logo");
                is = new ByteArrayInputStream(bytes);
            }
            rs.close();
            ps.close();
        }
        catch(SQLException e) {
            return null;
        }
        
        return is;
    }
}