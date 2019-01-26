/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import dao.ArtikalDAO;
import dao.DobavljacDAO;
import dao.DodatnaOpremaDAO;
import dao.NarudzbaDAO;
import dao.NarudzbaHasArtikalDAO;
import dao.RezervniDioDAO;
import dao.TelefonDAO;
import dto.DobavljacDTO;
import dto.DodatnaOpremaDTO;
import dto.NarudzbaDTO;
import dto.NarudzbaHasArtikalDTO;
import dto.RezervniDioDTO;
import dto.TelefonDTO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mySQL.MySQLDAOFactory;
import mySQL.MySQLNarudzbaHasArtikalDAO;
import javafx.scene.paint.Color;

public class NarudzbaFXMLController implements Initializable {

    @FXML
    private ComboBox comboDOBAVLJACI;
    @FXML 
    private ComboBox<String> comboTIP;
    @FXML 
    private ComboBox comboARTIKAL;
    @FXML 
    private TextField tKOLICINA;
    @FXML
    private Button btnDODAJ;  
    @FXML
    private Button btnOBRISI;
    @FXML
    private Button btnKREIRAJ;
    @FXML
    private TableView<NarudzbaHasArtikalDTO> tabelaNARUDZBA;
    @FXML
    private TableColumn columnID;
    @FXML 
    private TableColumn columnARTIKAL;
    @FXML 
    private TableColumn columnKOL;
    @FXML
    private AnchorPane aPANE;
    List<DobavljacDTO> dobavljacLIST=new ArrayList<>();
    ObservableList<NarudzbaHasArtikalDTO> narudzbaLIST;
    NarudzbaDTO narudzbaDTO=new NarudzbaDTO();
    List <TelefonDTO> telefonLIST;
    List <RezervniDioDTO> dioLIST;
    List <DodatnaOpremaDTO> opremaLIST;
    DobavljacDAO dobavljacDAO=(new MySQLDAOFactory()).getDobavljacDAO();
    ArtikalDAO artikalDAO=(new MySQLDAOFactory().getArtikalDAO());
    TelefonDAO telefonDAO=(new MySQLDAOFactory()).getTelefonDAO();
    RezervniDioDAO rezervniDioDAO=(new MySQLDAOFactory()).getRezervniDioDAO();
    DodatnaOpremaDAO dodatnaOpremaDAO=(new MySQLDAOFactory()).getDodatnaOpremaDAO();
    NarudzbaDAO narudzbaDAO=(new MySQLDAOFactory()).getNarudzbaDAO();
    NarudzbaHasArtikalDAO narudzbaHasArtikalDAO=(new MySQLDAOFactory()).getNarudzbaHasArtikalDAO();
    public int IdNarudzbe=0;
    public NarudzbaHasArtikalDTO narudzbaHasArtikalDTO;
    public int IdN;
    public String dobavljac;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       dobavljacLIST=dobavljacDAO.selectAll();
        for(DobavljacDTO d : dobavljacLIST)
        {
            comboDOBAVLJACI.getItems().add(d.getNaziv());
        }
        comboTIP.getItems().addAll("TELEFON","REZERVNI DIO","DODATNA OPREMA");
        comboARTIKAL.setDisable(true);
        narudzbaLIST=FXCollections.observableArrayList();
        tabelaNARUDZBA.getItems().addAll(narudzbaLIST);
        columnID.setCellValueFactory(new PropertyValueFactory<NarudzbaHasArtikalDTO,Integer>("IdArtikal"));
        columnKOL.setCellValueFactory(new PropertyValueFactory<NarudzbaHasArtikalDTO,Integer>("Kolicina"));
        columnARTIKAL.setCellValueFactory(new PropertyValueFactory<NarudzbaHasArtikalDTO,String>("Naziv"));
    }  
    
    public void insertNarudzbu(ActionEvent avtionEvent)
    {
        while(comboDOBAVLJACI.getValue()==null)
        {
            comboDOBAVLJACI.getValue();
        }
        dobavljac=String.valueOf(comboDOBAVLJACI.getValue());
        comboDOBAVLJACI.setDisable(true);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int id=0;
        for(DobavljacDTO d : dobavljacLIST)
        {
            if(String.valueOf(comboDOBAVLJACI.getValue())==d.getNaziv())
                id=d.getIdDobavljac();     
            System.out.println("id="+id);
        }
        narudzbaDTO.setDatumNarudzbe(timestamp);
        narudzbaDTO.setIdDobavljac(id);
        System.out.println(narudzbaDAO.insert(narudzbaDTO));
        List<NarudzbaDTO> list=new ArrayList<>();
        list=narudzbaDAO.selectAll();
        IdNarudzbe=list.size();
        IdN=IdNarudzbe;
        narudzbaDTO.setIdNarudzba(IdNarudzbe);
        System.out.println("mysqlID"+ IdN);
    } 
    public void dodajArtikle(ActionEvent actionEvent)
    {
        while(comboTIP.getValue()==null)
        {
            comboTIP.getValue();
        }
        
            comboARTIKAL.getItems().clear();
            comboARTIKAL.setDisable(false);
            if(comboTIP.getValue()=="TELEFON")
            {
                System.out.println(comboTIP.getValue());
                telefonLIST=new ArrayList<>();
                telefonLIST=telefonDAO.selectAll();
                for(TelefonDTO t : telefonLIST)
                {
                   comboARTIKAL.getItems().add(t.getNaziv());
                }
            }
             if(comboTIP.getValue()=="REZERVNI DIO")
            {
                System.out.println(comboTIP.getValue());
                dioLIST=new ArrayList<>();
                dioLIST=rezervniDioDAO.selectAllDetailed();
                for(RezervniDioDTO r : dioLIST)
                {
                   comboARTIKAL.getItems().add(r.getNaziv());
                }
            }
            if(comboTIP.getValue()=="DODATNA OPREMA")
            {
                System.out.println(comboTIP.getValue());
                opremaLIST=new ArrayList<>();
                opremaLIST=dodatnaOpremaDAO.selectAll();
                for(DodatnaOpremaDTO o : opremaLIST)
                {
                   comboARTIKAL.getItems().add(o.getNaziv());
                }
            }
        }
    
    public void btnDodaj(ActionEvent actionEvent)
    {
        int ID=0;
        if(comboDOBAVLJACI.getValue()!=null && comboTIP!=null && comboARTIKAL.getValue()!=null && tKOLICINA.getText()!=null && Integer.parseInt(tKOLICINA.getText())>0 )
        {
             if(comboTIP.getValue()=="TELEFON")
             {
                for(TelefonDTO t : telefonLIST)
                {
                    if(t.getNaziv()==String.valueOf(comboARTIKAL.getValue()))
                        ID=t.getIdModelTelefona();
                }
             }
              if(comboTIP.getValue()=="REZERVNI DIO")
             {
                for(RezervniDioDTO r : dioLIST)
                {
                    if(r.getNaziv()==String.valueOf(comboARTIKAL.getValue()))
                        ID=r.getIdArtikal();
                }
             }
               if(comboTIP.getValue()=="DODATNA OPREMA")
             {
                for(DodatnaOpremaDTO d : opremaLIST)
                {
                    if(d.getNaziv()==String.valueOf(comboARTIKAL.getValue()))
                        ID=d.getIdDodatnaOprema();
                }
             }
           System.out.println("ID="+ID);
           narudzbaHasArtikalDTO=new NarudzbaHasArtikalDTO(IdNarudzbe,ID,Integer.parseInt(tKOLICINA.getText()),String.valueOf(comboARTIKAL.getValue()));
           System.out.println(narudzbaHasArtikalDAO.insert(narudzbaHasArtikalDTO));
           osvjezi();
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Obavijest");
            alert.setHeaderText(null);
            alert.setContentText("Podaci nisu ispravno unijeti.");
            alert.showAndWait();
        }
        tKOLICINA.clear();
    }
    
    public void btnObrisi(ActionEvent actionEvent)
    {
        narudzbaHasArtikalDTO=tabelaNARUDZBA.getSelectionModel().getSelectedItem();
        if(narudzbaHasArtikalDTO!=null)
            narudzbaHasArtikalDAO.delete(narudzbaHasArtikalDTO);
        tabelaNARUDZBA.getItems().remove(narudzbaHasArtikalDTO);
        osvjezi();
        
    }
     
    public void btnKreiraj(ActionEvent actionEvent) throws DocumentException
    {
        Document document = new Document();
        try {
            String ime="Narudzba/Narudzba"+IdN+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(ime));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NarudzbaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.open();
    
            try {
              Image img = Image.getInstance("C:\\Users\\Korisnik\\Desktop\\M-Servis-master\\MServis\\Slike\\Logo.png");
              img.scaleAbsolute(250f, 200f);
              img.setAbsolutePosition(175f, 700f);
              document.add(img);
            } catch (BadElementException ex) {
                Logger.getLogger(NarudzbaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NarudzbaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        document.add(new Phrase("\n"));
        document.add(new Phrase("\n"));
        document.add(new Phrase("\n"));
        document.add(new Phrase("\n"));
        Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 18, BaseColor.BLACK);
        Chunk chunk = new Chunk("Narudzba "+(new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime())), font);
        chunk.setUnderline(0.5f, -2f);
        document.add(chunk);
        document.add(new Phrase("\n"));
        document.add(new Phrase("\n"));
        Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        Chunk c = new Chunk("Dobavljac: "+dobavljac ,font1);
        document.add(c);
        document.add(new Phrase("\n"));
        document.add(new Phrase("\n"));
        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        for(NarudzbaHasArtikalDTO n:narudzbaLIST)
        {
           addRows(table,n);
        }
 
        try {
            document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(NarudzbaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
        ((Stage) aPANE.getScene().getWindow()).close();
        
    }
    
    public void btnOdustani(ActionEvent actionEvent)
    {
        List<NarudzbaHasArtikalDTO> pom=new ArrayList<>(); 
        pom=narudzbaHasArtikalDAO.selectAll();
        for(NarudzbaHasArtikalDTO n:pom)
        {
            if(n.getIdNarudzba()==IdN)
                narudzbaHasArtikalDAO.delete(n);
        }
        System.out.println(narudzbaDTO.getIdNarudzba());
        narudzbaDAO.delete(narudzbaDTO);
        ((Stage) aPANE.getScene().getWindow()).close();
    }
    
    public void osvjezi()
    {
        narudzbaLIST.clear();
        List<NarudzbaHasArtikalDTO> pom=new ArrayList<>(); 
        pom=narudzbaHasArtikalDAO.selectAll();
        for(NarudzbaHasArtikalDTO n:pom)
            if(n.getIdNarudzba()==IdN)
                narudzbaLIST.add(n);         
        tabelaNARUDZBA.setItems(narudzbaLIST);
        tabelaNARUDZBA.refresh();
        
    }
    private void addTableHeader(PdfPTable table) {
    Stream.of("Id Artikal", "Naziv Artikla", "Kolicina")
      .forEach(new Consumer<String>() {
        @Override
        public void accept(String columnTitle) {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        }
    });
   }
    private void addRows(PdfPTable table,NarudzbaHasArtikalDTO n) 
    {
    table.addCell(Integer.toString(n.getIdArtikal()));
    table.addCell(n.getNaziv());
    table.addCell(Integer.toString(n.getKolicina()));
   }
    
}
