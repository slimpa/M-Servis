package dto;

public class ArtikalDTO {

	protected int idArtikal;
	protected String naziv;
	protected double cijena;
	protected int idProizvodjac;
	
	public int getIdArtikal() {
		return idArtikal;
	}
	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	
	public int getIdProizvodjac() {
		return idProizvodjac;
	}
	public void setIdProizvodjac(int idProizvodjac) {
		this.idProizvodjac = idProizvodjac;
	}

	
	
	

}