package finalyearproj;
//package common.database;

//import FinalYearProj;
//import customers.logic.Accounts;
//import customers.logic.Customers;
//import vehicles.logic.Vehicle;
import java.sql.*;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
//import parts.logic.PartsController;

public final class Database 
{
    private static final String DB_File_Name = "RevisionTimetable.db";
    
    private static final Database db = new Database(DB_File_Name);
    
    private Connection conn;
    
    private PreparedStatement stmt;

   // private ObservableList<SPC> spcData;
    private Database(String DBFileName)
    {
        System.out.println("Trying to connect to the database");
        try
        {
            //Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + DBFileName);
            //conn.setAutoCommit(false);
            System.out.println("Connection successful");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.err.println("Database connection failed");
            //throw new RuntimeException("Database connection failed!", ex);
        } 
    }
    
    public PreparedStatement preparedStatement(String sqlStmt)
    {
        //System.out.println("We are in the prepared statement method");
        PreparedStatement stmt = null;
        try 
        {
            stmt = conn.prepareStatement(sqlStmt);
            return stmt;
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            System.err.println("Unable to execute statement");
            return stmt;
        }
    }
    
    public boolean addSubject( String subName, String DoE, String HoursPWeek)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO Subjects VALUES (?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, subName);
           add.setString(3, DoE);
           add.setString(4, HoursPWeek);

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Subject successfully added");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
    
    public boolean AddActivity( String ActName, String ActDay, String ActTime)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO Activities VALUES (?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, ActName);
           add.setString(3, ActDay);
           add.setString(4, ActTime);

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Activity successfully added");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }
  public boolean nxt2( String MS, String MF, String TS, String TF, String WS, String WF, String ThS, String ThF, String FS, String FF)
    {
        PreparedStatement add = null;
        boolean added = false;
        try
        {
           add = preparedStatement("INSERT INTO Class VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
           add.setString(1, null);
           add.setString(2, MS);
           add.setString(3, MF);
           add.setString(4, TS);
           add.setString(5, TF);
           add.setString(6, WS);
           add.setString(7, WF);
           add.setString(8, ThS);
           add.setString(9, ThF);
           add.setString(10, FS);
           add.setString(11, FF);
           

           add.execute();
           add.close();
           added = true;
           JOptionPane.showMessageDialog(null,"Classes successfully added");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error, try again");
            ex.printStackTrace();
            System.err.println("Unable to access table or table doesnt exist");
        }
        
        return added;
    }

}
    
    
     
       





