package dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ServisTelefonaDTO {

	private int idServisTelefona;
	private int idKlijent;
	private int idZaposleni;
	private int idStanjeTelefona;
	private String opisKvara;
	private Date datumPrijema;
	private int idModelTelefona;
	private String serijskiBrojTelefona;
	private boolean telefonPreuzet;
        
        private String imePrezimeKlijent;
        private String imePrezimeZaposleni;
        private String nazivModela;
        private String stanjeTelefona;

    public ServisTelefonaDTO(int idServisTelefona, int idKlijent, int idZaposleni, int idStanjeTelefona, String opisKvara, Date datumPrijema, int idModelTelefona, String serijskiBrojTelefona, boolean telefonPreuzet) {
        this.idServisTelefona = idServisTelefona;
        this.idKlijent = idKlijent;
        this.idZaposleni = idZaposleni;
        this.idStanjeTelefona = idStanjeTelefona;
        this.opisKvara = opisKvara;
        this.datumPrijema = datumPrijema;
        this.idModelTelefona = idModelTelefona;
        this.serijskiBrojTelefona = serijskiBrojTelefona;
        this.telefonPreuzet = telefonPreuzet;
    }

    public ServisTelefonaDTO(int idZaposlenog, int idStanje, String opis, LocalDateTime datum, int idModel, String serijski) {
        this.idZaposleni = idZaposlenog;
        this.idStanjeTelefona = idStanje;
        this.opisKvara = opis;
        long millis = datum.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
        this.datumPrijema = new Date(millis);
        this.idModelTelefona = idModel;
        this.serijskiBrojTelefona = serijski;
        
    }

    public ServisTelefonaDTO(int idServisa, int stanje) {
        this.idServisTelefona = idServisa;
        this.idStanjeTelefona = stanje;
    }

    public ServisTelefonaDTO(int idServisa) {
        this.idServisTelefona = idServisa;
    }
    
    public ServisTelefonaDTO(StanjeTelefonaDTO stanje){
        this.idStanjeTelefona = stanje.getIdStanjeTelefona();
    }

    public int getIdServisTelefona() {
        return idServisTelefona;
    }

    public void setIdServisTelefona(int idServisTelefona) {
        this.idServisTelefona = idServisTelefona;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public int getIdStanjeTelefona() {
        return idStanjeTelefona;
    }

    public void setIdStanjeTelefona(int idStanjeTelefona) {
        this.idStanjeTelefona = idStanjeTelefona;
    }

    public String getOpisKvara() {
        return opisKvara;
    }

    public void setOpisKvara(String opisKvara) {
        this.opisKvara = opisKvara;
    }

    public Date getDatumPrijema() {
        return datumPrijema;
    }

    public void setDatumPrijema(Date datumPrijema) {
        this.datumPrijema = datumPrijema;
    }

    public int getIdModelTelefona() {
        return idModelTelefona;
    }

    public void setIdModelTelefona(int idModelTelefona) {
        this.idModelTelefona = idModelTelefona;
    }

    public String getSerijskiBrojTelefona() {
        return serijskiBrojTelefona;
    }

    public void setSerijskiBrojTelefona(String serijskiBrojTelefona) {
        this.serijskiBrojTelefona = serijskiBrojTelefona;
    }

    public boolean isTelefonPreuzet() {
        return telefonPreuzet;
    }

    public void setTelefonPreuzet(boolean telefonPreuzet) {
        this.telefonPreuzet = telefonPreuzet;
    }

    public String getImePrezimeKlijent() {
        return imePrezimeKlijent;
    }

    public void setImePrezimeKlijent(String imePrezimeKlijent) {
        this.imePrezimeKlijent = imePrezimeKlijent;
    }

    public String getImePrezimeZaposleni() {
        return imePrezimeZaposleni;
    }

    public void setImePrezimeZaposleni(String imePrezimeZaposleni) {
        this.imePrezimeZaposleni = imePrezimeZaposleni;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    public String getStanjeTelefona() {
        return stanjeTelefona;
    }

    public void setStanjeTelefona(String stanjeTelefona) {
        this.stanjeTelefona = stanjeTelefona;
    }

    @Override
    public String toString() {
        return "ServisTelefonaDTO{" + "idServisTelefona=" + idServisTelefona + ", idKlijent=" + idKlijent + ", idZaposleni=" + idZaposleni + ", idStanjeTelefona=" + idStanjeTelefona + ", opisKvara=" + opisKvara + ", datumPrijema=" + datumPrijema + ", idModelTelefona=" + idModelTelefona + ", serijskiBrojTelefona=" + serijskiBrojTelefona + ", telefonPreuzet=" + telefonPreuzet + ", imePrezimeKlijent=" + imePrezimeKlijent + ", imePrezimeZaposleni=" + imePrezimeZaposleni + ", nazivModela=" + nazivModela + ", stanjeTelefona=" + stanjeTelefona + '}';
    }
      
    
    
        

}