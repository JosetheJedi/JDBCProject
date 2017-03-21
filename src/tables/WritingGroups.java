package tables;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WritingGroups {
    /**
     * displayAllGroups will display all the information on
     * the writing groups listed in the table
     * @param conn 
     */
    public static void displayAllGroups(Connection conn){       
        System.out.println("Retrieving all Writing Groups");
        String sql = "SELECT groupName FROM WRITINGGROUPS";
        ResultSet rs = null;
        try(Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){    
            String groupName;
            rs = stmt.executeQuery(sql);
            System.out.println("Group Name");
            //will print the group name and its rows
            while(rs.next()){
                groupName = rs.getString("groupName");
                System.out.println(rs.getRow() + ") " + groupName);
            }                        
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    /**
     * displayAllInfo will display all the information based on the
     * given groupName given and the books associated with them
     * @param conn
     * @param gName String, group name to display the information 
     */
    public static void displayAllInfo(Connection conn, String gName){
        String sql = "SELECT * FROM writinggroups NATURAL JOIN books WHERE groupname = ?";
        ResultSet rs = null;        
        System.out.println("\nRetrieving all info for " + gName);        
        String groupname, headwriter, yearformed, subject, booktitle, publishername;
        int yearpublished = 0, numberofpages = 0;
        
        try(PreparedStatement stmt = conn.prepareStatement(sql, 
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);){
                stmt.setString(1, gName);            
            rs = stmt.executeQuery();
            
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                        "Group Name", "Head Writer", "Year Formed",
                        "Subject", "Book Title", "Publishers Name", "Number of Pages");
            
            if(!rs.next()){
                System.err.println("NO SUCH GROUP IN DATABASE!");
            }
            else
                rs.beforeFirst();
            //prints all the information of the writing group
            while(rs.next()){
                groupname = rs.getString("groupname");
                headwriter = rs.getString("headwriter");
                yearformed = rs.getString("yearformed");
                subject = rs.getString("subject");
                booktitle = rs.getString("booktitle");
                publishername = rs.getString("publishername");
                yearpublished = rs.getInt("yearpublished");
                numberofpages = rs.getInt("numberofpages");
                
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                        groupname, headwriter, yearformed,
                        subject, booktitle, publishername, numberofpages);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    
    public static boolean checkWriting(Connection conn, String wname) {
        String sql = "SELECT * FROM writinggroups WHERE groupname = ?";

        ResultSet rs = null;

        System.out.println("CHECKING FOR WRITING GROUP");

        try (PreparedStatement stmt = conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);) {

            // setting the question mark in the prepared statement to the
            // writing group name that the user specified.
            stmt.setString(1, wname);

            rs = stmt.executeQuery();

            // if the result set returns empty, then there is no such group
            // in the database.
            if (!rs.next()) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        }
    }
}
  