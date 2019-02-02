/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Nikola
 */
public class PeriodicniIzvjestajDTO {
    
    private int idRacun;
    private int idArtikal;
    private String naziv;
    private String datum;
    private int kolicina;
    private double cijena;

    public PeriodicniIzvjestajDTO() {
    }

    public PeriodicniIzvjestajDTO(int idRacun, int idArtikal, String naziv, String datum, int kolicina, double cijena) {
        this.idRacun = idRacun;
        this.idArtikal = idArtikal;
        this.naziv = naziv;
        this.datum = datum;
        this.kolicina = kolicina;
        this.cijena = cijena;
    }

    public int getIdRacun() {
        return idRacun;
    }

    public int getIdArtikal() {
        return idArtikal;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getDatum() {
        return datum;
    }

    public int getKolicina() {
        return kolicina;
    }

    public double getCijena() {
        return cijena;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public void setIdArtikal(int idArtikal) {
        this.idArtikal = idArtikal;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    @Override
    public String toString() {
        return "PeriodicniIzvjestajDTO{" + "idRacun=" + idRacun + ", idArtikal=" + idArtikal + ", naziv=" + naziv + ", datum=" + datum + ", kolicina=" + kolicina + ", cijena=" + cijena + '}';
    }
    
}
