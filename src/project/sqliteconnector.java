/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dibyendu
 */
public class sqliteconnector {
    
    public static Connection dbconnector() 
    {
        try{
            Connection connection=null;
            Class.forName("org.sqlite.JDBC");
            connection=DriverManager.getConnection("jdbc:sqlite:project.sqlite");
            return connection;
        }catch(ClassNotFoundException e)
        {
            
        } catch (SQLException ex) {
            Logger.getLogger(sqliteconnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
