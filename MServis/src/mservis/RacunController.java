/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.RacunDAO;
import dao.RacunHasArtikalDAO;
import dao.RacunHasServisTelefonaDAO;
import dao.ZaposleniDAO;
import dto.RacunDTO;
import dto.RacunHasArtikalDTO;
import dto.RacunHasServisTelefonaDTO;
import dto.ZaposleniDTO;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;
import mySQL.MySQLRacunDAO;
import net.sf.jasperreports.engine.JRException;
import service.GeneratorIzvjestaja;
import service.StavkaServisa;

/**
 *
 * @author Bojan
 */
public class RacunController implements Initializable {

    @FXML
    private Button btnStampaj;

    @FXML
    private Button btnIzadji;

    @FXML
    private TableView<StavkaServisa> tableRacun;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colNaziv;

    @FXML
    private TableColumn colCijena;

    private List<StavkaServisa> stavke = new ArrayList<>();

    @FXML
    private TextField tfUkupnaCijena;

    private double pdv = 0;
    private double ukupnaCijena = 0;
    private RacunDAO racunDao = new MySQLDAOFactory().getRacunDAO();
    private String zaposleni;
    private int idZaposlenog;
    private ZaposleniDAO zaposleniDao = new MySQLDAOFactory().getZaposleniDAO();
    private RacunHasServisTelefonaDAO racunServis = new MySQLDAOFactory().getRacunHasServisTelefonaDAO();
    private RacunHasArtikalDAO racunArtikal = new MySQLDAOFactory().getRacunHasArtikalDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zaposleni = LoginController.getKorisnickoIme();
        idZaposlenog = zaposleniDao.selectZaposleni(new ZaposleniDTO(zaposleni)).getIdOsoba();

    }

    public void popuniTabelu(List<StavkaServisa> listaStavki) {
        this.stavke = listaStavki;

        ObservableList<StavkaServisa> lista = FXCollections.observableArrayList(stavke);
        this.racunajCijenu(lista);
        if (lista != null) {

            colId.setCellValueFactory(new PropertyValueFactory<StavkaServisa, Integer>("idStavke"));
            colNaziv.setCellValueFactory(new PropertyValueFactory<StavkaServisa, String>("nazivStavke"));
            colCijena.setCellValueFactory(new PropertyValueFactory<StavkaServisa, Double>("cijena"));

            tableRacun.setItems(lista);
        }
    }

    private void racunajCijenu(ObservableList<StavkaServisa> lista) {

        for (StavkaServisa stavka : lista) {
            this.ukupnaCijena += stavka.getCijena();
        }

        this.pdv = this.ukupnaCijena * 0.17;
        this.pdv = Math.round(pdv * 100) / 100.0d;
        this.ukupnaCijena = Math.round(ukupnaCijena * 100) / 100.0d;

        tfUkupnaCijena.setText(String.valueOf(ukupnaCijena));
    }

    public List<StavkaServisa> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaServisa> stavke) {
        this.stavke = stavke;
    }

    public void btnIzadjiHandler(ActionEvent e) {
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }

    public void btnStampajHandler(ActionEvent e) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (racunDao.insert(new RacunDTO(0, timestamp, Double.parseDouble(tfUkupnaCijena.getText()), idZaposlenog))) {
            int idNovogRacuna = racunDao.getId();

            if (stavke.size() > 0) {
                for (StavkaServisa s : stavke) {
                    s.setIdRacuna(idNovogRacuna);
                    System.out.println(s.getIdRacuna());
                    
                    if(s.getIdStavke() > 5000){
                        racunArtikal.insert(new RacunHasArtikalDTO(idNovogRacuna, s.getIdStavke(), 1));
                    }
                }

                
                 
                racunServis.insert(new RacunHasServisTelefonaDTO(idNovogRacuna, stavke.get(0).getIdServisa()));

                GeneratorIzvjestaja gen = new GeneratorIzvjestaja();
                try {
                    gen.racunServisa((ArrayList<StavkaServisa>) stavke, Double.parseDouble(tfUkupnaCijena.getText()), pdv);
                } catch (JRException ex) {
                    Logger.getLogger(RacunController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Greška pri štampanju računa!");

            alert.showAndWait();
        }
    }

}
