/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.CjenovnikUslugaDAO;
import dao.RezervniDioDAO;
import dto.CjenovnikUslugaDTO;
import dto.RezervniDioDTO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mySQL.MySQLDAOFactory;
import service.StavkaRacuna;

/**
 *
 * @author Bojan
 */
public class IzmjenaServisController implements Initializable{
    
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
    
    private RezervniDioDAO rezervniDioDao = new MySQLDAOFactory().getRezervniDioDAO();
    private CjenovnikUslugaDAO uslugaDao = new MySQLDAOFactory().getCjenovnikUslugaDAO();
    private static int idModelTelefona;

    public int getIdModelTelefona() {
        return idModelTelefona;
    }

    public static void setIdModelTelefona(int id) {
       idModelTelefona = id;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.popuniTabeluDijelova();
        this.popuniTabeluUsluga();
    }
    
    public void popuniTabeluDijelova(){
        List<RezervniDioDTO> lista = rezervniDioDao.selectByModel(new RezervniDioDTO(idModelTelefona));
        ObservableList<RezervniDioDTO> listaDijelova = FXCollections.observableArrayList(lista);
        if (listaDijelova != null) {
            columnIdDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, Integer>("idRezervniDio"));
            columnDio.setCellValueFactory(new PropertyValueFactory<RezervniDioDTO, String>("Naziv"));

            tableDijelovi.setItems(listaDijelova);
        }
    }
    
    public void popuniTabeluUsluga(){
        List<CjenovnikUslugaDTO> lista = uslugaDao.selectAll();
        ObservableList<CjenovnikUslugaDTO> listaUsluga = FXCollections.observableArrayList(lista);
        if (listaUsluga != null) {
            columnIdUsluga.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, Integer>("idCjenovnikUsluga"));
            columnUsluga.setCellValueFactory(new PropertyValueFactory<CjenovnikUslugaDTO, String>("naziv"));

            tableUsluge.setItems(listaUsluga);
        }
    }
    
    
    
}
