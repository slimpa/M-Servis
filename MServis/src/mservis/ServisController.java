/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.KlijentDAO;
import dao.ModelTelefonaDAO;
import dao.ServisTelefonaDAO;
import dao.ServisTelefonaHasCjenovnikUslugaDAO;
import dao.StanjeTelefonaDAO;
import dao.UgradjeniRezervniDioDAO;
import dao.ZaposleniDAO;
import dto.KlijentDTO;
import dto.ModelTelefonaDTO;
import dto.ServisTelefonaDTO;
import dto.ServisTelefonaHasCjenovnikUslugaDTO;
import dto.StanjeTelefonaDTO;
import dto.UgradjeniRezervniDioDTO;
import dto.ZaposleniDTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mySQL.MySQLDAOFactory;
import net.sf.jasperreports.engine.JRException;
import service.GeneratorIzvjestaja;
import service.StavkaServisa;

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

    @FXML
    private Button btnPreuzimanje;

    @FXML
    private Button btnIzadji;

    private ServisTelefonaDAO servisDao = new MySQLDAOFactory().getServisTelefonaDAO();
    private KlijentDAO klijentDao = new MySQLDAOFactory().getKlijentDAO();
    private ZaposleniDAO zaposleniDao = new MySQLDAOFactory().getZaposleniDAO();
    private ModelTelefonaDAO modelDao = new MySQLDAOFactory().getModelTelefonaDAO();
    private StanjeTelefonaDAO stanjeDao = new MySQLDAOFactory().getStanjeTelefonaDAO();
    private String zaposleniKorisnicko = LoginController.getKorisnickoIme();
    private static int idServisa;
    private static int idModelTelefona;
    private static boolean naServisu = false;
    private static UgradjeniRezervniDioDAO ugradjeniDao = new MySQLDAOFactory().getUgradjeniRezervniDioDAO();
    private static ServisTelefonaHasCjenovnikUslugaDAO uslugeDao = new MySQLDAOFactory().getServisTelefonaHasCjenovnikUslugaDAO();
    private static boolean uspjesno = false;

    public static int getIdServisa() {
        return idServisa;
    }

    public static int getIdModelTelefona() {
        return idModelTelefona;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.popuniComboStanja();
        this.popuniTabeluServis();
    }

    private void popuniComboStanja() {
        List<StanjeTelefonaDTO> lista = stanjeDao.selectAll();
        List<String> naziviStanja = new ArrayList<>();

        if (lista != null) {
            for (StanjeTelefonaDTO s : lista) {
                naziviStanja.add(s.getStanje());
            }

            ObservableList<String> stanja = FXCollections.observableArrayList(naziviStanja);
            cbStanje.setItems(stanja);

        }

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
            scene.getStylesheets().add("dark-theme.css");
            stage.setTitle("Novi servis telefona");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
            stage.showAndWait();

            this.popuniTabeluServis();
            if (uspjesno) {
                this.generisiPotvrdu();
            }
            uspjesno = false;

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generisiPotvrdu() {
        ObservableList<ServisTelefonaDTO> listaServisa = tableServis.getItems();
        ServisTelefonaDTO servis = listaServisa.get(listaServisa.size() - 1);

        GeneratorIzvjestaja gen = new GeneratorIzvjestaja();
        try {
            gen.potvrdaServis(servis.getIdServisTelefona(), servis.getSerijskiBrojTelefona(), servis.getNazivModela());
        } catch (JRException ex) {
            Logger.getLogger(ServisController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void btnServisiraj(ActionEvent e) {
        ServisTelefonaDTO servis = tableServis.getSelectionModel().getSelectedItem();
        List<StanjeTelefonaDTO> stanja = stanjeDao.selectAll();
        int idPopravljenog = 0;
        int idNaServisu = 0;
        int idNemoguce = 0;
        for (StanjeTelefonaDTO s : stanja) {
            if ("Popravljen".equals(s.getStanje())) {
                idPopravljenog = s.getIdStanjeTelefona();
            } else if ("Na servisu".equals(s.getStanje())) {
                idNaServisu = s.getIdStanjeTelefona();
            } else if ("Nemoguće popraviti".equals(s.getStanje())) {
                idNemoguce = s.getIdStanjeTelefona();
            }
        }
        if (servis != null && servis.getIdStanjeTelefona() != idPopravljenog && servis.getIdStanjeTelefona() != idNemoguce) {
            try {
                idServisa = servis.getIdServisTelefona();
                idModelTelefona = servis.getIdModelTelefona();
                if (servis.getIdStanjeTelefona() == idNaServisu) {
                    naServisu = true;
                } else {
                    naServisu = false;
                }

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("IzmjenaServis.fxml"));
                loader.load();
                IzmjenaServisController controller = loader.getController();

                Parent p = loader.getRoot();
                Stage stage = new Stage();
                Scene scene = new Scene(p);
                scene.getStylesheets().add("dark-theme.css");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
                stage.showAndWait();

                this.popuniTabeluServis();
            } catch (IOException ex) {
                Logger.getLogger(ServisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (servis != null && servis.getIdStanjeTelefona() == idPopravljenog) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Telefon je popravljen, nemoguća izmjena!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        } else if (servis != null && servis.getIdStanjeTelefona() == idNemoguce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Telefon je nemoguće popraviti!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabran servis iz tabele!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        }
    }

    public static boolean isNaServisu() {
        return naServisu;
    }

    public static void setNaServisu(boolean naServisu) {
        ServisController.naServisu = naServisu;
    }

    public void btnPretraziHandler(ActionEvent e) {
        if ("".equals(tfIdServisa.getText()) && cbStanje.getSelectionModel().getSelectedItem() == null) {
            this.popuniTabeluServis();

        } else if (!"".equals(tfIdServisa.getText()) || cbStanje.getSelectionModel().getSelectedItem() != null) {

            int idServisa = 0;
            String stanje = "";
            List<ServisTelefonaDTO> lista = new ArrayList<>();
            StanjeTelefonaDTO stanjeDto = null;

            if (!"".equals(tfIdServisa.getText())) {
                idServisa = Integer.parseInt(tfIdServisa.getText());
            }

            if (cbStanje.getSelectionModel().getSelectedItem() != null) {
                stanje = (String) cbStanje.getSelectionModel().getSelectedItem();
                stanjeDto = stanjeDao.selectBy(new StanjeTelefonaDTO(stanje)).get(0);
            }

            if (idServisa != 0 && stanjeDto != null) {
                lista = servisDao.selectBy(new ServisTelefonaDTO(idServisa, stanjeDto.getIdStanjeTelefona()));
            } else if (idServisa != 0 && stanjeDto == null) {
                lista = servisDao.selectById(new ServisTelefonaDTO(idServisa));
            } else if (idServisa == 0 && stanjeDto != null) {
                lista = servisDao.selectByStanje(new ServisTelefonaDTO(stanjeDto));
            }
            ObservableList<ServisTelefonaDTO> listaServisa = null;

            if (lista != null) {
                for (ServisTelefonaDTO servis : lista) {
                    KlijentDTO klijent = klijentDao.selectBy(new KlijentDTO(servis.getIdKlijent())).get(0);
                    servis.setImePrezimeKlijent(klijent.getIme() + " " + klijent.getPrezime());

                    ZaposleniDTO zaposleni = zaposleniDao.selectFromSviZaposleni(new ZaposleniDTO(servis.getIdZaposleni()));
                    servis.setImePrezimeZaposleni(zaposleni.getIme() + " " + zaposleni.getPrezime());

                    ModelTelefonaDTO model = modelDao.selectById(new ModelTelefonaDTO(servis.getIdModelTelefona())).get(0);
                    servis.setNazivModela(model.getNazivModela());

                    StanjeTelefonaDTO stanjeTelefona = stanjeDao.selectById(new StanjeTelefonaDTO(servis.getIdStanjeTelefona()));
                    servis.setStanjeTelefona(stanjeTelefona.getStanje());

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
        cbStanje.setValue(null);
        tfIdServisa.setText("");
    }

    public void btnPreuzimanjeHandler(ActionEvent e) {
        ServisTelefonaDTO servis = tableServis.getSelectionModel().getSelectedItem();
        if (servis != null) {
            List<UgradjeniRezervniDioDTO> dijelovi = ugradjeniDao.selectCijena(new UgradjeniRezervniDioDTO(servis.getIdServisTelefona()));
            List<ServisTelefonaHasCjenovnikUslugaDTO> usluge = uslugeDao.selectCijena(new ServisTelefonaHasCjenovnikUslugaDTO(servis.getIdServisTelefona()));
            List<StavkaServisa> stavke = new ArrayList<>();

            for (UgradjeniRezervniDioDTO dio : dijelovi) {
                stavke.add(new StavkaServisa(servis.getIdServisTelefona(), dio.getIdRezervniDio(), dio.getNazivDijela(), dio.getCijena()));
            }

            for (ServisTelefonaHasCjenovnikUslugaDTO usluga : usluge) {
                stavke.add(new StavkaServisa(servis.getIdServisTelefona(), usluga.getIdCjenovnikUsluga(), usluga.getNazivUsluge(), usluga.getCijena()));
            }

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Racun.fxml"));
                loader.load();
                RacunController controller = loader.getController();
                controller.setIdServisa(servis.getIdServisTelefona());
                controller.popuniTabelu(stavke);
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                Scene scene = new Scene(p);
                scene.getStylesheets().add("dark-theme.css");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
                stage.showAndWait();

                this.popuniTabeluServis();
            } catch (IOException ex) {
                Logger.getLogger(ServisController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška!");
            alert.setHeaderText(null);
            alert.setContentText("Nije odabran servis iz tabele!");
            alert.getDialogPane().getScene().getStylesheets().add("dark-theme.css");
            alert.showAndWait();
        }
    }

    public static boolean isUspjesno() {
        return uspjesno;
    }

    public static void setUspjesno(boolean uspjesno) {
        ServisController.uspjesno = uspjesno;
    }

    public void btnIzadji(ActionEvent e) {
        Stage stage = (Stage) btnIzadji.getScene().getWindow();
        stage.close();
    }

}
