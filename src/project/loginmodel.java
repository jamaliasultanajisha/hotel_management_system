/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class loginmodel {
    Connection conn;
    loginmodel() 
    {
        conn=sqliteconnector.dbconnector();
    }
    public boolean valid(String user,String pass) throws SQLException
    {
        PreparedStatement prepare=null;
        ResultSet result=null;
        String query="select * from eee where name=? and password=?";
        try{
            prepare=conn.prepareStatement(query);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            result=prepare.executeQuery();
            if(result.next())
            {
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e)
        {
            return false;
        }finally{
            prepare.close();
            result.close();
        }
        
    }
}
