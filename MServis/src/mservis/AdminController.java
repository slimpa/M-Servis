/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ProizvodjacDAO;
import dao.TipDodatneOpremeDAO;
import dto.DodatnaOpremaDTO;
import dto.ProizvodjacDTO;
import dto.TipDodatneOpremeDTO;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class AdminController implements Initializable {

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
    private TableView<TipDodatneOpremeDTO> tableDodatnaOprema;
    
    @FXML
    private TableColumn<TipDodatneOpremeDTO, Integer> colIdTip;

    @FXML
    private TableColumn<TipDodatneOpremeDTO, String> colTipOpreme;
    
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

    private ProizvodjacDAO proizvodjacDao = new MySQLDAOFactory().getProizvodjacDAO();
     private TipDodatneOpremeDAO dodatnaOpremaDao = new MySQLDAOFactory().getTipDodatneOpremeDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popuniTabeluProizvodjaca();
        tabelaProizvodjacaClick();
        popuniTabeluOpreme();
        tabelaDodatnaOpremaClick();
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
            System.out.println("Dodato");
        } else {
            //ex.printStackTrace();
            System.out.println("Greska");
        }
        popuniTabeluProizvodjaca();
        
    }

    public void btnObrisiProizvodjacaHandler(ActionEvent e) {
        ProizvodjacDTO pr = tableProizvodjac.getSelectionModel().getSelectedItem();
        if (proizvodjacDao.delete(pr)) {
            System.out.println("Obrisano");
        } else {
            System.out.println("Greska");
        }
        popuniTabeluProizvodjaca();
    }

    public void btnIzmijeniProizvodjacaHandler(ActionEvent e) {
        String naziv = tfProizvodjac.getText();
        ProizvodjacDTO stari = tableProizvodjac.getSelectionModel().getSelectedItem();
        ProizvodjacDTO novi = new ProizvodjacDTO(stari.getIdProizvodjac(), naziv);
        if (proizvodjacDao.update(novi)) {
            System.out.println("Izmijenjeno");
        } else {
            System.out.println("Greska");
        }
        popuniTabeluProizvodjaca();
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
            System.out.println("Dodatno");
        } else {
            //ex.printStackTrace();
            System.out.println("Greska");
        }
        popuniTabeluOpreme();
    }

    public void btnObrisiOpremuHandler(ActionEvent e) {
        TipDodatneOpremeDTO tip = tableDodatnaOprema.getSelectionModel().getSelectedItem();
        if (dodatnaOpremaDao.delete(tip)) {
            System.out.println("Obrisano");
        } else {
            System.out.println("Greska");
        }
        popuniTabeluOpreme();
    }

    public void btnIzmijeniOpremuHandler(ActionEvent e) {
        String tip = tfDodatnaOprema.getText();
        TipDodatneOpremeDTO stari = tableDodatnaOprema.getSelectionModel().getSelectedItem();
        TipDodatneOpremeDTO novi = new TipDodatneOpremeDTO(stari.getIdTipDodatneOpreme(), tip);
        if (dodatnaOpremaDao.update(novi)) {
            System.out.println("Izmijenjeno");
        } else {
            System.out.println("Greska");
        }
        popuniTabeluOpreme();
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
}
