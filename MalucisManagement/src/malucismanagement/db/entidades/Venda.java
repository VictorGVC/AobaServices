package malucismanagement.db.entidades;

import java.time.LocalDate;

public class Venda {

    private int cod;
    private double valortotal;
    private LocalDate dtvenda;
    private String cli_id;

    public Venda() {}

    public Venda(int cod, double valortotal, LocalDate dtvenda, String cli_id) {
        this.cod = cod;
        this.valortotal = valortotal;
        this.dtvenda = dtvenda;
        this.cli_id = cli_id;
    }
    
    public Venda(double valortotal, LocalDate dtvenda, String cli_id) {
        this.valortotal = valortotal;
        this.dtvenda = dtvenda;
        this.cli_id = cli_id;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public LocalDate getDtvenda() {
        return dtvenda;
    }

    public void setDtvenda(LocalDate dtvenda) {
        this.dtvenda = dtvenda;
    }

    public String getCli_id() {
        return cli_id;
    }

    public void setCli_id(String cli_id) {
        this.cli_id = cli_id;
    }   
}