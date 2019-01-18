/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author Bojan
 */
public class DodajIzmjeniAdminZaposleniController implements Initializable {

    @FXML
    private TextField tfIme;
    @FXML
    private TextField tfPrezime;
    @FXML
    private TextField tfKorisnickoIme;
    @FXML
    private TextField tfLozinka;
    @FXML
    private TextField tfTelefon;
    @FXML
    private TextField tfFirma;
    @FXML
    private TextField tfMjesto;

    private static boolean admin = false;

    public static void setAdmin(boolean admin) {
        DodajIzmjeniAdminZaposleniController.admin = admin;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       if(admin){
           tfMjesto.setVisible(false);
       }
       else{
           tfFirma.setVisible(false);
       }
    }

}
