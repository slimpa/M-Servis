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
import javafx.scene.control.Alert;
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
    private String zaposleniKorisnicko = LoginController.getKorisnickoIme();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.popuniTabeluServis();
    }

    private void popuniTabeluServis() {
        List<ServisTelefonaDTO> lista = servisDao.selectAll();
        ObservableList<ServisTelefonaDTO> listaServisa = null;
        if (lista != null) {

            for (ServisTelefonaDTO servis : lista) {
                KlijentDTO klijent = klijentDao.selectBy(new KlijentDTO(servis.getIdKlijent())).get(0);
                servis.setImePrezimeKlijent(klijent.getIme() + " " + klijent.getPrezime());

                ZaposleniDTO zaposleni = zaposleniDao.selectFromSviZaposleni(new ZaposleniDTO(servis.getIdZaposleni()));
                servis.setImePrezimeZaposleni(zaposleni.getIme() + " " + zaposleni.getPrezime());

                ModelTelefonaDTO model = modelDao.selectById(new ModelTelefonaDTO(servis.getIdModelTelefona())).get(0);
                servis.setNazivModela(model.getNazivModela());

                StanjeTelefonaDTO stanje = stanjeDao.selectById(new StanjeTelefonaDTO(servis.getIdStanjeTelefona()));
                servis.setStanjeTelefona(stanje.getStanje());
            }
            listaServisa = FXCollections.observableArrayList(lista);
        }

        if (listaServisa != null) {
            columnIdServisa.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, Integer>("IdServisTelefona"));

            columnKlijent.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("imePrezimeKlijent"));
            columnServiser.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("imePrezimeZaposleni"));
            columnModel.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("nazivModela"));
            columnSerijski.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("serijskiBrojTelefona"));
            columnOpis.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("opisKvara"));
            columnDatum.setCellValueFactory(new PropertyValueFactory<ServisTelefonaDTO, String>("datumPrijema"));
            columnStanje.setCellValueFactory(new PropertyValueFactory<StanjeTelefonaDTO, String>("stanjeTelefona"));

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

            this.popuniTabeluServis();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnServisiraj(ActionEvent e) {
        ServisTelefonaDTO servis = tableServis.getSelectionModel().getSelectedItem();

        if (servis != null) {
            try {
                IzmjenaServisController.setIdModelTelefona(servis.getIdModelTelefona());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("IzmjenaServis.fxml"));
                loader.load();
                IzmjenaServisController controller = loader.getController();
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(ServisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabran servis iz tabele!");
            alert.showAndWait();
        }
    }

}
