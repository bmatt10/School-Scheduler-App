package com.example.part1.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import com.example.part1.Database.InventoryManagementRepository;
import com.example.part1.R;

import android.os.Bundle;
import android.view.View;

public class Terms extends AppCompatActivity {
    private InventoryManagementRepository inventoryManagementRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CourseDetails.id2=-1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        inventoryManagementRepository= new InventoryManagementRepository(getApplication());
        inventoryManagementRepository.getAllProducts();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setWords(inventoryManagementRepository.getAllProducts());

    }

    public void addTerm(View view) {
        Intent intent=new Intent(Terms.this, TermDetails.class);
        startActivity(intent);
    }
}