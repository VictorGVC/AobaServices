package malucismanagement.db.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.CategoriaProduto;
import malucismanagement.db.entidades.Produto;


public class DALProduto {
    public boolean gravar(Produto p) {
        
        String sql = "INSERT INTO Produto"
                + "(pro_nome, pro_preco, pro_quantidade, cat_cod)"
                + "VALUES('#1','#2','#3','#4')";
        sql = sql.replaceAll("#1",p.getPro_nome());
        sql = sql.replaceAll("#2", "" + p.getPro_preco());
        sql = sql.replaceAll("#3", "" + p.getPro_quantidade());
        sql = sql.replaceAll("#4", ""+p.getCat_cod());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Produto p) {
        
        String sql = "UPDATE Produto SET "
                + "pro_nome='#1', pro_preco='#2', pro_quantidade='#3', cat_cod='#4' WHERE pro_cod="+p.getPro_cod();
        
        sql = sql.replaceAll("#1",p.getPro_nome());
        sql = sql.replaceAll("#2", "" + p.getPro_preco());
        sql = sql.replaceAll("#3", "" + p.getPro_quantidade());
        sql = sql.replaceAll("#4", ""+p.getCat_cod());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM Produto p WHERE p.pro_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
    public ObservableList<Produto> getProdutos(){
        Produto p = null;
        ObservableList<Produto> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Produto");
        
        try {
            while(rs.next()){
                p = new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_qtd")),Integer.parseInt(rs.getString("pro_cat")),Double.parseDouble(rs.getString("pro_preco")),
                        rs.getString("pro_nome"));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Produto> getProdutosNome(String nome){
        Produto p = null;
        ObservableList<Produto> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Produto WHERE pro_nome = "+nome);
        
        try {
            while(rs.next()){
                p = new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_qtd")),Integer.parseInt(rs.getString("pro_cat")),Double.parseDouble(rs.getString("pro_preco")),
                        rs.getString("pro_nome"));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Produto> getProdutosPreco(Double preco){
        Produto p = null;
        ObservableList<Produto> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Produto WHERE pro_preco = "+preco);
        
        try {
            while(rs.next()){
                p = new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_qtd")),Integer.parseInt(rs.getString("pro_cat")),Double.parseDouble(rs.getString("pro_preco")),
                        rs.getString("pro_nome"));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Produto> getProdutosQtd(int qtd){
        Produto p = null;
        ObservableList<Produto> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Produto WHERE pro_quantidade = "+qtd);
        
        try {
            while(rs.next()){
                p = new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_qtd")),Integer.parseInt(rs.getString("pro_cat")),Double.parseDouble(rs.getString("pro_preco")),
                        rs.getString("pro_nome"));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        
        return lista;
    }
    
    public ObservableList<Produto> getProdutosCategoria(int Categoria){
        Produto p = null;
        ObservableList<Produto> lista = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Produto WHERE cat_cod = "+Categoria);
        
        try {
            while(rs.next()){
                p = new Produto(Integer.parseInt(rs.getString("pro_cod")),Integer.parseInt(rs.getString("pro_qtd")),Integer.parseInt(rs.getString("pro_cat")),Double.parseDouble(rs.getString("pro_preco")),
                        rs.getString("pro_nome"));
                lista.add(p);
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
