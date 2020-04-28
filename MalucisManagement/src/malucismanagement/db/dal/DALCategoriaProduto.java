package malucismanagement.db.dal;

import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.CategoriaProduto;

public class DALCategoriaProduto {
    public boolean gravar(CategoriaProduto p) {
        
        String sql = "INSERT INTO CategoriaProduto() VALUES()";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(CategoriaProduto p) {
        
        String sql = "UPDATE CategoriaProduto SET";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM CategoriaProduto c WHERE c.cat_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
}
