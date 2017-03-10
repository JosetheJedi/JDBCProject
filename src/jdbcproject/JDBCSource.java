/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcproject;

import beans.Book;
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
       Scanner inputInt = new Scanner(System.in);
       boolean cont = true;
       int userOp = 0;
       String usrStr = "";
       
       System.out.print("Enter the name of the database: ");
       DBNAME = input.nextLine();
       
       System.out.print("Enter your username: ");
       USER = input.nextLine();
       
       System.out.print("Enter your password: ");
       PASS = input.nextLine();
       
       DB_URL += DBNAME;
       
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
           System.out.println("ACCESS GRANTED!");
           
           System.out.println("\n\nWhat would you like to do?");
           
           while(cont){
               System.out.println("\n\n1) List all groups\n2) "
                   + "List group details for a specified group\n3) List all"
                   + " publishers\n4) List publisher details for a specified "
                   + "publisher\n5) List all books\n6) List all the data for"
                   + " a book specified\n7) Insert a new book\n8) "
                   + "Insert a new publisher to update a publishers info\n9)"
                   + " Remove a specified book\n10) Exit");
               userOp = inputInt.nextInt();
               
               //WritingGroups.displayAllGroups(conn);
               if(userOp == 1){
                   WritingGroups.displayAllGroups(conn);
               }
               else if(userOp == 2){
                   System.out.print("Enter group name: ");
                   usrStr = input.nextLine();
                   
                   WritingGroups.displayAllInfo(conn, usrStr);
               }else if(userOp == 3){
                   
                   
               }
               else if(userOp == 4){
                    
               }
               else if(userOp == 5){
                   
               }
               else if(userOp == 6){
               
               }
               else if(userOp == 7){
                   Book insert = new Book();
                   System.out.print("Enter the group name for the book: ");
                   insert.setGroupName(input.nextLine());
                   System.out.print("Book Title: ");
                   insert.setBookTitle(input.nextLine());
                   System.out.print("Publisher Name: ");
                   insert.setPublisherName(input.nextLine());
                   System.out.print("Year published: ");
                   insert.setYearpublished(inputInt.nextInt());
                   System.out.print("Number of Pages: ");
                   insert.setNumberofpages(inputInt.nextInt());
                   
                   // passing in Book bean and connection
                   // Books.InsertBook(conn, insert);
               }
               else if(userOp == 8){
                   
               }
               else if(userOp == 9){
                   String pubName = "";
               }
               else if(userOp == 10)
                   cont = false;
               
               
           }
           
       }
       catch(SQLException e){
           System.err.println(e);
       }
   }
}
