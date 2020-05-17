package malucismanagement.db.entidades;

public class Prodmarcas {
    int mar_cod,pro_cod,estoque;

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

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Prodmarcas(int mar_cod, int pro_cod, int estoque) {
        this.mar_cod = mar_cod;
        this.pro_cod = pro_cod;
        this.estoque = estoque;
    }

    public Prodmarcas() {
    }
    
}
