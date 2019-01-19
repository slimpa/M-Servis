/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.AdminDAO;
import dao.ZaposleniDAO;
import dto.AdminDTO;
import dto.ZaposleniDTO;
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
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;

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
    
    private static String korisnicko;
    public static String getKorisnickoIme() {
        return korisnicko;
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void btnPrijavaHandler(ActionEvent e) {
        boolean adminPrijava = false;
        boolean zaposleniPrijava = false;
        String korisnickoIme = usernameTF.getText();
        String lozinka = passwordPF.getText();

        ZaposleniDAO zaposleniDao = new MySQLDAOFactory().getZaposleniDAO();
        ZaposleniDTO zaposleni = zaposleniDao.selectZaposleni(new ZaposleniDTO(korisnickoIme, null, null));
        if (zaposleni != null && zaposleni.getLozinka().equals(lozinka)) {
            zaposleniPrijava = true;
            korisnicko=zaposleni.getKoriscnikoIme();
        }

        if (!zaposleniPrijava) {
            AdminDAO adminDao = new MySQLDAOFactory().getAdminDAO();
            AdminDTO admin = adminDao.selectAdmin(new AdminDTO(korisnickoIme, null, null));
            if (admin != null && admin.getLozinka().equals(lozinka)) {
                adminPrijava = true;
                korisnicko=admin.getKoriscnikoIme();
            }
        }
        if (zaposleniPrijava) {
            try {
                
                Stage stage = new Stage();
                Parent root2;

                root2 = FXMLLoader.load(getClass().getResource("GlavnaForma.fxml"));

                Scene scene = new Scene(root2);

                stage.setTitle("Glavna forma");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((Stage) loginPane.getScene().getWindow()).close();
        } else if (adminPrijava) {
            try {
                AdminController.setPrijavljeniAdmin(korisnickoIme);
                Stage stage = new Stage();
                Parent root2;
                root2 = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                Scene scene = new Scene(root2);
                stage.setTitle("Admin forma");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((Stage) loginPane.getScene().getWindow()).close();
        } else {
            System.out.println("Pogresno ime ili sifra");
        }
    }

}
