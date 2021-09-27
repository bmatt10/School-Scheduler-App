package com.example.part1.DAO;

import com.example.part1.Entity.AssessmentEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(AssessmentEntity assessment);

    @Query("DELETE FROM assessment_table")
    void deleteAllAssessments();

    @Query("SELECT * FROM assessment_table ORDER BY assessmentID ASC")
    List<AssessmentEntity> getAllAssessments();

    @Delete
    void delete(AssessmentEntity assessment);

}
