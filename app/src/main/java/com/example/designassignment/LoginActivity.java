package com.example.designassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail,etPassword;
    TextView registerNow;
    ImageView ivFacebook,ivGoogleplus;
    Button btnLogin;

    static  final int REGISTER_PAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        setUpUI();
        addListeners();
    }

    private void addListeners() {
        btnLogin.setOnClickListener(this);
        registerNow.setOnClickListener(this);
    }

    public boolean validateLogin(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        String useremail = sharedPref.getString(getString(R.string.useremail),"");
        String userpassword = sharedPref.getString(getString(R.string.userpassword),"");
        return (useremail.equals(etEmail.getText().toString().trim())) && (userpassword.equals(etPassword.getText().toString().trim()));
    }

    void setUpUI(){
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        ivFacebook = findViewById(R.id.ivLoginFacebook);
        ivGoogleplus = findViewById(R.id.ivGoogleplus);
        btnLogin = findViewById(R.id.btnLogin);
        registerNow = findViewById(R.id.tvRegisterNow);
    }

    public boolean isInputValid(){
        return
                !etPassword.getText().toString().isEmpty() &&
                !etEmail.getText().toString().isEmpty();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id){
            case R.id.tvRegisterNow:
                intent = new Intent(this,RegisterActivity.class);
                startActivityForResult(intent,REGISTER_PAGE);
                break;
            case R.id.btnLogin:
                if(!isInputValid()){
                    Toast.makeText(this,"All fields required!!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!validateLogin()){
                    Toast.makeText(this,"Invalid Logins!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REGISTER_PAGE){
            Toast.makeText(this,"From RegisterPage",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }
}
