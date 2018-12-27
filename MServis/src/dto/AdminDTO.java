package dto;

public class AdminDTO extends OsobaDTO {

	private String nazivFirme;

	public AdminDTO(int idOsoba, String ime, String prezime, String brojTelefona, String nazivFirme) {
		super(idOsoba, ime, prezime, brojTelefona);
		this.nazivFirme = nazivFirme;
	}

	public String getNazivFirme() {
		return nazivFirme;
	}

	public void setNazivFirme(String nazivFirme) {
		this.nazivFirme = nazivFirme;
	}

	
	

}