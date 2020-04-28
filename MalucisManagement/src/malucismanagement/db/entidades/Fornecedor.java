package malucismanagement.db.entidades;

public class Fornecedor {
    int for_inscestadual,for_cnpj,for_telefone;
    String for_tipo,for_nome,for_email;

    public Fornecedor(int for_inscestadual, int for_cnpj, int for_telefone, String for_tipo, String for_nome, String for_email) {
        this.for_inscestadual = for_inscestadual;
        this.for_cnpj = for_cnpj;
        this.for_telefone = for_telefone;
        this.for_tipo = for_tipo;
        this.for_nome = for_nome;
        this.for_email = for_email;
    }

    public Fornecedor() {
    }

    public int getFor_inscestadual() {
        return for_inscestadual;
    }

    public void setFor_inscestadual(int for_inscestadual) {
        this.for_inscestadual = for_inscestadual;
    }

    public int getFor_cnpj() {
        return for_cnpj;
    }

    public void setFor_cnpj(int for_cnpj) {
        this.for_cnpj = for_cnpj;
    }

    public int getFor_telefone() {
        return for_telefone;
    }

    public void setFor_telefone(int for_telefone) {
        this.for_telefone = for_telefone;
    }

    public String getFor_tipo() {
        return for_tipo;
    }

    public void setFor_tipo(String for_tipo) {
        this.for_tipo = for_tipo;
    }

    public String getFor_nome() {
        return for_nome;
    }

    public void setFor_nome(String for_nome) {
        this.for_nome = for_nome;
    }

    public String getFor_email() {
        return for_email;
    }

    public void setFor_email(String for_email) {
        this.for_email = for_email;
    }
}
