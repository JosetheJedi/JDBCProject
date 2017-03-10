
package tables;

import java.sql.*;

public class Books {
    
    public static void displayAllBooks(Connection conn){
        System.out.println("Retrieving all Books)");
        String sql = "SELECT bookTitle FROM books";
        ResultSet rs;
        try(Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){
            String bookTitles;
            rs = stmt.executeQuery(sql);
            
            System.out.println("Book Titles");
            while(rs.next()){
                bookTitles = rs.getString("bookTitle"); 
                System.out.println(rs.getRow() + ") " + bookTitles);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
