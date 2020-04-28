package malucismanagement.db.entidades;

public class CategoriaProduto {
    int cat_cod;
    String cat_nome;

    public CategoriaProduto() {
    }

    public CategoriaProduto(int cat_cod, String cat_nome) {
        this.cat_cod = cat_cod;
        this.cat_nome = cat_nome;
    }

    public CategoriaProduto(String cat_nome) {
        this.cat_nome = cat_nome;
    }

    public int getCat_cod() {
        return cat_cod;
    }

    public void setCat_cod(int cat_cod) {
        this.cat_cod = cat_cod;
    }

    public String getCat_nome() {
        return cat_nome;
    }

    public void setCat_nome(String cat_nome) {
        this.cat_nome = cat_nome;
    }
    
}
