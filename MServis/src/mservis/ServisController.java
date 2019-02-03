/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.KlijentDAO;
import dao.ModelTelefonaDAO;
import dao.ServisTelefonaDAO;
import dao.StanjeTelefonaDAO;
import dao.ZaposleniDAO;
import dto.KlijentDTO;
import dto.ModelTelefonaDTO;
import dto.ServisTelefonaDTO;
import dto.StanjeTelefonaDTO;
import dto.ZaposleniDTO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;

/**
 *
 * @author Bojan
 */
public class ServisController implements Initializable {
    
    @FXML
    private Button btnPretraziServis;
    
    @FXML
    private TextField tfIdServisa;
    
    @FXML
    private ComboBox cbStanje;
    
    @FXML
    private TableView<ServisTelefonaDTO> tableServis;
    
    @FXML
    private TableColumn columnIdServisa;
    
    @FXML
    private TableColumn columnKlijent;
    
    @FXML
    private TableColumn columnServiser;
    
    @FXML
    private TableColumn columnModel;
    
    @FXML
    private TableColumn columnSerijski;
    
    @FXML
    private TableColumn columnOpis;
    
    @FXML
    private TableColumn columnDatum;
    
    @FXML
    private TableColumn columnStanje;
    
    private ServisTelefonaDAO servisDao = new MySQLDAOFactory().getServisTelefonaDAO();
    private KlijentDAO klijentDao = new MySQLDAOFactory().getKlijentDAO();
    private ZaposleniDAO zaposleniDao = new MySQLDAOFactory().getZaposleniDAO();
    private ModelTelefonaDAO modelDao = new MySQLDAOFactory().getModelTelefonaDAO();
    private StanjeTelefonaDAO stanjeDao = new MySQLDAOFactory().getStanjeTelefonaDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.popuniTabeluServis();
    }
    
    private void popuniTabeluServis(){
        List<ServisTelefonaDTO> lista = servisDao.selectAll();
        ObservableList<ServisTelefonaDTO> listaServisa = null;
        if(lista != null){
        
        for(ServisTelefonaDTO servis : lista){
                KlijentDTO klijent = klijentDao.selectBy(new KlijentDTO(servis.getIdKlijent())).get(0);
                klijent.setImePrezime(klijent.getIme(), klijent.getPrezime());
                
                ZaposleniDTO zaposleni = zaposleniDao.selectBy(new ZaposleniDTO(servis.getIdZaposleni())).get(0);
                zaposleni.setImePrezime(zaposleni.getIme(), zaposleni.getPrezime());
                
                List<ModelTelefonaDTO> listaModela = modelDao.selectAll();
                System.out.println(listaModela);
//                int modelIndex = listaModela.indexOf(new ModelTelefonaDTO(servis.getIdModelTelefona()));
//                ModelTelefonaDTO model = listaModela.get(modelIndex);
//                model.setNaziv(model.getNaziv());
                
                StanjeTelefonaDTO stanje = stanjeDao.selectBy(new StanjeTelefonaDTO(servis.getIdStanjeTelefona())).get(0);
                stanje.setStanje(stanje.getStanje());
            }
            listaServisa = FXCollections.observableArrayList(lista);
        }
        
        if (listaServisa != null) {
            columnIdServisa.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, Integer>("IdServisTelefona"));
            
            columnKlijent.setCellValueFactory(new PropertyValueFactory<KlijentDTO, String>("imePrezime"));
            columnServiser.setCellValueFactory(new PropertyValueFactory<ZaposleniDTO, String>("imePrezime"));
            columnModel.setCellValueFactory(new PropertyValueFactory<ModelTelefonaDTO, String>("NazivModela"));
            columnSerijski.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("serijskiBrojTelefona"));
            columnOpis.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("opisKvara"));
            columnDatum.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("datumPrijema"));
            columnStanje.setCellFactory(new PropertyValueFactory<StanjeTelefonaDTO, String>("stanje"));
            
            tableServis.setItems(listaServisa);
        }
    }

    public void btnNoviServis(ActionEvent e) {
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("PrijemUredjajaServis.fxml"));

            Scene scene = new Scene(root2);

            stage.setTitle("Novi servis telefona");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void btnServisiraj(ActionEvent e) {
        //TREBA DIO ZA SELEKTOVANJE SERVISA IZ TABELE
            
        Stage stage = new Stage();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("IzmjenaServis.fxml"));

            Scene scene = new Scene(root2);

            stage.setTitle("Servisiranje uređaja");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
