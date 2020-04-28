package malucismanagement.db.entidades;

public class Parametrizacao {
    
    private String corprimaria, corsecundaria, fonte, corfonte, telefone, rua, cep, uf, cidade;
    private byte[] logo;
    
    public Parametrizacao() {}

    public Parametrizacao(String corprimaria, String corsecundaria, String fonte, String corfonte, String telefone, String rua, String cep, String uf, String cidade) {
        this.corprimaria = corprimaria;
        this.corsecundaria = corsecundaria;
        this.fonte = fonte;
        this.corfonte = corfonte;
        this.telefone = telefone;
        this.rua = rua;
        this.cep = cep;
        this.uf = uf;
        this.cidade = cidade;
    }

    public String getCorprimaria() {
        return corprimaria;
    }

    public void setCorprimaria(String corprimaria) {
        this.corprimaria = corprimaria;
    }

    public String getCorsecundaria() {
        return corsecundaria;
    }

    public void setCorsecundaria(String corsecundaria) {
        this.corsecundaria = corsecundaria;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getCorfonte() {
        return corfonte;
    }

    public void setCorfonte(String corfonte) {
        this.corfonte = corfonte;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    
    
}
