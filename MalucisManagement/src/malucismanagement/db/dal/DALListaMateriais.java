/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.ListaEscola;

/**
 *
 * @author HITRON
 */
public class DALListaMateriais 
{
    
    public List<ListaEscola> get(String filtro)
    {
        String sql = "SELECT * FROM Cliente c RIGHT JOIN listaescola l ON c.cli_id = l.cli_id";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <ListaEscola> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
            {
                aux.add(new ListaEscola(rs.getString("cli_nome"), rs.getString("lis_serie"), rs.getString("lis_desc"), rs.getDouble("lis_total")));
            }     
        } 
        catch (SQLException ex) {}
        
        return aux;
    }
}
