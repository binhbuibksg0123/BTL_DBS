package com.example.test0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class BuyActivity extends AppCompatActivity {

    ImageView imgAvt;
    TextView tvName,price;
    EditText edxAmount,address;
    Button btnBuy;
    String bookID;
    Book a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        initFunc();
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BuyActivity.this, "Ordered", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFunc() {
        imgAvt = findViewById(R.id.avtOrderBuy);
        tvName = findViewById(R.id.tvBookNameBuy);
        price = findViewById(R.id.tvCurrentPriceBuy);
        edxAmount = findViewById(R.id.editTextNumber);
        address = findViewById(R.id.edxAddressBuy);
        btnBuy = findViewById(R.id.btnBuy);
        Intent intent = getIntent();
        bookID = intent.getStringExtra("IDBookBuy");
        a = dbBook.getInstance().getBookByID(bookID);
        tvName.setText(a.getName());
        Glide.with(this)
                .asBitmap()
                .load(a.getUrlImg())
                .into(imgAvt);
        String amount =  a.getCurrentBookPrice();
        price.setText(amount);
    }
}