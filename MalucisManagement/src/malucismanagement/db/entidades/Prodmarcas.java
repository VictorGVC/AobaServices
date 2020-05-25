package malucismanagement.db.entidades;

public class Prodmarcas {
    
    int mar_cod, estoque;
    String pro_cod;

    public Prodmarcas() {}
    
    public Prodmarcas(int mar_cod, String pro_cod, int estoque) {
        this.mar_cod = mar_cod;
        this.pro_cod = pro_cod;
        this.estoque = estoque;
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

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
