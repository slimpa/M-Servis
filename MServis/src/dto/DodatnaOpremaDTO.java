package dto;

public class DodatnaOpremaDTO extends ArtikalDTO {

	private int idModelTelefona;
	private int idTipDodatneOpreme;
	private int kolicina;
	private String boja;
	
	
	
	public DodatnaOpremaDTO(int idArtikal, String naziv, double cijena, int idProizvodjac, int idModelTelefona,
			int idTipDodatneOpreme, int kolicina, String boja) {
		super(idArtikal, naziv, cijena, idProizvodjac);
		this.idModelTelefona = idModelTelefona;
		this.idTipDodatneOpreme = idTipDodatneOpreme;
		this.kolicina = kolicina;
		this.boja = boja;
	}
	public int getIdModelTelefona() {
		return idModelTelefona;
	}
	public void setIdModelTelefona(int idModelTelefona) {
		this.idModelTelefona = idModelTelefona;
	}
	public int getIdTipDodatneOpreme() {
		return idTipDodatneOpreme;
	}
	public void setIdTipDodatneOpreme(int idTipDodatneOpreme) {
		this.idTipDodatneOpreme = idTipDodatneOpreme;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public String getBoja() {
		return boja;
	}
	public void setBoja(String boja) {
		this.boja = boja;
	}
	
	

	
}