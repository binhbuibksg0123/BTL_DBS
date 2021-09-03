package com.example.test0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class changePass extends AppCompatActivity {
    EditText userName,password,ID;
    Button btnChange;
    Connection connection = ConnectionHelper.getConnect();
    String customerID,name,pass;
    ConstraintLayout parrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        initFunc();
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });
    }

    private void change() {
        customerID = ID.getText().toString();
        name = userName.getText().toString();
        pass = password.getText().toString();
        boolean check = false;
        if(customerID.isEmpty()||name.isEmpty()||pass.isEmpty()){
            Snackbar.make(parrent,"Please enter your information",Snackbar.LENGTH_LONG).show();
            return;
        }
        if(name.length()<8||name.length()>16) {
            Snackbar.make(parrent,"Your username must be 8-16 characters",Snackbar.LENGTH_LONG).show();
            return;
        }
        if(pass.length()<8||pass.length()>16){
            Snackbar.make(parrent,"Your password must be 8-16 characters",Snackbar.LENGTH_LONG).show();
            return;
        }
        if(customerID.length()!=7){
            Snackbar.make(parrent,"Your ID must be 7 numbers",Snackbar.LENGTH_LONG).show();
            return;
        }

        ResultSet rs;
        try {
            Statement smt = connection.createStatement();
            rs = smt.executeQuery("\n" +
                    "  SELECT [IDCustomer]\n" +
                    "  FROM [EBook].[EBook].[Customer]");
            Log.d("CheckForgotSelect",rs.toString()+"hello");
            while(rs.next()){
                if(customerID.equals(rs.getString(1))){
                    check = true;
                    smt.executeQuery("  UPDATE [EBook].[EBook].[Customer]\n" +
                            "    SET UserCustomer = '"+name+"', PasswordCustomer = '"+pass+"'\n" +
                            "  WHERE IDCustomer = '"+customerID+"'");
                    Intent intent = new Intent(changePass.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
        catch (Exception e){
            Log.e("Exception",e.toString()+"Bug there");
        }
        if(!check)
            Toast.makeText(this, "Please check your ID", Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(changePass.this,Login.class);
            startActivity(intent);
            finish();
        }
    }

    private void initFunc() {
        userName = findViewById(R.id.edxUserNameForgot);
        password = findViewById(R.id.edxPasswordForgot);
        ID = findViewById(R.id.edxID);
        parrent = findViewById(R.id.parrentForgot);
        btnChange = findViewById(R.id.btnForgot);
    }
}