package malucismanagement.db.entidades;

public class Marcas {
    int mar_cod;
    String mar_nome;

    public Marcas(int mar_cod, String mar_nome) {
        this.mar_cod = mar_cod;
        this.mar_nome = mar_nome;
    }

    public Marcas() {
    }

    public int getMar_cod() {
        return mar_cod;
    }

    public void setMar_cod(int mar_cod) {
        this.mar_cod = mar_cod;
    }

    public String getMar_nome() {
        return mar_nome;
    }

    public void setMar_nome(String mar_nome) {
        this.mar_nome = mar_nome;
    }
}
