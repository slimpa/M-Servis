/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ModelTelefonaDAO;
import dto.ModelTelefonaDTO;
import java.net.URL;
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

/**
 *
 * @author Bojan
 */
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
        }
        else{
        String model = cbModel.getSelectionModel().toString();
        }
    }
}
