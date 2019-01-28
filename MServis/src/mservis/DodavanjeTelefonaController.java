/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ArtikalDAO;
import dao.CijenaDAO;
import dao.ModelTelefonaDAO;
import dao.ProizvodjacDAO;
import dao.TelefonDAO;
import dto.ArtikalDTO;
import dto.CijenaDTO;
import dto.ModelTelefonaDTO;
import dto.ProizvodjacDTO;
import dto.TelefonDTO;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static mservis.ManipulacijaArtiklimaController.vrijednost;
import static mservis.ManipulacijaArtiklimaController.telefonDTO;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class DodavanjeTelefonaController implements Initializable{
    
    @FXML
    private TextField tfNaziv;
    
    @FXML
    private ComboBox cbProizvodjac;
    
    @FXML
    private TextField tfBarKod;
    
    @FXML
    private TextField tfModel;
    
    @FXML
    private TextField tfSerijskiBroj;
    
    @FXML
    private ComboBox cbBoja;
    
    @FXML
    private TextField tfCijena;
    
    @FXML
    private TextArea taSpecifikacija;
    
    @FXML
    private Button btnSacuvaj;
    
    @FXML
    private Button btnIzadji;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(vrijednost.equals("izmjeni")){
            popuniPolja(telefonDTO);
        }
        else{
            ProizvodjacDAO proizvodjacDAO=(new MySQLDAOFactory()).getProizvodjacDAO();
            ObservableList<ProizvodjacDTO> proizvodjacList;
            proizvodjacList=FXCollections.observableArrayList(proizvodjacDAO.selectAll());
            List<String> naziviProizvodjaca = new ArrayList<String>();
            for(ProizvodjacDTO dob : proizvodjacList){
                naziviProizvodjaca.add(dob.getNaziv());
            }

            cbProizvodjac.getItems().addAll(naziviProizvodjaca);
            //cbModel.getItems().addAll(modeliTelefona);
            cbBoja.getItems().addAll("Plava","Crvena","Bijela","Zlatna","Crna","Siva","Narandzasta","Zuta","Zelena");
        }
    }
    
    public void btnSacuvajHandler(ActionEvent e){
        String naziv = tfNaziv.getText();
        String proizvodjac =  cbProizvodjac.getValue().toString();
        String barKod = tfBarKod.getText(); 
        String model = tfModel.getText();
        String serijskiBroj =  tfSerijskiBroj.getText();
        String boja = cbBoja.getValue().toString();
        Integer cijena = Integer.parseInt(tfCijena.getText());
        String specifikacija =  taSpecifikacija.getText();
        Integer idModelaTelefona ;
        
        
        
        
        ProizvodjacDTO proizvodjacDTO = new ProizvodjacDTO(proizvodjac);
        //String Naziv, int Kolicina, int idProizvodjac, String BarKod
        ProizvodjacDAO proizvodjacDAO = new MySQLDAOFactory().getProizvodjacDAO();
        List<ProizvodjacDTO> proizvodjaci = proizvodjacDAO.selectBy(proizvodjacDTO);
        ArtikalDTO artikal = new ArtikalDTO(naziv,1,proizvodjaci.get(0).getIdProizvodjac(),barKod);
        //String Naziv, int Kolicina, int idProizvodjac, String BarKod
        ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
        artikalDAO.insert(artikal);
        idModelaTelefona=artikalDAO.getLastId();
        
        ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(idModelaTelefona,specifikacija,"nema slike",model);//int idModeltelefona, String Specifikacija, String Slika, String NazivModela
        //String Naziv, int Kolicina, int idProizvodjac, String BarKod
        ModelTelefonaDAO modelTelefonaDAO = new MySQLDAOFactory().getModelTelefonaDAO();
        List<ModelTelefonaDTO> modeli = modelTelefonaDAO.selectBy(modelTelefonaDTO);
        modelTelefonaDAO.insert(modelTelefonaDTO);
        //imam naziv, proizvodjac,barkod,model
        //String SerijskiBroj, String Boja, int idModelTelefona, String Naziv, String Model, String Proizvodjac, String Specifikacija, int Cijena
        TelefonDTO telefonDTO = new TelefonDTO(serijskiBroj,boja,idModelaTelefona,naziv,model, proizvodjac,specifikacija,cijena);
        TelefonDAO telefonDAO = new MySQLDAOFactory().getTelefonDAO();
        telefonDAO.insert(telefonDTO);
        

        
        CijenaDAO cijenaDAO = new MySQLDAOFactory().getCijenaDAO();
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        CijenaDTO cijenaDTO = new CijenaDTO(artikalDAO.getLastId(),cijena,ts);
        cijenaDAO.insert(cijenaDTO);
    }
    
    public void btnIzadjiHandler(ActionEvent e){
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }
    
    public void popuniPolja(TelefonDTO telefon){
        tfNaziv.setText(telefon.getNaziv());
        cbProizvodjac.setValue(telefon.getProizvodjac());
       // tfBarKod.setText(telefon);
        tfModel.setText(telefon.getModel());
        tfSerijskiBroj.setText(telefon.getSerijskiBroj());
        cbBoja.setValue(telefon.getBoja());
       // tfKolicina.setText(Integer.toString(telefon.getSerijs));
        tfCijena.setText(Integer.toString(telefon.getCijena()));
    }
}
