package malucismanagement.db.dal;

import java.sql.PreparedStatement;
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
        catch(Exception e) {
            System.out.println(e);
        }
        
        return false;
    }
}