/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import beans.Publisher;
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
public class Publishers {

    public static void displayAllPublishers(Connection conn) {

        System.out.println("Retrieving all Publishers");

        String sql = "SELECT publishername FROM PUBLISHERS";
        ResultSet rs = null;
        try (Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

            String publisherName;
            rs = stmt.executeQuery(sql);

            System.out.println("Publisher Name");
            while (rs.next()) {
                publisherName = rs.getString("publishername");

                System.out.println(rs.getRow() + ") " + publisherName);
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static void displayAllInfo(Connection conn, String pName) {

        String sql = "SELECT * FROM publishers NATURAL JOIN books WHERE publishername = ?";

        ResultSet rs = null;

        System.out.println("\nRetrieving all info for " + pName);

        String groupname, bookTitle, publisherName, pAddress, pPhone, pEmail;
        int yearpublished = 0, numberofpages = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);) {

            stmt.setString(1, pName);

            rs = stmt.executeQuery();

            System.out.println("groupname, publisher name"
                    + ", yearformed, subject, booktitle, publishername");

            if (!rs.next()) {
                System.err.println("NO SUCH GROUP IN DATABASE!");
            } else {
                rs.beforeFirst();
            }

            while (rs.next()) {
                groupname = rs.getString("groupname");
                bookTitle = rs.getString("booktitle");
                publisherName = rs.getString("publishername");
                pAddress = rs.getString("publisheraddress");
                pPhone = rs.getString("publisherphone");
                pEmail = rs.getString("publisheremail");
                yearpublished = rs.getInt("yearpublished");
                numberofpages = rs.getInt("numberofpages");

                System.out.println(groupname + " " + bookTitle + " "
                        + publisherName + " " + pAddress + " " + pPhone + " "
                        + pEmail + " " + yearpublished + " " + numberofpages);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static void updatePub(Connection conn, Publisher bean, String pname) {

        System.out.println("UPDATING INFORMATION");
        
        if (Publishers.InsertPub(conn, bean)) {
            if (Publishers.ChangePub(conn, bean, pname)) {
                if (Publishers.DeletePub(conn, pname)) {
                    System.out.println("INFORMATION UPDATED!!!");
                }
            }
        }
    }

    public static boolean InsertPub(Connection conn, Publisher bean) {

        System.out.println("INSERTING NEW PUBLISHER");

        String sql = "Insert into PUBLISHERS values (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, bean.getpName());
            stmt.setString(2, bean.getpAddress());
            stmt.setString(3, bean.getpPhone());
            stmt.setString(4, bean.getpEmail());

            int affected = stmt.executeUpdate();

            if (affected == 1) {
                System.out.println("INSERT SUCCESSFUL");
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.err.print(ex);
            return false;
        }

    }

    public static boolean ChangePub(Connection conn, Publisher bean, String pname) {

        System.out.println("UPDATING PUBLISHER INFO");

        String sql = "Update books set publishername = ? where publishername = ?";

        try (PreparedStatement stmt2 = conn.prepareStatement(sql);) {

            stmt2.setString(1, bean.getpName());
            stmt2.setString(2, pname);

            int affected2 = stmt2.executeUpdate();

            if (affected2 == 2) {
                System.out.println("PUBLISHER UPDATE SUCCESSFUL");
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.err.print(ex);
            return false;
        }

    }

    public static boolean DeletePub(Connection conn, String pname) {

        System.out.println("REMOVING PAST ENTRY");

        String sql = "Delete from publishers where publishername = ?";

        try (PreparedStatement stmt3 = conn.prepareStatement(sql);) {

            stmt3.setString(1, pname);

            int affected3 = stmt3.executeUpdate();

            if (affected3 == 1) {
                System.out.println("DELETED PREVIOUS PUBLISHER");
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.err.print(ex);
            return false;
        }

    }
}
