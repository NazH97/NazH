/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalyearproj;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author NazH9
 */
public class Activity {
    private StringProperty Activity;
    private StringProperty DayOfActivity;
    private StringProperty TimeOfActivity;
    
    public Activity () {
        this.Activity = ActivityProperty();
        this.DayOfActivity = DayOfActivityProperty();
        this.TimeOfActivity = TimeOfActivityProperty();
    }
    
   public Activity (ResultSet rs){
       try{
           this.Activity = new SimpleStringProperty(rs.getString("Activity"));
           this.DayOfActivity = new SimpleStringProperty(rs.getString("DayOfActivity"));
           this.TimeOfActivity = new SimpleStringProperty(rs.getString("TimeOfActivity"));
       } catch (SQLException e){
           e.printStackTrace();
       }
       
       }
   public String getActivity() {return Activity.get();}
   public StringProperty ActivityProperty() {return Activity;}
   public void setActivity (String Activity) {this.Activity.set(Activity);}
   
   public String getDayOfActivity() {return DayOfActivity.get();}
   public StringProperty DayOfActivityProperty() {return DayOfActivity;}
   public void setDayOfActivity (String DayOfActivity) {this.DayOfActivity.set(DayOfActivity);}
   
   public String getDateofExam() {return TimeOfActivity.get();}
   public StringProperty TimeOfActivityProperty(){return TimeOfActivity;}
   public void setTimeOfActivity(String TimeOfActivity) {this.TimeOfActivity.set(TimeOfActivity);}
   }
    
