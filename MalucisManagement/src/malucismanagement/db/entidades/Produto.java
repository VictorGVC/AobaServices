package malucismanagement.db.entidades;

public class Produto {
    int pro_cod,pro_quantidade,cat;
    double pro_preco;
    String pro_nome;
    
    public Produto() {
    }

    public Produto(int pro_cod, int pro_quantidade, double pro_preco, String pro_nome, int cat) {
        this.pro_cod = pro_cod;
        this.pro_quantidade = pro_quantidade;
        this.pro_preco = pro_preco;
        this.pro_nome = pro_nome;
        this.cat = cat;
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

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }
}
