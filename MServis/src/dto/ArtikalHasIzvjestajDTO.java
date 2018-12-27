package dto;

public class ArtikalHasIzvjestajDTO {

	private int idIzvjestaj;
	private int idArtikal;
	
	
	
	public ArtikalHasIzvjestajDTO(int idIzvjestaj, int idArtikal) {
		super();
		this.idIzvjestaj = idIzvjestaj;
		this.idArtikal = idArtikal;
	}
	public int getIdIzvjestaj() {
		return idIzvjestaj;
	}
	public void setIdIzvjestaj(int idIzvjestaj) {
		this.idIzvjestaj = idIzvjestaj;
	}
	public int getIdArtikal() {
		return idArtikal;
	}
	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}

	
}