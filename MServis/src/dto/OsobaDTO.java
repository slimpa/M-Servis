package dto;

public class OsobaDTO {

	protected int idOsoba;
	protected String ime;
	protected String prezime;
	protected String brojTelefona;

	
	
	public OsobaDTO(int idOsoba, String ime, String prezime, String brojTelefona) {
		super();
		this.idOsoba = idOsoba;
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
	}
	public int getIdOsoba() {
		return idOsoba;
	}
	public void setIdOsoba(int idOsoba) {
		this.idOsoba = idOsoba;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	
	

}