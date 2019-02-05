package dto;

public class RezervniDioDTO extends ArtikalDTO {
        
        private int idRezervniDio;
	private int idModelTelefona;
	private String Opis;
        
        private String nazivRezervnogdijela;
        private int kolicinaRezervnogdijela;
        private double cijena;
        private String proizvodjac;

    public RezervniDioDTO(int idModelTelefona) {

        this.idModelTelefona = idModelTelefona;
    }

    public int getKolicinaRezervnogdijela() {
        return kolicinaRezervnogdijela;
    }
    
    public RezervniDioDTO(String naziv, int idRezervniDio){
        super(naziv, idRezervniDio);
        this.idRezervniDio = idRezervniDio;
    }

    public RezervniDioDTO(int idRezervniDio, int idModelTelefona, String Opis, String nazivRezervnogdijela, int kolicinaRezervnogdijela, double cijena, String proizvodjac) {
        this.idRezervniDio = idRezervniDio;
        this.idModelTelefona = idModelTelefona;
        this.Opis = Opis;
        this.nazivRezervnogdijela = nazivRezervnogdijela;
        this.kolicinaRezervnogdijela = kolicinaRezervnogdijela;
        this.cijena = cijena;
        this.proizvodjac = proizvodjac;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setNazivRezervnogdijela(String nazivRezervnogdijela) {
        this.nazivRezervnogdijela = nazivRezervnogdijela;
    }

    public void setKolicinaRezervnogdijela(int kolicinaRezervnogdijela) {
        this.kolicinaRezervnogdijela = kolicinaRezervnogdijela;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }
        

    
    public RezervniDioDTO(int idRezervniDio, int idModelTelefona, String Opis, String naziv, int kolicina, double cijena) {
        this.idRezervniDio = idRezervniDio;
        this.idModelTelefona = idModelTelefona;
        this.Opis = Opis;
        this.nazivRezervnogdijela = naziv;
        this.kolicinaRezervnogdijela = kolicina;
        this.cijena = cijena;
    }

    public String getNazivRezervnogdijela() {
        return nazivRezervnogdijela;
    }

    public double getCijena() {
        return cijena;
    }



    public RezervniDioDTO(int idRezervniDio, int idModelTelefona, String Opis) {
        this.idRezervniDio = idRezervniDio;
        this.idModelTelefona = idModelTelefona;
        this.Opis = Opis;
    }

    public RezervniDioDTO(int idModelTelefona, String Opis) {
        this.idModelTelefona = idModelTelefona;
        this.Opis = Opis;
    }

    public RezervniDioDTO(int idModelTelefona, String Opis, int idArtikal, String Naziv, int Kolicina, int idProizvodjac, String BarKod, boolean Obrisano) {
        super(idArtikal, Naziv, Kolicina, idProizvodjac, BarKod, Obrisano);
        this.idModelTelefona = idModelTelefona;
        this.Opis = Opis;
    }

    public void setIdModelTelefona(int idModelTelefona) {
        this.idModelTelefona = idModelTelefona;
    }

    public void setOpis(String Opis) {
        this.Opis = Opis;
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

    public void setIdRezervniDio(int idRezervniDio) {
        this.idRezervniDio = idRezervniDio;
    }

    public int getIdRezervniDio() {
        return idRezervniDio;
    }
    
    public int getIdModelTelefona() {
        return idModelTelefona;
    }

    public String getOpis() {
        return Opis;
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