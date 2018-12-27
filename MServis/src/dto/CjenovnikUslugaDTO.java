package dto;

public class CjenovnikUslugaDTO {

	private int idCjenovnikUsluga;
	private String naziv;
	private double cijena;
	
	
	
	public CjenovnikUslugaDTO(int idCjenovnikUsluga, String naziv, double cijena) {
		super();
		this.idCjenovnikUsluga = idCjenovnikUsluga;
		this.naziv = naziv;
		this.cijena = cijena;
	}
	public int getIdCjenovnikUsluga() {
		return idCjenovnikUsluga;
	}
	public void setIdCjenovnikUsluga(int idCjenovnikUsluga) {
		this.idCjenovnikUsluga = idCjenovnikUsluga;
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

	
	

}