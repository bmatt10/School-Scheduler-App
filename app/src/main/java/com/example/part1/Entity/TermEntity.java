package com.example.part1.Entity;

import androidx.room.*;

@Entity(tableName="term_table")

public class TermEntity {

    @PrimaryKey
    private int termID;

    private String termName;
    private String termNotes;
    private String termStart;
    private String termEnd;

    public TermEntity(int termID, String termName, String termNotes, String termStart, String termEnd) {
        this.termID = termID;
        this.termName = termName;
        this.termNotes = termNotes;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "termID=" + termID +
                ", termName='" + termName + '\'' +
                ", termNotes=" + termNotes +
                ", termStart=" + termStart +
                ", termEnd=" + termEnd +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermNotes() {
        return termNotes;
    }

    public void setTermNotes(String termNotes) {
        this.termNotes = termNotes;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }
}
