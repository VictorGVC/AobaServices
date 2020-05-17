package malucismanagement.db.entidades;

import java.sql.Date;

public class Compras {
    
    int com_cod;
    String for_cnpj;
    Double com_total;
    Date com_dtcompra;

    public Compras() {
    }

    public Compras(int com_cod, String for_cnpj, Double com_total, Date com_dtcompra) {
        this.com_cod = com_cod;
        this.for_cnpj = for_cnpj;
        this.com_total = com_total;
        this.com_dtcompra = com_dtcompra;
    }

    public int getCom_cod() {
        return com_cod;
    }

    public void setCom_cod(int com_cod) {
        this.com_cod = com_cod;
    }

    public String getFor_cnpj() {
        return for_cnpj;
    }

    public void setFor_cnpj(String for_cnpj) {
        this.for_cnpj = for_cnpj;
    }

    public Double getCom_total() {
        return com_total;
    }

    public void setCom_total(Double com_total) {
        this.com_total = com_total;
    }

    public Date getCom_dtcompra() {
        return com_dtcompra;
    }

    public void setCom_dtcompra(Date com_dtcompra) {
        this.com_dtcompra = com_dtcompra;
    }

    
}
