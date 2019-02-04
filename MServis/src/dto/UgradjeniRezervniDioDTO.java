package dto;

public class UgradjeniRezervniDioDTO {

    private int idRezervniDio;
    private int idServisa;
    private String nazivDijela;

    public UgradjeniRezervniDioDTO(int idRezervniDio, int idServisa) {
        this.idRezervniDio = idRezervniDio;
        this.idServisa = idServisa;
    }

    public UgradjeniRezervniDioDTO(int idRezervniDio, int idServisa, String nazivDijela) {
        this.idRezervniDio = idRezervniDio;
        this.idServisa = idServisa;
        this.nazivDijela = nazivDijela;
    }

    public UgradjeniRezervniDioDTO(int idServisa) {
        this.idServisa = idServisa;
    }

    public String getNazivDijela() {
        return nazivDijela;
    }

    public void setNazivDijela(String nazivDijela) {
        this.nazivDijela = nazivDijela;
    }

    
    

    public int getIdRezervniDio() {
        return idRezervniDio;
    }

    public void setIdRezervniDio(int idRezervniDio) {
        this.idRezervniDio = idRezervniDio;
    }

    public int getIdServisa() {
        return idServisa;
    }

    public void setIdServisa(int idServisa) {
        this.idServisa = idServisa;
    }

}
