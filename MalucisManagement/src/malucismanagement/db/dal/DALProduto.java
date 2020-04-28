package malucismanagement.db.dal;

import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Produto;


public class DALProduto {
    public boolean gravar(Produto p) {
        
        String sql = "INSERT INTO Produto() VALUES()";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Produto p) {
        
        String sql = "UPDATE Produto SET";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM Produto p WHERE p.pro_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
}
