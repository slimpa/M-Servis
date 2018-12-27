package dto;

public class ServisTelefonaHasIzvjestajDTO {

	private int idServisa;
	private int idIzvjestaj;
	
	
	
	public ServisTelefonaHasIzvjestajDTO(int idServisa, int idIzvjestaj) {
		super();
		this.idServisa = idServisa;
		this.idIzvjestaj = idIzvjestaj;
	}
	public int getIdServisa() {
		return idServisa;
	}
	public void setIdServisa(int idServisa) {
		this.idServisa = idServisa;
	}
	public int getIdIzvjestaj() {
		return idIzvjestaj;
	}
	public void setIdIzvjestaj(int idIzvjestaj) {
		this.idIzvjestaj = idIzvjestaj;
	}
	
	

}