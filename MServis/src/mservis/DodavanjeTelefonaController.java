/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ArtikalDAO;
import dao.CijenaDAO;
import dao.ModelTelefonaDAO;
import dao.TelefonDAO;
import dto.ArtikalDTO;
import dto.CijenaDTO;
import dto.ModelTelefonaDTO;
import dto.TelefonDTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static mservis.ManipulacijaArtiklimaController.vrijednost;
import static mservis.ManipulacijaArtiklimaController.telefonDTO;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class DodavanjeTelefonaController implements Initializable {

    @FXML
    private TextField tfSerijskiBroj;

    @FXML
    private ComboBox cbBoja;

    @FXML
    private ComboBox cbModel;

    @FXML
    private Button btnSacuvaj;

    @FXML
    private Button btnIzadji;

    @FXML
    private Button btnDodajModel;

    @FXML
    private Button btnPromjeniCijenuModela;

    @FXML
    private TextField tfCijena;

    private TelefonDAO telefonDao = new MySQLDAOFactory().getTelefonDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (vrijednost.equals("izmjeni")) {
            popuniPolja(telefonDTO);
        } else {

            ModelTelefonaDAO modelTelefonaDAODAO = (new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList = FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
            List<String> modeliTelefona = new ArrayList<String>();
            for (ModelTelefonaDTO mt : modelTelefonaList) {
                modeliTelefona.add(mt.getNazivModela());
            }

            cbModel.getItems().addAll(modeliTelefona);
            cbBoja.getItems().addAll("Crna", "Siva", "Bijela", "Srebrna", "Plava", "Crvena", "Zlatna", "Narandžasta", "žuta", "Zelena");
        }
    }

    public void btnSacuvajHandler(ActionEvent e) {

        if (vrijednost.equals("izmjeni")) {
            if ("".equals(tfSerijskiBroj.getText()) || "".equals(tfCijena.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Niste unijeli sve podatke!");
                alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                alert.showAndWait();
            } else {
                String model = cbModel.getValue().toString();
                String serijskiBroj = tfSerijskiBroj.getText();
                String boja = cbBoja.getValue().toString();

                ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(model);
                ModelTelefonaDAO modelTelefonaDAODAO = (new MySQLDAOFactory()).getModelTelefonaDAO();
                ObservableList<ModelTelefonaDTO> modelTelefonaList;
                modelTelefonaList = FXCollections.observableArrayList(modelTelefonaDAODAO.selectBy(modelTelefonaDTO));
                List<String> modeliTelefona = new ArrayList<String>();
                for (ModelTelefonaDTO mt : modelTelefonaList) {
                    modeliTelefona.add(mt.getNazivModela());
                }

                ArtikalDTO artikalDTO = new ArtikalDTO(modelTelefonaList.get(0).getIdModeltelefona());
                ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
                artikalDAO.update(artikalDTO);

                TelefonDTO telefonDTO = new TelefonDTO(serijskiBroj, boja, modelTelefonaList.get(0).getIdModeltelefona());
                TelefonDAO telefonDAO = new MySQLDAOFactory().getTelefonDAO();
                if (telefonDAO.update(telefonDTO)) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspješno izmjenjeno!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();

                    vrijednost = "nesto";

                    Stage stage = (Stage) btnSacuvaj.getScene().getWindow();
                    stage.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText(null);
                    alert.setContentText("Neuspješna izmjena!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();
                }
            }
        } else {
            List<TelefonDTO> lista = telefonDao.selectAll();
            boolean postoji = false;
            if (!"".equals(tfSerijskiBroj.getText())) {
                for (TelefonDTO tel : lista) {
                    if (tel.getSerijskiBroj().equals(tfSerijskiBroj.getText())) {
                        postoji = true;
                    }
                }
            }

            if (postoji) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška!");
                alert.setHeaderText(null);
                alert.setContentText("Telefon sa datim serijskim brojem postoji!");
                alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                alert.showAndWait();
            } else {
                if ("".equals(tfSerijskiBroj.getText()) || cbModel.getSelectionModel().isEmpty() || cbModel.getSelectionModel().isEmpty() || cbBoja.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška!");
                    alert.setHeaderText(null);
                    alert.setContentText("Niste unijeli sve podatke!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();
                } else {
                    String model = cbModel.getValue().toString();
                    String serijskiBroj = tfSerijskiBroj.getText();
                    String boja = cbBoja.getValue().toString();

                    ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(model);
                    ModelTelefonaDAO modelTelefonaDAODAO = (new MySQLDAOFactory()).getModelTelefonaDAO();
                    ObservableList<ModelTelefonaDTO> modelTelefonaList;
                    modelTelefonaList = FXCollections.observableArrayList(modelTelefonaDAODAO.selectBy(modelTelefonaDTO));
                    List<String> modeliTelefona = new ArrayList<String>();
                    for (ModelTelefonaDTO mt : modelTelefonaList) {
                        modeliTelefona.add(mt.getNazivModela());
                    }

                    ArtikalDTO artikalDTO = new ArtikalDTO(modelTelefonaList.get(0).getIdModeltelefona());
                    ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
                    artikalDAO.update(artikalDTO);

                    TelefonDTO telefonDTO = new TelefonDTO(serijskiBroj, boja, modelTelefonaList.get(0).getIdModeltelefona());
                    TelefonDAO telefonDAO = new MySQLDAOFactory().getTelefonDAO();
                    if (telefonDAO.insert(telefonDTO)) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informacija");
                        alert.setHeaderText(null);
                        alert.setContentText("Uspješno dodano!");
                        alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                        alert.showAndWait();

                        Stage stage = (Stage) btnSacuvaj.getScene().getWindow();
                        stage.close();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Greška");
                        alert.setHeaderText(null);
                        alert.setContentText("Dodavanje neuspješno!");
                        alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                        alert.showAndWait();
                    }
                }

            }

        }
    }

    public void btnIzadjiHandler(ActionEvent e) {
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }

    public void popuniPolja(TelefonDTO telefon) {

        cbBoja.getItems().addAll("Plava", "Crvena", "Bijela", "Zlatna", "Crna", "Siva", "Narandzasta", "Zuta", "Zelena");
        tfCijena.setText(Double.toString(telefon.getCijena()));
        cbModel.setValue(telefon.getModel());
        tfSerijskiBroj.setText(telefon.getSerijskiBroj());
        tfSerijskiBroj.setEditable(false);
        cbBoja.getSelectionModel().select(telefon.getBoja());
    }

    public void btnDodajModelHandler(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeModelaTelefona.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Dodavanje modela telefona");
            stage.setResizable(false);
            Scene scene = new Scene(root1);
            scene.getStylesheets().add("dark-theme.css");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.showAndWait();

            ModelTelefonaDAO modelTelefonaDAODAO = (new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList = FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
            List<String> modeliTelefona = new ArrayList<String>();
            for (ModelTelefonaDTO mt : modelTelefonaList) {
                modeliTelefona.add(mt.getNazivModela());
            }

            cbModel.getItems().addAll(modeliTelefona);
        } catch (IOException ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnPromjeniCijenuModelaHandler(ActionEvent e) {
        Double cijena = Double.parseDouble(tfCijena.getText());
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        CijenaDAO cijenaDAO = new MySQLDAOFactory().getCijenaDAO();
        CijenaDTO cs = new CijenaDTO(telefonDTO.getIdModelTelefona(), ts, true);
        cijenaDAO.delete(cs);
        CijenaDTO cijenaDTO = new CijenaDTO(telefonDTO.getIdModelTelefona(), cijena, ts);
        if (cijenaDAO.insert(cijenaDTO)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Uspješna izmjena cijene modela!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nemoguća izmjena cijene modela!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        }
    }
}
