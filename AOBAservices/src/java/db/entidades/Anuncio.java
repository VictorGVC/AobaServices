package db.entidades;

public class Anuncio {
    int anu_cod,fot_cod,cat_cod;
    String anu_titulo,anu_telefone,anu_desc;
    Double anu_preco;

    public Anuncio() {
    }

    public Anuncio(int anu_cod, int fot_cod, int cat_cod, String anu_titulo, String anu_telefone, String anu_desc, Double anu_preco) {
        this.anu_cod = anu_cod;
        this.fot_cod = fot_cod;
        this.cat_cod = cat_cod;
        this.anu_titulo = anu_titulo;
        this.anu_telefone = anu_telefone;
        this.anu_desc = anu_desc;
        this.anu_preco = anu_preco;
    }

    public int getAnu_cod() {
        return anu_cod;
    }

    public void setAnu_cod(int anu_cod) {
        this.anu_cod = anu_cod;
    }

    public int getFot_cod() {
        return fot_cod;
    }

    public void setFot_cod(int fot_cod) {
        this.fot_cod = fot_cod;
    }

    public int getCat_cod() {
        return cat_cod;
    }

    public void setCat_cod(int cat_cod) {
        this.cat_cod = cat_cod;
    }

    public String getAnu_titulo() {
        return anu_titulo;
    }

    public void setAnu_titulo(String anu_titulo) {
        this.anu_titulo = anu_titulo;
    }

    public String getAnu_telefone() {
        return anu_telefone;
    }

    public void setAnu_telefone(String anu_telefone) {
        this.anu_telefone = anu_telefone;
    }

    public String getAnu_desc() {
        return anu_desc;
    }

    public void setAnu_desc(String anu_desc) {
        this.anu_desc = anu_desc;
    }

    public Double getAnu_preco() {
        return anu_preco;
    }

    public void setAnu_preco(Double anu_preco) {
        this.anu_preco = anu_preco;
    }

   
}
