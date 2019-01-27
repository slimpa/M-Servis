/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.ModelTelefonaDAO;
import dto.ModelTelefonaDTO;
import dto.RezervniDioDTO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static mservis.ManipulacijaArtiklimaController.rezervniDioDTO;
import static mservis.ManipulacijaArtiklimaController.vrijednost;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class DodavanjeRezervnogDijelaController implements Initializable{
    
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(vrijednost.equals("izmjeni")){
            popuniPolja(rezervniDioDTO);
        }
        else{
            ModelTelefonaDAO modelTelefonaDAODAO=(new MySQLDAOFactory()).getModelTelefonaDAO();
            ObservableList<ModelTelefonaDTO> modelTelefonaList;
            modelTelefonaList=FXCollections.observableArrayList(modelTelefonaDAODAO.selectAll());
            List<String> modeliTelefona = new ArrayList<String>();
            for(ModelTelefonaDTO mt : modelTelefonaList){
                modeliTelefona.add(mt.getNazivModela());
            }
            
            cbModelTelefona.getItems().addAll(modeliTelefona);
           
        }
    }
    
    public void btnIzadjiHandler(ActionEvent e){
        
    }
    
    public void btnSacuvajHandler(ActionEvent e){
        
    }
    public void popuniPolja(RezervniDioDTO rezervniDio){
        tfNaziv.setText(rezervniDio.getNaziv());
        cbModelTelefona.setValue(rezervniDio.getIdModelTelefona());
        tfOpis.setText(rezervniDio.getOpis());
        tfKolicina.setText(Integer.toString(rezervniDio.getKolicina()));
//        tfCijena.setText(Integer.toString(rezervniDio.getCijena()));
    }
}
