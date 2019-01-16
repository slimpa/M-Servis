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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Bojan
 */
public class LoginController implements Initializable {
    
    @FXML
    private Button btnPrijava;
    
    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordPF;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void btnPrijavaHandler(ActionEvent e){
        String username = usernameTF.getText();

        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("GlavnaForma.fxml"));

            Scene scene = new Scene(root2);
            
            stage.setTitle("Glavna forma");
            stage.setScene(scene);
            stage.show();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ((Stage) loginPane.getScene().getWindow()).close();
    }
   

}
