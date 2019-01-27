package dto;

import java.sql.Timestamp;

public class CijenaDTO {

	private int idCijene;
	private int idArtikal;
	private double Cijena;
	private Timestamp DatumOd;
	private Timestamp DatumDo;
	private boolean TrenutnaCijena;

    public CijenaDTO(double Cijena, Timestamp DatumOd) {
        this.Cijena = Cijena;
        this.DatumOd = DatumOd;
    }

    public CijenaDTO(int idArtikal, double Cijena, Timestamp DatumOd) {
        this.idArtikal = idArtikal;
        this.Cijena = Cijena;
        this.DatumOd = DatumOd;
    }

    public CijenaDTO(int idCijene, int idArtikal, double Cijena, Timestamp DatumOd, Timestamp DatumDo, boolean TrenutnaCijena) {
        this.idCijene = idCijene;
        this.idArtikal = idArtikal;
        this.Cijena = Cijena;
        this.DatumOd = DatumOd;
        this.DatumDo = DatumDo;
        this.TrenutnaCijena = TrenutnaCijena;
    }

    public CijenaDTO() {
    }

    public int getIdCijene() {
        return idCijene;
    }

    public int getIdArtikal() {
        return idArtikal;
    }

    public double getCijena() {
        return Cijena;
    }

    public Timestamp getDatumOd() {
        return DatumOd;
    }

    public Timestamp getDatumDo() {
        return DatumDo;
    }

    public boolean isTrenutnaCijena() {
        return TrenutnaCijena;
    }

    public void setIdCijene(int idCijene) {
        this.idCijene = idCijene;
    }

    public void setIdArtikal(int idArtikal) {
        this.idArtikal = idArtikal;
    }

    public void setCijena(double Cijena) {
        this.Cijena = Cijena;
    }

    public void setDatumOd(Timestamp DatumOd) {
        this.DatumOd = DatumOd;
    }

    public void setDatumDo(Timestamp DatumDo) {
        this.DatumDo = DatumDo;
    }

    public void setTrenutnaCijena(boolean TrenutnaCijena) {
        this.TrenutnaCijena = TrenutnaCijena;
    }
    
}