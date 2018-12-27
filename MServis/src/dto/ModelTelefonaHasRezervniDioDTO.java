package dto;

public class ModelTelefonaHasRezervniDioDTO {

	private int idRezervniDio;
	private int idModelTelefona;
	
	
	
	public ModelTelefonaHasRezervniDioDTO(int idRezervniDio, int idModelTelefona) {
		super();
		this.idRezervniDio = idRezervniDio;
		this.idModelTelefona = idModelTelefona;
	}
	public int getIdRezervniDio() {
		return idRezervniDio;
	}
	public void setIdRezervniDio(int idRezervniDio) {
		this.idRezervniDio = idRezervniDio;
	}
	public int getIdModelTelefona() {
		return idModelTelefona;
	}
	public void setIdModelTelefona(int idModelTelefona) {
		this.idModelTelefona = idModelTelefona;
	}
	
	

}