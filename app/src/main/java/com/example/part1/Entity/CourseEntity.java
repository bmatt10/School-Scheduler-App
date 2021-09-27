package com.example.part1.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="course_table")
public class CourseEntity {
    @PrimaryKey
    private int courseID;

    private String courseName;
    private String courseNotes;
    private int termID;
    private String startDate;
    private String endDate;
    private String mentor;
    private String phone;
    private String status;
    private String email;


    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", courseNotes=" + courseNotes +
                ", termID=" + termID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", mentor=" + mentor +
                ", phone=" + phone +
                ", status=" + status +
                ", email=" + email +
                '}';
    }

    public CourseEntity(int courseID, String courseName, String courseNotes, int termID, String startDate, String endDate, String mentor, String phone, String status, String email) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseNotes = courseNotes;
        this.termID = termID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mentor = mentor;
        this.phone = phone;
        this.status = status;
        this.email = email;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
