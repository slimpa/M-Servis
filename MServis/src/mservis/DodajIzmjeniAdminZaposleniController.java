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
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class DodajIzmjeniAdminZaposleniController implements Initializable {

    private AdminDAO adminDao = new MySQLDAOFactory().getAdminDAO();
    private ZaposleniDAO zaposleniDao = new MySQLDAOFactory().getZaposleniDAO();
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
    @FXML
    private Button btnDodaj;
    @FXML
    private Label lblLozinka;
    @FXML
    private Button btnOtkazi;

    private static boolean admin = false;
    private boolean izmjena = false;
    private static int odabraniId = 0;

    public static void setAdmin(boolean admin) {
        DodajIzmjeniAdminZaposleniController.admin = admin;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (admin) {
            tfMjesto.setEditable(false);
            tfMjesto.setText("Administrator");
        } else {
            tfFirma.setEditable(false);
            tfFirma.setText("m:servis");
        }
        if (AdminController.isIzmjena()) {
            tfLozinka.setVisible(false);
            lblLozinka.setVisible(false);
        }
    }

    public void btnDodajHandler() {
        String telefon = tfTelefon.getText();
        String ime = tfIme.getText();
        String prezime = tfPrezime.getText();
        String korisnicko = tfKorisnickoIme.getText();
        String lozinka = tfLozinka.getText();
        String radnoMjesto = tfMjesto.getText();
        String firma = tfFirma.getText();
        if (telefon.equals("") || ime.equals("") || prezime.equals("") || korisnicko.equals("")
                || radnoMjesto.equals("") || firma.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste unijeli sve podatke!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();

        } else {
            if (telefon.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")) {
                if (admin) {

                    AdminDTO adminNovi = new AdminDTO(odabraniId, korisnicko, getHash(lozinka), ime, prezime, telefon, firma);
                    AdminDTO adminProvjera = adminDao.selectAdmin(adminNovi);
                    if (adminProvjera != null && !izmjena) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Greška!");
                        alert.setHeaderText(null);
                        alert.setContentText("Korisničko ime postoji!");
                        alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                        alert.showAndWait();
                    } else if (izmjena) {
                        if (!adminDao.update(adminNovi)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Greška!");
                            alert.setHeaderText(null);
                            alert.setContentText("Izmjena nije moguća!");
                            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                            alert.showAndWait();
                        } else {

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Obavještenje!");
                            alert.setHeaderText(null);
                            alert.setContentText("Uspješna izmjena!");
                            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                            alert.showAndWait();

                            Stage stage = (Stage) btnDodaj.getScene().getWindow();
                            stage.close();

                        }
                    } else {
                        if (!adminDao.insert(adminNovi)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Greška!");
                            alert.setHeaderText(null);
                            alert.setContentText("Dodavanje nije moguće!");
                            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Obavještenje!");
                            alert.setHeaderText(null);
                            alert.setContentText("Uspješno dodavanje!");
                            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                            alert.showAndWait();

                            Stage stage = (Stage) btnDodaj.getScene().getWindow();
                            stage.close();
                        }

                    }
                } else {

                    ZaposleniDTO zaposleniNovi = new ZaposleniDTO(odabraniId, korisnicko, getHash(lozinka), radnoMjesto, ime, prezime, telefon);
                    ZaposleniDTO zaposleniProvjera = zaposleniDao.selectZaposleni(zaposleniNovi);
                    if (zaposleniProvjera != null && !izmjena) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Greška!");
                        alert.setHeaderText(null);
                        alert.setContentText("Korisničko ime postoji!");
                        alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                        alert.showAndWait();
                    } else if (izmjena) {
                        if (!zaposleniDao.update(zaposleniNovi)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Greška!");
                            alert.setHeaderText(null);
                            alert.setContentText("Izmjena nije moguća!");
                            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                            alert.showAndWait();
                        } else {

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Obavještenje!");
                            alert.setHeaderText(null);
                            alert.setContentText("Uspješna izmjena!");
                            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                            alert.showAndWait();

                            Stage stage = (Stage) btnDodaj.getScene().getWindow();
                            stage.close();

                        }
                    } else {
                        if (!zaposleniDao.insert(zaposleniNovi)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Greška!");
                            alert.setHeaderText(null);
                            alert.setContentText("Dodavanje nije moguće!");
                            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Obavještenje!");
                            alert.setHeaderText(null);
                            alert.setContentText("Uspješno dodavanje!");
                            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                            alert.showAndWait();

                            Stage stage = (Stage) btnDodaj.getScene().getWindow();
                            stage.close();
                        }
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Broj telefona nije u odgovarajućem formatu!");
                alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                alert.showAndWait();
            }
        }
    }

    public void setTextFieldsAdmin(AdminDTO admin) {
        this.tfIme.setText(admin.getIme());
        this.tfPrezime.setText(admin.getPrezime());
        this.tfKorisnickoIme.setText(admin.getKoriscnikoIme());
        this.tfTelefon.setText(admin.getBrojTelefona());
        this.tfFirma.setText(admin.getNazivFirme());
    }

    public void setTextFieldsZaposleni(ZaposleniDTO zaposleni) {
        this.tfIme.setText(zaposleni.getIme());
        this.tfPrezime.setText(zaposleni.getPrezime());
        this.tfKorisnickoIme.setText(zaposleni.getKoriscnikoIme());
        this.tfTelefon.setText(zaposleni.getBrojTelefona());
        this.tfMjesto.setText(zaposleni.getRadnoMjesto());
    }

    public static String getHash(String lozinka) {
        String hash = "";

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(lozinka.getBytes("utf8"));
            hash = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DodajIzmjeniAdminZaposleniController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DodajIzmjeniAdminZaposleniController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hash;
    }

    public void setIzmjena(boolean izmjena) {
        this.izmjena = izmjena;
    }

    public static void setOdabraniId(int odabraniId) {
        DodajIzmjeniAdminZaposleniController.odabraniId = odabraniId;
    }

    public void btnOtkazi(ActionEvent e) {
        Stage stage = (Stage) btnOtkazi.getScene().getWindow();
        stage.close();
    }

}
