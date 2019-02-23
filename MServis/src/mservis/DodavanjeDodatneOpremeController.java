/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ArtikalDAO;
import dao.CijenaDAO;

import dao.DodatnaOpremaDAO;
import dao.ModelTelefonaDAO;
import dao.ProizvodjacDAO;
import dao.TipDodatneOpremeDAO;
import dto.ArtikalDTO;
import dto.CijenaDTO;

import dto.DodatnaOpremaDTO;
import dto.ModelTelefonaDTO;
import dto.ProizvodjacDTO;
import dto.TipDodatneOpremeDTO;
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

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static mservis.ManipulacijaArtiklimaController.dodatnaOpremaDTO;
import mySQL.MySQLDAOFactory;
import static mservis.ManipulacijaArtiklimaController.vrijednost;

/**
 *
 * @author Bojan
 */
public class DodavanjeDodatneOpremeController implements Initializable {

    @FXML
    private Button btnIzadji;

    @FXML
    private Button btnSacuvaj;

    @FXML
    private TextField tfNaziv;

    @FXML
    private ComboBox cbTip;

    @FXML
    private ComboBox cbProizvodjac;

    @FXML
    private ComboBox cbModel;

    @FXML
    private TextField tfBarKod;

    @FXML
    private ComboBox cbBoja;

    @FXML
    private TextField tfKolicina;

    @FXML
    private TextField tfCijena;

    @FXML
    private Button btnDodajModelTelefona;

    public Integer id = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (vrijednost.equals("izmjeni")) {
            popuniPolja(dodatnaOpremaDTO);
        } else {
            ProizvodjacDAO proizvodjacDAO = (new MySQLDAOFactory()).getProizvodjacDAO();
            ObservableList<ProizvodjacDTO> proizvodjacList;
            proizvodjacList = FXCollections.observableArrayList(proizvodjacDAO.selectAll());
            List<String> naziviProizvodjaca = new ArrayList<String>();
            for (ProizvodjacDTO dob : proizvodjacList) {
                naziviProizvodjaca.add(dob.getNaziv());
            }

            TipDodatneOpremeDAO tipDodatneOpremeDAO = (new MySQLDAOFactory()).getTipDodatneOpremeDAO();
            ObservableList<TipDodatneOpremeDTO> tipDodatneOpremeList;
            tipDodatneOpremeList = FXCollections.observableArrayList(tipDodatneOpremeDAO.selectAll());
            List<String> tipoviDodatneOpreme = new ArrayList<String>();
            for (TipDodatneOpremeDTO tdo : tipDodatneOpremeList) {
                tipoviDodatneOpreme.add(tdo.getTip());
            }

            ModelTelefonaDAO modelTelefonaDAODAO = (new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList = FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
            List<String> modeliTelefona = new ArrayList<String>();
            for (ModelTelefonaDTO mt : modelTelefonaList) {
                modeliTelefona.add(mt.getNazivModela());
            }

            cbTip.getItems().addAll(tipoviDodatneOpreme);
            cbProizvodjac.getItems().addAll(naziviProizvodjaca);
            cbModel.getItems().addAll(modeliTelefona);
            cbBoja.getItems().addAll("Plava", "Crvena", "Bijela", "Zlatna", "Crna", "Siva", "Narandzasta", "Zuta", "Zelena");
        }

    }

    public void btnSacuvajHandler(ActionEvent e) {
        if ("".equals(tfNaziv.getText()) || "".equals(tfCijena.getText()) || "".equals(tfKolicina.getText()) || "".equals(tfBarKod.getText())
                || cbProizvodjac.getSelectionModel().isEmpty() || cbModel.getSelectionModel().isEmpty() || cbBoja.getSelectionModel().isEmpty() || cbTip.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Niste unijeli sve podatke!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        } else {
            if (vrijednost.equals("izmjeni")) {

                String naziv = tfNaziv.getText();
                String tip = cbTip.getValue().toString();
                String proizvodjac = cbProizvodjac.getValue().toString();
                String model = cbModel.getValue().toString();
                String barKod = tfBarKod.getText();
                String boja = cbBoja.getValue().toString();
                Integer kolicina = Integer.parseInt(tfKolicina.getText());
                Double cijena = Double.parseDouble(tfCijena.getText());

                ProizvodjacDTO proizvodjacDTO = new ProizvodjacDTO(proizvodjac);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ProizvodjacDAO proizvodjacDAO = new MySQLDAOFactory().getProizvodjacDAO();
                List<ProizvodjacDTO> proizvodjaci = proizvodjacDAO.selectBy(proizvodjacDTO);
                ArtikalDTO artikal = new ArtikalDTO(id, naziv, kolicina, proizvodjaci.get(0).getIdProizvodjac(), barKod);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
                artikalDAO.updateArtikal(artikal);

                DodatnaOpremaDAO doDAO = new MySQLDAOFactory().getDodatnaOpremaDAO();
                DodatnaOpremaDTO dodatnaOprema = new DodatnaOpremaDTO(boja, naziv, model, tip, proizvodjac, barKod, kolicina, cijena);
                //boja,naziv,model,tip,proizvodjac,barKod,kolicina,cijena
                dodatnaOprema.setIdDodatnaOprema(id);

                ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(model);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ModelTelefonaDAO modelTelefonaDAO = new MySQLDAOFactory().getModelTelefonaDAO();
                List<ModelTelefonaDTO> modeli = modelTelefonaDAO.selectBy(modelTelefonaDTO);
                dodatnaOprema.setIdModelTelefona(modeli.get(0).getIdModeltelefona());

                TipDodatneOpremeDTO tipDodatneOpremeDTO = new TipDodatneOpremeDTO(tip);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                TipDodatneOpremeDAO tipDodatneOpremeDAO = new MySQLDAOFactory().getTipDodatneOpremeDAO();
                List<TipDodatneOpremeDTO> tipovi = tipDodatneOpremeDAO.selectBy(tipDodatneOpremeDTO);
                dodatnaOprema.setIdTipDodatneOpreme(tipovi.get(0).getIdTipDodatneOpreme());
                if (doDAO.update(dodatnaOprema)) {

                    if (Double.parseDouble(tfCijena.getText()) != (dodatnaOpremaDTO.getCijena())) {
                        System.out.println("IZMJENA CIJENE");
                        Date date = new Date();
                        long time = date.getTime();
                        Timestamp ts = new Timestamp(time);
                        CijenaDAO cijenaDAO = new MySQLDAOFactory().getCijenaDAO();
                        CijenaDTO cs = new CijenaDTO(id, ts, true);
                        cijenaDAO.delete(cs);
                        CijenaDTO cijenaDTO = new CijenaDTO(id, cijena, ts);
                        cijenaDAO.insert(cijenaDTO);
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspjesno izmjenjeno!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();

                    Stage stage = (Stage) btnSacuvaj.getScene().getWindow();
                    stage.close();
                    vrijednost = "nesto";
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText(null);
                    alert.setContentText("Izmjena neuspješna!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();
                }
            } else {

                String naziv = tfNaziv.getText();
                String tip = cbTip.getValue().toString();
                String proizvodjac = cbProizvodjac.getValue().toString();
                String model = cbModel.getValue().toString();
                String barKod = tfBarKod.getText();
                String boja = cbBoja.getValue().toString();
                Integer kolicina = Integer.parseInt(tfKolicina.getText());
                Double cijena = Double.parseDouble(tfCijena.getText());

                ProizvodjacDTO proizvodjacDTO = new ProizvodjacDTO(proizvodjac);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ProizvodjacDAO proizvodjacDAO = new MySQLDAOFactory().getProizvodjacDAO();
                List<ProizvodjacDTO> proizvodjaci = proizvodjacDAO.selectBy(proizvodjacDTO);
                ArtikalDTO artikal = new ArtikalDTO(naziv, kolicina, proizvodjaci.get(0).getIdProizvodjac(), barKod);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
                artikalDAO.insert(artikal);

                DodatnaOpremaDAO doDAO = new MySQLDAOFactory().getDodatnaOpremaDAO();
                DodatnaOpremaDTO dodatnaOprema = new DodatnaOpremaDTO(boja, naziv, model, tip, proizvodjac, barKod, kolicina, cijena);
                //boja,naziv,model,tip,proizvodjac,barKod,kolicina,cijena
                dodatnaOprema.setIdDodatnaOprema(artikalDAO.getLastId());

                ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(model);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                ModelTelefonaDAO modelTelefonaDAO = new MySQLDAOFactory().getModelTelefonaDAO();
                List<ModelTelefonaDTO> modeli = modelTelefonaDAO.selectBy(modelTelefonaDTO);
                dodatnaOprema.setIdModelTelefona(modeli.get(0).getIdModeltelefona());

                TipDodatneOpremeDTO tipDodatneOpremeDTO = new TipDodatneOpremeDTO(tip);
                //String Naziv, int Kolicina, int idProizvodjac, String BarKod
                TipDodatneOpremeDAO tipDodatneOpremeDAO = new MySQLDAOFactory().getTipDodatneOpremeDAO();
                List<TipDodatneOpremeDTO> tipovi = tipDodatneOpremeDAO.selectBy(tipDodatneOpremeDTO);
                dodatnaOprema.setIdTipDodatneOpreme(tipovi.get(0).getIdTipDodatneOpreme());
                if (doDAO.insert(dodatnaOprema)) {

                    CijenaDAO cijenaDAO = new MySQLDAOFactory().getCijenaDAO();
                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);
                    CijenaDTO cijenaDTO = new CijenaDTO(artikalDAO.getLastId(), cijena, ts);
                    cijenaDAO.insert(cijenaDTO);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
                    alert.setContentText("Neuspješno dodavanje!");
                    alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
                    alert.showAndWait();
                }

            }

        }
    }

    public void btnIzadjiHandler(ActionEvent e) {
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }

    public void popuniPolja(DodatnaOpremaDTO dodatnaOprema) {

        ProizvodjacDAO proizvodjacDAO = (new MySQLDAOFactory()).getProizvodjacDAO();
        ObservableList<ProizvodjacDTO> proizvodjacList;
        proizvodjacList = FXCollections.observableArrayList(proizvodjacDAO.selectAll());
        List<String> naziviProizvodjaca = new ArrayList<String>();
        for (ProizvodjacDTO dob : proizvodjacList) {
            naziviProizvodjaca.add(dob.getNaziv());
        }

        TipDodatneOpremeDAO tipDodatneOpremeDAO = (new MySQLDAOFactory()).getTipDodatneOpremeDAO();
        ObservableList<TipDodatneOpremeDTO> tipDodatneOpremeList;
        tipDodatneOpremeList = FXCollections.observableArrayList(tipDodatneOpremeDAO.selectAll());
        List<String> tipoviDodatneOpreme = new ArrayList<String>();
        for (TipDodatneOpremeDTO tdo : tipDodatneOpremeList) {
            tipoviDodatneOpreme.add(tdo.getTip());
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

        String barkode = artikliList.get(dodatnaOprema.getIdDodatnaOprema());
        id = dodatnaOprema.getIdDodatnaOprema();
        cbTip.getItems().addAll(tipoviDodatneOpreme);
        cbProizvodjac.getItems().addAll(naziviProizvodjaca);
        cbModel.getItems().addAll(modeliTelefona);
        cbBoja.getItems().addAll("Plava", "Crvena", "Bijela", "Zlatna", "Crna", "Siva", "Narandzasta", "Zuta", "Zelena");

        tfNaziv.setText(dodatnaOprema.getNaziv());
        cbTip.getSelectionModel().select(dodatnaOprema.getIdTipDodatneOpreme());
        cbProizvodjac.getSelectionModel().select(dodatnaOprema.getIdProizvodjac());
        cbModel.getSelectionModel().select(dodatnaOprema.getModelTelefona());
        tfBarKod.setText(barkode);
        cbBoja.getSelectionModel().select(dodatnaOprema.getBoja());
        tfKolicina.setText(Integer.toString(dodatnaOprema.getKolicina()));
        tfCijena.setText(Double.toString(dodatnaOprema.getCijena()));
    }

    public void btnDodajModelTelefonaHandler() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeModelaTelefona.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Dodavanje modela telefona");
            stage.setResizable(false);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            Scene scene = new Scene(root1);
            scene.getStylesheets().add("dark-theme.css");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
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

}
