/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * 
 */
public class BookingController implements Initializable {

    Connection conn = sqliteconnector.dbconnector();
     ObservableList<user> data = FXCollections.observableArrayList();
     ObservableList<String> combo = FXCollections.observableArrayList("Paypall","Visa Card","Master Card");
     FilteredList<user> filter=new FilteredList<>(data,e->true);
     PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    TextField room1;
    @FXML
    TableView<user> table;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;
    @FXML
    TableColumn<?, ?> c3;
    @FXML
    TableColumn<?, ?> c4;
    @FXML
    TableColumn<?, ?> c5;
    @FXML
    TableColumn<?, ?> c6;
    @FXML
    DatePicker date1;
    @FXML
    DatePicker date2;
    @FXML
    TextField search1;
    @FXML
    RadioButton r1,r2;
    @FXML
    ComboBox<String> pay1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("Roomno"));
        c2.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        c3.setCellValueFactory(new PropertyValueFactory<>("Slot"));
        c4.setCellValueFactory(new PropertyValueFactory<>("Status"));
       pay1.setItems(combo);
    }    
    public void loaddatabase()
    {
        room1.clear();
        pay1.setValue("Payment");
        String query = "select * from room";
        try {
            date1.setValue(null);
            date2.setValue(null);
            r1.setSelected(false);
            r2.setSelected(false);
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new user(result.getString("Roomno"),
                                result.getString("Capacity"),
                                result.getString("Slot"),
                                result.getString("Status")
                        )
                );
                table.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    @FXML
    public void search()
    {
        search1.textProperty().addListener((observableValue,oldValue,newValue)->{
            filter.setPredicate((Predicate<? super user>)user2->{
                if(newValue==null || newValue.isEmpty()){
                    return true;
                }
                String lowercase=newValue.toLowerCase();
                if(user2.getSlot().toLowerCase().contains(lowercase)){
                    return true;
                }
                return false;
            });
        });
        SortedList<user> sort=new SortedList<>(filter);
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sort);
    }
    @FXML
    static String temp;
    public void showonclick() {
        try {
            user user2 = (user) table.getSelectionModel().getSelectedItem();
            String query = "select * from room";
            prepare = conn.prepareStatement(query);
            //result=prepare.executeQuery();
            temp=user2.getRoomno();
            room1.setText(user2.getRoomno());
            prepare.close();
            result.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    @FXML
    public void book() throws SQLException
    {
        String query = "update room set Status=?,Date1=?,Date2=?,Payment=? where Roomno='"+temp+"'";
        //prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            if(r1.isSelected()){
            prepare.setString(1, r1.getText().toUpperCase());
            }
            else if(r2.isSelected())
            {
                prepare.setString(1, r2.getText().toUpperCase());
            }
            prepare.setString(2, ((TextField)date1.getEditor()).getText());
            prepare.setString(3, ((TextField)date2.getEditor()).getText());
            prepare.setString(4, pay1.getValue());
             prepare.executeUpdate();
            prepare.close();
            loaddatabase();
        } catch (Exception e) {
            System.err.println(e);
        } 
    }
}
