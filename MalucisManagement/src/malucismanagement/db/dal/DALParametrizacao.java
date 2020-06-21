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
        
        String sql = "INSERT INTO Parametrizacao"
                + "(par_cprimaria, par_csecundaria, par_fonte, par_cfonte, par_fone, par_cep, par_rua, par_cidade, par_uf) "
                + "VALUES('#1','#2','#3','#4','#5','#6','#7','#8','#9')";
        
        sql = sql.replaceAll("#1", p.getCorprimaria());
        sql = sql.replaceAll("#2", p.getCorsecundaria());
        sql = sql.replaceAll("#3", p.getFonte());
        sql = sql.replaceAll("#4", p.getCorfonte());
        sql = sql.replaceAll("#5", p.getTelefone());
        sql = sql.replaceAll("#6", p.getCep());
        sql = sql.replaceAll("#7", p.getRua());
        sql = sql.replaceAll("#8", p.getCidade());
        sql = sql.replaceAll("#9", p.getUf());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Parametrizacao p) {
        
        String sql = "UPDATE Parametrizacao SET "
                + "par_cprimaria='#1', par_csecundaria='#2', par_fonte='#3', par_cfonte='#4', par_fone='#5' ,"
                + "par_cep='#6' ,par_rua='#7', par_cidade='#8', par_uf='#9'";
        
        sql = sql.replaceAll("#1", p.getCorprimaria());
        sql = sql.replaceAll("#2", p.getCorsecundaria());
        sql = sql.replaceAll("#3", p.getFonte());
        sql = sql.replaceAll("#4", p.getCorfonte());
        sql = sql.replaceAll("#5", p.getTelefone());
        sql = sql.replaceAll("#6", p.getCep());
        sql = sql.replaceAll("#7", p.getRua());
        sql = sql.replaceAll("#8", p.getCidade());
        sql = sql.replaceAll("#9", p.getUf());
        
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
            
            if(rs.next())
                p = new Parametrizacao(rs.getString("par_cprimaria"),rs.getString("par_csecundaria"),rs.getString("par_fonte"),
                    rs.getString("par_cfonte"),rs.getString("par_fone"),rs.getString("par_rua"),rs.getString("par_cep"),
                    rs.getString("par_uf"),rs.getString("par_cidade"));
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