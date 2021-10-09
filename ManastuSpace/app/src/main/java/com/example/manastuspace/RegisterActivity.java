package com.example.manastuspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manastuspace.ui.home.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText etRegEmail,etRegPassword,etRegName,etRegphno,etRegadd,etRegdate;
    TextView tvLoginHere;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etRegName = findViewById(R.id.etName);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegphno = findViewById(R.id.etphno);
        etRegadd = findViewById(R.id.etaddress);
        etRegdate = findViewById(R.id.etdate);
        etRegPassword = findViewById(R.id.etRegPass);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
    private void createUser() {
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String name = etRegName.getText().toString();
        String phno = etRegphno.getText().toString();
        String address = etRegadd.getText().toString();
        String date = etRegdate.getText().toString();

        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }else if (TextUtils.isEmpty(name)){
            etRegName.setError("Name cannot be empty");
            etRegName.requestFocus();
        }else if (TextUtils.isEmpty(phno)){
            etRegphno.setError("Phone No. cannot be empty");
            etRegphno.requestFocus();
        }else if (TextUtils.isEmpty(address)){
            etRegadd.setError("Address cannot be empty");
            etRegadd.requestFocus();
        }else if (TextUtils.isEmpty(date)){
            etRegdate.setError("Date cannot be empty");
            etRegdate.requestFocus();
        }else if (TextUtils.getTrimmedLength(phno)!=10){
            etRegphno.setError("Enter 10 digit Phone Number");
            etRegphno.requestFocus();
        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
                        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        UserHelperClass userHelperClass = new UserHelperClass(email,name,address,date,phno);
                        reference.child(id).setValue(userHelperClass);
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}