
package tables;

import java.sql.*;
import beans.Book;

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
    
    public static void displayAllInfo(Connection conn, String bTitle){
        //TODO FINISH
        String sql = "SELECT * FROM books WHERE booktitle = ?"; 
        String bookTitle, groupName, pubName, yearPub;
        int pages = 0;
        
        System.out.println("Retrieving all info for " + bTitle);
        ResultSet rs = null;
        try(PreparedStatement stmt = conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);){
            
            stmt.setString(1, bTitle);
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException ex){
            System.err.println(ex);
        }
    }
    public static void addBook(Connection conn, Book insert){
        //('GroupName', 'BookTitle', 'PubName', yearPub, numPages)
        String sql = "INSERT INTO books VALUES(?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, insert.getGroupName());
            stmt.setString(2, insert.getBookTitle());
            stmt.setString(3, insert.getPublisherName());
            stmt.setInt(4, insert.getYearpublished());
            stmt.setInt(5, insert.getNumberofpages());
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }
    
}
