package dto;

public class ArtikalHasDobavljacDTO {

	private int idDobavljac;
	private int idArtikal;
	
	
	
	public ArtikalHasDobavljacDTO(int idDobavljac, int idArtikal) {
		super();
		this.idDobavljac = idDobavljac;
		this.idArtikal = idArtikal;
	}
	public int getIdDobavljac() {
		return idDobavljac;
	}
	public void setIdDobavljac(int idDobavljac) {
		this.idDobavljac = idDobavljac;
	}
	public int getIdArtikal() {
		return idArtikal;
	}
	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}
	
	

}