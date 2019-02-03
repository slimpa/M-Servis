/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.CjenovnikUslugaDAO;
import dao.RezervniDioDAO;
import dao.StanjeTelefonaDAO;
import dto.CjenovnikUslugaDTO;
import dto.RezervniDioDTO;
import dto.StanjeTelefonaDTO;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mySQL.MySQLDAOFactory;
import service.StavkaRacuna;

/**
 *
 * @author Bojan
 */
public class IzmjenaServisController implements Initializable {

    @FXML
    TableView<RezervniDioDTO> tableDijelovi;

    @FXML
    TableView<CjenovnikUslugaDTO> tableUsluge;

    @FXML
    TableView<StavkaRacuna> tableStavke;

    @FXML
    TableColumn columnIdUsluga;

    @FXML
    TableColumn columnUsluga;

    @FXML
    TableColumn columnIdDio;

    @FXML
    TableColumn columnDio;

    @FXML
    TableColumn columnIdStavka;

    @FXML
    TableColumn columnNazivStavka;

    @FXML
    Button btnDodajDio;

    @FXML
    Button btnDodajUslugu;

    @FXML
    Button btnObrisiStavku;

    @FXML
    Button btnPotvrdi;

    @FXML
    ComboBox cbNovoStanje;

    private RezervniDioDAO rezervniDioDao = new MySQLDAOFactory().getRezervniDioDAO();
    private CjenovnikUslugaDAO uslugaDao = new MySQLDAOFactory().getCjenovnikUslugaDAO();
    private StanjeTelefonaDAO stanjeDao = new MySQLDAOFactory().getStanjeTelefonaDAO();
    private int idModelTelefona;
    private int idServisa;
    private ArrayList<StavkaRacuna> stavkeServisa = new ArrayList<StavkaRacuna>();

    public int getIdModelTelefona() {
        return idModelTelefona;
    }

    public void setIdModelTelefona(int id) {
        idModelTelefona = id;
    }

    public void setIdServisa(int idServisa) {
        this.idServisa = idServisa;
    }

    public int getIdServisa() {
        return idServisa;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.idServisa = ServisController.getIdServisa();
        this.idModelTelefona = ServisController.getIdModelTelefona();
        this.popuniTabeluDijelova();
        this.popuniTabeluUsluga();
        ArrayList<StanjeTelefonaDTO> stanja = (ArrayList<StanjeTelefonaDTO>) stanjeDao.selectAll();
        for (StanjeTelefonaDTO stanje : stanja) {
            cbNovoStanje.getItems().add(stanje.getStanje());
        }

    }

    public void popuniTabeluDijelova() {
        List<RezervniDioDTO> lista = rezervniDioDao.selectByModel(new RezervniDioDTO(idModelTelefona));
        ObservableList<RezervniDioDTO> listaDijelova = FXCollections.observableArrayList(lista);
        if (listaDijelova != null) {
            columnIdDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("idRezervniDio"));
            columnDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("Naziv"));

            tableDijelovi.setItems(listaDijelova);
        }
    }

    public void popuniTabeluUsluga() {
        List<CjenovnikUslugaDTO> lista = uslugaDao.selectAll();
        ObservableList<CjenovnikUslugaDTO> listaUsluga = FXCollections.observableArrayList(lista);
        if (listaUsluga != null) {
            columnIdUsluga.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Integer>("idCjenovnikUsluga"));
            columnUsluga.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, String>("naziv"));

            tableUsluge.setItems(listaUsluga);
        }
    }

    public void popuniTabeluStavkiServisa() {
        ObservableList<StavkaRacuna> listaStavki = FXCollections.observableArrayList(stavkeServisa);
        if (stavkeServisa.size() > 0) {
            columnIdStavka.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Integer>("idStavke"));
            columnNazivStavka.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, String>("nazivStavke"));

            tableStavke.setItems(listaStavki);
        }
    }

    public void btnDodajDio(ActionEvent e) {
        RezervniDioDTO dio = tableDijelovi.getSelectionModel().getSelectedItem();

        if (dio != null) {
            StavkaRacuna novaStavka = new StavkaRacuna(dio.getIdArtikal(), dio.getNaziv());
            if (stavkeServisa.contains(novaStavka)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Rezervni dio je već odabran!");
                alert.showAndWait();
            } else {
                stavkeServisa.add(novaStavka);
                this.popuniTabeluStavkiServisa();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabran rezervni dio!");
            alert.showAndWait();
        }
    }

    public void btnDodajUslugu(ActionEvent e) {
        CjenovnikUslugaDTO usluga = tableUsluge.getSelectionModel().getSelectedItem();

        if (usluga != null) {
            StavkaRacuna novaStavka = new StavkaRacuna(usluga.getIdCjenovnikUsluga(), usluga.getNaziv());
            if (stavkeServisa.contains(novaStavka)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Usluga je već odabrana!");
                alert.showAndWait();
            } else {
                stavkeServisa.add(novaStavka);
                this.popuniTabeluStavkiServisa();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabrana usluga!");
            alert.showAndWait();
        }

    }

    public void btnObrisi(ActionEvent e) {
        StavkaRacuna stavka = tableStavke.getSelectionModel().getSelectedItem();
        if (stavka != null) {
            stavkeServisa.remove(stavka);
            this.popuniTabeluStavkiServisa();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabrana stavka!");
            alert.showAndWait();
        }
    }

    public void btnPotvrdi(ActionEvent e) {
//MISLIM DA BI OVAKO TREBALO, A MOZDA I NE.
//        Ako se izabere stanje Nemoguce popraviti onda samo treba uraditi update stanja i ignorisati izabrane stavke.
//            
//        Ako se izabere Popravljen, onda stavke treba insertovati u tabele UgradjeniRezervniDio i CjenovnikUsluga_has_Servis
//        i treba postaviti stanje na Popravljen. Za telefone kojima je stanje popravljen treba zabraniti da se vise ulazi
//        u formu za servisiranje jer se nema sta servisirati.

//       Ako se izabere Na servisu , znaci treba omoguciti da se opet udje u ovu formu ali i prethodno ugradjene dijelove i
//         usluge treba insertovati u bazu, ali ih i opet ucitati kad se izabere Servisiranje.
                                    
        
    }
}
