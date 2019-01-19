package dto;

public class AdminDTO extends OsobaDTO {

	private String nazivFirme;
        private String koriscnikoIme;
        private String lozinka;

    public AdminDTO(String koriscnikoIme, String lozinka,String nazivFirme) {
        this.nazivFirme = nazivFirme;
        this.koriscnikoIme = koriscnikoIme;
        this.lozinka = lozinka;
    }

    public AdminDTO(int idOsoba,String koriscnikoIme, String lozinka,String nazivFirme) {
        super(idOsoba);
        this.nazivFirme = nazivFirme;
        this.koriscnikoIme = koriscnikoIme;
        this.lozinka = lozinka;
    }

    public AdminDTO(int idOsoba, String koriscnikoIme, String lozinka, String ime, String prezime, String brojTelefona,String nazivFirme) {
        super(idOsoba, ime, prezime, brojTelefona);
        this.nazivFirme = nazivFirme;
        this.koriscnikoIme = koriscnikoIme;
        this.lozinka = lozinka;
    }

    public AdminDTO(String korisnicko, String hash, String ime, String prezime, String telefon, String firma) {
        this.koriscnikoIme = korisnicko;
        this.lozinka = hash;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = telefon;
        this.nazivFirme = firma;
    }

    public AdminDTO(String korisnickoIme) {
        this.koriscnikoIme = korisnickoIme;
    }

    public AdminDTO(int idPrijavljenog, String ime, String prezime, String telefon, String firma) {
        this.idOsoba = idPrijavljenog;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = telefon;
        this.nazivFirme = firma;
    }

    public String getNazivFirme() {
        return nazivFirme;
    }

    public String getKoriscnikoIme() {
        return koriscnikoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public int getIdOsoba() {
        return idOsoba;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setNazivFirme(String nazivFirme) {
        this.nazivFirme = nazivFirme;
    }

    public void setKoriscnikoIme(String koriscnikoIme) {
        this.koriscnikoIme = koriscnikoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setIdOsoba(int idOsoba) {
        this.idOsoba = idOsoba;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }
        
        

    

        

}