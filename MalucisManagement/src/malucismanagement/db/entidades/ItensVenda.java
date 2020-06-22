package malucismanagement.db.entidades;

public class ItensVenda {
    
    private int ven_cod, mar_cod, qtde;
    private double preco, total;
    private String pro_cod;

    public ItensVenda() {}

    public ItensVenda(int ven_cod, int mar_cod, String pro_cod, int qtde, double preco, double total) {
        this.ven_cod = ven_cod;
        this.mar_cod = mar_cod;
        this.pro_cod = pro_cod;
        this.qtde = qtde;
        this.preco = preco;
        this.total = total;
    }

    public ItensVenda(int mar_cod, int qtde, double preco, double total, String pro_cod) {
        this.mar_cod = mar_cod;
        this.qtde = qtde;
        this.preco = preco;
        this.total = total;
        this.pro_cod = pro_cod;
    }
    
    public ItensVenda(int qtde, double preco, double total) {
        this.qtde = qtde;
        this.preco = preco;
        this.total = total;
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

    public String getPro_cod() {
        return pro_cod;
    }

    public void setPro_cod(String pro_cod) {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}