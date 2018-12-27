package dto;

public class DobavljacDTO {

	private int idDobavljac;
	private String naziv;
	private String adresa;
	private String telefon;
	private String email;
	private String fax;
	
	
	
	public DobavljacDTO(int idDobavljac, String naziv, String adresa, String telefon, String email, String fax) {
		super();
		this.idDobavljac = idDobavljac;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
		this.fax = fax;
	}
	public int getIdDobavljac() {
		return idDobavljac;
	}
	public void setIdDobavljac(int idDobavljac) {
		this.idDobavljac = idDobavljac;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	
	

}