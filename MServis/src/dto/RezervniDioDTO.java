package dto;

public class RezervniDioDTO extends ArtikalDTO {

	private int kolicina;
	private String opis;
	
	
	
	public RezervniDioDTO(int idArtikal, String naziv, double cijena, int idProizvodjac, int kolicina, String opis) {
		super(idArtikal, naziv, cijena, idProizvodjac);
		this.kolicina = kolicina;
		this.opis = opis;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
	

}