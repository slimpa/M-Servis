package dto;

import java.sql.Date;

public class ServisTelefonaDTO {

	private int idServisTelefona;
	private int idKlijent;
	private int idZaposleni;
	private int idStanjeTelefona;
	private String OpisKvara;
	private Date datumPrijema;
	private Date datumPreuzimanja;
	private int idCjenovnikUsluga;
	private int model;
	private String serijskiBrojTelefona;
	public int getIdServisTelefona() {
		return idServisTelefona;
	}
	public void setIdServisTelefona(int idServisTelefona) {
		this.idServisTelefona = idServisTelefona;
	}
	public int getIdKlijent() {
		return idKlijent;
	}
	public void setIdKlijent(int idKlijent) {
		this.idKlijent = idKlijent;
	}
	public int getIdZaposleni() {
		return idZaposleni;
	}
	public void setIdZaposleni(int idZaposleni) {
		this.idZaposleni = idZaposleni;
	}
	public int getIdStanjeTelefona() {
		return idStanjeTelefona;
	}
	public void setIdStanjeTelefona(int idStanjeTelefona) {
		this.idStanjeTelefona = idStanjeTelefona;
	}
	public String getOpisKvara() {
		return OpisKvara;
	}
	public void setOpisKvara(String opisKvara) {
		OpisKvara = opisKvara;
	}
	public Date getDatumPrijema() {
		return datumPrijema;
	}
	public void setDatumPrijema(Date datumPrijema) {
		this.datumPrijema = datumPrijema;
	}
	public Date getDatumPreuzimanja() {
		return datumPreuzimanja;
	}
	public void setDatumPreuzimanja(Date datumPreuzimanja) {
		this.datumPreuzimanja = datumPreuzimanja;
	}
	public int getIdCjenovnikUsluga() {
		return idCjenovnikUsluga;
	}
	public void setIdCjenovnikUsluga(int idCjenovnikUsluga) {
		this.idCjenovnikUsluga = idCjenovnikUsluga;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public String getSerijskiBrojTelefona() {
		return serijskiBrojTelefona;
	}
	public void setSerijskiBrojTelefona(String serijskiBrojTelefona) {
		this.serijskiBrojTelefona = serijskiBrojTelefona;
	}
	
	

}