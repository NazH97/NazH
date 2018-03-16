/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalyearproj;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author NazH9
 */
public class TimetableAppController implements Initializable {
    
        ObservableList<String> ActivityDayList = FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday","Friday");

    @FXML
    private TextField subjectName;
     @FXML
    private TabPane tabPane;
     
     @FXML
    private Button addSubject;
    @FXML
    private TextField DateOfExam;
    @FXML
    private TextField HoursPerWeek;
    
     @FXML
    private TableView<Subject> SubjectTable;

    @FXML
    private TableColumn<Subject, String> SubjectColumn;

    @FXML
    private TableColumn<Subject, String> HoursPWColumn;

    @FXML
    private TableColumn<Subject, String> ExamDateColumn;

    /**
     * Initializes the controller class.
     * @param event
     */
    private SQLiteConnection db = SQLiteConnection.getInstance();
    @FXML
    private TextField ActivityName;
    @FXML
    private ComboBox ActivityDay;
    @FXML
    private TextField ActivityTime;
    @FXML
    private Button AddActivity;
    @FXML
    private TextField MonS;
    @FXML
    private TextField TueS;
    @FXML
    private TextField WedS;
    @FXML
    private TextField ThurS;
    @FXML
    private TextField FriS;
    @FXML
    private TextField MonF;
    @FXML
    private TextField TueF;
    @FXML
    private TextField WedF;
    @FXML
    private TextField ThurF;
    @FXML
    private TextField FriF;
    @FXML
    private Button nxt2;
    @FXML
    private TableView<Activity> ActivityTable;
    @FXML
    private TableColumn<Activity, String> ActivityNameColumn;
    @FXML
    private TableColumn<Activity, String> ActivityDayColumn;
    @FXML
    private TableColumn<Activity, String> ActivityTimeColumn;
    @FXML
    private Tab Tab2;
    @FXML
    private Tab Tab3;
    @FXML
    private Tab Tab4;
    @FXML
    private Tab Tab1;
   
    
    
    @Override 
     public void initialize(URL location, ResourceBundle resourceBundle){
        fillTable();
        fillActivityTable();
        
        ActivityDay.setValue("Monday");
        ActivityDay.setItems(ActivityDayList);
     }
public void fillTable(){
    
        setValueFactories();
        ArrayList<Subject> tableValues = new ArrayList<>();
        String sql = "SELECT * FROM Subjects";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new Subject(rs));
            }
            SubjectTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void setValueFactories(){
        SubjectColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        HoursPWColumn.setCellValueFactory(new PropertyValueFactory<>("HoursPW"));
        ExamDateColumn.setCellValueFactory(new PropertyValueFactory<>("ExamDate"));
      
    }
  
    public void fillActivityTable(){
    
        setActivityValueFactories();
        ArrayList<Activity> ActivitiesTableValues = new ArrayList<>();
        String sql = "SELECT * FROM Activities";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                ActivitiesTableValues.add(new Activity(rs));
            }
            ActivityTable.setItems(FXCollections.observableArrayList(ActivitiesTableValues));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void setActivityValueFactories(){
        ActivityNameColumn.setCellValueFactory(new PropertyValueFactory<>("Activity"));
        ActivityDayColumn.setCellValueFactory(new PropertyValueFactory<>("DayOfActivity"));
        ActivityTimeColumn.setCellValueFactory(new PropertyValueFactory<>("TimeOfActivity"));
      
    }
    
    
    
    
    @FXML
    public void addNewSubject(ActionEvent event) {
        try {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO Subjects (Subject, HoursPW, ExamDate) VALUES ('" + subjectName.getText() + "', '" + HoursPerWeek.getText() + "', '" + DateOfExam.getText() + "');";
                db.update(stmt);
                fillTable();
            
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
    }
    

    @FXML
    private void Next1(ActionEvent event) {
        
        tabPane.getSelectionModel().select(Tab2);
      
    }

    @FXML
    private void AddNewActivity(ActionEvent event) {
         try {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO Activities (Activity, DayOfActivity, TimeOfActivity) VALUES ('" + ActivityName.getText() + "', '" + ActivityDay.getValue() + "', '" + ActivityTime.getText() + "');";
                db.update(stmt);
                fillActivityTable();
            
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
         
    }

    @FXML
    private void Next2(ActionEvent event) {
        try {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO Class (MondayStart, MondayEnd,TuesdayStart,TuesdayEnd,WednesdayStart,WednesdayEnd,ThursdayStart,ThursdayEnd,FridayStart,FridayEnd) VALUES ('" + MonS.getText() + "', '" + MonF.getText() + "', '" + TueS.getText() + "', '" + TueF.getText() + "', '" + WedS.getText() + "', '" + WedF.getText() + "', '" + ThurS.getText() + "', '" + ThurF.getText() + "', '" + FriS.getText() + "','" +FriF.getText() + "');";
                db.update(stmt);
                fillTable();
            
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
        
        tabPane.getSelectionModel().select(Tab3);
    } 

    @FXML
    private void Next3(ActionEvent event) {
        tabPane.getSelectionModel().select(Tab4);
    }

    
    
    
}