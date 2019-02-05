package dto;

public class ServisTelefonaHasCjenovnikUslugaDTO {

    private int idServistelefona;
    private int idCjenovnikUsluga;
    private String nazivUsluge;
    private double cijena;
    

    public ServisTelefonaHasCjenovnikUslugaDTO(int idServistelefona, int idCjenovnikUsluga, String nazivUsluge) {
        this.idServistelefona = idServistelefona;
        this.idCjenovnikUsluga = idCjenovnikUsluga;
        this.nazivUsluge = nazivUsluge;
    }

    public ServisTelefonaHasCjenovnikUslugaDTO(int idServisa) {
        this.idServistelefona = idServisa;
    }

    public ServisTelefonaHasCjenovnikUslugaDTO(int idServis, int idUsluga, String naziv, double cijena) {
        this.idCjenovnikUsluga = idUsluga;
        this.idServistelefona = idServis;
        this.nazivUsluge = naziv;
        this.cijena = cijena;
    }   

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }
    
    

    public ServisTelefonaHasCjenovnikUslugaDTO(int idServistelefona, int idCjenovnikUsluga) {
        this.idServistelefona = idServistelefona;
        this.idCjenovnikUsluga = idCjenovnikUsluga;
    }

    public int getIdServistelefona() {
        return idServistelefona;
    }

    public void setIdServistelefona(int idServistelefona) {
        this.idServistelefona = idServistelefona;
    }

    public int getIdCjenovnikUsluga() {
        return idCjenovnikUsluga;
    }

    public void setIdCjenovnikUsluga(int idCjenovnikUsluga) {
        this.idCjenovnikUsluga = idCjenovnikUsluga;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    
}
