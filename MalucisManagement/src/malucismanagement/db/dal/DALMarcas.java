package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Marcas;

public class DALMarcas {
    public boolean gravar(Marcas m) throws SQLException {
        
        
        String sql = "INSERT INTO marcas (mar_nome) "
                + "VALUES ('#1')";
        sql = sql.replaceAll("#1","" + m.getMar_nome());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Marcas m) throws SQLException {
        
        String sql = "UPDATE marcas SET "
                + "mar_nome ='#1' WHERE mar_cod="+m.getMar_cod();
        
        sql = sql.replaceAll("#1","" + m.getMar_nome());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM marcas WHERE mar_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Marcas> getMarcas(){
        List <Marcas> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM marcas");
        
        try {
            while(rs.next()){
                lista.add(new Marcas(Integer.parseInt(rs.getString("mar_cod")), rs.getString("mar_nome")));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
}
