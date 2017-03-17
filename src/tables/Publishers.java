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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josef
 */
public class Publishers {

    /**
     * displayAllPublishers will display the publisher name for every publisher
     * listed in the table.
     *
     * @param conn
     */
    public static void displayAllPublishers(Connection conn) {

        System.out.println("Retrieving all Publishers");

        String sql = "SELECT publishername FROM PUBLISHERS";
        ResultSet rs = null;
        try (Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

            String publisherName;
            rs = stmt.executeQuery(sql);

            System.out.println("Publisher Name");

            // This will print the publisher name if rs has more rows to display
            while (rs.next()) {
                publisherName = rs.getString("publishername");

                System.out.println(rs.getRow() + ") " + publisherName);
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * displayAllInfo will show every attribute value for a publisher that the
     * user specifies.
     *
     * @param conn
     * @param pName
     */
    public static void displayAllInfo(Connection conn, String pName) {

        String sql = "SELECT * FROM publishers NATURAL JOIN books WHERE publishername = ?";

        ResultSet rs = null;

        System.out.println("\nRetrieving all info for " + pName);

        String groupname, bookTitle, publisherName, pAddress, pPhone, pEmail;
        int yearpublished = 0, numberofpages = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);) {

            // setting the question mark in the prepared statement to the
            // publisher name that the user specified.
            stmt.setString(1, pName);

            rs = stmt.executeQuery();

            // if the result set returns empty, then there is no such publisher
            // in the database.
            if (!rs.next()) {
                System.err.println("NO PUBLISHER BY THAT NAME IN DATABASE!");
            } else {
                // setting the cursor before the first result
                // so that the first value is read inside the while loop.
                rs.beforeFirst();
                System.out.println("groupname, publisher name"
                        + ", yearformed, subject, booktitle, publishername");

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

    /**
     * updatePub will create a row for the publisher information that the user
     * provides and then set the books from the old publisher to be published by
     * the new publisher created. It will then delete the old publisher from the
     * database.
     *
     * @param conn
     * @param bean
     * @param pname
     */
    public static void updatePub(Connection conn, Publisher bean, String pname, Boolean exists) {

        System.out.println("UPDATING INFORMATION");

        // if the publisher is not found then the publisher is inserted
        if (!exists) {
            
            // if the publisher is inserted succesfully then it goes to change
            // the publisher value of every book to the new publisher.
            if (InsertPub(conn, bean)) {
                // if changing the publisher information from the old publisher 
                // to the new publisher is successful, then it informs the user
                if (ChangePub(conn, bean, pname)) {
                    System.out.println("INFORMATION UPDATED!!!");
                } else {
                    // if it fails to Change the new publisher the update will roll
                    // back deleting the publisher inserted
                    DeletePub(conn, bean.getpName());
                    System.out.println("UPDATE FAILED: ROLLING BACK ACTIONS");
                }
            } else {
                // if it fails to insert the new publisher the program will 
                // notify the user that nothing changed.
                System.out.println("UPDATE FAILED: NOTHING WAS CHANGED!");
            }
        } else { // if the publisher is found the update happens.

            // if changing the publisher information from the old publisher 
            // to the new publisher is successful, then it informs the user
            if (ChangePub(conn, bean, pname)) {
                System.out.println("INFORMATION UPDATED!!!");
            } else {
                // if it fails to Change the new publisher the update 
                // notify the user that nothing changed.
                System.out.println("UPDATE FAILED: NOTHING WAS CHANGED!");
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

            // affected will contain the number of rows affected by the 
            // execution of the sql statement
            int affected = stmt.executeUpdate();

            // if one row was affected for inserting a row then the insertion
            // was successful
            if (affected == 1) {
                System.out.println("INSERT SUCCESSFUL");
                return true;
            } else {
                return false;
            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("THE PUBLISHER NAME PROVIDED ALREADY EXISTS!");
            return false;
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

            // the affected value is 2 since we are changing information from
            // two rows.
            if (affected2 > 0) {
                System.out.println("PUBLISHER UPDATE SUCCESSFUL");
                return true;
            } else {
                System.out.println("PUBLISHER UPDATE FAILED");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Change failed");
            System.err.print(ex);
            return false;
        }

    }

    public static boolean DeletePub(Connection conn, String pname) {

        System.out.println("REMOVING ENTRY");

        String sql = "Delete from publishers where publishername = ?";

        try (PreparedStatement stmt3 = conn.prepareStatement(sql);) {

            stmt3.setString(1, pname);

            int affected3 = stmt3.executeUpdate();

            // affected value expected is 1 since we are deleting one row.
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

    public static boolean checkPub(Connection conn, String pname) {
        String sql = "SELECT * FROM publishers WHERE publishername = ?";

        ResultSet rs = null;

        System.out.println("CHECKING FOR PUBLISHER");

        try (PreparedStatement stmt = conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);) {

            // setting the question mark in the prepared statement to the
            // publisher name that the user specified.
            stmt.setString(1, pname);

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
