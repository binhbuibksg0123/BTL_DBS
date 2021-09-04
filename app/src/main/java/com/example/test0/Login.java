package com.example.test0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class Login extends AppCompatActivity {

    EditText edxUserName;
    EditText edxPassword;
    Button btnLogin;
    TextView tvSignUp,tvLogin,tvLoad,changePass;
    ProgressBar progessBar;
    ConstraintLayout layout;
    Connection connect=ConnectionHelper.getConnect();;
    String connectionResult = "";
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progessBar.setVisibility(View.VISIBLE);
//                tvLoad.setVisibility(View.VISIBLE);
                String query = "SELECT [PasswordCustomer],\n" +
                        "         [UserCustomer],\n" +
                        "         [IDCustomer]\n" +
                        "FROM [EBook].[EBook].[Customer]";
                loginFunc(query);
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,changePass.class);
                startActivity(intent);
            }
        });
    }

    private void loginFunc(String query) {
        String pass,userName;
        pass = edxPassword.getText().toString();
        userName = edxUserName.getText().toString();
        if(pass.isEmpty()||userName.isEmpty()){
            Snackbar.make(layout,"Please enter your information",Snackbar.LENGTH_LONG).show();
            return;
        }
//        else if(pass.length()<8||pass.length()>16){
//            nofPassword.setText("Your password must be 8-16 characters");
//            nofPassword.setVisibility(View.VISIBLE);
//        }
//        else if(userName.length()<8||userName.length()>16) {
//            nofPassword.setText("Your user name must be 8-16 characters");
//            nofPassword.setVisibility(View.VISIBLE);
//        }
        try {
            Statement smt = connect.createStatement();
            ResultSet rs = smt.executeQuery(query);
            while(rs.next()){
                if(pass.equals(rs.getString(1))&&userName.equals(rs.getString(2))){
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    intent.putExtra("CustomerID",rs.getString(3));
                    startActivity(intent);
                    finish();
                }
            }
        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }
        Snackbar.make(layout,"This account doesn't exist, please try again",Snackbar.LENGTH_LONG).show();
    }

    private void initView() {
        edxUserName = findViewById(R.id.edxUserNameRegister);
        edxPassword = findViewById(R.id.edxPasswordRegister);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvLogin = findViewById(R.id.tvRegister);
        layout = findViewById(R.id.parentLogin);
        progessBar = findViewById(R.id.progressBar);
        tvLoad = findViewById(R.id.tvLoad);
        changePass = findViewById(R.id.tvForgot);
    }
}