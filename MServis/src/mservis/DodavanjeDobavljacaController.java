/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.DobavljacDAO;
import dto.DobavljacDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static mservis.DobavljacController.dobavljacDTO;
import mySQL.MySQLDAOFactory;
import static mservis.DobavljacController.vrijednost;
import org.apache.commons.validator.EmailValidator;
/**
 *
 * @author Korisnik
 */
public class DodavanjeDobavljacaController implements Initializable{
    
    @FXML
    private Button btnPOTVRDI;
    @FXML
    private Button btnOTKAZI;
    @FXML
    private Label lNAZIV;
    @FXML
    private Label lADRESA;
    @FXML
    private Label lTEL;
    @FXML
    private Label lEMAIL;
    @FXML
    private TextField tNAZIV;
    @FXML
    private TextField tADRESA;
    @FXML
    private TextField tTEL;
    @FXML
    private TextField tEMAIL;
    @FXML
    private AnchorPane aPANE;
    public static DobavljacController dobavljac ;
    
    DobavljacDAO dobavljacDAO=(new MySQLDAOFactory()).getDobavljacDAO();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(vrijednost.equals("IZMIJENI"))
          if(dobavljacDTO!=null)
            ucitajPodatke(dobavljacDTO);
    }    
    
    public void btnPotvrdi(ActionEvent actionEvent)
    {
        if(tNAZIV.getText().length()>0 && tADRESA.getText().length()>0 && tTEL.getText().length()>0
                && checkMail())
        {
           
            if(vrijednost.equals("DODAJ"))
            {
               dobavljacDTO=new DobavljacDTO(tNAZIV.getText(),tADRESA.getText(),tTEL.getText(),
               tEMAIL.getText());
               dobavljacDAO.insert(dobavljacDTO);
               dobavljac.osvjezi();
            }
            else if(vrijednost.equals("IZMIJENI"))
            {
                dobavljacDTO.setNaziv(tNAZIV.getText());
                dobavljacDTO.setAdresa(tADRESA.getText());
                dobavljacDTO.setTelefon(tTEL.getText());
                dobavljacDTO.setEmail(tEMAIL.getText());
                dobavljacDAO.update(dobavljacDTO);
                dobavljac.osvjezi();
            }
            ((Stage)aPANE.getScene().getWindow()).close();
           
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Obavijest");
            alert.setHeaderText(null);
            alert.setContentText("Podaci nisu ispravno unijeti!");
            alert.showAndWait();
        }
    }
    public void btnOtkazi(ActionEvent actionEvent)
    {
        ((Stage)aPANE.getScene().getWindow()).close();
    }
    
    public void ucitajPodatke(DobavljacDTO dobavljac)
    {
        tNAZIV.setText(dobavljac.getNaziv());
        tADRESA.setText(dobavljac.getAdresa());
        tTEL.setText(dobavljac.getTelefon());
        tEMAIL.setText(dobavljac.getEmail());
    }
    public boolean checkMail()
    {
       boolean valid = EmailValidator.getInstance().isValid(tEMAIL.getText());
       return valid;
    }
    public static void setDobavljacController(DobavljacController controller)
    {
       dobavljac=controller;
    }
}
