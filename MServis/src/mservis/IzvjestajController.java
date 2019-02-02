/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.DirectoryChooser;
import net.sf.jasperreports.engine.JRException;
import service.GeneratorIzvjestaja;

/**
 *
 * @author Bojan
 */
public class IzvjestajController implements Initializable {

    @FXML
    private Button btnKreirajIzvjestaj;
    @FXML
    private DatePicker pickerDatumOd;
    @FXML
    private DatePicker pickerDatumDo;
    @FXML
    private ComboBox cbTip;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pickerDatumOd.setDisable(true);
        pickerDatumDo.setDisable(true);
        cbTip.getItems().addAll("Trenutno stanje artikala", "Dnevni izvještaj prodaje", "Periodični izvještaj prodaje");
    }

    public void btnKreirajIzvjestaj(ActionEvent e) {
        Date datumOdSql = null;
        Date datumDoSql = null;
        GeneratorIzvjestaja r = new GeneratorIzvjestaja();
        File selectedDirectory = null;
        try {
            String tip = cbTip.getSelectionModel().getSelectedItem().toString();

            if (!tip.equals("Tip izvještaja")) {
                if (tip.equals("Periodični izvještaj prodaje")) {
                    LocalDate datumOd = pickerDatumOd.getValue();
                    LocalDate datumDo = pickerDatumDo.getValue();
                    datumOdSql = Date.valueOf(datumOd);
                    datumDoSql = Date.valueOf(datumDo);
                    if (datumOd.isAfter(datumDo) || datumOd.isAfter(LocalDate.now()) || datumDo.isAfter(LocalDate.now())) {
                        throw new IllegalArgumentException();
                    }

                }

                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle("Izaberite odredište izvještaja");
                selectedDirectory = chooser.showDialog(null);

            }

            switch (tip) {
                case "Trenutno stanje artikala":
                    r.trenutnoStanje(selectedDirectory.getAbsolutePath());
                    break;
                case "Dnevni izvještaj prodaje":
                    r.dnevniIzvjestaj(selectedDirectory.getAbsolutePath());
                    break;
                case "Periodični izvještaj prodaje":
                    r.periodicniIzvjestaj(selectedDirectory.getAbsolutePath(), datumOdSql, datumDoSql);
                    break;
                default:

                    break;
            }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavještenje!");
            alert.setHeaderText(null);
            alert.setContentText("Izvještaj uspješno kreiran!");
            alert.showAndWait();
        } catch (NullPointerException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste odabrali sve potrebne parametre za izvještaj!");
            alert.showAndWait();
            // e1.printStackTrace();
        } catch (IllegalArgumentException e2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Odabrani datum nije ispravan!");
            alert.showAndWait();
        } catch (JRException ex) {
            Logger.getLogger(IzvjestajController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cbTip(ActionEvent e) {
        String tip = cbTip.getSelectionModel().getSelectedItem().toString();
        if (tip.equals("Periodični izvještaj prodaje")) {
            pickerDatumOd.setDisable(false);
            pickerDatumDo.setDisable(false);
        } else {
            pickerDatumOd.setDisable(true);
            pickerDatumDo.setDisable(true);
        }

    }

}
