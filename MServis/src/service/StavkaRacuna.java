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
public class StavkaRacuna {
    private int idStavke;
    private String nazivStavke;

    public StavkaRacuna(int idStavke, String nazivStavke) {
        this.idStavke = idStavke;
        this.nazivStavke = nazivStavke;
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
        final StavkaRacuna other = (StavkaRacuna) obj;
        if (this.idStavke != other.idStavke) {
            return false;
        }
        return true;
    }




    
    
    
}
