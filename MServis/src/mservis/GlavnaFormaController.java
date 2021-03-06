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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Bojan
 */
public class GlavnaFormaController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private AnchorPane glavnaFormaPane;
    @FXML
    private Button dobavljaciButton;
    @FXML
    private Button izvjestajiButton;
    @FXML
    private Button narudzbeButton;
    @FXML
    private Button radSaArtiklimaButton;
    @FXML
    private Button racuniButton;
    @FXML
    private Button odjavaButton;
    @FXML
    private Button servisButton;
    @FXML
    private Label ime;
    
     public void btnRadSaDobavljacimaHandler(ActionEvent e){
        
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("Dobavljac.fxml"));

            Scene scene = new Scene(root2);
            scene.getStylesheets().add("dark-theme.css");
            stage.setTitle("Dobavljaci");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void btnRadSaIzvjestajimaHandler(ActionEvent e){
        
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("Izvjestaj.fxml"));

            Scene scene = new Scene(root2);
            scene.getStylesheets().add("dark-theme.css");
            stage.setTitle("Izvjestaji");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void btnRadSaNarudzbamaHandler(ActionEvent e){
        
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("NarudzbaFXML.fxml"));

            Scene scene = new Scene(root2);
            scene.getStylesheets().add("dark-theme.css");
            stage.setTitle("Narudzbe");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void btnRadSaArtiklimaHandler(ActionEvent e){
        
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("ManipulacijaArtiklima.fxml"));

            Scene scene = new Scene(root2);
            scene.getStylesheets().add("dark-theme.css");
            stage.setTitle("Manipulacija artiklima");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void btnRadSaRacunimaHandler(ActionEvent e){
        
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("Racun.fxml"));

            Scene scene = new Scene(root2);
            scene.getStylesheets().add("dark-theme.css");
            stage.setTitle("Racunima");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void btnServisHandler(ActionEvent e){
        
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("Servis.fxml"));

            Scene scene = new Scene(root2);
            scene.getStylesheets().add("dark-theme.css");
            stage.setTitle("Servis");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.showAndWait();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void odjavaButtonHandler(ActionEvent e){
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("Login.fxml"));

            Scene scene = new Scene(root2);
            scene.getStylesheets().add("dark-theme.css");
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.show();
         } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((Stage) glavnaFormaPane.getScene().getWindow()).close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ime.setText(LoginController.getKorisnickoIme());
        imgView.setImage(new Image("file:resources" + File.separator + "user.png"));
        dobavljaciButton.setFocusTraversable(false);
        izvjestajiButton.setFocusTraversable(false);
        narudzbeButton.setFocusTraversable(false);
        radSaArtiklimaButton.setFocusTraversable(false);
        servisButton.setFocusTraversable(false);
        odjavaButton.setFocusTraversable(false);
       
    }    

    

}
