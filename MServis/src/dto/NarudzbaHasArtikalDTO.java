package dto;

public class NarudzbaHasArtikalDTO {

	private int idNarudzba;
	private int idArtikal;
	private int kolicina;
	
	
	
	public NarudzbaHasArtikalDTO(int idNarudzba, int idArtikal, int kolicina) {
		super();
		this.idNarudzba = idNarudzba;
		this.idArtikal = idArtikal;
		this.kolicina = kolicina;
	}
	public int getIdNarudzba() {
		return idNarudzba;
	}
	public void setIdNarudzba(int idNarudzba) {
		this.idNarudzba = idNarudzba;
	}
	public int getIdArtikal() {
		return idArtikal;
	}
	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	
}