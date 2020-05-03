package malucismanagement.db.entidades;

public class Produto {
    int pro_cod,pro_quantidade;
    double pro_preco;
    String pro_nome,cat_cod;

    public Produto() {
    }

    public Produto(int pro_cod, int pro_quantidade, String cat_cod, double pro_preco, String pro_nome) {
        this.pro_cod = pro_cod;
        this.pro_quantidade = pro_quantidade;
        this.cat_cod = cat_cod;
        this.pro_preco = pro_preco;
        this.pro_nome = pro_nome;
    }

    public Produto(int pro_quantidade, String cat_cod, double pro_preco, String pro_nome) {
        this.pro_quantidade = pro_quantidade;
        this.cat_cod = cat_cod;
        this.pro_preco = pro_preco;
        this.pro_nome = pro_nome;
    }

    public int getPro_cod() {
        return pro_cod;
    }

    public void setPro_cod(int pro_cod) {
        this.pro_cod = pro_cod;
    }

    public int getPro_quantidade() {
        return pro_quantidade;
    }

    public void setPro_quantidade(int pro_quantidade) {
        this.pro_quantidade = pro_quantidade;
    }

    public String getCat_cod() {
        return cat_cod;
    }

    public void setCat_cod(String cat_cod) {
        this.cat_cod = cat_cod;
    }

    public double getPro_preco() {
        return pro_preco;
    }

    public void setPro_preco(double pro_preco) {
        this.pro_preco = pro_preco;
    }

    public String getPro_nome() {
        return pro_nome;
    }

    public void setPro_nome(String pro_nome) {
        this.pro_nome = pro_nome;
    }
    
    
}