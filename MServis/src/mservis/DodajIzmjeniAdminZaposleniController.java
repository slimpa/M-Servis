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
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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

    private static boolean admin = false;

    public static void setAdmin(boolean admin) {
        DodajIzmjeniAdminZaposleniController.admin = admin;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (admin) {
            tfMjesto.setVisible(false);
        } else {
            tfFirma.setVisible(false);
        }
    }

    public void btnDodajHandler() {
        if (admin) {
            String ime = tfIme.getText();
            String prezime = tfPrezime.getText();
            String korisnicko = tfKorisnickoIme.getText();
            String lozinka = tfLozinka.getText();
            String telefon = tfTelefon.getText();
            String firma = tfTelefon.getText();
            AdminDTO adminNovi = new AdminDTO(0, korisnicko, this.getHash(lozinka), ime, prezime, telefon, firma);
            AdminDTO adminProvjera = adminDao.selectAdmin(adminNovi);
            if (adminProvjera != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Korisničko ime postoji!");
                alert.showAndWait();
            } else {
                if (!adminDao.insert(adminNovi)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška!");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodavanje nije moguće!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Obavještenje!");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspješno dodavanje!");
                    alert.showAndWait();
                }

            }
        } else {
            String ime = tfIme.getText();
            String prezime = tfPrezime.getText();
            String korisnicko = tfKorisnickoIme.getText();
            String lozinka = tfLozinka.getText();
            String telefon = tfTelefon.getText();
            String radnoMjesto = tfMjesto.getText();
            ZaposleniDTO zaposleniNovi = new ZaposleniDTO(0, korisnicko, this.getHash(lozinka), radnoMjesto, ime, prezime, telefon);
            ZaposleniDTO zaposleniProvjera = zaposleniDao.selectZaposleni(zaposleniNovi);
            if (zaposleniProvjera != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Korisničko ime postoji!");
                alert.showAndWait();
            } else {
                if (!zaposleniDao.insert(zaposleniNovi)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška!");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodavanje nije moguće!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Obavještenje!");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspješno dodavanje!");
                    alert.showAndWait();
                }
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

    private String getHash(String lozinka) {
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

}
