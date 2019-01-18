package dto;

public class CjenovnikUslugaDTO {

    private int idCjenovnikUsluga;
    private String naziv;
    private double cijena;

    public CjenovnikUslugaDTO(int idCjenovnikUsluga, String Naziv, double Cijena) {
        this.idCjenovnikUsluga = idCjenovnikUsluga;
        this.naziv = Naziv;
        this.cijena = Cijena;
    }

    public CjenovnikUslugaDTO(String Naziv, double Cijena) {
        this.naziv = Naziv;
        this.cijena = Cijena;
    }

    public CjenovnikUslugaDTO(String naziv) {
        this.naziv = naziv;
    }

    public int getIdCjenovnikUsluga() {
        return idCjenovnikUsluga;
    }

    public void setIdCjenovnikUsluga(int idCjenovnikUsluga) {
        this.idCjenovnikUsluga = idCjenovnikUsluga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String Naziv) {
        this.naziv = Naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double Cijena) {
        this.cijena = Cijena;
    }

}
