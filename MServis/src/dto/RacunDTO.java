package dto;

import java.sql.Timestamp;

public class RacunDTO {

	private int idRacun;
	private Timestamp vrijeme;
	private double ukupnaCijena;
	private int idZaposleni;
	
	
	
	public RacunDTO(int idRacun, Timestamp vrijeme, double ukupnaCijena, int idZaposleni) {
		super();
		this.idRacun = idRacun;
		this.vrijeme = vrijeme;
		this.ukupnaCijena = ukupnaCijena;
		this.idZaposleni = idZaposleni;
	}
	public int getIdRacun() {
		return idRacun;
	}
	public void setIdRacun(int idRacun) {
		this.idRacun = idRacun;
	}

	public Timestamp getVrijeme() {
		return vrijeme;
	}
	public void setVrijeme(Timestamp vrijeme) {
		this.vrijeme = vrijeme;
	}
	public double getUkupnaCijena() {
		return ukupnaCijena;
	}
	public void setUkupnaCijena(double ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}
	public int getIdZaposleni() {
		return idZaposleni;
	}
	public void setIdZaposleni(int idZaposleni) {
		this.idZaposleni = idZaposleni;
	}
	
	

}