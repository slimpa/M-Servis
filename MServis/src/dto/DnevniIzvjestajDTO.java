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
public class DnevniIzvjestajDTO {
      
        private int idRacun;
        private int idArtikal;
        private String naziv;
        private int kolicina;
        private double cijena;
        private String datum;

    public DnevniIzvjestajDTO(int idRacun, int idArtikal, String naziv, int kolicina, double cijena, String datum) {
        this.idRacun = idRacun;
        this.idArtikal = idArtikal;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.cijena = cijena;
        this.datum = datum;
    }

    public DnevniIzvjestajDTO() {
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

    public int getKolicina() {
        return kolicina;
    }

    public double getCijena() {
        return cijena;
    }

    public String getDatum() {
        return datum;
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

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "DnevniIzvjestajDTO{" + "idRacun=" + idRacun + ", idArtikal=" + idArtikal + ", naziv=" + naziv + ", kolicina=" + kolicina + ", cijena=" + cijena + ", datum=" + datum + '}';
    }
    
    
}