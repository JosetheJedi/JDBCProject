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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josef
 */
public class WritingGroups {
    
    public static void displayAllGroups(Connection conn){
        
        System.out.println("Retrieving all Writing Groups");
        
        String sql = "SELECT groupName FROM WRITINGGROUPS";
        ResultSet rs = null;
        try(Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){
            
            String groupName;
            rs = stmt.executeQuery(sql);
            
            System.out.println("Group Name");
            while(rs.next()){
                groupName = rs.getString("groupName");
                
                System.out.println(rs.getRow() + ") " + groupName);
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    public static void displayAllInfo(Connection conn, String gName){
        
        String sql = "SELECT * FROM writinggroups NATURAL JOIN books WHERE groupname = ?";
        
        ResultSet rs = null;
        
        
        System.out.println("Retrieving all info for " + gName);
        
        String groupname, headwriter, yearformed, subject, booktitle, publishername,
                yearpublished;
        int numberofpages = 0;
        
        try(PreparedStatement stmt = conn.prepareStatement(sql, 
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);){
            
            stmt.setString(1, gName);
            
            rs = stmt.executeQuery();
            
            System.out.println("groupname, headwriter, yearformed, subject, booktitle, publishername");
            
            while(rs.next()){
                groupname = rs.getString("groupname");
                headwriter = rs.getString("headwriter");
                yearformed = rs.getString("yearformed");
                subject = rs.getString("subject");
                booktitle = rs.getString("booktitle");
                publishername = rs.getString("publishername");
                //yearpublished = rs.getString("yearpublished");
                numberofpages = rs.getInt("numberofpages");
                
                System.out.println(groupname + " " + headwriter + " " + yearformed +  " " +subject +  " " +booktitle+ " " + publishername + " " + numberofpages);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
  