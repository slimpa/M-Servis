/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

public class RacunHasIzvjestajDTO {
    private int idRacun;
    private int idIzvjestaj;

    public RacunHasIzvjestajDTO(int idRacun, int idIzvjestaj) {
        this.idRacun = idRacun;
        this.idIzvjestaj = idIzvjestaj;
    }

    public int getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public int getIdIzvjestaj() {
        return idIzvjestaj;
    }

    public void setIdIzvjestaj(int idIzvjestaj) {
        this.idIzvjestaj = idIzvjestaj;
    }
    
    
}
