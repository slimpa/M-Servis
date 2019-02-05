package dto;

public class UgradjeniRezervniDioDTO {

    private int idRezervniDio;
    private int idServisa;
    private String nazivDijela;
    private double cijena;

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

    public UgradjeniRezervniDioDTO(int idRezervniDio, int idServisa, String nazivDijela, double cijena) {
        this.idServisa = idServisa;
        this.idRezervniDio = idRezervniDio;
        this.nazivDijela = nazivDijela;
        this.cijena = cijena;
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

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }
    
    

}
