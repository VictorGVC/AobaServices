package entidades;

public class Foto {
    int fot_cod;
    String fot_fotoprincipal,fot_fotoopcional1,fot_fotoopcional2;

    public int getFot_cod() {
        return fot_cod;
    }

    public void setFot_cod(int fot_cod) {
        this.fot_cod = fot_cod;
    }

    public String getFot_fotoprincipal() {
        return fot_fotoprincipal;
    }

    public void setFot_fotoprincipal(String fot_fotoprincipal) {
        this.fot_fotoprincipal = fot_fotoprincipal;
    }

    public String getFot_fotoopcional1() {
        return fot_fotoopcional1;
    }

    public void setFot_fotoopcional1(String fot_fotoopcional1) {
        this.fot_fotoopcional1 = fot_fotoopcional1;
    }

    public String getFot_fotoopcional2() {
        return fot_fotoopcional2;
    }

    public void setFot_fotoopcional2(String fot_fotoopcional2) {
        this.fot_fotoopcional2 = fot_fotoopcional2;
    }

    public Foto(int fot_cod, String fot_fotoprincipal, String fot_fotoopcional1, String fot_fotoopcional2) {
        this.fot_cod = fot_cod;
        this.fot_fotoprincipal = fot_fotoprincipal;
        this.fot_fotoopcional1 = fot_fotoopcional1;
        this.fot_fotoopcional2 = fot_fotoopcional2;
    }

    public Foto() {
    }
}
