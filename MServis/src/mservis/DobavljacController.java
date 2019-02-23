/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.DobavljacDAO;
import dto.DobavljacDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static mservis.DodavanjeDobavljacaController.setDobavljacController;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class DobavljacController implements Initializable {

    @FXML
    private Button btnDODAJ;
    @FXML
    private Button btnIZMIJENI;
    @FXML
    private Button btnOBRISI;
    @FXML
    private TableView<DobavljacDTO> tableDOBAVLJACI;
    @FXML
    private TableColumn columnID;
    @FXML
    private TableColumn columnNAZIV;
    @FXML
    private TableColumn columnADRESA;
    @FXML
    private TableColumn columnTEL;
    @FXML
    private TableColumn columnEMAIL;
    @FXML
    private Button btnPRETRAZI;
    @FXML
    private TextField tPRETRAZI;
    @FXML
    private Button btnIzadji;
    ObservableList<DobavljacDTO> dobavljacLIST;
    public static DobavljacDTO dobavljacDTO;
    DobavljacDAO dobavljacDAO = (new MySQLDAOFactory()).getDobavljacDAO();
    public static String vrijednost;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDobavljacController(this);
        dobavljacLIST = FXCollections.observableArrayList(dobavljacDAO.selectAll());
        tableDOBAVLJACI.setItems(dobavljacLIST);
        columnID.setCellValueFactory(new PropertyValueFactory<DobavljacDTO, Integer>("IdDobavljac"));
        columnNAZIV.setCellValueFactory(new PropertyValueFactory<DobavljacDTO, String>("Naziv"));
        columnADRESA.setCellValueFactory(new PropertyValueFactory<DobavljacDTO, String>("Adresa"));
        columnTEL.setCellValueFactory(new PropertyValueFactory<DobavljacDTO, String>("Telefon"));
        columnEMAIL.setCellValueFactory(new PropertyValueFactory<DobavljacDTO, String>("Email"));
    }

    public void btnDodaj(ActionEvent actionEvent) {
        vrijednost = "DODAJ";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeDobavljaca.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Dodavanje");
            stage.setResizable(false);
            Scene scene = new Scene(root1);
            scene.getStylesheets().add("dark-theme.css");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnIzmijeni(ActionEvent actionEvent) {
        vrijednost = "IZMIJENI";
        dobavljacDTO = tableDOBAVLJACI.getSelectionModel().getSelectedItem();
        if (dobavljacDTO != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeDobavljaca.fxml"));
                Parent root1 = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setTitle("IZMJENA");
                stage.setResizable(false);
                Scene scene = new Scene(root1);
                scene.getStylesheets().add("dark-theme.css");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabrana stavka iz tabele!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        }
    }

    public void btnOBrisi() {
        dobavljacDTO = tableDOBAVLJACI.getSelectionModel().getSelectedItem();
        if (dobavljacDTO != null) {
            dobavljacDAO.delete(dobavljacDTO);
            tableDOBAVLJACI.getItems().remove(dobavljacDTO);
            osvjezi();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabrana stavka iz tabele!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        }
    }

    public void osvjezi() {
        dobavljacLIST.clear();
        dobavljacLIST.addAll(dobavljacDAO.selectAll());
        tableDOBAVLJACI.refresh();
    }

    public void btnPretrazi(ActionEvent actionEvent) {
        if (tPRETRAZI.getText().isEmpty()) {
            osvjezi();
        } else {
            List<DobavljacDTO> lista = new ArrayList<DobavljacDTO>();
            lista.addAll(dobavljacDAO.selectByName(String.valueOf(tPRETRAZI.getText())));
            if (lista.size() > 0) {
                dobavljacLIST.clear();
                dobavljacLIST.addAll(lista);
                tableDOBAVLJACI.refresh();
            }
        }
    }
    
    public void btnIzadji(ActionEvent e){
         Stage stage = (Stage) btnIzadji.getScene().getWindow();
         stage.close();
    }
}
