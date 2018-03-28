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
import javafx.scene.control.DatePicker;
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
        private final ObservableList<String> TimesOfTheDay = FXCollections.observableArrayList("07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00");


    @FXML
    private TextField subjectName;
     
     @FXML
    private Button addSubject;
    @FXML
    private DatePicker DateOfExam;
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
    private ComboBox ActivityTime;
    @FXML
    private Button AddActivity;
    @FXML
    private ComboBox MonS;
    @FXML
    private ComboBox TueS;
    @FXML
    private ComboBox WedS;
    @FXML
    private ComboBox ThurS;
    @FXML
    private ComboBox FriS;
    @FXML
    private ComboBox MonF;
    @FXML
    private ComboBox TueF;
    @FXML
    private ComboBox WedF;
    @FXML
    private ComboBox ThurF;
    @FXML
    private ComboBox FriF;
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
    @FXML
    private Tab myTimetable;
    @FXML
    private Tab newTimetable;
    @FXML
    private Button generateTimetable;
    @FXML
    private TabPane mainMenu;
    @FXML
    private TabPane timetableMenu;
    @FXML
    private TextField ActivityLength;
    @FXML
    private TableColumn<?, ?> ActivityDurationColumn;
   
    
    
    @Override 
     public void initialize(URL location, ResourceBundle resourceBundle){
        fillTable();
        fillActivityTable();
        
        ActivityDay.setValue("Monday");
        ActivityDay.setItems(ActivityDayList);
        
        Tab2.setDisable(true);
        Tab3.setDisable(true);
        Tab4.setDisable(true);
        
        myTimetable.setDisable(true);
        
        mainMenu.getSelectionModel().select(newTimetable);
        
        MonS.getItems().addAll(TimesOfTheDay);
        MonF.getItems().addAll(TimesOfTheDay);
        
        TueS.getItems().addAll(TimesOfTheDay);
        TueF.getItems().addAll(TimesOfTheDay);
        
        WedS.getItems().addAll(TimesOfTheDay);
        WedF.getItems().addAll(TimesOfTheDay);
        
        ThurS.getItems().addAll(TimesOfTheDay);
        ThurF.getItems().addAll(TimesOfTheDay);
        
        FriS.getItems().addAll(TimesOfTheDay);
        FriF.getItems().addAll(TimesOfTheDay);
        
         ActivityTime.getItems().addAll(TimesOfTheDay);

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
        ActivityDurationColumn.setCellValueFactory(new PropertyValueFactory<>("ActivityDuration"));
      
    }
    
    
    
    
    @FXML
    public void addNewSubject(ActionEvent event) {
        try {
            
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO Subjects (Subject, HoursPW, ExamDate) VALUES ('" + subjectName.getText() + "', '" + HoursPerWeek.getText() + "', '" + DateOfExam.getValue() + "');";
                db.update(stmt);
                fillTable();
            
        } catch (NullPointerException e) {
      
        }
    }
    
     /*  @FXML
    public void DeleteNewSubject(ActionEvent event) {
        try {
            selected = SubjectTable.getSelectionModel().getSelectedItems();
            
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "DELETE FROM Subjects where);";
                db.update(stmt);
                fillTable();
            
        } catch (NullPointerException e) {
      
        }
    }
*/
    @FXML
    private void Next1(ActionEvent event) {
        
        timetableMenu.getSelectionModel().select(Tab2);
        Tab1.setDisable(true);
        Tab2.setDisable(false);
      
    }

    @FXML
    private void AddNewActivity(ActionEvent event) {
         try {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO Activities (Activity, DayOfActivity, TimeOfActivity, ActivityDuration) VALUES ('" + ActivityName.getText() + "', '" + ActivityDay.getValue() + "', '" + ActivityTime.getValue() + "', '" + ActivityLength.getText() + "');";
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
        Tab1.setDisable(true);
        Tab2.setDisable(true);
        Tab3.setDisable(false);
        try {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO Class (MondayStart, MondayEnd,TuesdayStart,TuesdayEnd,WednesdayStart,WednesdayEnd,ThursdayStart,ThursdayEnd,FridayStart,FridayEnd) VALUES ('" + MonS.getSelectionModel().getSelectedItem().toString() + "', '" + MonF.getSelectionModel().getSelectedItem().toString() + "', '" + TueS.getSelectionModel().getSelectedItem().toString() + "', '" + TueF.getSelectionModel().getSelectedItem().toString() + "', '" + WedS.getSelectionModel().getSelectedItem().toString() + "', '" + WedF.getSelectionModel().getSelectedItem().toString() + "', '" + ThurS.getSelectionModel().getSelectedItem().toString() + "', '" + ThurF.getSelectionModel().getSelectedItem().toString() + "', '" + FriS.getSelectionModel().getSelectedItem().toString() + "','" +FriF.getSelectionModel().getSelectedItem().toString() + "');";
                db.update(stmt);
                fillTable();
            
        } catch (NullPointerException e) {
 
        }
        
        timetableMenu.getSelectionModel().select(Tab3);
    } 

    @FXML
    private void Next3(ActionEvent event) {
        timetableMenu.getSelectionModel().select(Tab4);
        Tab1.setDisable(true);
        Tab2.setDisable(true);
        Tab3.setDisable(true);
        Tab4.setDisable(false);
    }

    @FXML
    private void generateTimetableAction(ActionEvent event) {
        myTimetable.setDisable(false);
        newTimetable.setDisable(true);
        mainMenu.getSelectionModel().select(myTimetable);
        
    }
}