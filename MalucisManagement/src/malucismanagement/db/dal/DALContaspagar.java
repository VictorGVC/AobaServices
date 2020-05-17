package malucismanagement.db.dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Contaspagar;
import malucismanagement.db.entidades.Fornecedor;

public class DALContaspagar {
    public boolean gravar(Contaspagar ct) throws SQLException {
        
        
        String sql = "INSERT INTO contaspagar (pag_parcela,pag_dtvencimento,pag_tipo,pag_contato,pag_valor,pag_status,for_cnpj)"
                + "VALUES (#1,'#2','#3','#4',#5,'#6','#7')";
        sql = sql.replaceAll("#1","" + ct.getPag_parcela());
        sql = sql.replaceAll("#2","" + ct.getPag_dtvencimento());
        sql = sql.replaceAll("#3","" + ct.getPag_tipo());
        sql = sql.replaceAll("#4","" + ct.getPag_contato());
        sql = sql.replaceAll("#5","" + ct.getPag_valor());
        sql = sql.replaceAll("#6","" + ct.getPag_status());
        sql = sql.replaceAll("#7","" + ct.getFor_cnpj());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Contaspagar ct) throws SQLException {
        
        String sql = "UPDATE contaspagar SET "
                + "pag_parcela =#1, pag_dtvencimento = '#2', pag_tipo ='#3', pag_contato='#4', pag_valor=#5, pag_status='#6', for_cnpj='#7' WHERE pag_cod="+ct.getPag_cod();
        
        sql = sql.replaceAll("#1","" + ct.getPag_parcela());
        sql = sql.replaceAll("#2","" + ct.getPag_dtvencimento());
        sql = sql.replaceAll("#3","" + ct.getPag_tipo());
        sql = sql.replaceAll("#4","" + ct.getPag_contato());
        sql = sql.replaceAll("#5","" + ct.getPag_valor());
        sql = sql.replaceAll("#6","" + ct.getPag_status());
        sql = sql.replaceAll("#7","" + ct.getFor_cnpj());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM contaspagar WHERE pag_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Contaspagar> getContapagar(){
        List <Contaspagar> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM contaspagar");
        
        try {
            while(rs.next()){
                lista.add(new Contaspagar(Integer.parseInt(rs.getString("pag_parcela")),Double.parseDouble(rs.getString("pag_valor")),rs.getString("pag_contato"),
                    rs.getString("for_cnpj"),(Date) new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("pag_vencimento")),
                        rs.getString("pag_tipo").charAt(0),rs.getString("pag_tipo").charAt(0)));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public List<Contaspagar> getContapagarFornecedor(String fornecedor){
        List <Contaspagar> lista = new ArrayList();
        DALFornecedores dal = new DALFornecedores();
        List <Fornecedor> forn = dal.getFornecedoresNome(fornecedor);
        for (int i = 0; i < forn.size(); i++) {
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM contaspagar WHERE for_cnpj like '"+forn.get(i).getFor_cnpj()+"'");
        
        try {
            while(rs.next()){
                lista.add(new Contaspagar(Integer.parseInt(rs.getString("pag_parcela")),Double.parseDouble(rs.getString("pag_valor")),rs.getString("pag_contato"),
                    rs.getString("for_cnpj"),(Date) new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("pag_vencimento")),
                        rs.getString("pag_tipo").charAt(0),rs.getString("pag_tipo").charAt(0)));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return lista;
    }
    
    public List<Contaspagar> getContapagarTipo(String tipo){
        List <Contaspagar> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM contaspagar WHERE pag_tipo like '"+tipo+"'");
        
        try {
            while(rs.next()){
                lista.add(new Contaspagar(Integer.parseInt(rs.getString("pag_parcela")),Double.parseDouble(rs.getString("pag_valor")),rs.getString("pag_contato"),
                    rs.getString("for_cnpj"),(Date) new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("pag_vencimento")),
                        rs.getString("pag_tipo").charAt(0),rs.getString("pag_tipo").charAt(0)));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public List<Contaspagar> getContapagarStatus(String status){
        List <Contaspagar> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM contaspagar WHERE pag_status like '"+status+"'");
        
        try {
            while(rs.next()){
                lista.add(new Contaspagar(Integer.parseInt(rs.getString("pag_parcela")),Double.parseDouble(rs.getString("pag_valor")),rs.getString("pag_contato"),
                    rs.getString("for_cnpj"),(Date) new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("pag_vencimento")),
                        rs.getString("pag_tipo").charAt(0),rs.getString("pag_tipo").charAt(0)));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public Boolean QuitarConta(int cod){
        String sql = "UPDATE contaspagar SET "
                + "pag_status ='F' WHERE pag_cod="+cod;

        return Banco.getCon().manipular(sql);
    }
}
