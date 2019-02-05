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
import dto.ArtikalDTO;
import dto.CijenaDTO;
import dto.ModelTelefonaDTO;
import dto.ProizvodjacDTO;
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
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class DodavanjeModelaTelefonaController implements Initializable{

    @FXML
    private TextField tfNazivModela;
    
    @FXML
    private TextArea taSpecifikacija;
    
    @FXML
    private TextField tfNaziv;
    
    @FXML
    private ComboBox cbProizvodjac;
    
    @FXML
    private TextField tfBarKod;
    
    @FXML 
    private TextField tfCijena;
    
    @FXML
    private Button btnSacuvaj;
    
    @FXML
    private Button btnOtkazi;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProizvodjacDAO proizvodjacDAO=(new MySQLDAOFactory()).getProizvodjacDAO();
        ObservableList<ProizvodjacDTO> proizvodjacList;
        proizvodjacList=FXCollections.observableArrayList(proizvodjacDAO.selectAll());
        List<String> naziviProizvodjaca = new ArrayList<String>();
        for(ProizvodjacDTO dob : proizvodjacList){
            naziviProizvodjaca.add(dob.getNaziv());
        }

        cbProizvodjac.getItems().addAll(naziviProizvodjaca);
    }
    
    public void btnSacuvajHandler(ActionEvent e){
        String specifikacija = taSpecifikacija.getText();
        String model = tfNazivModela.getText();
        Integer idModelaTelefona ;
        
        String naziv = tfNaziv.getText();
        String proizvodjac =  cbProizvodjac.getValue().toString();
        String barKod = tfBarKod.getText(); 
        Double cijena = Double.parseDouble(tfCijena.getText());
        
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
        
        CijenaDAO cijenaDAO = new MySQLDAOFactory().getCijenaDAO();
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        CijenaDTO cijenaDTO = new CijenaDTO(artikalDAO.getLastId(),cijena,ts);
        cijenaDAO.insert(cijenaDTO);
    }
    
    public void btnOtkaziHandler(ActionEvent e){
        
    }
    
}
