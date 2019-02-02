/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Bojan
 */
public class ServisController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }

    public void btnNoviServis(ActionEvent e) {
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("PrijemUredjajaServis.fxml"));

            Scene scene = new Scene(root2);

            stage.setTitle("Novi servis telefona");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void btnServisiraj(ActionEvent e) {
        //TREBA DIO ZA SELEKTOVANJE SERVISA IZ TABELE
            
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("IzmjenaServis.fxml"));

            Scene scene = new Scene(root2);

            stage.setTitle("Servisiranje uređaja");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
