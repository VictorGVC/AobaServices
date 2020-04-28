package malucismanagement.db.dal;

import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Fornecedor;

public class DALFornecedores {
    
     public boolean gravar(Fornecedor p) {
        
        String sql = "INSERT INTO Fornecedores() VALUES()";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Fornecedor p) {
        
        String sql = "UPDATE Fornecedores SET";
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean excluir(int codigo){
        String sql = "DELETE FROM Fornecedores f WHERE f.for_cod ="+codigo;
        
        return Banco.getCon().manipular(sql);
    }
    
}
