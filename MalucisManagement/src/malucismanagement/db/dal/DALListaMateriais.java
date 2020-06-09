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
import malucismanagement.db.entidades.ListaItens;

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
    
    public ListaEscola getEscolaProdutos(int cod)
    {
        ListaEscola aux = null;
        
        String sql = "SELECT * FROM ListaEscola l INNER JOIN cliente c ON l.cli_id = c.cli_id WHERE l.lis_cod = '" +cod+ "'";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs.next())
                aux = new ListaEscola(rs.getString("cli_nome"), rs.getString("lis_serie"), 
                        rs.getString("lis_desc"), rs.getString("cli_id"), 
                        rs.getDouble("lis_total"), rs.getDate("lis_data"));
        }
        catch(SQLException ex){}
        
        sql = "SELECT * FROM  listaescola le INNER JOIN listamateriais lm ON lm.liscod = le.lis_cod "
                + "INNER JOIN produto p ON lm.pro_cod = p.pro_cod";
        
        rs = Banco.getCon().consultar(sql);
        ArrayList<ListaItens> prod = new ArrayList<>();
        try
        {
            if(rs.next())
                prod.add(new ListaItens(rs.getInt("lis_quantidade"), rs.getInt("pro_cod"), rs.getString("pro_nome")));
        }
        catch(SQLException ex){}
        
        if(aux != null)
            aux.setProdutos(prod);
        
        return aux;
    }
}
