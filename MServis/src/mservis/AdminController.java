/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ProizvodjacDAO;
import dto.ProizvodjacDTO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mySQL.MySQLDAOFactory;
import mySQL.MySQLProizvodjacDAO;

/**
 *
 * @author Bojan
 */
public class AdminController implements Initializable{
    @FXML
    private TabPane adminPane;
    
    @FXML
    private TableView tableZaposleni;
    
    @FXML
    private Button btnDodajZaposlenog;
    
    @FXML
    private Button btnIzmijeniZaposlenog;
    
    @FXML
    private Button btnObrisiZaposlenog;
    
    @FXML
    private TableView tableAdmin;
    
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
    private TableView tableStanja;
    
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
    private TableView tableUsluga;
    
    @FXML
    private Button btnIzmijeniUslugu;
    
    @FXML
    private Button btnObrisiUslugu;
    
    @FXML
    private TableView tableDodatnaOprema;
    
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProizvodjacDAO proizvodjac = new MySQLDAOFactory().getProizvodjacDAO();
        List<ProizvodjacDTO> lista = proizvodjac.selectAll();
        
        if(lista != null){
            colIdProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, Integer>("id"));
            colNazivProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, String>("naziv"));
            
            tableProizvodjac.getItems().setAll(lista);
        }
    }
    
   
}
