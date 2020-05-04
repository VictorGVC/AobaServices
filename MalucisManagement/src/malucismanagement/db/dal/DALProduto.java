package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.CategoriaProduto;
import malucismanagement.db.entidades.Produto;


public class DALProduto {
    public boolean gravar(Produto p) throws SQLException {
        
        DALCategoriaProduto dalct = new DALCategoriaProduto();
        
        String sql = "INSERT INTO produtos (pro_nome, pro_preco, pro_quantidade, cat_cod) "
                + "VALUES ('#1',#2,#3,#4)";
        sql = sql.replaceAll("#1",p.getPro_nome());
        sql = sql.replaceAll("#2", "" + p.getPro_preco());
        sql = sql.replaceAll("#3", "" + p.getPro_quantidade());
        sql = sql.replaceAll("#4","" + dalct.getCategoriaProduto(p.getCat_cod()));
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Produto p) throws SQLException {
        
        DALCategoriaProduto dalct = new DALCategoriaProduto();
        
        String sql = "UPDATE Produto SET "
                + "pro_cod = '#1', pro_nome='#2', pro_preco='#3', pro_quantidade='#4', cat_cod='#5' WHERE pro_cod="+p.getPro_cod();
        
        sql = sql.replaceAll("#1","" + p.getPro_cod());
        sql = sql.replaceAll("#2",p.getPro_nome());
        sql = sql.replaceAll("#3", "" + p.getPro_preco());
        sql = sql.replaceAll("#4", "" + p.getPro_quantidade());
        sql = sql.replaceAll("#5", "" + dalct.getCategoriaProduto(p.getCat_cod()));
        
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
    
    public List<Produto> getProdutosNome(String nome){
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto WHERE Lower(pro_nome) like '%"+nome.toLowerCase()+"%'");
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public List<Produto> getProdutosPreco(Double preco){
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto WHERE pro_preco ="+preco);
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public List<Produto> getProdutosQtd(int qtd){
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto WHERE pro_quantidade ="+qtd);
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public List<Produto> getProdutosCategoria(String Categoria) throws SQLException{
        List <Produto> lista = new ArrayList();
        DALCategoriaProduto ctdal = new DALCategoriaProduto();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM produto WHERE cat_cod ="+ctdal.getCategoriaProduto(Categoria));
        
        try {
            while(rs.next()){
                lista.add(new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_quantidade")),rs.getString("cat_nome"),
                        Double.parseDouble(rs.getString("pro_preco")),rs.getString("pro_nome")));
            }
        } catch (Exception e) {
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
