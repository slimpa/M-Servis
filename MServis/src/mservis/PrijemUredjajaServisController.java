/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.KlijentDAO;
import dao.ModelTelefonaDAO;
import dao.ServisTelefonaDAO;
import dao.StanjeTelefonaDAO;
import dao.ZaposleniDAO;
import dto.KlijentDTO;
import dto.ModelTelefonaDTO;
import dto.ServisTelefonaDTO;
import dto.StanjeTelefonaDTO;
import dto.ZaposleniDTO;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mySQL.MySQLDAOFactory;


public class PrijemUredjajaServisController implements Initializable {

    @FXML
    private TextField tfIme;
    @FXML
    private TextField tfPrezime;
    @FXML
    private TextField tfBroj;
    @FXML
    private TextField tfSerijski;
    @FXML
    private TextArea taOpis;
    @FXML
    private ComboBox cbModel;
    @FXML
    private Button btnDodaj;

    private ModelTelefonaDAO modelDao = new MySQLDAOFactory().getModelTelefonaDAO();
    private ArrayList<ModelTelefonaDTO> modeli;
    private String zaposleni = LoginController.getKorisnickoIme();
    private ZaposleniDAO zaposleniDao = new MySQLDAOFactory().getZaposleniDAO();
    private StanjeTelefonaDAO stanjeDao = new MySQLDAOFactory().getStanjeTelefonaDAO();
    private ServisTelefonaDAO servisDao = new MySQLDAOFactory().getServisTelefonaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modeli = (ArrayList<ModelTelefonaDTO>) modelDao.selectAll();
        cbModel.getItems().addAll(modeli); //OVO MOZE AKO IMAMO toString metodu, mogli smo ubaciti i ID da ispise pa bi bilo {id nazivModela} mozda bi bilo lakse tako
    }

    public void btnDodaj(ActionEvent e) {
        String ime = tfIme.getText();
        String prezime = tfPrezime.getText();
        String broj = tfBroj.getText();
        String opis = taOpis.getText();
        String serijski = tfSerijski.getText();
        if (cbModel.getSelectionModel().isEmpty() || ime.equals("") || prezime.equals("") || broj.equals("") || opis.equals("")
                || serijski.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste popunili sva obavezna polja!");
            alert.showAndWait();
        } else {
            String model = cbModel.getSelectionModel().getSelectedItem().toString();
            System.out.println(model);
            int idZaposlenog = zaposleniDao.selectZaposleni(new ZaposleniDTO(zaposleni)).getIdOsoba();
            int idStanje = stanjeDao.selectBy(new StanjeTelefonaDTO("Pokvaren")).get(0).getIdStanjeTelefona();
            LocalDateTime datum = LocalDateTime.now();
            int idModel = modelDao.selectBy(new ModelTelefonaDTO(model)).get(0).getIdModeltelefona();

            KlijentDTO klijent = new KlijentDTO(ime, prezime, broj);
            KlijentDAO klijentDao = new MySQLDAOFactory().getKlijentDAO();
            if (klijentDao.insert(klijent)) {
                ServisTelefonaDTO servis = new ServisTelefonaDTO(idZaposlenog, idStanje, opis, datum, idModel, serijski);

                if (servisDao.insert(servis)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspješno dodavanje!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška!");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodavanje nije moguće!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Dodavanje nije moguće!");
                alert.showAndWait();
            }

        }
    }

}
