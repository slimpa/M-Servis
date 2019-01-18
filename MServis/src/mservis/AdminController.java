/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.AdminDAO;
import dao.CjenovnikUslugaDAO;
import dao.ProizvodjacDAO;
import dao.StanjeTelefonaDAO;
import dao.TipDodatneOpremeDAO;
import dao.ZaposleniDAO;
import dto.AdminDTO;
import dto.CjenovnikUslugaDTO;
import dto.ProizvodjacDTO;
import dto.StanjeTelefonaDTO;
import dto.TipDodatneOpremeDTO;
import dto.ZaposleniDTO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class AdminController implements Initializable {

    @FXML
    private TabPane adminPane;

    @FXML
    private Button btnDodajZaposlenog;

    @FXML
    private Button btnIzmijeniZaposlenog;

    @FXML
    private Button btnObrisiZaposlenog;

    @FXML
    private Button btnDodajAdmina;

    @FXML
    private Button btnIzmijeniAdmina;

    @FXML
    private Button btnObrisiAdmina;

    @FXML
    private TableView<ProizvodjacDTO> tableProizvodjac;

    @FXML
    private TableColumn<ProizvodjacDTO, Integer> colIdProizvodjaca;

    @FXML
    private TableColumn<ProizvodjacDTO, String> colNazivProizvodjaca;

    @FXML
    private TableView<StanjeTelefonaDTO> tableStanja;

    @FXML
    private TableColumn<StanjeTelefonaDTO, Integer> colIdStanja;

    @FXML
    private TableColumn<StanjeTelefonaDTO, String> colStanje;

    @FXML
    private Button btnPretraziProizvodjaca;

    @FXML
    private Button btnDodajProizvodjaca;

    @FXML
    private Button btnIzmijeniProizvodjaca;

    @FXML
    private Button btnObrisiProizvodjaca;

    @FXML
    private TextField tfProizvodjac;

    @FXML
    private TextField tfStanje;

    @FXML
    private Button btnIzmijeniStanje;

    @FXML
    private Button btnDodajStanje;

    @FXML
    private Button btnObrisiStanje;

    @FXML
    private Button btnPretraziStanje;

    @FXML
    private TextField tfUsluga;

    @FXML
    private TextField tfCijena;

    @FXML
    private Button btnPretraziUslugu;

    @FXML
    private Button btnDodajUslugu;

    @FXML
    private Button btnIzmijeniUslugu;

    @FXML
    private Button btnObrisiUslugu;

    @FXML
    private TableView<TipDodatneOpremeDTO> tableDodatnaOprema;

    @FXML
    private TableColumn<TipDodatneOpremeDTO, Integer> colIdTip;

    @FXML
    private TableColumn<TipDodatneOpremeDTO, String> colTipOpreme;

    @FXML
    private TableView<CjenovnikUslugaDTO> tableUsluga;

    @FXML
    private TableColumn<CjenovnikUslugaDTO, Integer> colIdUsluge;

    @FXML
    private TableColumn<CjenovnikUslugaDTO, String> colNazivUsluge;

    @FXML
    private TableColumn<CjenovnikUslugaDTO, Double> colCijenaUsluge;

    @FXML
    private TextField tfDodatnaOprema;

    @FXML
    private Button btnDodajTip;

    @FXML
    private Button btnPretraziTip;

    @FXML
    private Button btnIzmijeniTip;

    @FXML
    private Button btnObrisiTip;
    
    @FXML
    private TableView<AdminDTO> tableAdmin;
    @FXML
    private TableColumn<AdminDTO, Integer> colIdAdmin;
    @FXML
    private TableColumn<AdminDTO, String> colImea;
    @FXML
    private TableColumn<AdminDTO, String> colPrezimea;
    @FXML
    private TableColumn<AdminDTO, String> colKorisnickoImea;
    @FXML
    private TableColumn<AdminDTO, String> colFirma;
    @FXML
    private TableColumn<AdminDTO, String> colTelefona;
    @FXML
    private TableView<ZaposleniDTO> tableZaposleni;
    @FXML
    private TableColumn<ZaposleniDTO, Integer> colIdZap;
    @FXML
    private TableColumn<ZaposleniDTO, String> colImez;
    @FXML
    private TableColumn<ZaposleniDTO, String> colPrezimez;
    @FXML
    private TableColumn<ZaposleniDTO, String> colTelefonz;
    @FXML
    private TableColumn<ZaposleniDTO, String> colKorisnickoImez;
    @FXML
    private TableColumn<ZaposleniDTO, String> colRadno;

    private ProizvodjacDAO proizvodjacDao = new MySQLDAOFactory().getProizvodjacDAO();
    private TipDodatneOpremeDAO dodatnaOpremaDao = new MySQLDAOFactory().getTipDodatneOpremeDAO();
    private StanjeTelefonaDAO stanjeTelefonaDao = new MySQLDAOFactory().getStanjeTelefonaDAO();
    private CjenovnikUslugaDAO cjenovnikUslugaDao = new MySQLDAOFactory().getCjenovnikUslugaDAO();
    private AdminDAO adminDao = new MySQLDAOFactory().getAdminDAO();
    private ZaposleniDAO zaposleniDao = new MySQLDAOFactory().getZaposleniDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popuniTabeluProizvodjaca();
        tabelaProizvodjacaClick();

        popuniTabeluOpreme();
        tabelaDodatnaOpremaClick();

        popuniTabeluStanja();
        tabelaStanjaClick();
        
        popuniTabeluUsluga();
        tabelaUslugaClick();
        
        popuniTabeluAdmina();
        popuniTabeluZaposlenih();
    }

    public void btnPretraziProizvodjacaHandler(ActionEvent e) {
        if (tfProizvodjac.getText().isEmpty()) {
            popuniTabeluProizvodjaca();
        } else {
            String naziv = tfProizvodjac.getText();
            List<ProizvodjacDTO> lista = proizvodjacDao.selectByName(naziv);

            if (lista != null) {
                ObservableList<ProizvodjacDTO> listaProizvodjaca = FXCollections.observableArrayList(lista);
                colIdProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, Integer>("IdProizvodjac"));
                colNazivProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, String>("Naziv"));

                tableProizvodjac.setItems(listaProizvodjaca);
            }
        }
    }

    public void btnDodajProizvodjacaHandler(ActionEvent e) {
        String naziv = tfProizvodjac.getText();

        if (proizvodjacDao.insert(new ProizvodjacDTO(naziv))) {
            popuniTabeluProizvodjaca();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Dodavanje nije moguće!");
            alert.showAndWait();
        }

    }

    public void btnObrisiProizvodjacaHandler(ActionEvent e) {
        ProizvodjacDTO pr = tableProizvodjac.getSelectionModel().getSelectedItem();
        if (pr != null) {
            if (proizvodjacDao.delete(pr)) {
                popuniTabeluProizvodjaca();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Brisanje nije moguće!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije izabran proizvođač iz tabele!");
            alert.showAndWait();
        }

    }

    public void btnIzmijeniProizvodjacaHandler(ActionEvent e) {
        String naziv = tfProizvodjac.getText();
        ProizvodjacDTO stari = tableProizvodjac.getSelectionModel().getSelectedItem();

        if (stari != null) {
            ProizvodjacDTO novi = new ProizvodjacDTO(stari.getIdProizvodjac(), naziv);
            if (proizvodjacDao.update(novi)) {
                popuniTabeluProizvodjaca();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Izmjena nije moguća!");
                alert.showAndWait();
            }
        } else if (stari == null || naziv.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Potrebno je izabrati proizvođača iz tabele i unijeti novi naziv!");
            alert.showAndWait();
        }

    }

    private void popuniTabeluProizvodjaca() {
        List<ProizvodjacDTO> lista = proizvodjacDao.selectAll();
        ObservableList<ProizvodjacDTO> listaProizvodjaca = FXCollections.observableArrayList(lista);
        if (listaProizvodjaca != null) {
            colIdProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, Integer>("IdProizvodjac"));
            colNazivProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, String>("Naziv"));

            tableProizvodjac.setItems(listaProizvodjaca);
        }
    }

    private TableRow<ProizvodjacDTO> tabelaProizvodjacaClick() {
        tableProizvodjac.setRowFactory(tv -> {
            TableRow<ProizvodjacDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    String rowData = row.getItem().getNaziv();
                    tfProizvodjac.setText(rowData);
                }
            });
            return row;
        });
        return null;
    }

    public void btnPretraziOpremuHandler(ActionEvent e) {
        if (tfDodatnaOprema.getText().isEmpty()) {
            popuniTabeluOpreme();
        } else {
            String naziv = tfDodatnaOprema.getText();
            List<TipDodatneOpremeDTO> lista = dodatnaOpremaDao.selectBy(new TipDodatneOpremeDTO(naziv));

            if (lista != null) {
                ObservableList<TipDodatneOpremeDTO> listaOpreme = FXCollections.observableArrayList(lista);
                colIdTip.setCellValueFactory(new PropertyValueFactory<TipDodatneOpremeDTO, Integer>("IdTipDodatneOpreme"));
                colTipOpreme.setCellValueFactory(new PropertyValueFactory<TipDodatneOpremeDTO, String>("Tip"));

                tableDodatnaOprema.setItems(listaOpreme);
            }
        }
    }

    public void btnDodajOpremuHandler(ActionEvent e) {
        String tip = tfDodatnaOprema.getText();

        if (dodatnaOpremaDao.insert(new TipDodatneOpremeDTO(tip))) {
            popuniTabeluOpreme();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Dodavanje nije moguće!");
            alert.showAndWait();
        }

    }

    public void btnObrisiOpremuHandler(ActionEvent e) {
        TipDodatneOpremeDTO tip = tableDodatnaOprema.getSelectionModel().getSelectedItem();
        if (tip != null) {
            if (dodatnaOpremaDao.delete(tip)) {
                popuniTabeluOpreme();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Nije moguće brisanje!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije izabran tip iz tabele!");
            alert.showAndWait();
        }

    }

    public void btnIzmijeniOpremuHandler(ActionEvent e) {
        String tip = tfDodatnaOprema.getText();
        TipDodatneOpremeDTO stari = tableDodatnaOprema.getSelectionModel().getSelectedItem();
        if (stari != null) {
            TipDodatneOpremeDTO novi = new TipDodatneOpremeDTO(stari.getIdTipDodatneOpreme(), tip);
            if (dodatnaOpremaDao.update(novi)) {
                popuniTabeluOpreme();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Nije moguća izmjena!");
                alert.showAndWait();
            }

        } else if (tip.isEmpty() || stari == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Potrebno izabrati tip iz tabele i unijeti novi naziv!");
            alert.showAndWait();
        }
    }

    private void popuniTabeluOpreme() {
        List<TipDodatneOpremeDTO> lista = dodatnaOpremaDao.selectAll();
        ObservableList<TipDodatneOpremeDTO> listaOpreme = FXCollections.observableArrayList(lista);
        if (listaOpreme != null) {

            colIdTip.setCellValueFactory(new PropertyValueFactory<TipDodatneOpremeDTO, Integer>("IdTipDodatneOpreme"));
            colTipOpreme.setCellValueFactory(new PropertyValueFactory<TipDodatneOpremeDTO, String>("Tip"));

            tableDodatnaOprema.setItems(listaOpreme);
        }
    }

    private TableRow<TipDodatneOpremeDTO> tabelaDodatnaOpremaClick() {
        tableDodatnaOprema.setRowFactory(tv -> {
            TableRow<TipDodatneOpremeDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    String rowData = row.getItem().getTip();
                    tfDodatnaOprema.setText(rowData);
                }
            });
            return row;
        });
        return null;
    }

    public void btnPretraziStanjaHandler(ActionEvent e) {
        if (tfStanje.getText().isEmpty()) {
            popuniTabeluStanja();
        } else {
            String stanje = tfStanje.getText();
            List<StanjeTelefonaDTO> lista = stanjeTelefonaDao.selectBy(new StanjeTelefonaDTO(stanje));

            if (lista != null) {
                ObservableList<StanjeTelefonaDTO> listaStanja = FXCollections.observableArrayList(lista);
                colIdStanja.setCellValueFactory(new PropertyValueFactory<StanjeTelefonaDTO, Integer>("IdStanjeTelefona"));
                colStanje.setCellValueFactory(new PropertyValueFactory<StanjeTelefonaDTO, String>("Stanje"));

                tableStanja.setItems(listaStanja);
            }
        }
    }

    private void popuniTabeluStanja() {
        List<StanjeTelefonaDTO> lista = stanjeTelefonaDao.selectAll();
        ObservableList<StanjeTelefonaDTO> listaStanja = FXCollections.observableArrayList(lista);
        if (listaStanja != null) {
            colIdStanja.setCellValueFactory(new PropertyValueFactory<StanjeTelefonaDTO, Integer>("IdStanjeTelefona"));
            colStanje.setCellValueFactory(new PropertyValueFactory<StanjeTelefonaDTO, String>("Stanje"));

            tableStanja.setItems(listaStanja);
        }
    }

    public void btnDodajStanjeHandler(ActionEvent e) {
        String stanje = tfStanje.getText();

        if (stanjeTelefonaDao.insert(new StanjeTelefonaDTO(stanje))) {
            popuniTabeluStanja();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Dodavanje nije moguće!");
            alert.showAndWait();
        }

    }

    public void btnObrisiStanjeHandler(ActionEvent e) {
        StanjeTelefonaDTO stanje = tableStanja.getSelectionModel().getSelectedItem();
        if (stanje != null) {
            if (stanjeTelefonaDao.delete(stanje)) {
                popuniTabeluStanja();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Nije moguće brisanje!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije izabrano stanje iz tabele!");
            alert.showAndWait();
        }

    }

    public void btnIzmijeniStanjeHandler(ActionEvent e) {
        String stanje = tfStanje.getText();
        StanjeTelefonaDTO stari = tableStanja.getSelectionModel().getSelectedItem();
        if (stari != null) {
            StanjeTelefonaDTO novi = new StanjeTelefonaDTO(stari.getIdStanjeTelefona(), stanje);
            if (stanjeTelefonaDao.update(novi)) {
                popuniTabeluStanja();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Nije moguća izmjena!");
                alert.showAndWait();
            }

        } else if (stanje.isEmpty() || stari == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Potrebno izabrati stanje iz tabele i unijeti novi naziv!");
            alert.showAndWait();
        }
    }

    private TableRow<StanjeTelefonaDTO> tabelaStanjaClick() {
        tableStanja.setRowFactory(tv -> {
            TableRow<StanjeTelefonaDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    String rowData = row.getItem().getStanje();
                    tfStanje.setText(rowData);
                }
            });
            return row;
        });
        return null;
    }

    public void btnPretraziUslugeHandler(ActionEvent e) {
        if (tfUsluga.getText().isEmpty() && tfCijena.getText().isEmpty()) {
            popuniTabeluUsluga();
        } else if (tfUsluga.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Potrebno je unijeti naziv usluge za pretragu!");
            alert.showAndWait();
        } else {
            String naziv = tfUsluga.getText();
            List<CjenovnikUslugaDTO> lista = cjenovnikUslugaDao.selectBy(new CjenovnikUslugaDTO(naziv));

            if (lista != null) {
                ObservableList<CjenovnikUslugaDTO> cjenovnik = FXCollections.observableArrayList(lista);
                colIdUsluge.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Integer>("IdCijenovnikUsluga"));
                colNazivUsluge.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, String>("Naziv"));
                colCijenaUsluge.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Double>("Cijena"));
                tableUsluga.setItems(cjenovnik);
            }
        }
    }

    private void popuniTabeluUsluga() {
        List<CjenovnikUslugaDTO> lista = cjenovnikUslugaDao.selectAll();
        ObservableList<CjenovnikUslugaDTO> cjenovnik = FXCollections.observableArrayList(lista);
        if (cjenovnik != null) {
            colIdUsluge.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Integer>("IdCijenovnikUsluga"));
            colNazivUsluge.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, String>("Naziv"));
            colCijenaUsluge.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Double>("Cijena"));
            tableUsluga.setItems(cjenovnik);
        }
    }
    
    public void btnDodajUsluguHandler(ActionEvent e) {
        String naziv = tfUsluga.getText();
        double cijena = Double.parseDouble(tfCijena.getText());

        if(!tfCijena.getText().isEmpty()){
        if (cjenovnikUslugaDao.insert(new CjenovnikUslugaDTO(naziv, cijena))) {
            popuniTabeluUsluga();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Dodavanje nije moguće!");
            alert.showAndWait();
        }
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Popuniti podatke o novoj usluzi!");
            alert.showAndWait();
        }

    }
    
    public void btnObrisiUsluguHandler(ActionEvent e){
        CjenovnikUslugaDTO usluga = tableUsluga.getSelectionModel().getSelectedItem();
        if (usluga != null) {
            if (cjenovnikUslugaDao.delete(usluga)) {
                popuniTabeluUsluga();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Nije moguće brisanje!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije izabrana usluga iz tabele!");
            alert.showAndWait();
        }

    }
    
    public void btnIzmijeniUsluguHandler(ActionEvent e){
        String naziv = tfUsluga.getText();
        Double cijena = Double.parseDouble(tfCijena.getText());
        CjenovnikUslugaDTO stari = tableUsluga.getSelectionModel().getSelectedItem();
        if (stari != null && !tfCijena.getText().isEmpty()) {
            CjenovnikUslugaDTO novi = new CjenovnikUslugaDTO(stari.getIdCjenovnikUsluga(), naziv, cijena);
            if (cjenovnikUslugaDao.update(novi)) {
                popuniTabeluUsluga();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Nije moguća izmjena!");
                alert.showAndWait();
            }
            
        } else if (naziv.isEmpty() || stari == null || tfCijena.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Potrebno izabrati uslugu iz tabele i unijeti novi naziv i cijenu!");
            alert.showAndWait();
        }
    }
    
    private TableRow<CjenovnikUslugaDTO> tabelaUslugaClick() {
        tableUsluga.setRowFactory(tv -> {
            TableRow<CjenovnikUslugaDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    String naziv = row.getItem().getNaziv();
                    double cijena = row.getItem().getCijena();
                    tfUsluga.setText(naziv);
                    tfCijena.setText(String.valueOf(cijena));
                }
            });
            return row;
        });
        return null;
    }
    
     private void popuniTabeluAdmina() {
        List<AdminDTO> lista = adminDao.selectAll();
        ObservableList<AdminDTO> listaAdmina = FXCollections.observableArrayList(lista);
        if (listaAdmina != null) {

            colIdAdmin.setCellValueFactory(new PropertyValueFactory<AdminDTO, Integer>("idOsoba"));
            colImea.setCellValueFactory(new PropertyValueFactory<AdminDTO, String>("ime"));
            colPrezimea.setCellValueFactory(new PropertyValueFactory<AdminDTO, String>("prezime"));
            colTelefona.setCellValueFactory(new PropertyValueFactory<AdminDTO, String>("brojTelefona"));
            colFirma.setCellValueFactory(new PropertyValueFactory<AdminDTO, String>("nazivFirme"));
            colKorisnickoImea.setCellValueFactory(new PropertyValueFactory<AdminDTO, String>("koriscnikoIme"));

            tableAdmin.setItems(listaAdmina);
        }
    }

    private void popuniTabeluZaposlenih() {
        List<ZaposleniDTO> lista = zaposleniDao.selectAll();
        ObservableList<ZaposleniDTO> listaZaposlenih = FXCollections.observableArrayList(lista);
        if (listaZaposlenih != null) {

            colIdZap.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, Integer>("idOsoba"));
            colImez.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("ime"));
            colPrezimez.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("prezime"));
            colTelefonz.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("brojTelefona"));
            colRadno.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("radnoMjesto"));
            colKorisnickoImez.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("koriscnikoIme"));

            tableZaposleni.setItems(listaZaposlenih);
        }
    }

    public void btnObrisiAdminaHandler(ActionEvent e) {
        AdminDTO tip = tableAdmin.getSelectionModel().getSelectedItem();
        if (adminDao.delete(tip)) {
            System.out.println("Obrisano");
        } else {
            System.out.println("Greska");
        }
        popuniTabeluAdmina();
    }

    public void btnObrisiZaposlenogHandler(ActionEvent e) {
        ZaposleniDTO tip = tableZaposleni.getSelectionModel().getSelectedItem();
        if (zaposleniDao.delete(tip)) {
            System.out.println("Obrisano");
        } else {
            System.out.println("Greska");
        }
        popuniTabeluZaposlenih();
    }

    public void btnDodajAdminaHandler(ActionEvent e) {
        Parent root;
        try {
            DodajIzmjeniAdminZaposleniController.setAdmin(true);
            Stage stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("DodajIzmjeniAdminZaposleni.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Dodaj novog admina");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void btnDodajZaposlenogHandler(ActionEvent e) {
        Parent root;
        try {
            DodajIzmjeniAdminZaposleniController.setAdmin(false);
            Stage stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("DodajIzmjeniAdminZaposleni.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Dodaj novog zaposlenog");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnIzmijeniAdminaHandler(ActionEvent e) {
        Parent root;
        try {
            DodajIzmjeniAdminZaposleniController.setAdmin(true);
            Stage stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("DodajIzmjeniAdminZaposleni.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Izmijeni admina");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnIzmijeniZaposlenogHandler(ActionEvent e) {
        Parent root;
        try {
            DodajIzmjeniAdminZaposleniController.setAdmin(false);
            Stage stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("DodajIzmjeniAdminZaposleni.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Izmijeni zaposlenog");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
