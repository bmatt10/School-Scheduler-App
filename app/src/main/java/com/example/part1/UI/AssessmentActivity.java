package com.example.part1.UI;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.part1.Database.InventoryManagementRepository;
import com.example.part1.Entity.AssessmentEntity;
import com.example.part1.Entity.TermEntity;
import com.example.part1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AssessmentActivity extends AppCompatActivity {
    private InventoryManagementRepository inventoryManagementRepository;
    public static int numAlert;
    static int id3;
    int Id2;
    int Id;
    String name;
    String notes;
    int partId;
    int prodId;
    String startDate;
    String assessmentType;
    EditText editName;
    EditText editNote;
    EditText editDate;
    EditText editAssessment;
    Calendar myCalendar3=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener  myDate;
    Long date;
    AssessmentEntity currentAssessment;
    TermEntity currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        Id2=getIntent().getIntExtra("assessmentID",-1);
        prodId=getIntent().getIntExtra("productID", -1);

        inventoryManagementRepository= new InventoryManagementRepository(getApplication());
        List<AssessmentEntity> allAssessments =inventoryManagementRepository.getAllAssessments();

        for(AssessmentEntity a:allAssessments){
            if(a.getAssessmentID()==Id2)currentAssessment=a;
        }

        partId=getIntent().getIntExtra("partID", -1);
        startDate=getIntent().getStringExtra("assessmentDate");
        id3=partId;
        name=getIntent().getStringExtra("assessmentName");
        notes=getIntent().getStringExtra("assessmentNotes");
        assessmentType=getIntent().getStringExtra("assessmentType");

        editName=findViewById(R.id.assessmentName);
        editNote=findViewById(R.id.assessmentNotes);
        editDate = findViewById(R.id.productEnd);
        editAssessment=findViewById(R.id.assessmentType);
        if(Id2!=-1){
            editName.setText(name);
            editNote.setText(notes);
            editDate.setText(startDate);
            editAssessment.setText(assessmentType);
        }
        inventoryManagementRepository= new InventoryManagementRepository(getApplication());


        myDate = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar3.set(Calendar.YEAR, year);
            myCalendar3.set(Calendar.MONTH, monthOfYear);
            myCalendar3.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            updateLabel();
        };

        editDate.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(AssessmentActivity.this, myDate, myCalendar3
                    .get(Calendar.YEAR), myCalendar3.get(Calendar.MONTH),
                    myCalendar3.get(Calendar.DAY_OF_MONTH)).show();
        });

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(myCalendar3.getTime()));
    }

    public void addAssessmentFromScreen(View view) {

        hideKeyboard(this, view);

        AssessmentEntity a;

        if(Id2!=-1)a= new AssessmentEntity(Id2,editName.getText().toString(),editNote.getText().toString(),editDate.getText().toString(),editAssessment.getText().toString(), partId, prodId);
        else{
            List<AssessmentEntity> allAssessments=inventoryManagementRepository.getAllAssessments();
            Id2=allAssessments.get(allAssessments.size()-1).getAssessmentID();
            a= new AssessmentEntity(++Id2,editName.getText().toString(),editNote.getText().toString(),editDate.getText().toString(),editAssessment.getText().toString(), partId, prodId);
        }
        inventoryManagementRepository.insert(a);

        Toast toast = Toast.makeText(AssessmentActivity.this,"Assessment added. Please wait for the screen to redirect to the Term Detail page!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);;
        toast.show();

        Intent intent= new Intent(AssessmentActivity.this, TermDetails.class);
        startActivity(intent);

    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void backToTerms(View view) {

        hideKeyboard(this, view);

        Toast toast = Toast.makeText(AssessmentActivity.this,"Please wait for the screen to redirect to the Term Detail page!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);;
        toast.show();

        Intent intent= new Intent(AssessmentActivity.this, TermDetails.class);
        startActivity(intent);
    }

    public void setNotification(View view) {

            Intent intent = new Intent(AssessmentActivity.this, MyReceiver.class);
            intent.putExtra("key", "You have an assessment today!");
            PendingIntent sender = PendingIntent.getBroadcast(AssessmentActivity.this, ++numAlert, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            date = myCalendar3.getTimeInMillis();
            alarmManager.set(AlarmManager.RTC_WAKEUP, date, sender);


    }

    public void deleteAssessmentFromScreen(View view) {

        inventoryManagementRepository.delete(currentAssessment);

        Toast toast = Toast.makeText(AssessmentActivity.this,"Assessment deleted. Please wait for the screen to redirect to the Term Detail page!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);;
        toast.show();

        Intent intent= new Intent(AssessmentActivity.this, TermDetails.class);
        startActivity(intent);

    }
}
