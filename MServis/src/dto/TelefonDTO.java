package dto;

import java.sql.Date;

public class TelefonDTO extends ArtikalDTO {

	private String serijskiBroj;
	private Date datumProizvodnje;
	private String boja;
	private int idModelTelefona;

	
	
	public TelefonDTO(int idArtikal, String naziv, double cijena, int idProizvodjac, String serijskiBroj,
			Date datumProizvodnje, String boja, int idModelTelefona) {
		super(idArtikal, naziv, cijena, idProizvodjac);
		this.serijskiBroj = serijskiBroj;
		this.datumProizvodnje = datumProizvodnje;
		this.boja = boja;
		this.idModelTelefona = idModelTelefona;
	}
	public String getSerijskiBroj() {
		return serijskiBroj;
	}
	public void setSerijskiBroj(String serijskiBroj) {
		this.serijskiBroj = serijskiBroj;
	}
	public Date getDatumProizvodnje() {
		return datumProizvodnje;
	}
	public void setDatumProizvodnje(Date datumProizvodnje) {
		this.datumProizvodnje = datumProizvodnje;
	}
	public String getBoja() {
		return boja;
	}
	public void setBoja(String boja) {
		this.boja = boja;
	}
	public int getIdModelTelefona() {
		return idModelTelefona;
	}
	public void setIdModelTelefona(int idModelTelefona) {
		this.idModelTelefona = idModelTelefona;
	}
	
	

}