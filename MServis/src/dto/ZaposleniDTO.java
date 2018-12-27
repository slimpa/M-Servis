package dto;

public class ZaposleniDTO extends OsobaDTO {

	private String radnoMjesto;
	
	

	public ZaposleniDTO(int idOsoba, String ime, String prezime, String brojTelefona, String radnoMjesto) {
		super(idOsoba, ime, prezime, brojTelefona);
		this.radnoMjesto = radnoMjesto;
	}

	public String getRadnoMjesto() {
		return radnoMjesto;
	}

	public void setRadnoMjesto(String radnoMjesto) {
		this.radnoMjesto = radnoMjesto;
	}
	
	

}