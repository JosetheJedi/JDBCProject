/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Josef
 */
public class Publishers {
    
    public static void displayAllPublishers(Connection conn){
        
        System.out.println("Retrieving all Publishers");
        
        String sql = "SELECT publishername FROM PUBLISHERS";
        ResultSet rs = null;
        try(Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){
            
            String publisherName;
            rs = stmt.executeQuery(sql);
            
            System.out.println("Publisher Name");
            while(rs.next()){
                publisherName = rs.getString("publishername");
                
                System.out.println(rs.getRow() + ") " + publisherName);
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    public static void displayAllInfo(Connection conn, String pName){
        
    }
}
