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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    private ProizvodjacDAO proizvodjac = new MySQLDAOFactory().getProizvodjacDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       popuniTabelu();
    }
    
    public void btnPretraziProizvodjacaHandler(ActionEvent e){
      String naziv = tfProizvodjac.getText();
      List<ProizvodjacDTO> lista = proizvodjac.selectByName(naziv);
        ObservableList<ProizvodjacDTO> listaProizvodjaca = FXCollections.observableArrayList(lista);
        if(listaProizvodjaca != null){
            colIdProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, Integer>("IdProizvodjac"));
            colNazivProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, String>("Naziv"));
            
            tableProizvodjac.setItems(listaProizvodjaca);
        }
    }
    
     public void btnDodajProizvodjacaHandler(ActionEvent e){
         String naziv = tfProizvodjac.getText();
         
         if(proizvodjac.insert(new ProizvodjacDTO(naziv))){
             System.out.println("Dodatno");
         }
         else{
             //ex.printStackTrace();
              System.out.println("Greska");
         }
         popuniTabelu();
     }
     public void btnObrisiProizvodjacaHandler(ActionEvent e){
          ProizvodjacDTO pr=tableProizvodjac.getSelectionModel().getSelectedItem();
          if(proizvodjac.delete(pr)){
            System.out.println("Obrisano");
         }
         else{
             System.out.println("Greska");
         }
          popuniTabelu();
      }
     public void btnIzmijeniProizvodjacaHandler(ActionEvent e){
         String naziv = tfProizvodjac.getText();
         ProizvodjacDTO stari=tableProizvodjac.getSelectionModel().getSelectedItem();
         ProizvodjacDTO novi=new ProizvodjacDTO(stari.getIdProizvodjac(),naziv);
          if(proizvodjac.update(novi)){
            System.out.println("Izmijenjeno");
         }
         else{
             System.out.println("Greska");
         }
          popuniTabelu();
     }
     
     private void popuniTabelu(){
          List<ProizvodjacDTO> lista = proizvodjac.selectAll();
        ObservableList<ProizvodjacDTO> listaProizvodjaca = FXCollections.observableArrayList(lista);
        if(listaProizvodjaca != null){
            colIdProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, Integer>("IdProizvodjac"));
            colNazivProizvodjaca.setCellValueFactory(new PropertyValueFactory<ProizvodjacDTO, String>("Naziv"));
            
            tableProizvodjac.setItems(listaProizvodjaca);
     }
}
}
