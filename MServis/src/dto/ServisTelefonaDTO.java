package dto;

import java.sql.Date;

public class ServisTelefonaDTO {

	private int idServisTelefona;
	private int idKlijent;
	private int idZaposleni;
	private int idStanjeTelefona;
	private String opisKvara;
	private Date datumPrijema;
	private Date datumPreuzimanja;
	private int idCjenovnikUsluga;
	private int idModelTelefona;
	private String serijskiBrojTelefona;
	
	
	
	public ServisTelefonaDTO(int idServisTelefona, int idKlijent, int idZaposleni, int idStanjeTelefona,
			String opisKvara, Date datumPrijema, Date datumPreuzimanja, int idCjenovnikUsluga, int idModelTelefona,
			String serijskiBrojTelefona) {
		super();
		this.idServisTelefona = idServisTelefona;
		this.idKlijent = idKlijent;
		this.idZaposleni = idZaposleni;
		this.idStanjeTelefona = idStanjeTelefona;
		this.opisKvara = opisKvara;
		this.datumPrijema = datumPrijema;
		this.datumPreuzimanja = datumPreuzimanja;
		this.idCjenovnikUsluga = idCjenovnikUsluga;
		this.idModelTelefona = idModelTelefona;
		this.serijskiBrojTelefona = serijskiBrojTelefona;
	}
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
		return opisKvara;
	}
	public void setOpisKvara(String opisKvara) {
		this.opisKvara = opisKvara;
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
	public int getidModelTelefona() {
		return idModelTelefona;
	}
	public void setidModelTelefona(int idModelTelefona) {
		this.idModelTelefona = idModelTelefona;
	}
	public String getSerijskiBrojTelefona() {
		return serijskiBrojTelefona;
	}
	public void setSerijskiBrojTelefona(String serijskiBrojTelefona) {
		this.serijskiBrojTelefona = serijskiBrojTelefona;
	}
	
	

}