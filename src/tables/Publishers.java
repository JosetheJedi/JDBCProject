/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        
        String sql = "SELECT * FROM publishers NATURAL JOIN books WHERE publishername = ?";
        
        ResultSet rs = null;
        
        
        System.out.println("\nRetrieving all info for " + pName);
        
        String groupname, bookTitle, publisherName, pAddress, pPhone, pEmail;
        int yearpublished = 0, numberofpages = 0;
        
        try(PreparedStatement stmt = conn.prepareStatement(sql, 
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);){
            
            stmt.setString(1, pName);
            
            rs = stmt.executeQuery();
            
            System.out.println("groupname, publisher name"
                    + ", yearformed, subject, booktitle, publishername");
            
            if(!rs.next()){
                System.err.println("NO SUCH GROUP IN DATABASE!");
            }
            else
                rs.beforeFirst();
            
            while(rs.next()){
                groupname = rs.getString("groupname");
                bookTitle = rs.getString("booktitle");
                publisherName = rs.getString("publishername");
                pAddress = rs.getString("publisheraddress");
                pPhone = rs.getString("publisherphone");
                pEmail = rs.getString("publisheremail");
                yearpublished = rs.getInt("yearpublished");
                numberofpages = rs.getInt("numberofpages");
                
                System.out.println(groupname + " " + bookTitle + " " + 
                        publisherName +  " " +pAddress +  " " +pPhone+ " " +
                        pEmail + " " + yearpublished + " " + numberofpages);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
