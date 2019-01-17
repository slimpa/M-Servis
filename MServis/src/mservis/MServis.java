/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;

import dao.*;
import dto.OsobaDTO;
import java.util.ArrayList;
import java.util.List;
import mySQL.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mySQL.MySQLOsobaDAO;

/**
 *
 * @author Bojan
 */
public class MServis extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Prijavljivanje");
        stage.setScene(scene);
        stage.show();
        //stage.hide();
        

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // launch(args);
       System.out.print("AAAA");
        OsobaDAO osoba=new MySQLDAOFactory().getOsobaDAO();
        List<OsobaDTO> lista=osoba.selectAll();
        for(OsobaDTO o:lista){
            System.out.println(o.idOsoba+" "+o.Ime);
                    
        }
    }
    
}
