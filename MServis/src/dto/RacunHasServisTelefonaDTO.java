package dto;

public class RacunHasServisTelefonaDTO {

    private int idRacun;
    private int idServisa;

    public RacunHasServisTelefonaDTO(int idRacun, int idServisa) {
        this.idRacun = idRacun;
        this.idServisa = idServisa;
    }

    public int getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public int getIdServisa() {
        return idServisa;
    }

    public void setIdServisa(int idServisa) {
        this.idServisa = idServisa;
    }

}
