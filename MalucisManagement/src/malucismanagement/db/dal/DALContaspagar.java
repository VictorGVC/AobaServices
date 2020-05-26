package malucismanagement.db.dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Contaspagar;
import malucismanagement.db.entidades.Fornecedor;

public class DALContaspagar {
    public boolean gravar(Contaspagar ct) throws SQLException {
        
        
        String sql = "INSERT INTO contaspagar (pag_parcela,pag_dtvencimento,pag_tipo,pag_dtpagamento,pag_valor,for_cnpj)"
                + "VALUES (#1,'#2','#3','#4',#5,'#6')";
        sql = sql.replaceAll("#1","" + ct.getPag_parcela());
        sql = sql.replaceAll("#2","" + ct.getPag_dtvencimento());
        sql = sql.replaceAll("#3","" + ct.getPag_tipo());
        sql = sql.replaceAll("#4","" + ct.getPag_dtpagamento());
        sql = sql.replaceAll("#5","" + ct.getPag_valor());
        sql = sql.replaceAll("#5","" + ct.getFor_cnpj());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Contaspagar ct) throws SQLException {
        
        String sql = "UPDATE contaspagar SET "
                + "pag_parcela =#1, pag_dtvencimento = '#2', pag_tipo ='#3', pag_dtpagamento='#4', pag_valor=#5, for_cnpj='#6' WHERE pag_cod="+ct.getPag_cod();
        
        sql = sql.replaceAll("#1","" + ct.getPag_parcela());
        sql = sql.replaceAll("#2","" + ct.getPag_dtvencimento());
        sql = sql.replaceAll("#3","" + ct.getPag_tipo());
        sql = sql.replaceAll("#4","" + ct.getPag_dtpagamento());
        sql = sql.replaceAll("#5","" + ct.getPag_valor());
        sql = sql.replaceAll("#6","" + ct.getFor_cnpj());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM contaspagar WHERE pag_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Contaspagar> getContapagarNaoPagas(){
        List <Contaspagar> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM contaspagar WHERE pag_dtpagamento is NULL");
        try {
            while(rs.next()){
                DALFornecedores dal = new DALFornecedores();
                String nomeFornecedor = dal.getFornecedorNome(rs.getString("for_cnpj"));
                lista.add(new Contaspagar(Integer.parseInt(rs.getString("pag_cod")),Integer.parseInt(rs.getString("pag_parcela")),Double.parseDouble(rs.getString("pag_valor")),
                        nomeFornecedor, rs.getString("pag_dtvencimento"), "",rs.getString("pag_tipo").charAt(0)));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public List<Contaspagar> getContapagarPagas(){
        List <Contaspagar> lista = new ArrayList();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM contaspagar WHERE pag_dtpagamento is not NULL");
        try {
            while(rs.next()){
                DALFornecedores dal = new DALFornecedores();
                String nomeFornecedor = dal.getFornecedorNome(rs.getString("for_cnpj"));
                lista.add(new Contaspagar(Integer.parseInt(rs.getString("pag_cod")),Integer.parseInt(rs.getString("pag_parcela")),Double.parseDouble(rs.getString("pag_valor")),
                        nomeFornecedor, rs.getString("pag_dtvencimento"), rs.getString("pag_dtpagamento"),rs.getString("pag_tipo").charAt(0)));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
            return lista;
    }
    
    public List<Contaspagar> getContapagarFornecedorNaoPag(String fornecedor){
        List <Contaspagar> lista = new ArrayList();
        DALFornecedores dal = new DALFornecedores();
        List <String> forn = dal.getFornecedoresNomes(fornecedor);
        for (int i = 0; i < forn.size(); i++) {
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM contaspagar WHERE for_cnpj like '"+forn.get(i)+"' AND pag_dtpagamento is null");
        
        try {
            while(rs.next()){
                dal = new DALFornecedores();
                String nomeFornecedor = dal.getFornecedorNome(rs.getString("for_cnpj"));
                lista.add(new Contaspagar(Integer.parseInt(rs.getString("pag_cod")),Integer.parseInt(rs.getString("pag_parcela")),Double.parseDouble(rs.getString("pag_valor")),
                        nomeFornecedor, rs.getString("pag_dtvencimento"), "",rs.getString("pag_tipo").charAt(0)));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return lista;
    }
    
    public List<Contaspagar> getContapagarFornecedorPag(String fornecedor){
        List <Contaspagar> lista = new ArrayList();
        DALFornecedores dal = new DALFornecedores();
        List <String> forn = dal.getFornecedoresNomes(fornecedor);
        for (int i = 0; i < forn.size(); i++) {
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM contaspagar WHERE for_cnpj like '"+forn.get(i)+"' AND pag_dtpagamento is not null");
        
        try {
            while(rs.next()){
                dal = new DALFornecedores();
                String nomeFornecedor = dal.getFornecedorNome(rs.getString("for_cnpj"));
                lista.add(new Contaspagar(Integer.parseInt(rs.getString("pag_cod")),Integer.parseInt(rs.getString("pag_parcela")),Double.parseDouble(rs.getString("pag_valor")),
                        nomeFornecedor, rs.getString("pag_dtvencimento"), rs.getString("pag_dtpagamento"),rs.getString("pag_tipo").charAt(0)));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return lista;
    }
    
    public Boolean QuitarConta(int cod){
        Calendar hoje = Calendar.getInstance();
        
        String sql = "UPDATE contaspagar SET pag_dtpagamento = '#1' WHERE pag_cod="+cod;
        
        sql = sql.replaceAll("#1","" +hoje.getTime());

        return Banco.getCon().manipular(sql);
    }
    
    public Boolean ExtornarConta(int cod){
        String sql = "UPDATE contaspagar SET "
                + "pag_dtpagamento =null WHERE pag_cod="+cod;

        return Banco.getCon().manipular(sql);
    }
}
