/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement.db.entidades;

/**
 *
 * @author HITRON
 */
public class ListaItens 
{
    int quantidade, codigo;
    double preco,total;
    String nome,categoria;

    public ListaItens(int quantidade, double preco, int codigo, String nome, String categoria) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ListaItens(int quantidade, int codigo, String nome) {
        this.quantidade = quantidade;
        this.codigo = codigo;
        this.nome = nome;
    }
    
    

    public ListaItens() {
    }

    public ListaItens(int quantidade, double preco, String nome, String categoria) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.nome = nome;
        this.categoria = categoria;
    }
    

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
