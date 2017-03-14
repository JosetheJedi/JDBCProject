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
            
            System.out.println("groupname, headwriter, yearformed, subject, booktitle, publishername");
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
                
                System.out.println(groupname + " " + headwriter + " " + 
                        yearformed +  " " +subject +  " " +booktitle+ " " +
                        publishername + " " + numberofpages);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
  