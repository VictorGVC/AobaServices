package malucismanagement.db.entidades;

import java.time.LocalDate;

public class ContasReceber {
    
    private int cod, parcela, ven_cod;
    private double valor;
    private LocalDate dtvencimento, dtpagamento;
    private String tipo, contato;

    public ContasReceber() {}

    public ContasReceber(int cod, int parcela, int ven_cod, double valor, LocalDate dtvencimento, LocalDate dtpagamento, String tipo, String contato) {
        this.cod = cod;
        this.parcela = parcela;
        this.ven_cod = ven_cod;
        this.valor = valor;
        this.dtvencimento = dtvencimento;
        this.dtpagamento = dtpagamento;
        this.tipo = tipo;
        this.contato = contato;
    }

    public ContasReceber(int parcela, int ven_cod, double valor, LocalDate dtvencimento, LocalDate dtpagamento, String tipo, String contato) {
        this.parcela = parcela;
        this.ven_cod = ven_cod;
        this.valor = valor;
        this.dtvencimento = dtvencimento;
        this.dtpagamento = dtpagamento;
        this.tipo = tipo;
        this.contato = contato;
    }

    public ContasReceber(int parcela, int ven_cod, double valor, LocalDate dtvencimento, LocalDate dtpagamento, String tipo) {
        this.parcela = parcela;
        this.ven_cod = ven_cod;
        this.valor = valor;
        this.dtvencimento = dtvencimento;
        this.dtpagamento = dtpagamento;
        this.tipo = tipo;
    }
    
    public ContasReceber(int cod, int parcela, int ven_cod, double valor, LocalDate dtvencimento, String tipo, String contato) {
        this.cod = cod;
        this.parcela = parcela;
        this.ven_cod = ven_cod;
        this.valor = valor;
        this.dtvencimento = dtvencimento;
        this.tipo = tipo;
        this.contato = contato;
    }

    public ContasReceber(int parcela, int ven_cod, double valor, LocalDate dtvencimento, String tipo, String contato) {
        this.parcela = parcela;
        this.ven_cod = ven_cod;
        this.valor = valor;
        this.dtvencimento = dtvencimento;
        this.tipo = tipo;
        this.contato = contato;
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public int getVen_cod() {
        return ven_cod;
    }

    public void setVen_cod(int ven_cod) {
        this.ven_cod = ven_cod;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDtvencimento() {
        return dtvencimento;
    }

    public void setDtvencimento(LocalDate dtvencimento) {
        this.dtvencimento = dtvencimento;
    }

    public LocalDate getDtpagamento() {
        return dtpagamento;
    }

    public void setDtpagamento(LocalDate dtpagamento) {
        this.dtpagamento = dtpagamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }   
}