package dto;

public class NarudzbaHasArtikalDTO {

	private int IdNarudzba;
	private int IdArtikal;
	private int Kolicina;
        private String Naziv;

    public NarudzbaHasArtikalDTO(int IdNarudzba, int IdArtikal, int Kolicina) {
        this.IdNarudzba = IdNarudzba;
        this.IdArtikal = IdArtikal;
        this.Kolicina = Kolicina;
    }

    public NarudzbaHasArtikalDTO(int IdArtikal, int Kolicina) {
        this.IdArtikal = IdArtikal;
        this.Kolicina = Kolicina;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public NarudzbaHasArtikalDTO(int IdNarudzba,int IdArtikal, int Kolicina, String Naziv) {
        this.IdNarudzba = IdNarudzba;
        this.Kolicina = Kolicina;
        this.Naziv = Naziv;
        this.IdArtikal=IdArtikal;
    }

    public int getIdNarudzba() {
        return IdNarudzba;
    }

    public void setIdNarudzba(int IdNarudzba) {
        this.IdNarudzba = IdNarudzba;
    }

    public int getIdArtikal() {
        return IdArtikal;
    }

    public void setIdArtikal(int IdArtikal) {
        this.IdArtikal = IdArtikal;
    }

    public int getKolicina() {
        return Kolicina;
    }

    public void setKolicina(int Kolicina) {
        this.Kolicina = Kolicina;
    }

    @Override
    public String toString() {
        return "NarudzbaHasArtikalDTO{" + "IdNarudzba=" + IdNarudzba + ", IdArtikal=" + IdArtikal + ", Kolicina=" + Kolicina + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.IdNarudzba;
        hash = 29 * hash + this.IdArtikal;
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
        final NarudzbaHasArtikalDTO other = (NarudzbaHasArtikalDTO) obj;
        if (this.IdNarudzba != other.IdNarudzba) {
            return false;
        }
        if (this.IdArtikal != other.IdArtikal) {
            return false;
        }
        return true;
    }


  
        

}