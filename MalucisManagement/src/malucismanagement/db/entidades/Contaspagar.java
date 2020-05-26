package malucismanagement.db.entidades;

import java.sql.Date;

public class Contaspagar {
    int pag_cod,pag_parcela;
    Double pag_valor;
    String for_cnpj, pag_dtvencimento,pag_dtpagamento;
    char pag_tipo;

    public Contaspagar(int pag_parcela, Double pag_valor, String for_cnpj, String pag_dtvencimento, String pag_dtpagamento, char pag_tipo) {
        this.pag_parcela = pag_parcela;
        this.pag_valor = pag_valor;
        this.for_cnpj = for_cnpj;
        this.pag_dtvencimento = pag_dtvencimento;
        this.pag_dtpagamento = pag_dtpagamento;
        this.pag_tipo = pag_tipo;
    }

    public Contaspagar(int pag_cod, int pag_parcela, Double pag_valor, String for_cnpj, String pag_dtvencimento, String pag_dtpagamento, char pag_tipo) {
        this.pag_cod = pag_cod;
        this.pag_parcela = pag_parcela;
        this.pag_valor = pag_valor;
        this.for_cnpj = for_cnpj;
        this.pag_dtvencimento = pag_dtvencimento;
        this.pag_dtpagamento = pag_dtpagamento;
        this.pag_tipo = pag_tipo;
    }

    public Contaspagar() {
    }

    public int getPag_cod() {
        return pag_cod;
    }

    public void setPag_cod(int pag_cod) {
        this.pag_cod = pag_cod;
    }

    public int getPag_parcela() {
        return pag_parcela;
    }

    public void setPag_parcela(int pag_parcela) {
        this.pag_parcela = pag_parcela;
    }

    public Double getPag_valor() {
        return pag_valor;
    }

    public void setPag_valor(Double pag_valor) {
        this.pag_valor = pag_valor;
    }

    public String getFor_cnpj() {
        return for_cnpj;
    }

    public void setFor_cnpj(String for_cnpj) {
        this.for_cnpj = for_cnpj;
    }

    public String getPag_dtvencimento() {
        return pag_dtvencimento;
    }

    public void setPag_dtvencimento(String pag_dtvencimento) {
        this.pag_dtvencimento = pag_dtvencimento;
    }

    public String getPag_dtpagamento() {
        return pag_dtpagamento;
    }

    public void setPag_dtpagamento(String pag_dtpagamento) {
        this.pag_dtpagamento = pag_dtpagamento;
    }

    public char getPag_tipo() {
        return pag_tipo;
    }

    public void setPag_tipo(char pag_tipo) {
        this.pag_tipo = pag_tipo;
    }

    
}
