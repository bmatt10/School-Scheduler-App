package com.example.part1.Database;

import android.app.Application;

import com.example.part1.DAO.AssessmentDAO;
import com.example.part1.DAO.CourseDAO;
import com.example.part1.DAO.TermDAO;
import com.example.part1.Entity.AssessmentEntity;
import com.example.part1.Entity.CourseEntity;
import com.example.part1.Entity.TermEntity;


import java.util.List;

public class InventoryManagementRepository {
    private CourseDAO mCourseDAO;
    private TermDAO mTermDAO;
    private AssessmentDAO mAssessmentDAO;
    private List<CourseEntity> mAllParts;
    private List<TermEntity> mAllProducts;
    private List<AssessmentEntity> mAllAssessments;
    private int productID;

     public InventoryManagementRepository(Application application){
        InventoryManagementDatabase db=InventoryManagementDatabase.getDatabase(application);
        mCourseDAO =db.courseDAO();
        mTermDAO =db.termDAO();
        mAssessmentDAO =db.assessmentDAO();

         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

    }
    public List<CourseEntity> getAllParts(){
        InventoryManagementDatabase.databaseWriteExecutor.execute(()->{
            mAllParts= mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         return mAllParts;
    }
    public List<TermEntity> getAllProducts(){
            InventoryManagementDatabase.databaseWriteExecutor.execute(()->{
                mAllProducts= mTermDAO.getAllTerms();
            });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         return mAllProducts;
    }
    public List<AssessmentEntity> getAllAssessments(){
        InventoryManagementDatabase.databaseWriteExecutor.execute(()->{
            mAllAssessments= mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }
    public void insert (CourseEntity courseEntity) {
        InventoryManagementDatabase.databaseWriteExecutor.execute(()->{
            mCourseDAO.insert(courseEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void insert (TermEntity termEntity) {
        InventoryManagementDatabase.databaseWriteExecutor.execute(()->{
            mTermDAO.insert(termEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert (AssessmentEntity assessmentEntity) {
        InventoryManagementDatabase.databaseWriteExecutor.execute(()->{
            mAssessmentDAO.insert(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete (TermEntity termEntity) {
        InventoryManagementDatabase.databaseWriteExecutor.execute(() -> {
            mTermDAO.delete(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void delete (CourseEntity courseEntity) {
        InventoryManagementDatabase.databaseWriteExecutor.execute(() -> {
            mCourseDAO.delete(courseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete (AssessmentEntity assessmentEntity) {
        InventoryManagementDatabase.databaseWriteExecutor.execute(() -> {
            mAssessmentDAO.delete(assessmentEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
