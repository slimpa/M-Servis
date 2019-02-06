/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Objects;

/**
 *
 * @author Nikola
 */
public class StavkaRacuna {
    
   private int idArtikla;
   private String naziv;
   private Double cijena;
   private int kolicina;
   private boolean telefon=false;
   private String serijskiBroj;
     private int idRacuna;

    public StavkaRacuna(int idArtikla, String naziv, Double cijena, int kolicina, boolean telefon, String serijskiBroj) {
        this.idArtikla = idArtikla;
        this.naziv = naziv;
        this.cijena = cijena;
        this.kolicina = kolicina;
        this.telefon = telefon;
        this.serijskiBroj = serijskiBroj;
    }

    public int getIdArtikla() {
        return idArtikla;
    }

    public String getNaziv() {
        return naziv;
    }

    public Double getCijena() {
        return cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public boolean isTelefon() {
        return telefon;
    }

    public String getSerijskiBroj() {
        return serijskiBroj;
    }

    public void setIdArtikla(int idArtikla) {
        this.idArtikla = idArtikla;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setTelefon(boolean telefon) {
        this.telefon = telefon;
    }

    public void setSerijskiBroj(String serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }
    
        public int getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(int idRacuna) {
        this.idRacuna = idRacuna;
    }

    @Override
    public String toString() {
        return "StavkaRacuna{" + "idArtikla=" + idArtikla + ", naziv=" + naziv + ", cijena=" + cijena + ", kolicina=" + kolicina + ", telefon=" + telefon + ", serijskiBroj=" + serijskiBroj + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaRacuna other = (StavkaRacuna) obj;
        if (this.idArtikla != other.idArtikla) {
            return false;
        }
        if (!Objects.equals(this.serijskiBroj, other.serijskiBroj)) {
            return false;
        }
        return true;
    }
   
    
}
