/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mservis;


import dao.ProizvodjacDAO;
import dto.ProizvodjacDTO;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mySQL.MySQLDAOFactory;

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
        scene.getStylesheets().add("dark-theme.css");
        stage.setTitle("Prijavljivanje");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:resources" + File.separator + "icon.png"));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        //stage.hide();
        

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);

    }
    
}
