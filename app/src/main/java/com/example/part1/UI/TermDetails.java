package com.example.part1.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import com.example.part1.Database.InventoryManagementRepository;
import com.example.part1.Entity.CourseEntity;
import com.example.part1.Entity.TermEntity;
import com.example.part1.R;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TermDetails extends AppCompatActivity {
    private InventoryManagementRepository inventoryManagementRepository;

    int Id;
    String name;
    String price;
    String startDate;
    String endDate;
    EditText editName;
    EditText editPrice;
    EditText editDate;
    EditText editDate2;
    TermEntity currentProduct;
    public static int numParts;
    Calendar myCalendar=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener  myDate;
    DatePickerDialog.OnDateSetListener  myDate2;
    Long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AssessmentActivity.id3=-1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        Id = getIntent().getIntExtra("productID", -1);

        if (Id == -1) Id = CourseDetails.id2;
        inventoryManagementRepository = new InventoryManagementRepository(getApplication());
        List<TermEntity> allProducts = inventoryManagementRepository.getAllProducts();

        for (TermEntity p : allProducts) {
            if (p.getTermID() == Id) currentProduct = p;
        }

        name = getIntent().getStringExtra("productName");
        price = getIntent().getStringExtra("productPrice");
        startDate = getIntent().getStringExtra("productStart");
        endDate = getIntent().getStringExtra("productEnd");
        editName = findViewById(R.id.productName);
        editPrice = findViewById(R.id.productPrice);
        editDate = findViewById(R.id.productStart);
        editDate2 = findViewById(R.id.productEnd);


        if (currentProduct != null) {
            name = currentProduct.getTermName();
            price = currentProduct.getTermNotes();
            startDate = currentProduct.getTermStart();
            endDate = currentProduct.getTermEnd();
        }
        if (Id != -1) {
            editName.setText(name);
            editPrice.setText(price);
            editDate.setText(startDate);
            editDate2.setText(endDate);
        }
        inventoryManagementRepository = new InventoryManagementRepository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.associated_parts);
        final CourseAdapter adapter = new CourseAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<CourseEntity> filteredParts = new ArrayList<>();
        for (CourseEntity p : inventoryManagementRepository.getAllParts()) {
            if (p.getTermID() == Id) filteredParts.add(p);
        }
        numParts = filteredParts.size();
        adapter.setWords(filteredParts);
        

        myDate = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            updateLabel();

        };

        myDate2 = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            updateLabel2();

        };

        editDate.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(TermDetails.this, myDate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        editDate2.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(TermDetails.this, myDate2, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

        editDate2.setText(sdf.format(myCalendar.getTime()));

    }

    public void addCourse(View view) {
        Intent intent=new Intent(TermDetails.this, CourseDetails.class);
        intent.putExtra("productID",Id);
        startActivity(intent);

        Toast toast = Toast.makeText(TermDetails.this,"Please wait for the screen to redirect to the Course Detail page!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);;
        toast.show();
    }



    public void addTermFromScreen(View view) {

        hideKeyboard(this, view);

        TermEntity p;

        if(Id!=-1)p= new TermEntity(Id,editName.getText().toString(),editPrice.getText().toString(), editDate.getText().toString(), editDate2.getText().toString());
        else{
            List<TermEntity> allProducts=inventoryManagementRepository.getAllProducts();
            Id=allProducts.get(allProducts.size()-1).getTermID();
            p= new TermEntity(++Id,editName.getText().toString(),editPrice.getText().toString(), editDate.getText().toString(), editDate2.getText().toString());
        }
        inventoryManagementRepository.insert(p);

        Toast.makeText(getApplicationContext(),"Term has been saved. Please wait for the screen to redirect to the previous page!",Toast.LENGTH_LONG).show();

        Intent intent= new Intent(TermDetails.this, Terms.class);
        startActivity(intent);

    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.delete) {
            if(numParts==0) {
                inventoryManagementRepository.delete(currentProduct);
                Intent intent=new Intent(TermDetails.this, Terms.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),"You cannot delete a term with courses in it",Toast.LENGTH_LONG).show();// make another toast
            }
        }


        return super.onOptionsItemSelected(item);
    }

}