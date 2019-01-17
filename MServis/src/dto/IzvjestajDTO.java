package dto;
import java.sql.Date;
public class IzvjestajDTO {

	private int idIzvjestaj;
	private String opis;
	private Date datumIzdavanja;
	private String putanja;
	
	public int getIdIzvjestaj() {
		return idIzvjestaj;
	}
	public void setIdIzvjestaj(int idIzvjestaj) {
		this.idIzvjestaj = idIzvjestaj;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Date getDatumIzdavanja() {
		return datumIzdavanja;
	}
	public void setDatumIzdavanja(Date datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}
	public String getPutanja() {
		return putanja;
	}
	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}

	
	
	

}