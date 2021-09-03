package com.example.test0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv0;
    BookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String idCustomer = intent.getStringExtra("CustomerID");
        rv0 = findViewById(R.id.rvBook);
        adapter = new BookAdapter(this,idCustomer);
        rv0.setAdapter(adapter);
        rv0.setLayoutManager(new GridLayoutManager(this,2));
        adapter.setBooks(dbBook.getInstance().getBooks());
    }
    public void GetDataToTextView(View v){
    }
}