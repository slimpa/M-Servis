package dto;

public class KlijentDTO extends OsobaDTO {

    public KlijentDTO(int idOsoba, String ime, String prezime, String brojTelefona) {
        super(idOsoba, ime, prezime, brojTelefona);
    }
    
    public KlijentDTO(String ime, String prezime, String brojTelefona){
        super(ime, prezime, brojTelefona);
    }

    public KlijentDTO(int idKlijent) {
        super(idKlijent);
    }
}