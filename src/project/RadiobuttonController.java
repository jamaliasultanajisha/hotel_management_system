/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class RadiobuttonController implements Initializable {
    @FXML
    RadioButton a1;
    @FXML
    RadioButton u1;
    public void select() throws IOException
    {
        if(a1.isSelected())
        {
            Stage primaryStage=new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("booking.fxml"));
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else if(u1.isSelected()){
             Stage primaryStage=new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("booking.fxml"));
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
