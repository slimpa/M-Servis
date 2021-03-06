/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ArtikalDAO;
import dao.CijenaDAO;
import dao.ModelTelefonaDAO;
import dao.ProizvodjacDAO;
import dao.RezervniDioDAO;
import dto.ArtikalDTO;
import dto.CijenaDTO;
import dto.ModelTelefonaDTO;
import dto.ProizvodjacDTO;
import dto.RezervniDioDTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static mservis.ManipulacijaArtiklimaController.rezervniDioDTO;
import static mservis.ManipulacijaArtiklimaController.vrijednost;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class DodavanjeRezervnogDijelaController implements Initializable {

    @FXML
    private TextField tfNaziv;

    @FXML
    private ComboBox cbModelTelefona;

    @FXML
    private TextField tfOpis;

    @FXML
    private TextField tfKolicina;

    @FXML
    private TextField tfCijena;

    @FXML
    private ComboBox cbProizvodjac;

    @FXML
    private TextField tfBarKod;

    @FXML
    private Button btnSacuvaj;

    @FXML
    private Button btnIzadji;

    @FXML
    private Button btnDodajModelTelefona;

    public Integer id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (vrijednost.equals("izmjeni")) {
            popuniPolja(rezervniDioDTO);
        } else {
            ModelTelefonaDAO modelTelefonaDAODAO = (new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList = FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
            List<String> modeliTelefona = new ArrayList<String>();
            for (ModelTelefonaDTO mt : modelTelefonaList) {
                modeliTelefona.add(mt.getNazivModela());
            }

            ProizvodjacDAO proizvodjacDAO = (new MySQLDAOFactory()).getProizvodjacDAO();
            ObservableList<ProizvodjacDTO> proizvodjacList;
            proizvodjacList = FXCollections.observableArrayList(proizvodjacDAO.selectAll());
            List<String> naziviProizvodjaca = new ArrayList<String>();
            for (ProizvodjacDTO dob : proizvodjacList) {
                naziviProizvodjaca.add(dob.getNaziv());
            }

            cbModelTelefona.getItems().addAll(modeliTelefona);
            cbProizvodjac.getItems().addAll(naziviProizvodjaca);

        }
    }

    public void btnIzadjiHandler(ActionEvent e) {
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }

    public void btnSacuvajHandler(ActionEvent e) {
        if ("".equals(tfNaziv.getText()) || "".equals(tfKolicina.getText()) || "".equals(tfCijena.getText()) || "".equals(tfBarKod.getText())
                || cbModelTelefona.getSelectionModel().isEmpty() || cbProizvodjac.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste unijeli sve podatke!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        } else {
            if (vrijednost.equals("izmjeni")) {

                String naziv = tfNaziv.getText();
                String modelTelefona = cbModelTelefona.getValue().toString();
                String opis = tfOpis.getText();
                Integer kolicina = Integer.parseInt(tfKolicina.getText());
                Double cijena = Double.parseDouble(tfCijena.getText());
                String proizvodjac = cbProizvodjac.getValue().toString();
                String barKod = tfBarKod.getText();
                Integer idRezervniDio;

                ProizvodjacDTO proizvodjacDTO = new ProizvodjacDTO(proizvodjac);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ProizvodjacDAO proizvodjacDAO = new MySQLDAOFactory().getProizvodjacDAO();
                List<ProizvodjacDTO> proizvodjaci = proizvodjacDAO.selectBy(proizvodjacDTO);
                ArtikalDTO artikal = new ArtikalDTO(id, naziv, kolicina, proizvodjaci.get(0).getIdProizvodjac(), barKod);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
                artikalDAO.update(artikal);
                idRezervniDio = id;

                ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(modelTelefona);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ModelTelefonaDAO modelTelefonaDAO = new MySQLDAOFactory().getModelTelefonaDAO();
                List<ModelTelefonaDTO> modeli = modelTelefonaDAO.selectBy(modelTelefonaDTO);

                RezervniDioDTO rezervniDioDTO = new RezervniDioDTO(idRezervniDio, modeli.get(0).getIdModeltelefona(), opis);//getIdRezervniDio getOpis modelTelefona
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                RezervniDioDAO rezervniDioDAO = new MySQLDAOFactory().getRezervniDioDAO();

                if (rezervniDioDAO.update(rezervniDioDTO)) {
                    //imam naziv, proizvodjac,barkod,model

                    if (Double.parseDouble(tfCijena.getText()) != (rezervniDioDTO.getCijena())) {
                        Date date = new Date();
                        long time = date.getTime();
                        Timestamp ts = new Timestamp(time);
                        CijenaDAO cijenaDAO = new MySQLDAOFactory().getCijenaDAO();
                        CijenaDTO cs = new CijenaDTO(id, ts, true);
                        cijenaDAO.delete(cs);
                        CijenaDTO cijenaDTO = new CijenaDTO(id, cijena, ts);
                        cijenaDAO.insert(cijenaDTO);
                    }

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspjesno dodano!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();

                    Stage stage = (Stage) btnSacuvaj.getScene().getWindow();
                    stage.close();
                    vrijednost = "NESTO";
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodavanje neuspješno!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();
                }
            } else {
                String naziv = tfNaziv.getText();
                String modelTelefona = cbModelTelefona.getValue().toString();
                String opis = tfOpis.getText();
                Integer kolicina = Integer.parseInt(tfKolicina.getText());
                Double cijena = Double.parseDouble(tfCijena.getText());
                String proizvodjac = cbProizvodjac.getValue().toString();
                String barKod = tfBarKod.getText();
                Integer idRezervniDio;

                ProizvodjacDTO proizvodjacDTO = new ProizvodjacDTO(proizvodjac);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ProizvodjacDAO proizvodjacDAO = new MySQLDAOFactory().getProizvodjacDAO();
                List<ProizvodjacDTO> proizvodjaci = proizvodjacDAO.selectBy(proizvodjacDTO);
                ArtikalDTO artikal = new ArtikalDTO(naziv, kolicina, proizvodjaci.get(0).getIdProizvodjac(), barKod);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
                artikalDAO.insert(artikal);
                idRezervniDio = artikalDAO.getLastId();

                ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(modelTelefona);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ModelTelefonaDAO modelTelefonaDAO = new MySQLDAOFactory().getModelTelefonaDAO();
                List<ModelTelefonaDTO> modeli = modelTelefonaDAO.selectBy(modelTelefonaDTO);

                RezervniDioDTO rezervniDioDTO = new RezervniDioDTO(idRezervniDio, modeli.get(0).getIdModeltelefona(), opis);//getIdRezervniDio getOpis modelTelefona
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                RezervniDioDAO rezervniDioDAO = new MySQLDAOFactory().getRezervniDioDAO();

                if (rezervniDioDAO.insert(rezervniDioDTO)) {
                    //imam naziv, proizvodjac,barkod,model

                    CijenaDAO cijenaDAO = new MySQLDAOFactory().getCijenaDAO();
                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);
                    CijenaDTO cijenaDTO = new CijenaDTO(artikalDAO.getLastId(), cijena, ts);
                    cijenaDAO.insert(cijenaDTO);

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspjesno dodano!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();

                    Stage stage = (Stage) btnSacuvaj.getScene().getWindow();
                    stage.close();
                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText(null);
                    alert.setContentText("Izmjena neuspješna!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();
                }
            }
        }
    }

    public void popuniPolja(RezervniDioDTO rezervniDio) {
        ProizvodjacDAO proizvodjacDAO = (new MySQLDAOFactory()).getProizvodjacDAO();
        ObservableList<ProizvodjacDTO> proizvodjacList;
        proizvodjacList = FXCollections.observableArrayList(proizvodjacDAO.selectAll());
        List<String> naziviProizvodjaca = new ArrayList<String>();
        for (ProizvodjacDTO dob : proizvodjacList) {
            naziviProizvodjaca.add(dob.getNaziv());
        }

        ModelTelefonaDAO modelTelefonaDAODAO = (new MySQLDAOFactory()).getModelTelefonaDAO();
        ObservableList<ModelTelefonaDTO> modelTelefonaList;
        modelTelefonaList = FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
        List<String> modeliTelefona = new ArrayList<String>();
        for (ModelTelefonaDTO mt : modelTelefonaList) {
            modeliTelefona.add(mt.getNazivModela());
        }

        ArtikalDAO artikalDAODAO = (new MySQLDAOFactory()).getArtikalDAO();
        ObservableList<ArtikalDTO> artikalList;
        artikalList = FXCollections.observableArrayList(artikalDAODAO.selectAll());
        Map<Integer, String> artikliList = new HashMap<Integer, String>();
        for (ArtikalDTO a : artikalList) {
            artikliList.put(a.getIdArtikal(), a.getBarKod());
        }

        String barkode = artikliList.get(rezervniDio.getIdRezervniDio());
        id = rezervniDio.getIdRezervniDio();

        cbProizvodjac.getItems().addAll(naziviProizvodjaca);
        cbModelTelefona.getItems().addAll(modeliTelefona);

        tfNaziv.setText(rezervniDio.getNazivRezervnogdijela());
        cbModelTelefona.getSelectionModel().selectFirst();;
        tfOpis.setText(rezervniDio.getOpis());
        tfKolicina.setText(Integer.toString(rezervniDio.getKolicina()));
        tfCijena.setText(Double.toString(rezervniDio.getCijena()));
        cbProizvodjac.getSelectionModel().selectFirst();
        tfBarKod.setText(barkode);
    }

    public void btnDodajModelTelefonaHandler() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeModelaTelefona.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Dodavanje modela telefona");
            stage.setResizable(false);
            Scene scene = new Scene(root1);
            scene.getStylesheets().add("dark-theme.css");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.setResizable(false);
            stage.showAndWait();

            ModelTelefonaDAO modelTelefonaDAODAO = (new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList = FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
            List<String> modeliTelefona = new ArrayList<String>();
            for (ModelTelefonaDTO mt : modelTelefonaList) {
                modeliTelefona.add(mt.getNazivModela());
            }

            cbModelTelefona.getItems().addAll(modeliTelefona);
        } catch (IOException ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
