package dto;

import java.util.Objects;

public class ZaposleniDTO extends OsobaDTO {

        private String koriscnikoIme;
        private String lozinka;
	private String radnoMjesto;

    public ZaposleniDTO(String koriscnikoIme, String lozinka, String radnoMjesto) {
        this.koriscnikoIme = koriscnikoIme;
        this.lozinka = lozinka;
        this.radnoMjesto = radnoMjesto;
    }

    public ZaposleniDTO(int idOsoba,String koriscnikoIme, String lozinka, String radnoMjesto) {
        super(idOsoba);
        this.koriscnikoIme = koriscnikoIme;
        this.lozinka = lozinka;
        this.radnoMjesto = radnoMjesto;
    }

    public ZaposleniDTO(int idOsoba, String koriscnikoIme, String lozinka, String radnoMjesto, String ime, String prezime, String brojTelefona) {
        super(idOsoba, ime, prezime, brojTelefona);
        this.koriscnikoIme = koriscnikoIme;
        this.lozinka = lozinka;
        this.radnoMjesto = radnoMjesto;
    }

    public ZaposleniDTO(String zaposleni) {
        this.koriscnikoIme = zaposleni;
    }
    
    public ZaposleniDTO(int idZaposleni){
        super(idZaposleni);
        
    }

    public ZaposleniDTO(int idZaposleni, String korisnickoIme, String radnoMjesto, String ime, String prezime, String brojTelefona) {
        super(idZaposleni, ime, prezime, brojTelefona);
        this.koriscnikoIme = korisnickoIme;
        this.prezime = prezime;
    }

    public String getKoriscnikoIme() {
        return koriscnikoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getRadnoMjesto() {
        return radnoMjesto;
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

    public void setKoriscnikoIme(String koriscnikoIme) {
        this.koriscnikoIme = koriscnikoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setRadnoMjesto(String radnoMjesto) {
        this.radnoMjesto = radnoMjesto;
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