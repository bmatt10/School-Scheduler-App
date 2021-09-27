package com.example.part1.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.example.part1.Database.InventoryManagementRepository;
import com.example.part1.Entity.AssessmentEntity;
import com.example.part1.Entity.CourseEntity;
import com.example.part1.R;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {
    private InventoryManagementRepository inventoryManagementRepository;
    public static int numAlert;
    static int id2;
    int Id;
    String name;
    String price;
    int prodId;
    String startDate;
    String endDate;
    String mentor;
    String phone;
    String status;
    String email;

    EditText editName;
    EditText editPrice;
    EditText editDate;
    EditText editDate2;
    EditText editMentor;
    EditText editPhone;
    EditText editStatus;
    EditText editEmail;

    Calendar myCalendar=Calendar.getInstance();
    Calendar myCalendar2=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener  myDate;
    DatePickerDialog.OnDateSetListener  myDate2;
    Long date;
    CourseEntity currentPart;
    public static int numAssessments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        Id=getIntent().getIntExtra("partID",-1);

        prodId=getIntent().getIntExtra("productID", -1);
        startDate=getIntent().getStringExtra("startDate");
        id2=prodId;
        name=getIntent().getStringExtra("partName");
        price=getIntent().getStringExtra("partPrice");
        endDate=getIntent().getStringExtra("endDate");
        mentor=getIntent().getStringExtra("mentor");
        phone=getIntent().getStringExtra("phone");
        status=getIntent().getStringExtra("status");
        email=getIntent().getStringExtra("email");


        editName=findViewById(R.id.partName);
        editPrice=findViewById(R.id.partPrice);
        editDate = findViewById(R.id.productEnd);
        editDate2 = findViewById(R.id.pickDate3);
        editMentor = findViewById(R.id.editTextTextPersonName2);
        editPhone = findViewById(R.id.editTextTextPersonName3);
        editStatus = findViewById(R.id.editTextTextPersonName);
        editEmail = findViewById(R.id.editTextTextPersonName4);

        if (id2 == -1) id2 = AssessmentActivity.id3;
        inventoryManagementRepository= new InventoryManagementRepository(getApplication());
        List<CourseEntity> allParts=inventoryManagementRepository.getAllParts();
        for(CourseEntity p:allParts){
            if(p.getCourseID()==Id)currentPart=p;
        }

           if(currentPart!=null) {
               name = currentPart.getCourseName();
               price = currentPart.getCourseNotes();
               startDate=currentPart.getStartDate();
               endDate = currentPart.getEndDate();
               mentor = currentPart.getMentor();
               phone = currentPart.getPhone();
               status = currentPart.getStatus();
               email = currentPart.getEmail();
           }

        if(Id!=-1){
            editName.setText(name);
            editPrice.setText(price);
            editDate.setText(startDate);
            editDate2.setText(endDate);
            editMentor.setText(mentor);
            editPhone.setText(phone);
            editStatus.setText(status);
            editEmail.setText(email);
        }
        inventoryManagementRepository= new InventoryManagementRepository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.associated_assessments);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AssessmentEntity> filteredAssessments=new ArrayList<>();
        for(AssessmentEntity p:inventoryManagementRepository.getAllAssessments()){
            if(p.getCourseID()==Id)filteredAssessments.add(p);
        }
        numAssessments=filteredAssessments.size();
        adapter.setWords(filteredAssessments);


        myDate = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            updateLabel();
        };

        editDate.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(CourseDetails.this, myDate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        myDate2 = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, monthOfYear);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            updateLabel2();
        };

        editDate2.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(CourseDetails.this, myDate2, myCalendar2
                    .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                    myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
        });

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel2() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate2.setText(sdf.format(myCalendar2.getTime()));
    }
    public void addCourseFromScreen(View view) {

        hideKeyboard(this, view);

        CourseEntity p;

        if(Id!=-1)p= new CourseEntity(Id,editName.getText().toString(),editPrice.getText().toString(),prodId,editDate.getText().toString(), editDate2.getText().toString(),
                editMentor.getText().toString(), editPhone.getText().toString(), editStatus.getText().toString(), editEmail.getText().toString());
        else{
            List<CourseEntity> allParts=inventoryManagementRepository.getAllParts();
            Id=allParts.get(allParts.size()-1).getCourseID();
            p= new CourseEntity(++Id,editName.getText().toString(),editPrice.getText().toString(),prodId, editDate.getText().toString(), editDate2.getText().toString(),
                    editMentor.getText().toString(), editPhone.getText().toString(), editStatus.getText().toString(), editEmail.getText().toString());
        }
        inventoryManagementRepository.insert(p);

        Toast toast = Toast.makeText(CourseDetails.this,"Course added. Please wait for the screen to redirect to the previous page!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);;
        toast.show();

        Intent intent= new Intent(CourseDetails.this, TermDetails.class);
        startActivity(intent);

    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_partdetail, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.sharing) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, editPrice.getText().toString());

            sendIntent.putExtra(Intent.EXTRA_TITLE, "Send message title");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return true;
        }

        if (id == R.id.delete_course) {

                    inventoryManagementRepository.delete(currentPart);

            Intent intent=new Intent(CourseDetails.this, TermDetails.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void addAssessment(View view) {

        Toast toast = Toast.makeText(CourseDetails.this,"Please wait for the screen to redirect to the Assessment Detail page!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);;
        toast.show();

        Intent intent=new Intent(CourseDetails.this,AssessmentActivity.class);
        intent.putExtra("partID",Id);
        startActivity(intent);

    }

    public void NotifyAssessmentStart(View view) {
        Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
        intent.putExtra("key", "Your course starts today!");
        PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, ++numAlert, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        date = myCalendar.getTimeInMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP, date, sender);
    }

    public void NotifyAssessmentEnd(View view) {
        Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
        intent.putExtra("key", "Your course ends today!");
        PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, ++numAlert, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        date = myCalendar2.getTimeInMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP, date, sender);
    }
}