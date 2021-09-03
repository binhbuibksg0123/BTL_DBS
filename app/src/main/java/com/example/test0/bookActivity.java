package com.example.test0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class bookActivity extends AppCompatActivity {
    private Button btnComment,btnBuy;
    private TextView tv0,tv1,tv2,tv3;
    private ImageView img0;
    public static final String code = "BookID";
    String customerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initFunc();
        Intent intent = getIntent();
        if(intent!=null) {
            String id = intent.getStringExtra("BookID");
            customerID = intent.getStringExtra("CustomerID");
            Book a = dbBook.getInstance().getBookByID(id);
            if(a!=null) {
                setData(a);
                Comment(a.getId());
                BuyFunc(a.getId());
            }
        }
    }

    private void BuyFunc(String id) {
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookActivity.this,BuyActivity.class);
                intent.putExtra("IDBookBuy",id);
                intent.putExtra("customerID",customerID);
                startActivity(intent);
            }
        });
    }

    private void Comment(String id) {
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookActivity.this,CommentActivity.class);
                intent.putExtra("IDBookComment",id);
                intent.putExtra("customerIDComment",customerID);
                startActivity(intent);
            }
        });

    }

    private void initFunc(){
        btnComment = findViewById(R.id.btnOder);
        btnBuy = findViewById(R.id.btnBuy);
        tv0 = findViewById(R.id.tvBookNameBuy);
        tv1 = findViewById(R.id.tvAuthorCt);
        tv2 = findViewById(R.id.tvPriceCt);
        tv3 = findViewById(R.id.tvDesCt);
        img0 = findViewById(R.id.avtOrderBuy);
    }
    private void setData(Book a){
        tv0.setText(a.getName());
        tv1.setText(a.getPublisher());
        tv2.setText(String.valueOf(a.getCurrentBookPrice()));
        tv3.setText(a.getDesc());
        Glide.with(this)
                .asBitmap()
                .load(a.getUrlImg())
                .into(img0);
    }
}