package malucismanagement.db.dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Compras;

public class DALCompras {
    public boolean gravar(Compras c) throws SQLException {
        
        
        String sql = "INSERT INTO compras (com_cod,com_total,com_dtcompra,for_cnpj)"
                + "VALUES (#1,#2,'#3'#4')";
        sql = sql.replaceAll("#1","" + c.getCom_cod());
        sql = sql.replaceAll("#2","" + c.getCom_total());
        sql = sql.replaceAll("#3","" + c.getCom_dtcompra());
        sql = sql.replaceAll("#4","" + c.getFor_cnpj());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Compras c) throws SQLException {
        
        String sql = "UPDATE compras SET "
                + "com_total =#1, com_dtcompra = '#2', for_cnpj = '#3' WHERE com_cod="+c.getCom_cod();
        
        sql = sql.replaceAll("#1","" + c.getCom_total());
        sql = sql.replaceAll("#2","" + c.getCom_dtcompra());
        sql = sql.replaceAll("#3","" + c.getFor_cnpj());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM compras WHERE com_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Compras> getCompras(){
        List <Compras> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM compras");
        
        try {
            while(rs.next()){
                lista.add(new Compras(Integer.parseInt(rs.getString("com_cod")),rs.getString("for_cnpj"),Double.parseDouble("com_total"),
                        (Date) new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("com_dtcompra"))));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
}
