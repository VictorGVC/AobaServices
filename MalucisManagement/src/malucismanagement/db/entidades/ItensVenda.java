package malucismanagement.db.entidades;

public class ItensVenda {
    
    private int ven_cod, mar_cod, pro_cod, qtde;
    private double preco;

    public ItensVenda() {}

    public ItensVenda(int ven_cod, int mar_cod, int pro_cod, int qtde, double preco) {
        this.ven_cod = ven_cod;
        this.mar_cod = mar_cod;
        this.pro_cod = pro_cod;
        this.qtde = qtde;
        this.preco = preco;
    }

    public ItensVenda(int qtde, double preco) {
        this.qtde = qtde;
        this.preco = preco;
    }

    public int getVen_cod() {
        return ven_cod;
    }

    public void setVen_cod(int ven_cod) {
        this.ven_cod = ven_cod;
    }

    public int getMar_cod() {
        return mar_cod;
    }

    public void setMar_cod(int mar_cod) {
        this.mar_cod = mar_cod;
    }

    public int getPro_cod() {
        return pro_cod;
    }

    public void setPro_cod(int pro_cod) {
        this.pro_cod = pro_cod;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }   
}