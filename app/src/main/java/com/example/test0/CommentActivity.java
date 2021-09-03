package com.example.test0;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    ImageView imgAvt;
    TextView name;
    RatingBar eva,ship,ser;
    EditText comment;
    Button btnComment;
    Book a = new Book();
    int ratingEva = 0;
    int ratingSer = 0;
    int ratingShip = 0;
    String Comment;
    String customerID;
    String dateTime;
    String id;
    LocalDate today;
    LocalTime time;
    Connection connect = commentAdapt.connect;
    RecyclerView cvcmt;
    commentAdapt adapter;
    String nameUser;
    ArrayList<comment> comments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initFunc();
        btnComment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                ratingEva = (int) eva.getRating();
                ratingSer = (int) ser.getRating();
                ratingShip = (int) ship.getRating();
                Comment = comment.getText().toString();
                today = LocalDate.now();
                time = LocalTime.now();
                dateTime = today.toString()+" "+time.toString();
                try {
                    Statement smt = connect.createStatement();
                    smt.executeQuery("INSERT INTO [EBook].[EBook].[Comment]\n" +
                            "VALUES('"+customerID+"','"+dateTime+"','"+a.getId()+"')\n");
                }
                catch (Exception e){
                }
                try {
                    Statement smt = connect.createStatement();
                    smt.executeQuery("INSERT INTO [EBook].[EBook].[RComment]\n" +
                            "VALUES('"+customerID+"','"+a.getId()+"','"+dateTime+"')\n");
                }catch (Exception e){
                }
                try {
                    Statement smt = connect.createStatement();
                    smt.executeQuery("INSERT INTO [EBook].[EBook].[Content]\n" +
                            "VALUES('"+ratingEva+"','"+Comment+"','"+ratingShip+"','"+ratingSer+"'" +
                            ",'"+customerID+"','"+a.getId()+"','"+dateTime+"')\n");
                }catch (Exception e){
                }
                try {
                    Statement smt = connect.createStatement();
                    ResultSet rs = smt.executeQuery("SELECT [UserCustomer]\n" +
                            "  FROM [EBook].[EBook].[Customer]\n" +
                            "  WHERE Customer.IDCustomer='"+customerID+"'");
                    while(rs.next())
                        nameUser = rs.getString(1);
                }
                catch (Exception e){
                }
                Toast.makeText(CommentActivity.this, "Commented", Toast.LENGTH_LONG).show();
                adapter.comments.add(0,new comment(nameUser,dateTime,Comment,ratingEva,ratingShip,ratingSer,customerID));
                adapter.notifyItemInserted(0);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cvcmt.setLayoutManager(linearLayoutManager);
        setDataCmt();

        adapter = new commentAdapt(this,comments,customerID);
        cvcmt.setAdapter(adapter);
    }

    private void setDataCmt() {
        try {
            Statement smt = connect.createStatement();
            ResultSet rs = smt.executeQuery("SELECT [Evaluate]\n" +
                    "      ,[Comment]\n" +
                    "      ,[ShippingQuantity]\n" +
                    "      ,[ServiceQuantity]\n" +
                    "      ,Content.[IDCustomer]\n" +
                    "      ,[IDBookC]\n" +
                    "      ,[Time],\n" +
                    "\t  Customer.UserCustomer\n" +
                    "  FROM [EBook].[EBook].[Content],[EBook].[EBook].Customer\n" +
                    "  WHERE Content.IDCustomer = Customer.IDCustomer AND Content.IDCustomer = "+customerID+" AND Content.IDBookC='"+id+"'");
            while(rs.next()){
                comment a = new comment(rs.getString(8),
                                        rs.getString(7),
                                        rs.getString(2),
                                        rs.getInt(1),
                                        rs.getInt(3),
                                        rs.getInt(4),
                                        rs.getString(5)
                );

                comments.add(a);
            }
        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }
        try {
            Statement smt = connect.createStatement();
            ResultSet rs = smt.executeQuery("SELECT [Evaluate]\n" +
                    "      ,[Comment]\n" +
                    "      ,[ShippingQuantity]\n" +
                    "      ,[ServiceQuantity]\n" +
                    "      ,Content.[IDCustomer]\n" +
                    "      ,[IDBookC]\n" +
                    "      ,[Time],\n" +
                    "\t  Customer.UserCustomer\n" +
                    "  FROM [EBook].[EBook].[Content],[EBook].[EBook].Customer\n" +
                    "  WHERE Content.IDCustomer = Customer.IDCustomer AND Content.IDCustomer != "+customerID+" AND Content.IDBookC='"+id+"'");
            while(rs.next()){
                comment a = new comment(rs.getString(8),
                        rs.getString(7),
                        rs.getString(2),
                        rs.getInt(1),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5));

                comments.add(a);
            }
        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }

    }

    private void initFunc() {
        Intent intent = getIntent();
        if(intent!=null) {
            id = intent.getStringExtra("IDBookComment");
            customerID = intent.getStringExtra("customerIDComment");
            a = dbBook.getInstance().getBookByID(id);
        }
        imgAvt = findViewById(R.id.avtOrderBuy);
        name = findViewById(R.id.tvBookNameBuy);
        eva = findViewById(R.id.ratingBarEva);
        ship = findViewById(R.id.ratingBarShip);
        ser = findViewById(R.id.ratingBarShip);
        comment = findViewById(R.id.edxComment0123);
        btnComment = findViewById(R.id.btnOder);
        Glide.with(this)
                .asBitmap()
                .load(a.getUrlImg())
                .into(imgAvt);
        name.setText(a.getPublisher());
        cvcmt = findViewById(R.id.rvCmt);
    }
}