package dto;

import java.sql.Timestamp;

public class NarudzbaDTO {

	private int IdNarudzba;
	private Timestamp DatumNarudzbe;
	private int IdDobavljac;

    public NarudzbaDTO() {
    }

    public NarudzbaDTO(int IdNarudzba, Timestamp DatumNarudzbe, int IdDobavljac) {
        this.IdNarudzba = IdNarudzba;
        this.DatumNarudzbe = DatumNarudzbe;
        this.IdDobavljac = IdDobavljac;
    }

    public NarudzbaDTO(Timestamp DatumNarudzbe, int IdDobavljac) {
        this.DatumNarudzbe = DatumNarudzbe;
        this.IdDobavljac = IdDobavljac;
    }

    public int getIdNarudzba() {
        return IdNarudzba;
    }

    public void setIdNarudzba(int IdNarudzba) {
        this.IdNarudzba = IdNarudzba;
    }

    public Timestamp getDatumNarudzbe() {
        return DatumNarudzbe;
    }

    public void setDatumNarudzbe(Timestamp DatumNarudzbe) {
        this.DatumNarudzbe = DatumNarudzbe;
    }

    public int getIdDobavljac() {
        return IdDobavljac;
    }

    public void setIdDobavljac(int IdDobavljac) {
        this.IdDobavljac = IdDobavljac;
    }

    @Override
    public String toString() {
        return "NarudzbaDTO{" + "IdNarudzba=" + IdNarudzba + ", DatumNarudzbe=" + DatumNarudzbe + ", IdDobavljac=" + IdDobavljac + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.IdNarudzba;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NarudzbaDTO other = (NarudzbaDTO) obj;
        if (this.IdNarudzba != other.IdNarudzba) {
            return false;
        }
        return true;
    }
        
        
        
        

}