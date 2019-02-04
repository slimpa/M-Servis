/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ArtikalDAO;
import dao.DodatnaOpremaDAO;
import dao.RezervniDioDAO;
import dao.TelefonDAO;
import dto.ArtikalDTO;
import dto.DodatnaOpremaDTO;
import dto.RezervniDioDTO;
import dto.TelefonDTO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class ManipulacijaArtiklimaController implements Initializable {

    @FXML
    private TableView<RezervniDioDTO> tableRezervniDijelovi;
    
    @FXML
    private TableColumn<RezervniDioDTO, Integer> colIdRezervniDio;
    
    @FXML
    private TableColumn<RezervniDioDTO, String> colNaziv;
    
    @FXML
    private TableColumn<RezervniDioDTO, Integer> colIdModelTelefona;
    
    @FXML
    private TableColumn<RezervniDioDTO, String> colOpis;
    
    @FXML
    private TableColumn<RezervniDioDTO, Integer> colKolicina;
    
    @FXML
    private TableColumn<RezervniDioDTO, Integer> colCijena;
    
    @FXML
    private Button btnRezervniDioPretraga;
    
    @FXML
    private Button btnRezervniDioprikaziRacun;
    
    @FXML
    private TextField tfRezervniDio;
    
    @FXML
    private Button btnRezervniDioDodaj;
    
    @FXML
    private Button btnRezervniDioIzmjeni;
     
    @FXML
    private Button btnRezervniDioObrisi;
    
    
    
    @FXML
    private TableView<TelefonDTO> tableTelefoni;
    
    @FXML
    private TableColumn<TelefonDTO, Integer> colTelefonIdTelefona;
    
    @FXML
    private TableColumn<TelefonDTO, String> colTelefonNaziv;
    
    @FXML
    private TableColumn<TelefonDTO, String> colTelefonModel;
    
    @FXML
    private TableColumn<TelefonDTO, String> colTelefonProizvodjac;
    
    @FXML
    private TableColumn<TelefonDTO, String> colTelefonBoja;
    
    @FXML
    private TableColumn<TelefonDTO, String> colTelefonSpecifikacija;
    
    @FXML
    private TableColumn<TelefonDTO, String> colTelefonSerijskiBroj;
    
    @FXML
    private TableColumn<TelefonDTO, Integer> colTelefonCijena;
    
    @FXML 
    private Button btnTelefonPretrazi;
    
    @FXML
    private Button btnTelefonDodaj;
    
    @FXML
    private Button btnTelefonIzmjeni;
    
    @FXML 
    private TextField tfTelefoniPretraga;
    
    @FXML
    private Button btnTelefonPrikaziRacun;
    
    @FXML
    private TableView<DodatnaOpremaDTO> tableDodatnaOprema;
    
    @FXML
    private TableColumn<DodatnaOpremaDTO, Integer> colDodatnaOpremaIdDodatneOpreme;
    
    @FXML
    private TableColumn<DodatnaOpremaDTO, String> colDodatnaOpremaNaziv;
    
    @FXML
    private TableColumn<DodatnaOpremaDTO, String> colDodatnaOpremaTip;
    
    @FXML
    private TableColumn<DodatnaOpremaDTO, String> colDodatnaOpremaBoja;
    
    @FXML
    private TableColumn<DodatnaOpremaDTO, String> colDodatnaOpremaModelTelefona;
    
    @FXML
    private TableColumn<DodatnaOpremaDTO, String> colDodatnaOpremaKolicina;
    
    @FXML
    private TableColumn<DodatnaOpremaDTO, String> colDodatnaOpremaCijena;
    
    @FXML
    private Button btnDodatnaOpremaDodaj;
    
    @FXML
    private Button btnDodatnaOpremaIzmjeni;
    
    @FXML
    private Button btnDodatnaOpremaobrisi;
     
    @FXML
    private Button btnDodatnaOpremaPretrazi;
    
    @FXML
    private Button btnDodatnaOpremaPrikaziRacun;
    
    @FXML
    private TextField tfPretragaDodatnaOprema;
    
    public static String vrijednost="nijeBitno";
    
    public static DodatnaOpremaDTO dodatnaOpremaDTO;
    
    public static TelefonDTO telefonDTO;
    
    public static RezervniDioDTO rezervniDioDTO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //rezervni dijelovi
        RezervniDioDAO rezervniDio = new MySQLDAOFactory().getRezervniDioDAO();
        List<RezervniDioDTO> lista = rezervniDio.selectAllDetailed();
        ObservableList<RezervniDioDTO> listaRezervnihDijelova = FXCollections.observableArrayList(lista);
        
        
        if(listaRezervnihDijelova != null){
            colIdRezervniDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdRezervniDio"));
            colIdModelTelefona.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdModelTelefona"));
            colOpis.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("Opis"));
            colNaziv.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("nazivRezervnogdijela"));
            colKolicina.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("kolicinaRezervnogdijela"));
            colCijena.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("Cijena"));
            tableRezervniDijelovi.setItems(listaRezervnihDijelova);
        }
        
        
        //telefoni
        TelefonDAO telefoni = new MySQLDAOFactory().getTelefonDAO();
        List<TelefonDTO> listaTelefona = telefoni.selectAll();
        ObservableList<TelefonDTO> listaSvihTelefona = FXCollections.observableArrayList(listaTelefona);
        
        
        if(listaSvihTelefona != null){
            colTelefonIdTelefona.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("IdModelTelefona"));
            colTelefonNaziv.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Naziv"));
            colTelefonModel.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Model"));
            colTelefonProizvodjac.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Proizvodjac"));
            colTelefonBoja.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Boja"));
            colTelefonSpecifikacija.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Specifikacija"));
            colTelefonSerijskiBroj.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("SerijskiBroj"));
            colTelefonCijena.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("Cijena"));
            tableTelefoni.setItems(listaSvihTelefona);
        }
        
        
        //dodatna oprema
        DodatnaOpremaDAO dodatnaOprema = new MySQLDAOFactory().getDodatnaOpremaDAO();
        List<DodatnaOpremaDTO> listaDodatneOpreme = dodatnaOprema.selectAll();
        ObservableList<DodatnaOpremaDTO> listaSveDodatneOpreme = FXCollections.observableArrayList(listaDodatneOpreme);
        
        
        if(listaSveDodatneOpreme != null){
            colDodatnaOpremaIdDodatneOpreme.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Integer>("idDodatnaOprema"));
            colDodatnaOpremaNaziv.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Naziv"));
            colDodatnaOpremaTip.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("TipOpreme"));
            colDodatnaOpremaBoja.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Boja"));
            colDodatnaOpremaModelTelefona.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("ModelTelefona"));
            colDodatnaOpremaKolicina.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Kolicina"));
            colDodatnaOpremaCijena.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Cijena"));
            
            tableDodatnaOprema.setItems(listaSveDodatneOpreme);
        }
        
    }    

    
    public void btnPretraziRezervneDijeloveHandler(ActionEvent e){
        String naziv = tfRezervniDio.getText();
        //System.out.println(naziv);
        RezervniDioDAO rezervniDio = new MySQLDAOFactory().getRezervniDioDAO();
        List<RezervniDioDTO> lista = rezervniDio.selectBy(naziv);
        ObservableList<RezervniDioDTO> listaRezervnihDijelova = FXCollections.observableArrayList(lista);
        if(listaRezervnihDijelova != null){
            colIdRezervniDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdRezervniDio"));
            colIdModelTelefona.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdModelTelefona"));
            colOpis.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("Opis"));
            colNaziv.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("nazivRezervnogdijela"));
            colKolicina.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("kolicinaRezervnogdijela"));
            colCijena.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("Cijena"));
            tableRezervniDijelovi.setItems(listaRezervnihDijelova);
        }
    }
    
    public void btnPretraziTelefoneHandler(ActionEvent e){
        String model = tfTelefoniPretraga.getText();
        //System.out.println(model);
        TelefonDAO telefoni = new MySQLDAOFactory().getTelefonDAO();
        List<TelefonDTO> listaTelefona = telefoni.selectBy(model);
        ObservableList<TelefonDTO> listaSvihTelefona = FXCollections.observableArrayList(listaTelefona);
        
        
        if(listaSvihTelefona != null){
            colTelefonIdTelefona.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("IdModelTelefona"));
            colTelefonNaziv.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Naziv"));
            colTelefonModel.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Model"));
            colTelefonProizvodjac.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Proizvodjac"));
            colTelefonBoja.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Boja"));
            colTelefonSpecifikacija.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Specifikacija"));
            colTelefonSerijskiBroj.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("SerijskiBroj"));
            colTelefonCijena.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("Cijena"));
            tableTelefoni.setItems(listaSvihTelefona);
        }
    }
    
    public void btnPretraziDodatnuOpremuHandler(ActionEvent e){
        String naziv = tfPretragaDodatnaOprema.getText();
        //System.out.println(naziv);
        DodatnaOpremaDAO dodatnaOprema = new MySQLDAOFactory().getDodatnaOpremaDAO();
        List<DodatnaOpremaDTO> listaDodatneOpreme = dodatnaOprema.selectBy(naziv);
        ObservableList<DodatnaOpremaDTO> listaSveDodatneOpreme = FXCollections.observableArrayList(listaDodatneOpreme);
        
        
        if(listaSveDodatneOpreme != null){
            colDodatnaOpremaIdDodatneOpreme.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Integer>("idDodatnaOprema"));
            colDodatnaOpremaNaziv.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Naziv"));
            colDodatnaOpremaTip.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("TipOpreme"));
            colDodatnaOpremaBoja.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Boja"));
            colDodatnaOpremaModelTelefona.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("ModelTelefona"));
            colDodatnaOpremaKolicina.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Kolicina"));
            colDodatnaOpremaCijena.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Cijena"));
            
            tableDodatnaOprema.setItems(listaSveDodatneOpreme);
        }
    }
    
    public void btnDodatnaOpremaDodajHandler(ActionEvent e){
        vrijednost = "nijeBitno";
        Stage stage = new Stage();
        Parent root3;
        try {
            root3 = FXMLLoader.load(getClass().getResource("DodavanjeDodatneOpreme.fxml"));

            Scene scene = new Scene(root3);
            
            stage.setTitle("Dodavanje dodatne opreme");
            stage.setScene(scene);
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void btnDodatnaOpremaIzmjeniHandler(ActionEvent e){
       vrijednost = "izmjeni";
       dodatnaOpremaDTO=tableDodatnaOprema.getSelectionModel().getSelectedItem();
        if(dodatnaOpremaDTO!=null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeDodatneOpreme.fxml"));
                Parent root1=(Parent)loader.load();
                Stage stage=new Stage();
                stage.setTitle("Izmjena");
                stage.setResizable(false);
                
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL); 
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         }
    }
    
    public void btnDodatnaOpremaObrisiHandler(ActionEvent e){
        dodatnaOpremaDTO=tableDodatnaOprema.getSelectionModel().getSelectedItem();
        DodatnaOpremaDAO dodatnaOpremaDAO=(new MySQLDAOFactory()).getDodatnaOpremaDAO();
        if(dodatnaOpremaDTO!=null)
            dodatnaOpremaDAO.delete(dodatnaOpremaDTO);
        tableDodatnaOprema.getItems().removeAll();
        
       
        DodatnaOpremaDAO dodatnaOprema = new MySQLDAOFactory().getDodatnaOpremaDAO();
        List<DodatnaOpremaDTO> listaDodatneOpreme = dodatnaOprema.selectAll();
        ObservableList<DodatnaOpremaDTO> listaSveDodatneOpreme = FXCollections.observableArrayList(listaDodatneOpreme);
        
        
        if(listaSveDodatneOpreme != null){
            colDodatnaOpremaIdDodatneOpreme.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Integer>("idDodatnaOprema"));
            colDodatnaOpremaNaziv.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Naziv"));
            colDodatnaOpremaTip.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("TipOpreme"));
            colDodatnaOpremaBoja.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Boja"));
            colDodatnaOpremaModelTelefona.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("ModelTelefona"));
            colDodatnaOpremaKolicina.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Kolicina"));
            colDodatnaOpremaCijena.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Cijena"));
            
            tableDodatnaOprema.setItems(listaSveDodatneOpreme);
        }
    }
    
    public void btnDodatnaOpremaPrikaziRacunHandler(ActionEvent e){
        Stage stage = new Stage();
        Parent root3;
        try {
            root3 = FXMLLoader.load(getClass().getResource("Racun.fxml"));

            Scene scene = new Scene(root3);
            
            stage.setTitle("Racun");
            stage.setScene(scene);
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnRezervniDioPrikaziRacunHandler(ActionEvent e){
        Stage stage = new Stage();
        Parent root3;
        try {
            root3 = FXMLLoader.load(getClass().getResource("Racun.fxml"));

            Scene scene = new Scene(root3);
            
            stage.setTitle("Racun");
            stage.setScene(scene);
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void btnTelefonPrikaziRacunHandler(ActionEvent e){
        Stage stage = new Stage();
        Parent root3;
        try {
            root3 = FXMLLoader.load(getClass().getResource("Racun.fxml"));

            Scene scene = new Scene(root3);
            
            stage.setTitle("Racun");
            stage.setScene(scene);
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnTelefonDodajHandler(ActionEvent e){
        vrijednost = "nijebitno";
        Stage stage = new Stage();
        Parent root3;
        try {
            root3 = FXMLLoader.load(getClass().getResource("DodavanjeTelefona.fxml"));

            Scene scene = new Scene(root3);
            
            stage.setTitle("Dodavanje telefona");
            stage.setScene(scene);
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnTelefonIzmjeniHandler(ActionEvent e){
        vrijednost = "izmjeni";
        telefonDTO=tableTelefoni.getSelectionModel().getSelectedItem();
        if(telefonDTO!=null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeTelefona.fxml"));
                Parent root1=(Parent)loader.load();
                Stage stage=new Stage();
                stage.setTitle("Izmjena");
                stage.setResizable(false);
                
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL); 
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         }
    }
    
    public void btnTelefonObrisiHandler(ActionEvent e){
        telefonDTO=tableTelefoni.getSelectionModel().getSelectedItem();
        TelefonDAO telefonDAO=(new MySQLDAOFactory()).getTelefonDAO();
        if(telefonDTO!=null)
            telefonDAO.delete(telefonDTO);
        tableTelefoni.getItems().removeAll();
        
       
        TelefonDAO telefoni = new MySQLDAOFactory().getTelefonDAO();
        List<TelefonDTO> listaTelefona = telefoni.selectAll();
        ObservableList<TelefonDTO> listaSvihTelefona = FXCollections.observableArrayList(listaTelefona);
        
        
        if(listaSvihTelefona != null){
            colTelefonIdTelefona.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("IdModelTelefona"));
            colTelefonNaziv.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Naziv"));
            colTelefonModel.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Model"));
            colTelefonProizvodjac.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Proizvodjac"));
            colTelefonBoja.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Boja"));
            colTelefonSpecifikacija.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Specifikacija"));
            colTelefonSerijskiBroj.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("SerijskiBroj"));
            colTelefonCijena.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("Cijena"));
            tableTelefoni.setItems(listaSvihTelefona);
        }
    }
    
    
    public void btnRezervniDioDodajHandler(ActionEvent e){
        vrijednost = "nijebitno";
        Stage stage = new Stage();
        Parent root3;
        try {
            root3 = FXMLLoader.load(getClass().getResource("DodavanjeRezervnogDijela.fxml"));

            Scene scene = new Scene(root3);
            
            stage.setTitle("Dodavanje rezervnog dijela");
            stage.setScene(scene);
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnRezervniDioIzmjeniHandler(ActionEvent e){
        vrijednost = "izmjeni";
        rezervniDioDTO=tableRezervniDijelovi.getSelectionModel().getSelectedItem();
        if(rezervniDioDTO!=null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeRezervnogDijela.fxml"));
                Parent root1=(Parent)loader.load();
                Stage stage=new Stage();
                stage.setTitle("Izmjena");
                stage.setResizable(false);
                
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL); 
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         }
    }
    
    public void btnRezervniDioObrisiHandler(ActionEvent e){
        rezervniDioDTO=tableRezervniDijelovi.getSelectionModel().getSelectedItem();
        RezervniDioDAO rezervniDioDAO=(new MySQLDAOFactory()).getRezervniDioDAO();
        if(rezervniDioDTO!=null)
            rezervniDioDAO.delete(rezervniDioDTO);
        tableRezervniDijelovi.getItems().removeAll();
        
       
        RezervniDioDAO rezervniDio = new MySQLDAOFactory().getRezervniDioDAO();
        List<RezervniDioDTO> lista = rezervniDio.selectAllDetailed();
        ObservableList<RezervniDioDTO> listaRezervnihDijelova = FXCollections.observableArrayList(lista);
        
        
        if(listaRezervnihDijelova != null){
            colIdRezervniDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdRezervniDio"));
            colIdModelTelefona.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdModelTelefona"));
            colOpis.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("Opis"));
            colNaziv.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("nazivRezervnogdijela"));
            colKolicina.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("kolicinaRezervnogdijela"));
            colCijena.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("Cijena"));
            tableRezervniDijelovi.setItems(listaRezervnihDijelova);
        }
    }
}
