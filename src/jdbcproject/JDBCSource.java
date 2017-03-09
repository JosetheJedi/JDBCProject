/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import tables.WritingGroups;

/**
 *
 * @author Josef
 */
public class JDBCSource {
   static String USER;
   static String PASS;
   static String DBNAME; // will hold the database name
   static String DB_URL = "jdbc:derby://localhost:1527/";
   
   public static void main(String args[]){
       Scanner input = new Scanner(System.in);
       
       
       System.out.print("Enter the name of the database: ");
       DBNAME = input.nextLine();
       
       System.out.print("Enter your username: ");
       USER = input.nextLine();
       
       System.out.print("Enter your password: ");
       PASS = input.nextLine();
       
       DB_URL += DBNAME;
       
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
           System.out.println("ACCESS GRANTED");
           //WritingGroups.displayAllGroups(conn);
           
           //WritingGroups.displayAllInfo(conn, "test");
       }
       catch(SQLException e){
           System.err.println(e);
       }
   }
}
