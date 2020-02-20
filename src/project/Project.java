/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * 
 */
public class project extends Application {
    public static Stage stage;
    public static BorderPane borser;
    Connection conn;
    @Override
    public void start(Stage primaryStage) throws SQLException, IOException {
      Parent root=FXMLLoader.load(getClass().getResource("Radiobutton.fxml"));
      Scene scene=new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
    }
    public static void showmain() throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(project.class.getResource("main.fxml"));
        borser=loader.load();
       Scene scene=new Scene(borser);
       stage.setScene(scene);
       stage.show();
    }
    public static void showinformationalert(String message)
    {
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void showconfirmationalert(String message)
    {
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void show() throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(project.class.getResource("main1.fxml"));
       BorderPane borser1=loader.load();
       borser.setCenter(borser1);
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
