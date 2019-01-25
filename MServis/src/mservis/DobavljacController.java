/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.DobavljacDAO;
import dto.DobavljacDTO;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    ObservableList<DobavljacDTO> dobavljacLIST;
    public static DobavljacDTO dobavljacDTO;
    DobavljacDAO dobavljacDAO=(new MySQLDAOFactory()).getDobavljacDAO();
    public static String vrijednost;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setDobavljacController(this);
       dobavljacLIST=FXCollections.observableArrayList(dobavljacDAO.selectAll());
       columnID.setCellValueFactory(new PropertyValueFactory<DobavljacDTO,Integer>("IdDobavljac"));
       columnNAZIV.setCellValueFactory(new PropertyValueFactory<DobavljacDTO,String>("Naziv"));
       columnADRESA.setCellValueFactory(new PropertyValueFactory<DobavljacDTO,String>("Adresa"));
       columnTEL.setCellValueFactory(new PropertyValueFactory<DobavljacDTO,String>("Telefon"));
       columnEMAIL.setCellValueFactory(new PropertyValueFactory<DobavljacDTO,String>("Email"));
       tableDOBAVLJACI.setItems(dobavljacLIST);
    }    
    public void btnDodaj(ActionEvent actionEvent)
    {
        vrijednost="DODAJ";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeDobavljaca.fxml"));
            Parent root1=(Parent)loader.load();
            Stage stage=new Stage();
            stage.setTitle("Dodavanje");
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait(); 
        } catch (IOException ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnIzmijeni(ActionEvent actionEvent)
    {
        vrijednost="IZMIJENI";
        dobavljacDTO=tableDOBAVLJACI.getSelectionModel().getSelectedItem();
        if(dobavljacDTO!=null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeDobavljaca.fxml"));
                Parent root1=(Parent)loader.load();
                Stage stage=new Stage();
                stage.setTitle("IZMJENA");
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL); 
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         }
    }
    public void btnOBrisi()
    {
         dobavljacDTO=tableDOBAVLJACI.getSelectionModel().getSelectedItem();
        if(dobavljacDTO!=null)
            dobavljacDAO.delete(dobavljacDTO);
        tableDOBAVLJACI.getItems().remove(dobavljacDTO);
        osvjezi();
    }
    public void osvjezi()
    {
        dobavljacLIST.clear();
        dobavljacLIST.addAll(dobavljacDAO.selectAll());
        tableDOBAVLJACI.refresh();
    }
}