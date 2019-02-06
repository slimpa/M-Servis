package dto;

public class RacunHasArtikalDTO {

	private int idRacun;
	private int idArtikal;
	private int kolicina;

    public RacunHasArtikalDTO(int idRacun, int idArtikal, int kolicina) {
        this.idRacun = idRacun;
        this.idArtikal = idArtikal;
        this.kolicina = kolicina;
    }

    public RacunHasArtikalDTO() {
    }

    public int getIdRacun() {
        return idRacun;
    }

    public int getIdArtikal() {
        return idArtikal;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public void setIdArtikal(int idArtikal) {
        this.idArtikal = idArtikal;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "RacunHasArtikalDTO{" + "idRacun=" + idRacun + ", idArtikal=" + idArtikal + ", kolicina=" + kolicina + '}';
    }
        
        

}