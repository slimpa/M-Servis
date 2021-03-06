package dto;

public class StanjeTelefonaDTO {

    private int idStanjeTelefona;
    private String stanje;
    
    public StanjeTelefonaDTO(int idStanjeTelefona){
        this.idStanjeTelefona = idStanjeTelefona;
    }

    public StanjeTelefonaDTO(int idStanjeTelefona, String stanje) {
        this.idStanjeTelefona = idStanjeTelefona;
        this.stanje = stanje;
    }

    public StanjeTelefonaDTO(String stanje) {
        this.stanje = stanje;
    }

    public int getIdStanjeTelefona() {
        return idStanjeTelefona;
    }

    public void setIdStanjeTelefona(int idStanjeTelefona) {
        this.idStanjeTelefona = idStanjeTelefona;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

}
