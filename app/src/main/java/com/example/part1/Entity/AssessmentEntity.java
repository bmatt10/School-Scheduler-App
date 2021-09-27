package com.example.part1.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="assessment_table")
public class AssessmentEntity {
    @PrimaryKey
    private int assessmentID;

    private String assessmentName;
    private String assessmentNotes;
    private String assessmentDate;
    private String assessmentType;
    private int courseID;
    private int termID;

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentID=" + assessmentID +
                ", assessmentName='" + assessmentName + '\'' +
                ", assessmentNotes=" + assessmentNotes +
                ", assessmentDate=" + assessmentDate +
                ", assessmentType=" + assessmentType +
                ", courseID=" + courseID +
                ", termID=" + termID +
                '}';
    }

    public AssessmentEntity(int assessmentID, String assessmentName, String assessmentNotes, String assessmentDate, String assessmentType, int courseID, int termID) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentNotes = assessmentNotes;
        this.assessmentDate = assessmentDate;
        this.assessmentType = assessmentType;
        this.courseID = courseID;
        this.termID = termID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getAssessmentNotes() {
        return assessmentNotes;
    }

    public void setAssessmentNotes(String assessmentNotes) {
        this.assessmentNotes = assessmentNotes;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }
}
