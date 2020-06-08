/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement.db.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HITRON
 */
public class ListaEscola 
{
    
    private String escola,serie,descricao,cnpj;
    private Date data;
    private double total;
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    @Override
    public String toString() {
        return this.getEscola();
    }
    
    public String getEscola() {
        return escola;
    }

    public ListaEscola(String escola, String serie, String descricao, double total) {
        this.escola = escola;
        this.serie = serie;
        this.descricao = descricao;
        this.total = total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ListaEscola(String escola, String serie, String descricao, String cnpj, double total, Date data) {
        this.escola = escola;
        this.serie = serie;
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.total = total;
        this.data = data;
    }

    public ListaEscola(String escola, String serie, String descricao, String cnpj, double total, Date data, List<Produto> produtos) {
        this.escola = escola;
        this.serie = serie;
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.total = total;
        this.data = data;
    }
    
    public ListaEscola() {
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
