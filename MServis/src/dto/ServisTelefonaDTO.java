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
        
        

}