/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.CjenovnikUslugaDAO;
import dao.RezervniDioDAO;
import dao.ServisTelefonaDAO;
import dao.ServisTelefonaHasCjenovnikUslugaDAO;
import dao.StanjeTelefonaDAO;
import dao.UgradjeniRezervniDioDAO;
import dto.CjenovnikUslugaDTO;
import dto.RezervniDioDTO;
import dto.ServisTelefonaDTO;
import dto.ServisTelefonaHasCjenovnikUslugaDTO;
import dto.StanjeTelefonaDTO;
import dto.UgradjeniRezervniDioDTO;
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
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;
import mySQL.MySQLServisTelefonaDAO;
import service.StavkaServisa;

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
    TableView<StavkaServisa> tableStavke;

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
    private ArrayList<StavkaServisa> stavkeServisa = new ArrayList<StavkaServisa>();
    private ServisTelefonaDAO servisDao = new MySQLDAOFactory().getServisTelefonaDAO();
    private ArrayList<StanjeTelefonaDTO> stanja;
    private UgradjeniRezervniDioDAO ugradjeniDioDao = new MySQLDAOFactory().getUgradjeniRezervniDioDAO();
    private ServisTelefonaHasCjenovnikUslugaDAO cjenovnikDao = new MySQLDAOFactory().getServisTelefonaHasCjenovnikUslugaDAO();
    private boolean naServisu = false;

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
        this.naServisu = ServisController.isNaServisu();
        this.idServisa = ServisController.getIdServisa();
        this.idModelTelefona = ServisController.getIdModelTelefona();
        this.popuniTabeluDijelova();
        this.popuniTabeluUsluga();
        stanja = (ArrayList<StanjeTelefonaDTO>) stanjeDao.selectAll();
        for (StanjeTelefonaDTO stanje : stanja) {
            cbNovoStanje.getItems().add(stanje.getStanje());
        }
        if (naServisu) {
            this.popuniNaServisu();
        }
    }

    private void popuniNaServisu() {
        btnObrisiStavku.setVisible(naServisu);
        if (naServisu) {

            List<UgradjeniRezervniDioDTO> dijelovi = ugradjeniDioDao.selectBy(new UgradjeniRezervniDioDTO(idServisa));
            List<ServisTelefonaHasCjenovnikUslugaDTO> usluge = cjenovnikDao.selectBy(new ServisTelefonaHasCjenovnikUslugaDTO(idServisa));
            for (UgradjeniRezervniDioDTO dio : dijelovi) {
                StavkaServisa stavka = new StavkaServisa(dio.getIdRezervniDio(), dio.getNazivDijela());
                stavka.setIdServisa(idServisa);
                stavkeServisa.add(stavka);
            }

            for (ServisTelefonaHasCjenovnikUslugaDTO usluga : usluge) {
                StavkaServisa stavka = new StavkaServisa(usluga.getIdCjenovnikUsluga(), usluga.getNazivUsluge());
                stavka.setIdServisa(idServisa);
                stavkeServisa.add(stavka);
            }

            ObservableList<StavkaServisa> listaStavki = FXCollections.observableArrayList(stavkeServisa);
            if (stavkeServisa.size() >= 0) {
                columnIdStavka.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Integer>("idStavke"));
                columnNazivStavka.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, String>("nazivStavke"));

                tableStavke.setItems(listaStavki);
            }
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
        ObservableList<StavkaServisa> listaStavki = FXCollections.observableArrayList(stavkeServisa);
        if (stavkeServisa.size() >= 0) {
            columnIdStavka.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Integer>("idStavke"));
            columnNazivStavka.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, String>("nazivStavke"));

            tableStavke.setItems(listaStavki);
        }
    }

    public void btnDodajDio(ActionEvent e) {
        RezervniDioDTO dio = tableDijelovi.getSelectionModel().getSelectedItem();

        if (dio != null) {
            StavkaServisa novaStavka = new StavkaServisa(dio.getIdRezervniDio(), dio.getNaziv());
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
            StavkaServisa novaStavka = new StavkaServisa(usluga.getIdCjenovnikUsluga(), usluga.getNaziv());
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
        StavkaServisa stavka = tableStavke.getSelectionModel().getSelectedItem();
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
        String stanje = (String) cbNovoStanje.getSelectionModel().getSelectedItem();
        if (stanje != null) {

            int idStanja = 0;
            for (StanjeTelefonaDTO s : stanja) {
                if (s.getStanje().equals(stanje)) {
                    idStanja = s.getIdStanjeTelefona();
                }

            }

            if ("Nemoguce popraviti".equals(stanje)) {

                if (servisDao.updateStanje(new ServisTelefonaDTO(idServisa, idStanja))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspješno servisiranje!");
                    alert.showAndWait();

                    Stage stage = (Stage) btnPotvrdi.getScene().getWindow();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška!");
                    alert.setHeaderText(null);
                    alert.setContentText("Neuspješno servisiranje!");
                    alert.showAndWait();

                    Stage stage = (Stage) btnPotvrdi.getScene().getWindow();
                    stage.close();
                }
            } else {
                if (servisDao.updateStanje(new ServisTelefonaDTO(idServisa, idStanja))) {

                    for (StavkaServisa stavka : stavkeServisa) {
                        if (stavka.getIdStavke() > 5000) {

                            ugradjeniDioDao.insert(new UgradjeniRezervniDioDTO(stavka.getIdStavke(), idServisa));
                        } else {

                            cjenovnikDao.insert(new ServisTelefonaHasCjenovnikUslugaDTO(idServisa, stavka.getIdStavke()));
                        }
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspješno servisiranje!");
                    alert.showAndWait();

                    Stage stage = (Stage) btnPotvrdi.getScene().getWindow();
                    stage.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška!");
                    alert.setHeaderText(null);
                    alert.setContentText("Neuspješno servisiranje!");
                    alert.showAndWait();

                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabrano stanje telefona!");
            alert.showAndWait();
        }
    }

    public boolean isNaServisu() {
        return naServisu;
    }

    public void setNaServisu(boolean naServisu) {
        this.naServisu = naServisu;
    }

}
