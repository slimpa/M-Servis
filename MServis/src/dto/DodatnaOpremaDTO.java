package dto;

public class DodatnaOpremaDTO extends ArtikalDTO {

	private String Boja;
	private int idModelTelefona;
	private int idTipDodatneOpreme;
        
        private int idDodatnaOprema;
        private String Naziv;
        private String ModelTelefona;
        private String TipOpreme;
        private String Proizvodjac;
        private String BarKod;


    public String getBarKod() {
        return BarKod;
    }

    public void setBarKod(String BarKod) {
        this.BarKod = BarKod;
    }
    
    

    public DodatnaOpremaDTO(String Boja, int idTipDodatneOpreme, String Naziv, String ModelTelefona, String Proizvodjac, String BarCode, int Kolicina, double Cijena) {
        this.Boja = Boja;
        this.idTipDodatneOpreme = idTipDodatneOpreme;
        this.Naziv = Naziv;
        this.ModelTelefona = ModelTelefona;
        this.Proizvodjac = Proizvodjac;
        this.BarKod = BarCode;
        this.Kolicina = Kolicina;
        this.Cijena = Cijena;
    }


    public DodatnaOpremaDTO(String boja, int idDodatnaOprema, String naziv, String nazivModela,
            String TipOpreme, int kolicina, double cijena, String barkod){
        
        this.Boja = boja;
        this.idDodatnaOprema = idDodatnaOprema;
        this.Naziv = naziv;
        this.ModelTelefona = nazivModela;
        this.TipOpreme = TipOpreme;
        this.Kolicina = kolicina;
        this.Cijena = cijena;
        this.BarKod = barkod;
    }
    public DodatnaOpremaDTO(String Boja, String Naziv, String ModelTelefona, String TipOpreme, String Proizvodjac, String BarCode, int Kolicina, double Cijena) {
        this.Boja = Boja;
        this.Naziv = Naziv;
        this.ModelTelefona = ModelTelefona;
        this.TipOpreme = TipOpreme;
        this.Proizvodjac = Proizvodjac;
        this.BarKod = BarCode;
        this.Kolicina = Kolicina;
        this.Cijena = Cijena;
    }
    

    public DodatnaOpremaDTO(String Boja, String Naziv, String ModelTelefona, String TipOpreme, String Proizvodjac) {
        this.Boja = Boja;
        this.Naziv = Naziv;
        this.ModelTelefona = ModelTelefona;
        this.TipOpreme = TipOpreme;
        this.Proizvodjac = Proizvodjac;
    }

    public void setTipOpreme(String TipOpreme) {
        this.TipOpreme = TipOpreme;
    }

    public String getTipOpreme() {
        return TipOpreme;
    }
        private int Kolicina;
        private double Cijena;

    public DodatnaOpremaDTO(String Boja, int idDodatnaOprema, String Naziv, String ModelTelefona, String TipOpreme, int Kolicina, double Cijena) {
        this.Boja = Boja;
        this.idDodatnaOprema = idDodatnaOprema;
        this.Naziv = Naziv;
        this.ModelTelefona = ModelTelefona;
        this.TipOpreme = TipOpreme;
        this.Kolicina = Kolicina;
        this.Cijena = Cijena;
    }

    public DodatnaOpremaDTO(String Boja, int idModelTelefona, int idTipDodatneOpreme, int idDodatnaOprema, String Naziv, String ModelTelefona, int Kolicina, double Cijena) {
        this.Boja = Boja;
        this.idModelTelefona = idModelTelefona;
        this.idTipDodatneOpreme = idTipDodatneOpreme;
        this.idDodatnaOprema = idDodatnaOprema;
        this.Naziv = Naziv;
        this.ModelTelefona = ModelTelefona;
        this.Kolicina = Kolicina;
        this.Cijena = Cijena;
    }

    public DodatnaOpremaDTO() {
    }

    public String getBoja() {
        return Boja;
    }

    public int getIdModelTelefona() {
        return idModelTelefona;
    }

    public int getIdTipDodatneOpreme() {
        return idTipDodatneOpreme;
    }

    public int getIdDodatnaOprema() {
        return idDodatnaOprema;
    }

    public String getNaziv() {
        return Naziv;
    }

    public String getModelTelefona() {
        return ModelTelefona;
    }

    public int getKolicina() {
        return Kolicina;
    }

    public double getCijena() {
        return Cijena;
    }

    public void setBoja(String Boja) {
        this.Boja = Boja;
    }

    public void setIdModelTelefona(int idModelTelefona) {
        this.idModelTelefona = idModelTelefona;
    }

    public void setIdTipDodatneOpreme(int idTipDodatneOpreme) {
        this.idTipDodatneOpreme = idTipDodatneOpreme;
    }

    public void setIdDodatnaOprema(int idDodatnaOprema) {
        this.idDodatnaOprema = idDodatnaOprema;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public void setModelTelefona(String ModelTelefona) {
        this.ModelTelefona = ModelTelefona;
    }

    public void setKolicina(int Kolicina) {
        this.Kolicina = Kolicina;
    }

    public void setCijena(double Cijena) {
        this.Cijena = Cijena;
    }
        
        

}