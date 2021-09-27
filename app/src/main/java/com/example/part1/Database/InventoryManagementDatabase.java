package com.example.part1.Database;

import android.content.Context;

import com.example.part1.DAO.AssessmentDAO;
import com.example.part1.DAO.CourseDAO;
import com.example.part1.DAO.TermDAO;
import com.example.part1.Entity.AssessmentEntity;
import com.example.part1.Entity.CourseEntity;
import com.example.part1.Entity.TermEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {CourseEntity.class, TermEntity.class, AssessmentEntity.class}, version = 15)//change to v2 when adding a column

public abstract class InventoryManagementDatabase extends RoomDatabase {
    public abstract CourseDAO courseDAO();
    public abstract TermDAO termDAO();
    public abstract AssessmentDAO assessmentDAO();
    private static final int NUMBER_OF_THREADS = 5;
    /**
     * The Database write executor.
     */
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    private static volatile InventoryManagementDatabase INSTANCE;

    static InventoryManagementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryManagementDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), InventoryManagementDatabase.class, "inventory_management_database.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {

                /**
                 * Populate the database in the background.
                 * If you want to start with more words, just add them.
                 */


                CourseDAO mCourseDao = INSTANCE.courseDAO();
                TermDAO mTermDao = INSTANCE.termDAO();
                AssessmentDAO mAssessmentDao = INSTANCE.assessmentDAO();


        /*        mTermDao.deleteAllTerms();
                mCourseDao.deleteAllCourses();
                mAssessmentDao.deleteAllAssessments();*/

                CourseEntity course = new CourseEntity(1, "English", "This course is fun!", 1, "09/23/21", "09/23/21", "Bob", "317-222-2222", "In Progress", "bob@wgu.edu");
                mCourseDao.insert(course);
                course = new CourseEntity(2, "Math", "This course is fun!", 1, "10/13/21", "09/23/21", "Bob", "317-222-2222", "In Progress", "bob@wgu.edu");
                mCourseDao.insert(course);
                course = new CourseEntity(3, "Writing", "This course is fun!", 1, "11/13/21", "09/23/21", "Bob", "317-222-2222", "In Progress", "bob@wgu.edu");
                mCourseDao.insert(course);
                course = new CourseEntity(4, "General Ed", "This course is fun!", 1, "12/13/21", "09/23/21", "Bob", "317-222-2222", "In Progress", "bob@wgu.edu");
                mCourseDao.insert(course);
                course = new CourseEntity(1, "Special Skills", "This course is fun!", 2, "12/13/21", "09/23/21", "Bob", "317-222-2222", "In Progress", "bob@wgu.edu");
                mCourseDao.insert(course);


                TermEntity term = new TermEntity(1, "Term 1", "This term will be hard", "02/01/21", "08/01/21");
                mTermDao.insert(term);
                term = new TermEntity(2, "Term 2", "This term will be easy", "08/01/21", "02/01/22");
                mTermDao.insert(term);

                AssessmentEntity assessment = new AssessmentEntity(1, "Assessment 1", "happy", "12/13/21", "PA", 2, 1);
                mAssessmentDao.insert(assessment);


            });
        }
    };
}
