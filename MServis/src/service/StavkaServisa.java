/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Ivana
 */
public class StavkaServisa {
    private int idServisa;
    private int idStavke;
    private String nazivStavke;
    private int idRacuna;
    private double cijena;

    public StavkaServisa(int idStavke, String nazivStavke) {
        this.idStavke = idStavke;
        this.nazivStavke = nazivStavke;
    }

    public StavkaServisa(int idStavke, String naziv, double cijena) {
        this.idStavke = idStavke;
        this.nazivStavke = naziv;
        this.cijena = cijena;
    }

    public StavkaServisa(int idServisa, int idStavke, String nazivStavke, double cijena) {
        this.idServisa = idServisa;
        this.idStavke = idStavke;
        this.nazivStavke = nazivStavke;
        this.cijena = cijena;
    }
    
    

    public int getIdServisa() {
        return idServisa;
    }

    public void setIdServisa(int idServisa) {
        this.idServisa = idServisa;
    }
    
    

    public int getIdStavke() {
        return idStavke;
    }

    public void setIdStavke(int idStavke) {
        this.idStavke = idStavke;
    }

    public String getNazivStavke() {
        return nazivStavke;
    }

    public void setNazivStavke(String nazivStavke) {
        this.nazivStavke = nazivStavke;
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
        final StavkaServisa other = (StavkaServisa) obj;
        if (this.idStavke != other.idStavke) {
            return false;
        }
        return true;
    }

    public int getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(int idRacuna) {
        this.idRacuna = idRacuna;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }


    

    
    
    
}
