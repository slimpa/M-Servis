package dto;

public class ModelTelefonaDTO extends ArtikalDTO {

    public ModelTelefonaDTO(String nazivModela) {
        NazivModela = nazivModela;
    }

	private int idModeltelefona;
	private String Specifikacija;
	private String Slika;

    public ModelTelefonaDTO(int idModeltelefona, String Specifikacija, String NazivModela) {
        this.idModeltelefona = idModeltelefona;
        this.Specifikacija = Specifikacija;
        this.NazivModela = NazivModela;
    }
	private String NazivModela;

    public ModelTelefonaDTO(int idModeltelefona, String Specifikacija, String Slika, String NazivModela) {
        this.idModeltelefona = idModeltelefona;
        this.Specifikacija = Specifikacija;
        this.Slika = Slika;
        this.NazivModela = NazivModela;
    }

    public void setIdModeltelefona(int idModeltelefona) {
        this.idModeltelefona = idModeltelefona;
    }

    public void setSpecifikacija(String Specifikacija) {
        this.Specifikacija = Specifikacija;
    }

    public void setSlika(String Slika) {
        this.Slika = Slika;
    }

    public void setNazivModela(String NazivModela) {
        this.NazivModela = NazivModela;
    }

    public int getIdModeltelefona() {
        return idModeltelefona;
    }

    public String getSpecifikacija() {
        return Specifikacija;
    }

    public String getSlika() {
        return Slika;
    }

    public String getNazivModela() {
        return NazivModela;
    }

}