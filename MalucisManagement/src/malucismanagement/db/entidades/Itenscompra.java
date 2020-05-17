package malucismanagement.db.entidades;

public class Itenscompra {
    int com_cod,it_quantidade,mar_cod,pro_cod;
    Double it_preco;

    public int getCom_cod() {
        return com_cod;
    }

    public void setCom_cod(int com_cod) {
        this.com_cod = com_cod;
    }

    public int getIt_quantidade() {
        return it_quantidade;
    }

    public void setIt_quantidade(int it_quantidade) {
        this.it_quantidade = it_quantidade;
    }

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

    public Double getIt_preco() {
        return it_preco;
    }

    public void setIt_preco(Double it_preco) {
        this.it_preco = it_preco;
    }

    public Itenscompra(int com_cod, int it_quantidade, int mar_cod, int pro_cod, Double it_preco) {
        this.com_cod = com_cod;
        this.it_quantidade = it_quantidade;
        this.mar_cod = mar_cod;
        this.pro_cod = pro_cod;
        this.it_preco = it_preco;
    }

    public Itenscompra() {
    }
}
