/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class Main1Controller implements Initializable {

    Connection conn = sqliteconnector.dbconnector();

    ObservableList<user> data = FXCollections.observableArrayList();
    FilteredList<user> filter=new FilteredList<>(data,e->true);
    PreparedStatement prepare = null;
    ResultSet result = null;
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
    TextField room1;
    @FXML
    TextField capacity1;
    @FXML
    DatePicker date1;
    @FXML
    DatePicker date2;
    @FXML
    TextField slot1;
    @FXML
    TextField search1;
    @FXML
    RadioButton r1,r2;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        c1.setCellValueFactory(new PropertyValueFactory<>("Roomno"));
        c2.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        c5.setCellValueFactory(new PropertyValueFactory<>("Date1"));
        c6.setCellValueFactory(new PropertyValueFactory<>("Date2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("Slot"));
        c4.setCellValueFactory(new PropertyValueFactory<>("Status"));
       // loaddatabase();
    }

    public void loaddatabase() {
        String query = "select * from room";
        try {
            room1.clear();
            capacity1.clear();
            date1.setValue(null);
            date2.setValue(null);
            slot1.clear();
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
                                result.getString("Status"),
                                result.getString("Date1"),
                                result.getString("Date2")
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
    public void adduser() throws SQLException {
        String room = room1.getText();
        String slot = slot1.getText();
        String capacity= capacity1.getText();
        String query = "insert into room(Roomno,Capacity,Slot,Status,Date1,Date2)values(?,?,?,?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setString(1, room);
            prepare.setString(2, capacity);
            prepare.setString(5, ((TextField)date1.getEditor()).getText());
             prepare.setString(6, ((TextField)date2.getEditor()).getText());
            prepare.setString(3, slot);
            if(r1.isSelected()){
            prepare.setString(4, r1.getText());
            }
            else{
                prepare.setString(4, r2.getText());
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            prepare.execute();
            prepare.close();
        }
        project.showinformationalert("New Room " + room + " added successfully");

        loaddatabase();
    }
 static String temp;
    @FXML
    public void showonclick() {
        try {
            user user2 = (user) table.getSelectionModel().getSelectedItem();
            String query = "select * from room";
            prepare = conn.prepareStatement(query);
            //result=prepare.executeQuery();
            temp=user2.getRoomno();
            room1.setText(user2.getRoomno());
            capacity1.setText(user2.getCapacity());
            ((TextField)date1.getEditor()).setText(user2.getDate1());
            ((TextField)date2.getEditor()).setText(user2.getDate2());
            slot1.setText(user2.getSlot());
            if(user2.getStatus().toLowerCase().equals(r1.getText().toLowerCase()))
            {
                r1.setSelected(true);
            }else if(user2.getStatus().toLowerCase().equals(r2.getText().toLowerCase()))
            {
                r2.setSelected(true);
            }else{
                r1.setSelected(false);
                r2.setSelected(false);
            }
            prepare.close();
            result.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void deleteuser() throws SQLException {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure want to delete?");
        Optional <ButtonType>action=alert.showAndWait();
        if(action.get()==ButtonType.OK)
        {
            try{
            user user2 = (user) table.getSelectionModel().getSelectedItem();
            String query = "delete from room where Roomno=?";
            prepare = conn.prepareStatement(query);
            prepare.setString(1, user2.getRoomno());
            prepare.executeUpdate();
            prepare.close();
             loaddatabase();
            } catch (SQLException e) {
            System.err.println(e);
        }
        }
            loaddatabase();
    }
    @FXML
    public void updateuser()
    {
        String query="update room set Roomno=?,Capacity=?,Slot=?,Status=?,Date1=?,Date2=? where Roomno='"+temp+"'";
        try{
            prepare=conn.prepareStatement(query);
            prepare.setString(1, room1.getText());
            prepare.setString(2, capacity1.getText());
            prepare.setString(3, slot1.getText());
            if(r1.isSelected())
            {
                prepare.setString(4, r1.getText());
            }
            else{
                prepare.setString(4, r2.getText());
            }
            prepare.setString(5, ((TextField)date1.getEditor()).getText());
             prepare.setString(6, ((TextField)date2.getEditor()).getText());
            prepare.execute();
            prepare.close();
            project.showinformationalert("user "+room1.getText()+" updated succesfully");
             loaddatabase();
        }catch(Exception e)
        {
            System.err.println(e);
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
                if(user2.getDate1().toLowerCase().contains(lowercase)){
                    return true;
                }
                else if(user2.getSlot().toLowerCase().contains(lowercase)){
                    return true;
                }
                else if(user2.getDate2().toLowerCase().contains(lowercase)){
                    return true;
                }
                return false;
            });
        });
        SortedList<user> sort=new SortedList<>(filter);
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sort);
    }
}
