package dto;

public class UgradjeniRezervniDioDTO {

	private int idRezervniDio;
	private int idServisa;
	
	
	
	public UgradjeniRezervniDioDTO(int idRezervniDio, int idServisa) {
		super();
		this.idRezervniDio = idRezervniDio;
		this.idServisa = idServisa;
	}
	public int getIdRezervniDio() {
		return idRezervniDio;
	}
	public void setIdRezervniDio(int idRezervniDio) {
		this.idRezervniDio = idRezervniDio;
	}
	public int getIdServisa() {
		return idServisa;
	}
	public void setIdServisa(int idServisa) {
		this.idServisa = idServisa;
	}
	
	

}