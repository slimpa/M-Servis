/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.DodatnaOpremaDAO;
import dao.RacunDAO;
import dao.RacunHasArtikalDAO;
import dao.RezervniDioDAO;
import dao.TelefonDAO;
import dao.ZaposleniDAO;
import dto.DodatnaOpremaDTO;
import dto.RacunDTO;
import dto.RacunHasArtikalDTO;
import dto.RezervniDioDTO;
import dto.StanjeTelefonaDTO;
import dto.TelefonDTO;
import dto.ZaposleniDTO;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;
import net.sf.jasperreports.engine.JRException;
import service.GeneratorIzvjestaja;
import service.StavkaRacuna;

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
    private TableColumn<RezervniDioDTO, Double> colCijena;

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
    private TableColumn<TelefonDTO, Double> colTelefonCijena;

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
    private TableColumn<DodatnaOpremaDTO, Double> colDodatnaOpremaCijena;

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

    public static String vrijednost = "nijeBitno";

    public static DodatnaOpremaDTO dodatnaOpremaDTO;

    public static TelefonDTO telefonDTO;

    public static RezervniDioDTO rezervniDioDTO;

    @FXML
    private Button btnDodajTelefonNaRacun;
    @FXML
    private Button btnDodajOpremuNaRacun;
    @FXML
    private Button btnDodajRezervniDioNaRacun;

    @FXML
    private Button btnStampaj;
    @FXML
    private Button btnOtkazi;
    @FXML
    private Button btnObrisiStavku;

    @FXML
    private TextField tfUkupno;
    @FXML
    private TableView<StavkaRacuna> tableRacun;

    @FXML
    private TableColumn<StavkaRacuna, Integer> colIdStavke;
    @FXML
    private TableColumn<StavkaRacuna, String> colNazivStavke;
    @FXML
    private TableColumn<StavkaRacuna, Double> colCijenaStavke;
    @FXML
    private TableColumn<StavkaRacuna, Integer> colKolicinaStavke;

    ArrayList<StavkaRacuna> racun;
    RacunDAO racunDao = new MySQLDAOFactory().getRacunDAO();
    RacunHasArtikalDAO racunArtikal = new MySQLDAOFactory().getRacunHasArtikalDAO();
    private String zaposleni;
    private ZaposleniDAO zaposleniDao = new MySQLDAOFactory().getZaposleniDAO();
    private int idZaposlenog;
       GeneratorIzvjestaja generator = new GeneratorIzvjestaja();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zaposleni = LoginController.getKorisnickoIme();
        idZaposlenog = zaposleniDao.selectZaposleni(new ZaposleniDTO(zaposleni)).getIdOsoba();
        tfUkupno.setEditable(false);
        racun = new ArrayList<StavkaRacuna>();

        //rezervni dijelovi
        RezervniDioDAO rezervniDio = new MySQLDAOFactory().getRezervniDioDAO();
        List<RezervniDioDTO> lista = rezervniDio.selectAllDetailed();
        ObservableList<RezervniDioDTO> listaRezervnihDijelova = FXCollections.observableArrayList(lista);

        if (listaRezervnihDijelova != null) {
            colIdRezervniDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdRezervniDio"));
            colIdModelTelefona.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdModelTelefona"));
            colOpis.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("Opis"));
            colNaziv.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("nazivRezervnogdijela"));
            colKolicina.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("kolicinaRezervnogdijela"));
            colCijena.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Double>("Cijena"));
            tableRezervniDijelovi.setItems(listaRezervnihDijelova);
        }

        //telefoni
        TelefonDAO telefoni = new MySQLDAOFactory().getTelefonDAO();
        List<TelefonDTO> listaTelefona = telefoni.selectAll();
        ObservableList<TelefonDTO> listaSvihTelefona = FXCollections.observableArrayList(listaTelefona);

        if (listaSvihTelefona != null) {
            colTelefonIdTelefona.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("IdModelTelefona"));
            colTelefonNaziv.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Naziv"));
            colTelefonModel.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Model"));
            colTelefonProizvodjac.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Proizvodjac"));
            colTelefonBoja.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Boja"));
            colTelefonSpecifikacija.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Specifikacija"));
            colTelefonSerijskiBroj.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("SerijskiBroj"));
            colTelefonCijena.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Double>("Cijena"));
            tableTelefoni.setItems(listaSvihTelefona);
        }

        //dodatna oprema
        DodatnaOpremaDAO dodatnaOprema = new MySQLDAOFactory().getDodatnaOpremaDAO();
        List<DodatnaOpremaDTO> listaDodatneOpreme = dodatnaOprema.selectAll();
        ObservableList<DodatnaOpremaDTO> listaSveDodatneOpreme = FXCollections.observableArrayList(listaDodatneOpreme);

        if (listaSveDodatneOpreme != null) {
            colDodatnaOpremaIdDodatneOpreme.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Integer>("idDodatnaOprema"));
            colDodatnaOpremaNaziv.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Naziv"));
            colDodatnaOpremaTip.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("TipOpreme"));
            colDodatnaOpremaBoja.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Boja"));
            colDodatnaOpremaModelTelefona.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("ModelTelefona"));
            colDodatnaOpremaKolicina.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Kolicina"));
            colDodatnaOpremaCijena.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Double>("Cijena"));

            tableDodatnaOprema.setItems(listaSveDodatneOpreme);
        }

    }

    public void btnPretraziRezervneDijeloveHandler(ActionEvent e) {
        String naziv = tfRezervniDio.getText();
        //System.out.println(naziv);
        RezervniDioDAO rezervniDio = new MySQLDAOFactory().getRezervniDioDAO();
        List<RezervniDioDTO> lista = rezervniDio.selectBy(naziv);
        ObservableList<RezervniDioDTO> listaRezervnihDijelova = FXCollections.observableArrayList(lista);
        if (listaRezervnihDijelova != null) {
            colIdRezervniDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdRezervniDio"));
            colIdModelTelefona.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdModelTelefona"));
            colOpis.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("Opis"));
            colNaziv.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("nazivRezervnogdijela"));
            colKolicina.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("kolicinaRezervnogdijela"));
            colCijena.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Double>("Cijena"));
            tableRezervniDijelovi.setItems(listaRezervnihDijelova);
        }
    }

    public void btnPretraziTelefoneHandler(ActionEvent e) {
        String model = tfTelefoniPretraga.getText();
        //System.out.println(model);
        TelefonDAO telefoni = new MySQLDAOFactory().getTelefonDAO();
        List<TelefonDTO> listaTelefona = telefoni.selectBy(model);
        ObservableList<TelefonDTO> listaSvihTelefona = FXCollections.observableArrayList(listaTelefona);

        if (listaSvihTelefona != null) {
            colTelefonIdTelefona.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("IdModelTelefona"));
            colTelefonNaziv.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Naziv"));
            colTelefonModel.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Model"));
            colTelefonProizvodjac.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Proizvodjac"));
            colTelefonBoja.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Boja"));
            colTelefonSpecifikacija.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Specifikacija"));
            colTelefonSerijskiBroj.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("SerijskiBroj"));
            colTelefonCijena.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Double>("Cijena"));
            tableTelefoni.setItems(listaSvihTelefona);
        }
    }

    public void btnPretraziDodatnuOpremuHandler(ActionEvent e) {
        String naziv = tfPretragaDodatnaOprema.getText();
        //System.out.println(naziv);
        DodatnaOpremaDAO dodatnaOprema = new MySQLDAOFactory().getDodatnaOpremaDAO();
        List<DodatnaOpremaDTO> listaDodatneOpreme = dodatnaOprema.selectBy(naziv);
        ObservableList<DodatnaOpremaDTO> listaSveDodatneOpreme = FXCollections.observableArrayList(listaDodatneOpreme);

        if (listaSveDodatneOpreme != null) {
            colDodatnaOpremaIdDodatneOpreme.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Integer>("idDodatnaOprema"));
            colDodatnaOpremaNaziv.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Naziv"));
            colDodatnaOpremaTip.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("TipOpreme"));
            colDodatnaOpremaBoja.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Boja"));
            colDodatnaOpremaModelTelefona.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("ModelTelefona"));
            colDodatnaOpremaKolicina.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Kolicina"));
            colDodatnaOpremaCijena.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Double>("Cijena"));

            tableDodatnaOprema.setItems(listaSveDodatneOpreme);
        }
    }

    public void btnDodatnaOpremaDodajHandler(ActionEvent e) {
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

    public void btnDodatnaOpremaIzmjeniHandler(ActionEvent e) {
        vrijednost = "izmjeni";
        dodatnaOpremaDTO = tableDodatnaOprema.getSelectionModel().getSelectedItem();
        if (dodatnaOpremaDTO != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeDodatneOpreme.fxml"));
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
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

    public void btnDodatnaOpremaObrisiHandler(ActionEvent e) {
        dodatnaOpremaDTO = tableDodatnaOprema.getSelectionModel().getSelectedItem();
        DodatnaOpremaDAO dodatnaOpremaDAO = (new MySQLDAOFactory()).getDodatnaOpremaDAO();
        if (dodatnaOpremaDTO != null) {
            dodatnaOpremaDAO.delete(dodatnaOpremaDTO);
        }
        tableDodatnaOprema.getItems().removeAll();

        DodatnaOpremaDAO dodatnaOprema = new MySQLDAOFactory().getDodatnaOpremaDAO();
        List<DodatnaOpremaDTO> listaDodatneOpreme = dodatnaOprema.selectAll();
        ObservableList<DodatnaOpremaDTO> listaSveDodatneOpreme = FXCollections.observableArrayList(listaDodatneOpreme);

        if (listaSveDodatneOpreme != null) {
            colDodatnaOpremaIdDodatneOpreme.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Integer>("idDodatnaOprema"));
            colDodatnaOpremaNaziv.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Naziv"));
            colDodatnaOpremaTip.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("TipOpreme"));
            colDodatnaOpremaBoja.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Boja"));
            colDodatnaOpremaModelTelefona.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("ModelTelefona"));
            colDodatnaOpremaKolicina.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, String>("Kolicina"));
            colDodatnaOpremaCijena.setCellValueFactory(new PropertyValueFactory<DodatnaOpremaDTO, Double>("Cijena"));

            tableDodatnaOprema.setItems(listaSveDodatneOpreme);
        }
    }

    public void btnDodatnaOpremaPrikaziRacunHandler(ActionEvent e) {
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

    public void btnRezervniDioPrikaziRacunHandler(ActionEvent e) {
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

    public void btnTelefonPrikaziRacunHandler(ActionEvent e) {
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

    public void btnTelefonDodajHandler(ActionEvent e) {
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

    public void btnTelefonIzmjeniHandler(ActionEvent e) {
        vrijednost = "izmjeni";
        telefonDTO = tableTelefoni.getSelectionModel().getSelectedItem();
        if (telefonDTO != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeTelefona.fxml"));
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
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

    public void btnTelefonObrisiHandler(ActionEvent e) {
        telefonDTO = tableTelefoni.getSelectionModel().getSelectedItem();
        TelefonDAO telefonDAO = (new MySQLDAOFactory()).getTelefonDAO();
        if (telefonDTO != null) {
            telefonDAO.delete(telefonDTO);
        }
        tableTelefoni.getItems().removeAll();

        TelefonDAO telefoni = new MySQLDAOFactory().getTelefonDAO();
        List<TelefonDTO> listaTelefona = telefoni.selectAll();
        ObservableList<TelefonDTO> listaSvihTelefona = FXCollections.observableArrayList(listaTelefona);

        if (listaSvihTelefona != null) {
            colTelefonIdTelefona.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Integer>("IdModelTelefona"));
            colTelefonNaziv.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Naziv"));
            colTelefonModel.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Model"));
            colTelefonProizvodjac.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Proizvodjac"));
            colTelefonBoja.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Boja"));
            colTelefonSpecifikacija.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("Specifikacija"));
            colTelefonSerijskiBroj.setCellValueFactory(new PropertyValueFactory<TelefonDTO, String>("SerijskiBroj"));
            colTelefonCijena.setCellValueFactory(new PropertyValueFactory<TelefonDTO, Double>("Cijena"));
            tableTelefoni.setItems(listaSvihTelefona);
        }
    }

    public void btnRezervniDioDodajHandler(ActionEvent e) {
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

    public void btnRezervniDioIzmjeniHandler(ActionEvent e) {
        vrijednost = "izmjeni";
        rezervniDioDTO = tableRezervniDijelovi.getSelectionModel().getSelectedItem();
        if (rezervniDioDTO != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeRezervnogDijela.fxml"));
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
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

    public void btnRezervniDioObrisiHandler(ActionEvent e) {
        rezervniDioDTO = tableRezervniDijelovi.getSelectionModel().getSelectedItem();
        RezervniDioDAO rezervniDioDAO = (new MySQLDAOFactory()).getRezervniDioDAO();
        if (rezervniDioDTO != null) {
            rezervniDioDAO.delete(rezervniDioDTO);
        }
        tableRezervniDijelovi.getItems().removeAll();

        RezervniDioDAO rezervniDio = new MySQLDAOFactory().getRezervniDioDAO();
        List<RezervniDioDTO> lista = rezervniDio.selectAllDetailed();
        ObservableList<RezervniDioDTO> listaRezervnihDijelova = FXCollections.observableArrayList(lista);

        if (listaRezervnihDijelova != null) {
            colIdRezervniDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdRezervniDio"));
            colIdModelTelefona.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("IdModelTelefona"));
            colOpis.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("Opis"));
            colNaziv.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("nazivRezervnogdijela"));
            colKolicina.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("kolicinaRezervnogdijela"));
            colCijena.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Double>("Cijena"));
            tableRezervniDijelovi.setItems(listaRezervnihDijelova);
        }
    }

    //    -------------------------------------------------------------------------------------------
//     --------------------------------RAD SA RACUNIMAAAA-----------------------------------------------------------
//     -------------------------------------------------------------------------------------------
    public void btnStampaj(ActionEvent e) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Štampanje maloprodajnog računa");
        alert.setHeaderText("Ukupna cijena: " + tfUkupno.getText() + "KM");
        alert.setContentText("Da li ste sigurni?");
        ButtonType button1 = new ButtonType("Da");
        ButtonType button2 = new ButtonType("Ne", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(button1, button2);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == button1) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (racunDao.insert(new RacunDTO(0, timestamp, Double.parseDouble(tfUkupno.getText()), idZaposlenog))) {
                int idNovogRacuna = racunDao.getId();

                 racun.stream().map((s) -> {
                    s.setIdRacuna(idNovogRacuna);
                    return s;
                }).forEachOrdered((s) -> {
                    racunArtikal.insert(new RacunHasArtikalDTO(idNovogRacuna, s.getIdArtikla(), s.getKolicina()));
                });
                try {
                    Double ukupnaCijena = Double.parseDouble(tfUkupno.getText());
                    Double pdv = ukupnaCijena * 0.17;
                    pdv = Math.round(pdv * 100) / 100.0d;
                    ukupnaCijena = Math.round(ukupnaCijena * 100) / 100.0d;
                    generator.racun(racun, ukupnaCijena, pdv);
                } catch (JRException ex) {
                    Logger.getLogger(ManipulacijaArtiklimaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                racun.clear();
                this.popuniTabeluRacun();
            } else {
                System.out.println("Greska prilikom kreiranja racuna");
            }
        } else {

        }
    }

    public void btnObrisiStavku(ActionEvent e) {
        StavkaRacuna stavka = tableRacun.getSelectionModel().getSelectedItem();
        if (stavka != null) {
            racun.remove(stavka);
            this.popuniTabeluRacun();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste izabrali artikal!");
            alert.showAndWait();

        }

    }

    public void btnOtkazi(ActionEvent e) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Brisanje tekućeg računa");
        alert.setHeaderText("Svi odabrani artikli biće obrisani sa tekućeg računa.");
        alert.setContentText("Da li ste sigurni?");
        ButtonType button1 = new ButtonType("Da");
        ButtonType button2 = new ButtonType("Ne", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(button1, button2);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == button1) {
            racun.clear();
            this.popuniTabeluRacun();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void btnTelefonNaRacun(ActionEvent e) {
        TelefonDTO telefon = tableTelefoni.getSelectionModel().getSelectedItem();
        if (telefon != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Potvrda o dodavanju na račun");
            alert.setHeaderText("Telefon: " + telefon.getModel());
            alert.setContentText("Dodati artikal na račun?");
            ButtonType button1 = new ButtonType("OK");
            ButtonType button2 = new ButtonType("Otkaži", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(button1, button2);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == button1) {
                StavkaRacuna stavkaTmp = new StavkaRacuna(telefon.getIdModelTelefona(), telefon.getNaziv(), telefon.getCijena(), 1, true, telefon.getSerijskiBroj());
                if (racun.contains(stavkaTmp)) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Greška!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Odabrani artikal je na računu!");
                    alert1.showAndWait();
                } else {
                    racun.add(stavkaTmp);
                    this.popuniTabeluRacun();
                }

            } else {
                // ... user chose CANCEL or closed the dialog
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste izabrali artikal!");
            alert.showAndWait();

        }
    }

    public void btnRezervniDioNaRacun(ActionEvent e) {
        RezervniDioDTO dio = tableRezervniDijelovi.getSelectionModel().getSelectedItem();

        if (dio != null) {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Potvrda o dodavanju  na račun");
            dialog.setHeaderText("Rezervni dio: " + dio.getNazivRezervnogdijela());
            dialog.setContentText("Unesite količinu(max " + dio.getKolicina() + " ):");
            ((Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Otkaži");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                try {
                    int unesenaKolicina = Integer.parseInt(result.get());
                    if (unesenaKolicina > dio.getKolicina()) {
                        throw new Exception();
                    } else {
                        StavkaRacuna stavkaTmp = new StavkaRacuna(dio.getIdRezervniDio(), dio.getNazivRezervnogdijela(), dio.getCijena(), unesenaKolicina, false, null);
                        if (racun.contains(stavkaTmp)) {
                            racun.remove(stavkaTmp);
                            racun.add(stavkaTmp);
                            tableRacun.refresh();
                            this.popuniTabeluRacun();
                        } else {
                            racun.add(stavkaTmp);
                            this.popuniTabeluRacun();
                        }

                    }
                } catch (Exception e1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška!");
                    alert.setHeaderText(null);
                    alert.setContentText("Unesena vrijednost nije ispravna!");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste izabrali artikal!");
            alert.showAndWait();
        }
    }

    public void btnOpremuNaRacun(ActionEvent e) {
        DodatnaOpremaDTO oprema = tableDodatnaOprema.getSelectionModel().getSelectedItem();
        if (oprema != null) {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Potvrda o dodavanju  na račun");
            dialog.setHeaderText("Dodatna oprema: " + oprema.getNaziv());
            dialog.setContentText("Unesite količinu(max " + oprema.getKolicina() + " ):");
            ((Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Otkaži");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    int unesenaKolicina = Integer.parseInt(result.get());
                    if (unesenaKolicina > oprema.getKolicina()) {
                        throw new Exception();
                    } else {
                        StavkaRacuna stavkaTmp = new StavkaRacuna(oprema.getIdDodatnaOprema(), oprema.getNaziv(), oprema.getCijena(), unesenaKolicina, false, null);
                        if (racun.contains(stavkaTmp)) {
                            racun.remove(stavkaTmp);
                            racun.add(stavkaTmp);
                            tableRacun.refresh();
                            this.popuniTabeluRacun();
                        } else {
                            racun.add(stavkaTmp);
                            this.popuniTabeluRacun();
                        }

                    }
                } catch (Exception e1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška!");
                    alert.setHeaderText(null);
                    alert.setContentText("Unesena vrijednost nije ispravna!");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste izabrali artikal!");
            alert.showAndWait();
        }
    }

    private void popuniTabeluRacun() {
        List<StavkaRacuna> lista = racun;
        ObservableList<StavkaRacuna> listaStavki = FXCollections.observableArrayList(lista);
        if (listaStavki != null) {
            colIdStavke.setCellValueFactory(new PropertyValueFactory<StavkaRacuna, Integer>("idArtikla"));
            colNazivStavke.setCellValueFactory(new PropertyValueFactory<StavkaRacuna, String>("naziv"));
            colKolicinaStavke.setCellValueFactory(new PropertyValueFactory<StavkaRacuna, Integer>("kolicina"));
            colCijenaStavke.setCellValueFactory(new PropertyValueFactory<StavkaRacuna, Double>("cijena"));

            tableRacun.setItems(listaStavki);
        }

        double ukupnaCijena = 0;
        ukupnaCijena = racun.stream().map((s) -> s.getCijena() * s.getKolicina()).reduce(ukupnaCijena, (accumulator, _item) -> accumulator + _item);

        tfUkupno.setText(String.valueOf(ukupnaCijena));
    }

}
