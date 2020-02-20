/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class MainController implements Initializable{

    loginmodel log=new loginmodel();
    @FXML
    TextField t1;
    @FXML
    PasswordField p1;
    @FXML
    Label l1;
    public void login(ActionEvent e) throws IOException, SQLException
    {
        if(log.valid(t1.getText(), p1.getText())==true)
        {
            Stage primaryStage=new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("main1.fxml"));
      Scene scene=new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
        }
        else{
            l1.setText("SORRY BRO");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
