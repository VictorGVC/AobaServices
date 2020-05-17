package malucismanagement.db.entidades;

import java.sql.Date;

public class Contaspagar {
    int pag_cod,pag_parcela;
    Double pag_valor;
    String pag_contato,for_cnpj;
    Date pag_dtvencimento;
    char pag_tipo,pag_status;

    public Contaspagar() {
    }

    public Contaspagar(int pag_parcela, Double pag_valor, String pag_contato, String for_cnpj, Date pag_dtvencimento, char pag_tipo, char pag_status) {
        this.pag_parcela = pag_parcela;
        this.pag_valor = pag_valor;
        this.pag_contato = pag_contato;
        this.for_cnpj = for_cnpj;
        this.pag_dtvencimento = pag_dtvencimento;
        this.pag_tipo = pag_tipo;
        this.pag_status = pag_status;
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

    public String getPag_contato() {
        return pag_contato;
    }

    public void setPag_contato(String pag_contato) {
        this.pag_contato = pag_contato;
    }

    public String getFor_cnpj() {
        return for_cnpj;
    }

    public void setFor_cnpj(String for_cnpj) {
        this.for_cnpj = for_cnpj;
    }

    public Date getPag_dtvencimento() {
        return pag_dtvencimento;
    }

    public void setPag_dtvencimento(Date pag_dtvencimento) {
        this.pag_dtvencimento = pag_dtvencimento;
    }

    public char getPag_tipo() {
        return pag_tipo;
    }

    public void setPag_tipo(char pag_tipo) {
        this.pag_tipo = pag_tipo;
    }

    public char getPag_status() {
        return pag_status;
    }

    public void setPag_status(char pag_status) {
        this.pag_status = pag_status;
    }
    
    
}
