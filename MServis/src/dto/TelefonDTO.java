package dto;

public class TelefonDTO {

	private String SerijskiBroj;
	private String Boja;
	private int idModelTelefona;

        private String Naziv;
        private String Model;
        private String Proizvodjac;
        private String Specifikacija;
        private double Cijena;
        private String BarKod;

    public TelefonDTO(String SerijskiBroj, String Boja, int idModelTelefona, String Naziv, String Model, String Proizvodjac, String Specifikacija, double Cijena) {
        this.SerijskiBroj = SerijskiBroj;
        this.Boja = Boja;
        this.idModelTelefona = idModelTelefona;
        this.Naziv = Naziv;
        this.Model = Model;
        this.Proizvodjac = Proizvodjac;
        this.Specifikacija = Specifikacija;
        this.Cijena = Cijena;
    }
    
    
     public TelefonDTO(String SerijskiBroj, String Boja, int idModelTelefona, String Naziv, String Model, 
             String Proizvodjac, String Specifikacija, double Cijena, String barkod) {
       
        this.SerijskiBroj = SerijskiBroj;
        this.Boja = Boja;
        this.idModelTelefona = idModelTelefona;
        this.Naziv = Naziv;
        this.Model = Model;
        this.Proizvodjac = Proizvodjac;
        this.Specifikacija = Specifikacija;
        this.Cijena = Cijena;
        this.BarKod = barkod;
        
    }

    public TelefonDTO(String serijskiBroj, int idArtikla) {
        this.SerijskiBroj = serijskiBroj;
        this.idModelTelefona = idArtikla;
    }

    public String getNaziv() {
        return Naziv;
    }

    public String getModel() {
        return Model;
    }

    public String getProizvodjac() {
        return Proizvodjac;
    }

    public String getSpecifikacija() {
        return Specifikacija;
    }

    public double getCijena() {
        return Cijena;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public void setProizvodjac(String Proizvodjac) {
        this.Proizvodjac = Proizvodjac;
    }

    public void setSpecifikacija(String Specifikacija) {
        this.Specifikacija = Specifikacija;
    }

    public void setCijena(double Cijena) {
        this.Cijena = Cijena;
    }
    
    public TelefonDTO() {
    }

    public TelefonDTO(String SerijskiBroj, String Boja, int idModelTelefona) {
        this.SerijskiBroj = SerijskiBroj;
        this.Boja = Boja;
        this.idModelTelefona = idModelTelefona;
    }

    public String getSerijskiBroj() {
        return SerijskiBroj;
    }

    public String getBoja() {
        return Boja;
    }

    public int getIdModelTelefona() {
        return idModelTelefona;
    }

    public void setSerijskiBroj(String SerijskiBroj) {
        this.SerijskiBroj = SerijskiBroj;
    }

    public void setBoja(String Boja) {
        this.Boja = Boja;
    }

    public void setIdModelTelefona(int idModelTelefona) {
        this.idModelTelefona = idModelTelefona;
    }

    public String getBarKod() {
        return BarKod;
    }

    public void setBarKod(String BarKod) {
        this.BarKod = BarKod;
    }
    
    
    
}