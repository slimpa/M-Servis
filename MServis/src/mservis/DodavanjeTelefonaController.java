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
import dao.TelefonDAO;
import dto.ArtikalDTO;
import dto.CijenaDTO;
import dto.ModelTelefonaDTO;
import dto.ProizvodjacDTO;
import dto.TelefonDTO;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static mservis.ManipulacijaArtiklimaController.vrijednost;
import static mservis.ManipulacijaArtiklimaController.telefonDTO;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class DodavanjeTelefonaController implements Initializable{
   
    
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(vrijednost.equals("izmjeni")){
            popuniPolja(telefonDTO);
        }
        else{

            
            ModelTelefonaDAO modelTelefonaDAODAO=(new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList=FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
            List<String> modeliTelefona = new ArrayList<String>();
            for(ModelTelefonaDTO mt : modelTelefonaList){
                modeliTelefona.add(mt.getNazivModela());
            }


            cbModel.getItems().addAll(modeliTelefona);
            cbBoja.getItems().addAll("Plava","Crvena","Bijela","Zlatna","Crna","Siva","Narandzasta","Zuta","Zelena");
        }
    }
    
    public void btnSacuvajHandler(ActionEvent e){
        if(vrijednost.equals("izmjeni")){
            String model = cbModel.getValue().toString();
            String serijskiBroj =  tfSerijskiBroj.getText();
            String boja = cbBoja.getValue().toString();
            
            ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(model);
            ModelTelefonaDAO modelTelefonaDAODAO=(new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList=FXCollections.observableArrayList(modelTelefonaDAODAO.selectBy(modelTelefonaDTO));
            List<String> modeliTelefona = new ArrayList<String>();
            for(ModelTelefonaDTO mt : modelTelefonaList){
                modeliTelefona.add(mt.getNazivModela());
            }
            
            ArtikalDTO artikalDTO = new ArtikalDTO(modelTelefonaList.get(0).getIdModeltelefona());
            ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
            artikalDAO.update(artikalDTO);
            
            TelefonDTO telefonDTO = new TelefonDTO(serijskiBroj,boja,modelTelefonaList.get(0).getIdModeltelefona());
            TelefonDAO telefonDAO = new MySQLDAOFactory().getTelefonDAO();
            telefonDAO.update(telefonDTO);
            
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspjesno izmjenjeno!");

            alert.showAndWait();

            Stage stage = (Stage) btnSacuvaj.getScene().getWindow();
            stage.close();
            vrijednost= "nesto";
        }
        else{
            String model = cbModel.getValue().toString();
            String serijskiBroj =  tfSerijskiBroj.getText();
            String boja = cbBoja.getValue().toString();

            ModelTelefonaDTO modelTelefonaDTO = new ModelTelefonaDTO(model);
            ModelTelefonaDAO modelTelefonaDAODAO=(new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList=FXCollections.observableArrayList(modelTelefonaDAODAO.selectBy(modelTelefonaDTO));
            List<String> modeliTelefona = new ArrayList<String>();
            for(ModelTelefonaDTO mt : modelTelefonaList){
                modeliTelefona.add(mt.getNazivModela());
            }

            ArtikalDTO artikalDTO = new ArtikalDTO(modelTelefonaList.get(0).getIdModeltelefona());
            ArtikalDAO artikalDAO = new MySQLDAOFactory().getArtikalDAO();
            artikalDAO.update(artikalDTO);




            TelefonDTO telefonDTO = new TelefonDTO(serijskiBroj,boja,modelTelefonaList.get(0).getIdModeltelefona());
            TelefonDAO telefonDAO = new MySQLDAOFactory().getTelefonDAO();
            telefonDAO.insert(telefonDTO);





            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Uspjesno dodano!");

            alert.showAndWait();

            Stage stage = (Stage) btnSacuvaj.getScene().getWindow();
            stage.close();
        }
    }
    
    public void btnIzadjiHandler(ActionEvent e){
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }
    
    public void popuniPolja(TelefonDTO telefon){


        cbBoja.getItems().addAll("Plava","Crvena","Bijela","Zlatna","Crna","Siva","Narandzasta","Zuta","Zelena");
        
        cbModel.setValue(telefon.getModel());
        tfSerijskiBroj.setText(telefon.getSerijskiBroj());
        cbBoja.getSelectionModel().select(telefon.getBoja());
    }
    
    public void btnDodajModelHandler(){
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodavanjeModelaTelefona.fxml"));
            Parent root1=(Parent)loader.load();
            Stage stage=new Stage();
            stage.setTitle("Dodavanje modela telefona");
            stage.setResizable(false);

            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
