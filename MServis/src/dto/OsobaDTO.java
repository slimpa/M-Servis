package dto;

public class OsobaDTO {

	public int idOsoba;
	public String Ime;
	protected String Prezime;
	protected String BrojTelefona;
	protected String KorisnickoIme;
	protected String Lozinka;
	protected boolean Obrisano;

    public OsobaDTO() {
    }
        
        

    public OsobaDTO(int idOsoba, String Ime) {
        this.idOsoba = idOsoba;
        this.Ime = Ime;
    }
        
        

}