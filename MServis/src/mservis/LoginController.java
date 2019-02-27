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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnPrijava;
    @FXML
    private Button btnUgasi;
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
        if (zaposleni != null) {
            if (zaposleni.getLozinka().equals(DodajIzmjeniAdminZaposleniController.getHash(lozinka))) {
                zaposleniPrijava = true;
                korisnicko = zaposleni.getKoriscnikoIme();
            }
        }

        if (!zaposleniPrijava) {
            AdminDAO adminDao = new MySQLDAOFactory().getAdminDAO();
            AdminDTO admin = adminDao.selectAdmin(new AdminDTO(korisnickoIme, null, null));
            if (admin != null) {
                if (admin.getLozinka().equals(DodajIzmjeniAdminZaposleniController.getHash(lozinka))) {
                    adminPrijava = true;
                    korisnicko = admin.getKoriscnikoIme();
                }
            }
        }
        if (zaposleniPrijava) {
            try {

                Stage stage = new Stage();
                Parent root2;

                root2 = FXMLLoader.load(getClass().getResource("GlavnaForma.fxml"));

                Scene scene = new Scene(root2);
                scene.getStylesheets().add("dark-theme.css");
                stage.setTitle("Glavna forma");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
                stage.initStyle(StageStyle.TRANSPARENT);
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
                scene.getStylesheets().add("dark-theme.css");
                stage.setTitle("Admin forma");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((Stage) loginPane.getScene().getWindow()).close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Pogrešno korisničko ime ili lozinka!");
            
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        }
    }

    public void btnUgasi(ActionEvent e) {
        Stage stage = (Stage) btnUgasi.getScene().getWindow();
        stage.close();
    }

}
