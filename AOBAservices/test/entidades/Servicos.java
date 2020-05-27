package entidades;

public class Servicos {
    int ser_cod;
    String ser_descricao;

    public int getSer_cod() {
        return ser_cod;
    }

    public void setSer_cod(int ser_cod) {
        this.ser_cod = ser_cod;
    }

    public String getSer_descricao() {
        return ser_descricao;
    }

    public void setSer_descricao(String ser_descricao) {
        this.ser_descricao = ser_descricao;
    }

    public Servicos(int ser_cod, String ser_descricao) {
        this.ser_cod = ser_cod;
        this.ser_descricao = ser_descricao;
    }

    public Servicos() {
    }
}
