package dto;

import java.sql.Timestamp;

public class NarudzbaDTO {

	private int idNarudzba;
	private Timestamp datumNarudzbe;
	private int idDobavljac;
	
	public int getIdNarudzba() {
		return idNarudzba;
	}
	public void setIdNarudzba(int idNarudzba) {
		this.idNarudzba = idNarudzba;
	}
	public Timestamp getDatumNarudzbe() {
		return datumNarudzbe;
	}
	public void setDatumNarudzbe(Timestamp datumNarudzbe) {
		this.datumNarudzbe = datumNarudzbe;
	}
	public int getIdDobavljac() {
		return idDobavljac;
	}
	public void setIdDobavljac(int idDobavljac) {
		this.idDobavljac = idDobavljac;
	}
	
	

}