/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ArtikalDAO;
import dao.DnevniIzvjestajDAO;
import dto.ArtikalDTO;
import dto.DnevniIzvjestajDTO;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mySQL.MySQLDAOFactory;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author Nikola
 */
public class GeneratorIzvjestaja {
    

    
     public void trenutnoStanje(String putanja) throws JRException {
        ArtikalDAO artikalDao = new MySQLDAOFactory().getArtikalDAO();
        ArrayList<ArtikalDTO> artikli = (ArrayList<ArtikalDTO>) artikalDao.selectAll();

        List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();
        for (ArtikalDTO artikal : artikli) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("id", artikal.getIdArtikal());
            m.put("barKod", artikal.getBarKod());
            m.put("naziv", artikal.getNaziv());
            m.put("kolicina", artikal.getKolicina());
            dataSource.add(m);
        }

        JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
        String sourceName = "resources/TrenutnoStanjeReport.jrxml";

        JasperReport report = JasperCompileManager.compileReport(sourceName);
        JasperPrint filledReport = JasperFillManager.fillReport(report, null, jrDataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        ExporterInput exporterInput = new SimpleExporterInput(filledReport);
        exporter.setExporterInput(exporterInput);

        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(putanja+File.separator+LocalDate.now() +"TrenutnoStanje.pdf");
        exporter.setExporterOutput(exporterOutput);
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        System.out.print("Uspjesno kreiran izvjestaj - Trenutno stanje!");
    }
     
         public void dnevniIzvjestaj(String putanja) throws JRException {
        DnevniIzvjestajDAO izvjestajDao = new MySQLDAOFactory().getDnevniIzvjestajDAO();
        double ukupnaCijena = 0, pdv = 0;
        ArrayList<DnevniIzvjestajDTO> artikli = (ArrayList<DnevniIzvjestajDTO>) izvjestajDao.selectAll();
        for (DnevniIzvjestajDTO a : artikli) {
            ukupnaCijena += a.getCijena() * a.getKolicina();
            System.out.println(a);
        }
        pdv = ukupnaCijena * 0.17;
        pdv = Math.round(pdv * 100) / 100.0d;
        ukupnaCijena = Math.round(ukupnaCijena * 100) / 100.0d;

        List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();
        for (DnevniIzvjestajDTO racun : artikli) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("idArtikal", racun.getIdArtikal());
            m.put("idRacun", racun.getIdRacun());
            m.put("naziv", racun.getNaziv());
            m.put("kolicina", racun.getKolicina());
            m.put("cijena", racun.getCijena());
            m.put("ukupno", ukupnaCijena);
            m.put("pdv", pdv);
            dataSource.add(m);
        }
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
        String sourceName = "resources/DnevniIzvjestajReport.jrxml";

        JasperReport report = JasperCompileManager.compileReport(sourceName);
        JasperPrint filledReport = JasperFillManager.fillReport(report, null, jrDataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        ExporterInput exporterInput = new SimpleExporterInput(filledReport);
        exporter.setExporterInput(exporterInput);

        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(putanja+File.separator+LocalDate.now()+"DnevniIzvjestajProdaje.pdf");
        exporter.setExporterOutput(exporterOutput);
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        System.out.print("Uspjesno kreiran izvjestaj - Dnevni izvjestaj!");
    }
}
