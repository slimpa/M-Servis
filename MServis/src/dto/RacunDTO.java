package dto;

import java.sql.Timestamp;

public class RacunDTO {

	private int idRacun;
	private Timestamp Vrijeme;
	private double UkupnaCijena;
	private int idZaposleni;

    public RacunDTO(int idRacun, Timestamp Vrijeme, double UkupnaCijena, int idZaposleni) {
        this.idRacun = idRacun;
        this.Vrijeme = Vrijeme;
        this.UkupnaCijena = UkupnaCijena;
        this.idZaposleni = idZaposleni;
    }

    public int getIdRacun() {
        return idRacun;
    }

    public Timestamp getVrijeme() {
        return Vrijeme;
    }

    public double getUkupnaCijena() {
        return UkupnaCijena;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

        
}