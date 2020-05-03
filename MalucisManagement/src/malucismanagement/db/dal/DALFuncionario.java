/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement.db.dal;

import malucismanagement.db.banco.Banco;
import malucismanagement.db.entidades.Funcionario;

/**
 *
 * @author HITRON
 */
public class DALFuncionario {
    public boolean gravar(Funcionario f, String senha) 
    {
        String sql = "INSERT INTO Cliente(cli_id, cli_nome, cli_sexo, cli_datanasc, cli_email, cli_fone, "
                + "cli_cep, cli_rua, cli_numero, cli_bairro, cli_cidade, cli_uf) "
                + "VALUES (#1,#2,'#3',#4,'#5','#6','#7','#8','#9','#10','#11','#12');";
        
        sql = sql.replaceAll("#1", "" + f.getCpf());
        sql = sql.replaceAll("#2", "" + f.getNome());
        sql = sql.replaceAll("#3", "" + f.getSexo());
        sql = sql.replaceAll("#4", "" + f.getDatanasc().toString());
        sql = sql.replaceAll("#5", "" + f.getEmail());
        sql = sql.replaceAll("#6", "" + f.getTelefone());
        sql = sql.replaceAll("#7", "" + f.getCep());
        sql = sql.replaceAll("#8", "" + f.getRua());
        sql = sql.replaceAll("#9", "" + f.getNumero());
        sql = sql.replaceAll("#10", "" + f.getBairro());
        sql = sql.replaceAll("#11", "" + f.getCidade());
        sql = sql.replaceAll("#12", "" + f.getUf());
        
        sql += "INSERT INTO Login(log_usuario, cli_id, log_nivel, log_senha)"
                + "VALUES ('#1','#2',#3,'#4');";
        sql = sql.replaceAll("#1", "" + f.getLogin());
        sql = sql.replaceAll("#2", "" + f.getCpf());
        sql = sql.replaceAll("#3", "" + f.getNivel());
        sql = sql.replaceAll("#3", "" + senha);
        
        return Banco.getCon().manipular(sql);
    }
}
