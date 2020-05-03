/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malucismanagement.db.entidades;

import java.time.LocalDate;

/**
 *
 * @author HITRON
 */
public class Funcionario {
    private int numero, nivel;
    private char sexo;
    private String nome, cpf, email, telefone, cep, rua, bairro, cidade, uf, login; 
    private LocalDate datanasc;
    private boolean ativo;

    public Funcionario(int numero, char sexo, String nome, String cpf, String email, String telefone, String cep, String rua, String bairro, String cidade, String uf, String login, LocalDate datanasc, boolean ativo,int nivel) {
        this.numero = numero;
        this.sexo = sexo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.login = login;
        this.datanasc = datanasc;
        this.ativo = ativo;
        this.nivel = nivel;
    }

    public Funcionario(int numero, char sexo, String nome, String email, String telefone, String cep, String rua, String bairro, String cidade, String uf, String login, LocalDate datanasc, boolean ativo,int nivel) {
        this.numero = numero;
        this.sexo = sexo;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.login = login;
        this.datanasc = datanasc;
        this.ativo = ativo;
        this.nivel = nivel;
    }

    public Funcionario(String cpf) {
        this.cpf = cpf;
    }

    public Funcionario() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDate getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(LocalDate datanasc) {
        this.datanasc = datanasc;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
}
