package dto;

public class DobavljacDTO {

	private int IdDobavljac;
	private String Naziv;
	private String Adresa;
	private String Telefon;
	private String Email;
	private boolean Obrisano;

    public DobavljacDTO() {}
    
    public DobavljacDTO(String Naziv, String Adresa, String Telefon, String Email) {
        this.Naziv = Naziv;
        this.Adresa = Adresa;
        this.Telefon = Telefon;
        this.Email = Email;
    }

    public DobavljacDTO(int IdDobavljac, String Naziv, String Adresa, String Telefon, String Email) {
        this.IdDobavljac = IdDobavljac;
        this.Naziv = Naziv;
        this.Adresa = Adresa;
        this.Telefon = Telefon;
        this.Email = Email;
    }
    
    

    public int getIdDobavljac() {
        return IdDobavljac;
    }

    public void setIdDobavljac(int IdDobavljac) {
        this.IdDobavljac = IdDobavljac;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String Adresa) {
        this.Adresa = Adresa;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isObrisano() {
        return Obrisano;
    }

    public void setObrisano(boolean Obrisano) {
        this.Obrisano = Obrisano;
    }

    @Override
    public String toString() {
        return "DobavljacDTO{" + "idDobavljac=" + IdDobavljac + ", Naziv=" + Naziv + ", Adresa=" + Adresa + ", Telefon=" + Telefon + ", Email=" + Email + ", Obrisano=" + Obrisano + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.IdDobavljac;
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
        final DobavljacDTO other = (DobavljacDTO) obj;
        if (this.IdDobavljac != other.IdDobavljac) {
            return false;
        }
        return true;
    }    

}