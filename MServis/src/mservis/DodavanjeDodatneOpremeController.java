/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ArtikalDAO;
import dao.CijenaDAO;

import dao.DodatnaOpremaDAO;
import dao.ModelTelefonaDAO;
import dao.ProizvodjacDAO;
import dao.TipDodatneOpremeDAO;
import dto.ArtikalDTO;
import dto.CijenaDTO;

import dto.DodatnaOpremaDTO;
import dto.ModelTelefonaDTO;
import dto.ProizvodjacDTO;
import dto.TipDodatneOpremeDTO;
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
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static mservis.ManipulacijaArtiklimaController.dodatnaOpremaDTO;
import mySQL.MySQLDAOFactory;
import static mservis.ManipulacijaArtiklimaController.vrijednost;



/**
 *
 * @author Bojan
 */
public class DodavanjeDodatneOpremeController implements Initializable {

    @FXML
    private Button btnIzadji;
    
    @FXML
    private Button btnSacuvaj;
    
    @FXML
    private TextField tfNaziv;
    
    @FXML
    private ComboBox cbTip;
    
    @FXML
    private ComboBox cbProizvodjac;
    
    @FXML
    private ComboBox cbModel;
    
    @FXML
    private TextField tfBarKod;
    
    @FXML
    private ComboBox cbBoja;
    
    @FXML
    private TextField tfKolicina;
    
    @FXML
    private TextField tfCijena;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(vrijednost.equals("izmjeni")){
            popuniPolja(dodatnaOpremaDTO);
        }
        else{
            ProizvodjacDAO proizvodjacDAO=(new MySQLDAOFactory()).getProizvodjacDAO();
            ObservableList<ProizvodjacDTO> proizvodjacList;
            proizvodjacList=FXCollections.observableArrayList(proizvodjacDAO.selectAll());
            List<String> naziviProizvodjaca = new ArrayList<String>();
            for(ProizvodjacDTO dob : proizvodjacList){
                naziviProizvodjaca.add(dob.getNaziv());
            }

            TipDodatneOpremeDAO tipDodatneOpremeDAO=(new MySQLDAOFactory()).getTipDodatneOpremeDAO();
            ObservableList<TipDodatneOpremeDTO> tipDodatneOpremeList;
            tipDodatneOpremeList=FXCollections.observableArrayList(tipDodatneOpremeDAO.selectAll());
            List<String> tipoviDodatneOpreme = new ArrayList<String>();
            for(TipDodatneOpremeDTO tdo : tipDodatneOpremeList){
                tipoviDodatneOpreme.add(tdo.getTip());
            }

            ModelTelefonaDAO modelTelefonaDAODAO=(new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList=FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
            List<String> modeliTelefona = new ArrayList<String>();
            for(ModelTelefonaDTO mt : modelTelefonaList){
                modeliTelefona.add(mt.getNazivModela());
            }

            cbTip.getItems().addAll(tipoviDodatneOpreme);
            cbProizvodjac.getItems().addAll(naziviProizvodjaca);
            cbModel.getItems().addAll(modeliTelefona);
            cbBoja.getItems().addAll("Plava","Crvena","Bijela","Zlatna","Crna","Siva","Narandzasta","Zuta","Zelena");
        }
        
    }
        
        
    
    public void btnSacuvajHandler(ActionEvent e){
        
        
        String naziv = tfNaziv.getText();
        String tip =  cbTip.getValue().toString();
        String proizvodjac =  cbProizvodjac.getValue().toString();
        String model = cbModel.getValue().toString();
        String barKod = tfBarKod.getText(); 
        String boja = cbBoja.getValue().toString();
        Integer kolicina = Integer.parseInt(tfKolicina.getText());
        Integer cijena = Integer.parseInt(tfCijena.getText());
        
        ProizvodjacDTO proizvodjacDTO = new ProizvodjacDTO(proizvodjac);
        //String Naziv, int Kolicina, int idProizvodjac, String BarKod
        ProizvodjacDAO proizvodjacDAO = new MySQLDAOFactory().getProizvodjacDAO();
        List<ProizvodjacDTO> proizvodjaci = proizvodjacDAO.selectBy(proizvodjacDTO);
        ArtikalDTO artikal = new ArtikalDTO(naziv,kolicina,proizvodjaci.get(0).getIdProizvodjac(),barKod);
        //String Naziv, int Kolicina, int idProizvodjac, String BarKod
        ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
        artikalDAO.insert(artikal);
        
        DodatnaOpremaDAO doDAO = new MySQLDAOFactory().getDodatnaOpremaDAO();
        DodatnaOpremaDTO dodatnaOprema = new DodatnaOpremaDTO(boja,naziv,model,tip,proizvodjac,barKod,kolicina,cijena);
        //boja,naziv,model,tip,proizvodjac,barKod,kolicina,cijena
        dodatnaOprema.setIdDodatnaOprema(artikalDAO.getLastId());

        ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(model);
        //String Naziv, int Kolicina, int idProizvodjac, String BarKod
        ModelTelefonaDAO modelTelefonaDAO = new MySQLDAOFactory().getModelTelefonaDAO();
        List<ModelTelefonaDTO> modeli = modelTelefonaDAO.selectBy(modelTelefonaDTO);
        dodatnaOprema.setIdModelTelefona(modeli.get(0).getIdModeltelefona());
        
        TipDodatneOpremeDTO tipDodatneOpremeDTO = new TipDodatneOpremeDTO(tip);
        //String Naziv, int Kolicina, int idProizvodjac, String BarKod
        TipDodatneOpremeDAO tipDodatneOpremeDAO = new MySQLDAOFactory().getTipDodatneOpremeDAO();
        List<TipDodatneOpremeDTO> tipovi = tipDodatneOpremeDAO.selectBy(tipDodatneOpremeDTO);
        dodatnaOprema.setIdTipDodatneOpreme(tipovi.get(0).getIdTipDodatneOpreme());
        doDAO.insert(dodatnaOprema);
        
        CijenaDAO cijenaDAO = new MySQLDAOFactory().getCijenaDAO();
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        CijenaDTO cijenaDTO = new CijenaDTO(artikalDAO.getLastId(),cijena,ts);
        cijenaDAO.insert(cijenaDTO);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setHeaderText(null);
        alert.setContentText("Uspjesno dodano!");

        alert.showAndWait();
        
        Stage stage = (Stage) btnSacuvaj.getScene().getWindow();
        stage.close();
        
    }
    
    public void btnIzadjiHandler(ActionEvent e){
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }

    
    public void popuniPolja(DodatnaOpremaDTO dodatnaOprema){
        tfNaziv.setText(dodatnaOprema.getNaziv());
        cbTip.setValue(dodatnaOprema.getIdTipDodatneOpreme());
        cbProizvodjac.setValue(dodatnaOprema.getIdProizvodjac());
        cbModel.setValue(dodatnaOprema.getModelTelefona());
        tfBarKod.setText(dodatnaOprema.getBarKod());
        cbBoja.setValue(dodatnaOprema.getBoja());
        tfKolicina.setText(Integer.toString(dodatnaOprema.getKolicina()));
        tfCijena.setText(Integer.toString(dodatnaOprema.getCijena()));
    }
    
}
