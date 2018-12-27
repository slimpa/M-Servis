package dto;

public class ProizvodjacDTO {

	private int idProizvodjac;
	private String naziv;
	
	
	
	public ProizvodjacDTO(int idProizvodjac, String naziv) {
		super();
		this.idProizvodjac = idProizvodjac;
		this.naziv = naziv;
	}
	public int getIdProizvodjac() {
		return idProizvodjac;
	}
	public void setIdProizvodjac(int idProizvodjac) {
		this.idProizvodjac = idProizvodjac;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	
	
}