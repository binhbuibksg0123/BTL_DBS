package com.example.test0;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView dayOfBirth;
    EditText fname,mname,lname,userName,pass,email,phoneNumber,Address;
    Switch gender;
    Button btnRegister;
    ConstraintLayout layout;
    Connection connect = ConnectionHelper.getConnect();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initFunc();

        dayOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerFunc();
            }
        });
    }
    private void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    private void initFunc(){
        dayOfBirth = findViewById(R.id.tvDayOfBirthShow);
        fname = findViewById(R.id.edxFname);
        mname = findViewById(R.id.edxMname);
        lname = findViewById(R.id.edxLname);
        userName = findViewById(R.id.edxUserNameRegister);
        pass = findViewById(R.id.edxPasswordRegister);
        email = findViewById(R.id.edxEmail);
        phoneNumber = findViewById(R.id.edxPhoneNumber);
        Address = findViewById(R.id.edxAnddress);
        gender = findViewById(R.id.gender);
        btnRegister = findViewById(R.id.btnRegister);
        layout = findViewById(R.id.parrent);
    }
    private void registerFunc() {
        String passWord,username,fName,mName,lName,Email,Phone,address,DayOfBirth;
        passWord = pass.getText().toString();
        username = userName.getText().toString();
        fName = fname.getText().toString();
        mName = mname.getText().toString();
        lName = lname.getText().toString();
        Email = email.getText().toString();
        Phone = phoneNumber.getText().toString();
        address = Address.getText().toString();
        DayOfBirth = dayOfBirth.getText().toString();
        int random_id = 0000000;
        char Gender = gender.isChecked() ? 'F':'M';
        boolean Duplicate = false;
        if(passWord.isEmpty()||username.isEmpty()||fName.isEmpty()||mName.isEmpty()||lName.isEmpty()||Email.isEmpty()||Phone.isEmpty()||address.isEmpty()){
            Snackbar.make(layout,"Please enter your information",Snackbar.LENGTH_LONG).show();
            return;
        }
        if(username.length()<8||username.length()>16) {
            Snackbar.make(layout,"Your username must be 8-16 characters",Snackbar.LENGTH_LONG).show();
            return;
        }
        if(passWord.length()<8||passWord.length()>16){
            Snackbar.make(layout,"Your password must be 8-16 characters",Snackbar.LENGTH_LONG).show();
            return;
        }

        try {
            do {
                random_id = (int)Math.floor(Math.random()*(9999999-1000000+1)+1000000);
                Statement smt = connect.createStatement();
                ResultSet rs = smt.executeQuery("SELECT [PasswordCustomer],\n" +
                        "\t\t[UserCustomer],[IDCustomer]\n" +
                        "FROM [EBook].[EBook].[Customer]");
                while (rs.next()) {
                    if (passWord.equals(rs.getString(1)) && username.equals(rs.getString(2))) {
                        Snackbar.make(layout, "This account have existed, please try again", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                }
                if(Integer.toString(random_id).equals(rs.getString(3))) Duplicate = true;
            }while (Duplicate);
        }
        catch (Exception e){
            Log.e("Exception",e.toString());
        }
        try {
            Log.d("Entered","Entered");
            Statement smt = connect.createStatement();
            smt.executeQuery("INSERT INTO [EBook].[EBook].[Customer]\n" +
                    "  VALUES('"+fName+"','"+mName+"','"+lName+"','"+Gender+"','"+DayOfBirth+"','"+address+"','"+Phone+"','"+Email+"','"+passWord+"','"+username+"','"+random_id+"',null,0)");
        }
        catch (Exception e){
            Log.d("Error register",e.toString());
        }
        JavaMailAPI mailApt = new JavaMailAPI(this,Email,"Register MyOurBook","Your CustomerID is "+random_id);
        mailApt.execute();
        Intent intent = new Intent(RegisterActivity.this,Login.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        dayOfBirth.setText(i+"-"+i1+"-"+i2);
    }
}