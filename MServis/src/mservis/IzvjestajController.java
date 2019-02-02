/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
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
        GeneratorIzvjestaja r = new GeneratorIzvjestaja();
        File selectedDirectory=null;
        String tip = cbTip.getSelectionModel().getSelectedItem().toString();
        if (!tip.equals("Tip izvještaja")) {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Izaberite odredište izvještaja");
            // File defaultDirectory = new File("c:/dev/javafx");
            //  chooser.setInitialDirectory(defaultDirectory);
            selectedDirectory = chooser.showDialog(null);
            System.out.print(selectedDirectory.getAbsolutePath());
            
        }
        try{
        switch (tip) {
            case "Trenutno stanje artikala":     
                r.trenutnoStanje(selectedDirectory.getAbsolutePath());
                break;
            case "Dnevni izvještaj prodaje":
                r.dnevniIzvjestaj(selectedDirectory.getAbsolutePath());
                break;
            case "Periodični izvještaj prodaje":

                break;
            default:

                break;
        }
        }
        catch(Exception e1){
            e1.printStackTrace();
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
