/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.RacunDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;
import mySQL.MySQLRacunDAO;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        this. pdv = Math.round(pdv * 100) / 100.0d;
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
    
    public void btnStampajHandler(ActionEvent e){
        
    }

}
