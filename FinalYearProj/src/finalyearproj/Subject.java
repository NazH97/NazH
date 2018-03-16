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
public class Subject {
    private StringProperty Subject;
    private StringProperty HoursPW;
    private StringProperty ExamDate;
    
    public Subject () {
        this.Subject = SubjectProperty();
        this.HoursPW = HoursPWProperty();
        this.ExamDate = ExamDateProperty();
    }
    
   public Subject (ResultSet rs){
       try{
           this.Subject = new SimpleStringProperty(rs.getString("Subject"));
           this.HoursPW = new SimpleStringProperty(rs.getString("HoursPW"));
           this.ExamDate = new SimpleStringProperty(rs.getString("ExamDate"));
       } catch (SQLException e){
           e.printStackTrace();
       }
       
       }
   public String getSubject() {return Subject.get();}
   public StringProperty SubjectProperty() {return Subject;}
   public void setSubject (String Subject) {this.Subject.set(Subject);}
   
   public String getHoursPW() {return HoursPW.get();}
   public StringProperty HoursPWProperty() {return HoursPW;}
   public void setHoursPW (String HoursPW) {this.HoursPW.set(HoursPW);}
   
   public String getDateofExam() {return ExamDate.get();}
   public StringProperty ExamDateProperty(){return ExamDate;}
   public void setExamDate(String ExamDate) {this.ExamDate.set(ExamDate);}
   }
    
