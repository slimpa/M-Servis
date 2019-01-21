/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.DodatnaOpremaDAO;
import dto.DodatnaOpremaDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;



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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTip.getItems().addAll(1);
        cbProizvodjac.getItems().addAll("Samsung");
        cbModel.getItems().addAll("Samsung S9+");
        cbBoja.getItems().addAll("Plava","Crvena","Bijela","Zlatna","Crna","Siva","Narandzasta","Zuta","Zelena");
        
        
    }
        
        
    
    public void btnSacuvajHandler(ActionEvent e){
        String naziv = tfNaziv.getText();
        Integer tip =  Integer.parseInt(cbTip.getValue().toString());
        String proizvodjac =  cbProizvodjac.getValue().toString();
        String model = cbModel.getValue().toString();
        String barKod = tfBarKod.getText(); 
        String boja = cbBoja.getValue().toString();
        Integer kolicina = Integer.parseInt(tfKolicina.getText());
        Integer cijena = Integer.parseInt(tfCijena.getText());
        
        DodatnaOpremaDAO doDAO = new MySQLDAOFactory().getDodatnaOpremaDAO();
        DodatnaOpremaDTO dodatnaOprema = new DodatnaOpremaDTO(naziv,tip,proizvodjac,model,barKod,boja,kolicina,cijena);
        dodatnaOprema.setIdDodatnaOprema(7);
        dodatnaOprema.setIdModelTelefona(1);
        doDAO.insert(dodatnaOprema);
        //String Boja, int idTipDodatneOpreme, String Naziv, String ModelTelefona, String Proizvodjac, String BarCode, int Kolicina, int Cijena
    }
    
    public void btnIzadjiHandler(ActionEvent e){
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }

    
}
