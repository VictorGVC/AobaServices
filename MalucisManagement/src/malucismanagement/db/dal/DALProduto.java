package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Produto;


public class DALProduto {
    public boolean gravar(Produto p) throws SQLException {
        
        DALCategoriaProduto dalct = new DALCategoriaProduto();
        
        String sql = "INSERT INTO produto (pro_nome, pro_preco, pro_quantidade, cat_cod) "
                + "VALUES (#1,#'2',#3,#4,#5)";
        sql = sql.replaceAll("#1","" + p.getPro_cod());
        sql = sql.replaceAll("#2",p.getPro_nome());
        sql = sql.replaceAll("#3", "" + p.getPro_preco());
        sql = sql.replaceAll("#4", "" + p.getPro_quantidade());
        sql = sql.replaceAll("#5","" + dalct.getCategoriaProduto(p.getCat_cod()));
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Produto p) throws SQLException {
        
        DALCategoriaProduto dalct = new DALCategoriaProduto();
        
        String sql = "UPDATE produto SET "
                + "pro_cod =#1, pro_nome='#2', pro_preco=#3, pro_quantidade=#4, cat_cod=#5 WHERE pro_cod="+p.getPro_cod();
        
        sql = sql.replaceAll("#1","" + p.getPro_cod());
        sql = sql.replaceAll("#1",p.getPro_nome());
        sql = sql.replaceAll("#2", "" + p.getPro_preco());
        sql = sql.replaceAll("#3", "" + p.getPro_quantidade());
        sql = sql.replaceAll("#4", "" + dalct.getCategoriaProduto(p.getCat_cod()));
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM Produto p WHERE p.pro_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Produto> getProdutos(){
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto p inner join CategoriaProduto ct ON"
                + " p.cat_cod = ct.cat_cod");
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public Produto getProdutoCod(String cod){
        
        Produto p = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto WHERE pro_cod='" + cod + "'");
        
        try{
            
            if(rs.next())
                p = new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome"));
        }
        catch(SQLException e){}
        
        return p;
    }
    
    public List<Produto> getProdutosNome(String nome){
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto p inner join CategoriaProduto ct ON"
                + " Lower(p.pro_nome) like '%"+nome.toLowerCase()+"%' and ct.cat_cod = p.cat_cod");
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public List<Produto> getProdutosPreco(Double preco){
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto p inner join CategoriaProduto ct ON"
                + " p.pro_preco = "+preco+" and ct.cat_cod = p.cat_cod");
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public List<Produto> getProdutosQtd(int qtd){
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto p inner join CategoriaProduto ct ON"
                + " p.pro_quantidade = "+qtd+" and ct.cat_cod = p.cat_cod");
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public List<Produto> getProdutosCategoria(String Categoria) throws SQLException{
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto p inner join CategoriaProduto ct ON"
                + " ct.cat_cod = p.cat_cod and ct.cat_cod ="+ctdal.getCategoriaProduto(Categoria));
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
            }
        } catch (Exception e) {
                System.out.println(e);
        }
        
        return lista;
    }
    
    public int getCodCat(String NomeCat){
        
        DALCategoriaProduto DALct = new DALCategoriaProduto();
        
        try {
            int cod = DALct.getCategoriaProduto(NomeCat);
        
                return cod;
        } 
        catch (SQLException ex) {}
        
        return -1;
    }
}
