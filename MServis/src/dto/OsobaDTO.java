package dto;

public class OsobaDTO {

	protected int idOsoba;
        protected String ime;
	protected String prezime;
	protected String brojTelefona;

    public OsobaDTO() {
    }
        
    public OsobaDTO(int idOsoba) {
        this.idOsoba = idOsoba;
    }

    public OsobaDTO(int idOsoba, String ime, String prezime, String brojTelefona) {
        this.idOsoba = idOsoba;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
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

    @Override
    public int hashCode() {
        int hash = 7;
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
        final OsobaDTO other = (OsobaDTO) obj;
        if (this.idOsoba != other.idOsoba) {
            return false;
        }
        return true;
    }

    

        
        
        

}