package dto;

public class ArtikalDTO {

	protected int idArtikal;
	protected String Naziv;
	protected int Kolicina;
	protected int idProizvodjac;
	protected String BarKod;
	protected boolean Obrisano;

    public ArtikalDTO() {
    }

    public ArtikalDTO(String Naziv, int Kolicina, int idProizvodjac, String BarKod) {
        this.Naziv = Naziv;
        this.Kolicina = Kolicina;
        this.idProizvodjac = idProizvodjac;
        this.BarKod = BarKod;
    }
    
    public ArtikalDTO(String naziv, int idArtikal){
        this.Naziv = naziv;
        this.idArtikal = idArtikal;
    }
    
    
    public ArtikalDTO(int idArtikal, String Naziv, int Kolicina, int idProizvodjac, String BarKod, boolean Obrisano) {
        this.idArtikal = idArtikal;
        this.Naziv = Naziv;
        this.Kolicina = Kolicina;
        this.idProizvodjac = idProizvodjac;
        this.BarKod = BarKod;
        this.Obrisano = Obrisano;
    }

    public void setIdArtikal(int idArtikal) {
        this.idArtikal = idArtikal;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public void setKolicina(int Kolicina) {
        this.Kolicina = Kolicina;
    }

    public void setIdProizvodjac(int idProizvodjac) {
        this.idProizvodjac = idProizvodjac;
    }

    public void setBarKod(String BarKod) {
        this.BarKod = BarKod;
    }

    public void setObrisano(boolean Obrisano) {
        this.Obrisano = Obrisano;
    }

    public int getIdArtikal() {
        return idArtikal;
    }

    public String getNaziv() {
        return Naziv;
    }

    public int getKolicina() {
        return Kolicina;
    }

    public int getIdProizvodjac() {
        return idProizvodjac;
    }

    public String getBarKod() {
        return BarKod;
    }

    public boolean isObrisano() {
        return Obrisano;
    }

}