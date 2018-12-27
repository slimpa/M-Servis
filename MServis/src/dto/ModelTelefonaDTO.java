package dto;

public class ModelTelefonaDTO {

	private int idModeltelefona;
	private String specifikacija;
	private String slika;
	private String nazivModela;
	
	
	
	public ModelTelefonaDTO(int idModeltelefona, String specifikacija, String slika, String nazivModela) {
		super();
		this.idModeltelefona = idModeltelefona;
		this.specifikacija = specifikacija;
		this.slika = slika;
		this.nazivModela = nazivModela;
	}
	public int getIdModeltelefona() {
		return idModeltelefona;
	}
	public void setIdModeltelefona(int idModeltelefona) {
		this.idModeltelefona = idModeltelefona;
	}
	public String getSpecifikacija() {
		return specifikacija;
	}
	public void setSpecifikacija(String specifikacija) {
		this.specifikacija = specifikacija;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	public String getNazivModela() {
		return nazivModela;
	}
	public void setNazivModela(String nazivModela) {
		this.nazivModela = nazivModela;
	}

	

}