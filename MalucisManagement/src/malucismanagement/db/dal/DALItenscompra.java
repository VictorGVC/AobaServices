package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Itenscompra;

public class DALItenscompra {
    public boolean gravar(Itenscompra it) throws SQLException {
        
        
        String sql = "INSERT INTO itenscompra (com_cod,mar_cod,pro_cod,it_quantidade,it_preco)"
                + "VALUES (#1,#2,#3,#4,#5)";
        sql = sql.replaceAll("#1","" + it.getCom_cod());
        sql = sql.replaceAll("#2","" + it.getMar_cod());
        sql = sql.replaceAll("#3","" + it.getPro_cod());
        sql = sql.replaceAll("#4","" + it.getIt_quantidade());
        sql = sql.replaceAll("#5","" + it.getIt_preco());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Itenscompra it) throws SQLException {
        
        String sql = "UPDATE itenscompra SET "
                + "mar_cod =#2, pro_cod =#3, it_quantidade =#4, it_peco =#5 WHERE com_cod="+it.getCom_cod();
        
        sql = sql.replaceAll("#1","" + it.getMar_cod());
        sql = sql.replaceAll("#2","" + it.getPro_cod());
        sql = sql.replaceAll("#3","" + it.getIt_quantidade());
        sql = sql.replaceAll("#4","" + it.getIt_preco());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM itenscompra WHERE com_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Itenscompra> getItenscompra(){
        List <Itenscompra> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM itenscompra");
        
        try {
            while(rs.next()){
                lista.add(new Itenscompra(Integer.parseInt(rs.getString("com_cod")), Integer.parseInt(rs.getString("mar_cod")),Integer.parseInt(rs.getString("pro_cod")),
                Integer.parseInt(rs.getString("it_quantidade")),Double.parseDouble(rs.getString("it_preco"))));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
}
