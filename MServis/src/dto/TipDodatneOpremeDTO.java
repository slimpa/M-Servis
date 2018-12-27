package dto;

public class TipDodatneOpremeDTO {

	private int idTipDodatneOpreme;
	private String tip;
	
	
	
	public TipDodatneOpremeDTO(int idTipDodatneOpreme, String tip) {
		super();
		this.idTipDodatneOpreme = idTipDodatneOpreme;
		this.tip = tip;
	}
	public int getIdTipDodatneOpreme() {
		return idTipDodatneOpreme;
	}
	public void setIdTipDodatneOpreme(int idTipDodatneOpreme) {
		this.idTipDodatneOpreme = idTipDodatneOpreme;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}

	
	

}