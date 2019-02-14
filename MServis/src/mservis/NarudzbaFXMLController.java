
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
import dao.ArtikalDAO;
import dao.DobavljacDAO;
import dao.DodatnaOpremaDAO;
import dao.ModelTelefonaDAO;
import dao.NarudzbaDAO;
import dao.NarudzbaHasArtikalDAO;
import dao.RezervniDioDAO;
import dto.DobavljacDTO;
import dto.DodatnaOpremaDTO;
import dto.ModelTelefonaDTO;
import dto.NarudzbaDTO;
import dto.NarudzbaHasArtikalDTO;
import dto.RezervniDioDTO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.application.Platform;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import mySQL.MySQLDAOFactory;
import org.controlsfx.control.textfield.TextFields;

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
    List <ModelTelefonaDTO> modelTelefonLIST;
    List <RezervniDioDTO> dioLIST;
    List <DodatnaOpremaDTO> opremaLIST;
    DobavljacDAO dobavljacDAO=(new MySQLDAOFactory()).getDobavljacDAO();
    ArtikalDAO artikalDAO=(new MySQLDAOFactory().getArtikalDAO());
    ModelTelefonaDAO modelTelefonDAO=(new MySQLDAOFactory()).getModelTelefonaDAO();
    RezervniDioDAO rezervniDioDAO=(new MySQLDAOFactory()).getRezervniDioDAO();
    DodatnaOpremaDAO dodatnaOpremaDAO=(new MySQLDAOFactory()).getDodatnaOpremaDAO();
    NarudzbaDAO narudzbaDAO=(new MySQLDAOFactory()).getNarudzbaDAO();
    NarudzbaHasArtikalDAO narudzbaHasArtikalDAO=(new MySQLDAOFactory()).getNarudzbaHasArtikalDAO();
    public NarudzbaHasArtikalDTO narudzbaHasArtikalDTO;
    public int IdN=0;
    public String dobavljac;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       dobavljacLIST=dobavljacDAO.selectAll();
        for(DobavljacDTO d : dobavljacLIST)
        {
            comboDOBAVLJACI.getItems().add(d.getNaziv());
        }
        comboDOBAVLJACI.setFocusTraversable(false);
        comboTIP.getItems().addAll("TELEFON","REZERVNI DIO","DODATNA OPREMA");;
        comboTIP.setDisable(true);
        comboARTIKAL.setDisable(true);
        TextFields.bindAutoCompletion(comboDOBAVLJACI.getEditor(), comboDOBAVLJACI.getItems());
        TextFields.bindAutoCompletion(comboTIP.getEditor(), comboTIP.getItems());
        narudzbaLIST=FXCollections.observableArrayList();
        tabelaNARUDZBA.getItems().addAll(narudzbaLIST);
        tKOLICINA.setFocusTraversable(false);
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
        comboTIP.setDisable(false);
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
        NarudzbaDTO a=new NarudzbaDTO();
        a=list.get(list.size()-1);
        IdN=a.getIdNarudzba();
        narudzbaDTO.setIdNarudzba(IdN);
        System.out.println("mysqlID"+ IdN);
        comboTIP.setFocusTraversable(false);
    } 
    public void dodajArtikle(ActionEvent actionEvent)
    {
        while(comboTIP.getValue()==null)
        {
            comboTIP.getValue();
        }
        
            comboARTIKAL.getItems().clear();
            comboARTIKAL.setDisable(false);
            if(comboTIP.getValue().equals("TELEFON"))
            {
                System.out.println(comboTIP.getValue());
                modelTelefonLIST=new ArrayList<>();
                modelTelefonLIST=modelTelefonDAO.selectAll();
                for(ModelTelefonaDTO t : modelTelefonLIST)
                {
                   comboARTIKAL.getItems().add(t.getNazivModela());
                }
            }
             if(comboTIP.getValue().equals("REZERVNI DIO"))
            {
                System.out.println(comboTIP.getValue());
                dioLIST=new ArrayList<>();
                dioLIST=rezervniDioDAO.selectAllDetailed();
                for(RezervniDioDTO r : dioLIST)
                {
                   comboARTIKAL.getItems().add(r.getNazivRezervnogdijela());
                }
            }
            if(comboTIP.getValue().equals("DODATNA OPREMA"))
            {
                System.out.println(comboTIP.getValue());
                opremaLIST=new ArrayList<>();
                opremaLIST=dodatnaOpremaDAO.selectAll();
                for(DodatnaOpremaDTO o : opremaLIST)
                {
                   comboARTIKAL.getItems().add(o.getNaziv());
                }
            }
            TextFields.bindAutoCompletion(comboARTIKAL.getEditor(), comboARTIKAL.getItems());
            comboARTIKAL.setFocusTraversable(false);
        }
    
    public void btnDodaj(ActionEvent actionEvent)
    {
        int ID=0;
        if(comboDOBAVLJACI.getValue()!=null && comboTIP.getValue()!=null && comboARTIKAL.getValue()!=null && tKOLICINA.getText().length()>0 && Integer.parseInt(tKOLICINA.getText())>0 && provjeri(String.valueOf(comboARTIKAL.getValue())))
        {
             if(comboTIP.getValue().equals("TELEFON"))
             {
                for(ModelTelefonaDTO t : modelTelefonLIST)
                {
                    if(t.getNazivModela().equals(String.valueOf(comboARTIKAL.getValue())))
                        ID=t.getIdModeltelefona();
                }
             }
              if(comboTIP.getValue().equals("REZERVNI DIO"))
             {
                for(RezervniDioDTO r : dioLIST)
                {
                    if(r.getNazivRezervnogdijela().equals(String.valueOf(comboARTIKAL.getValue())))
                        ID=r.getIdRezervniDio();
                }
             }
               if(comboTIP.getValue().equals("DODATNA OPREMA"))
             {
                for(DodatnaOpremaDTO d : opremaLIST)
                {
                    if(d.getNaziv().equals(String.valueOf(comboARTIKAL.getValue())))
                        ID=d.getIdDodatnaOprema();
                }
             }
           System.out.println("ID="+ID);
           narudzbaHasArtikalDTO=new NarudzbaHasArtikalDTO(IdN,ID,Integer.parseInt(tKOLICINA.getText()),String.valueOf(comboARTIKAL.getValue()));
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
        String ime="./Narudzba/Narudzba"+IdN+".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(ime));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NarudzbaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.open();
    
            try {
              Image img = Image.getInstance("./resources/Logo.png");
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
        
        File file=new File(ime);
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(NarudzbaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
	
       
        sendMail(ime);
        ((Stage) aPANE.getScene().getWindow()).close();
        
    }
    
    public void btnOdustani(ActionEvent actionEvent)
    {
        if(IdN>0)
        {
            /*
        List<NarudzbaHasArtikalDTO> pom=new ArrayList<>(); 
        pom=narudzbaHasArtikalDAO.selectAll();
        for(NarudzbaHasArtikalDTO n : pom)
        {
            if(n.getIdNarudzba()==IdN)
                narudzbaHasArtikalDAO.delete(n);
        }
        System.out.println(narudzbaDTO.getIdNarudzba());
*/
        narudzbaDAO.delete(narudzbaDTO);
        System.out.println("Obrisana:"+narudzbaDTO.getIdNarudzba());
        }
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
    
    private boolean provjeri(String artikal)
    {
        if(artikal!=null)
        {
        List<ModelTelefonaDTO> listaModela=new ArrayList<ModelTelefonaDTO>();
        listaModela.addAll(modelTelefonDAO.selectAll());
        for(ModelTelefonaDTO m: listaModela)
        {
            if(m.getNazivModela().equals(artikal))
                return true;
        }
        List<RezervniDioDTO> listaDio=new ArrayList<RezervniDioDTO>();
        listaDio.addAll(rezervniDioDAO.selectAllDetailed());
        for(RezervniDioDTO r: listaDio)
        {
            if(r.getNazivRezervnogdijela().equals(artikal))
                return true;
        }
        List<DodatnaOpremaDTO> listaOprema=new ArrayList<DodatnaOpremaDTO>();
        listaOprema.addAll(dodatnaOpremaDAO.selectAll());
        for(DodatnaOpremaDTO d: listaOprema)
        {
            if(d.getNaziv().equals(artikal))
                return true;
        }
        }
        return false;
    }
    
    private void sendMail(String putanja)
    {
     
	final String username = "servis.m.info@gmail.com";
        final String password = "servis123";
        String fromEmail = "servis.m.info@gmail.com";
        String toEmail = "kris.jelica@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("Narudžba");
			
			Multipart emailContent = new MimeMultipart();
			
			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("Pozdrav, u prilogu vam šaljem novu narudžbu");
			
			//Attachment body part.
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile(putanja);
			
			//Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);
			
			//Attach multipart to message
			msg.setContent(emailContent);
			
			Transport.send(msg);
			System.out.println("Sent message");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
