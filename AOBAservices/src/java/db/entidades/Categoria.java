package db.entidades;

public class Categoria {
    String cat_descricao;
    int cat_cod;

    public String getCat_descricao() {
        return cat_descricao;
    }

    public void setCat_descricao(String cat_descricao) {
        this.cat_descricao = cat_descricao;
    }

    public int getCat_cod() {
        return cat_cod;
    }

    public void setCat_cod(int cat_cod) {
        this.cat_cod = cat_cod;
    }

    public Categoria(String cat_descricao, int cat_cod) {
        this.cat_descricao = cat_descricao;
        this.cat_cod = cat_cod;
    }

    public Categoria() {
    }
}
