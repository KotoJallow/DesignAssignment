package com.example.designassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etPassword,etEmail,etPhoneNumber;
    Button btnRegister;
    ImageView ivGooglepus,ivFacebbok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        setUpUI();
        addListeners();
    }
    public void saveInfo(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.username),etName.getText().toString().trim());
        editor.putString(getString(R.string.userpassword),etPassword.getText().toString().trim());
        editor.putString(getString(R.string.useremail),etEmail.getText().toString().trim());
        editor.putString(getString(R.string.usermobile),etPhoneNumber.getText().toString().trim());
        editor.apply();
    }

    private void addListeners() {
        btnRegister.setOnClickListener(this);
    }

    private void setUpUI() {
        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etPhoneNumber = findViewById(R.id.etMobile);
        btnRegister = findViewById(R.id.btnRegister);
        ivGooglepus = findViewById(R.id.ivGoogleplus);
        ivFacebbok = findViewById(R.id.ivFacebook);
    }

    public boolean isInputValid(){
        return !etName.getText().toString().isEmpty() &&
               !etPassword.getText().toString().isEmpty() &&
               !etEmail.getText().toString().isEmpty() &&
               !etPhoneNumber.getText().toString().isEmpty();
    }
    public static boolean isValidEmail(CharSequence target) {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnRegister:
                if(!isInputValid()){
                    Toast.makeText(this,"All fields required!!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(etEmail.getText().toString().trim())){
                    Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                saveInfo();
                Intent intent = new Intent();
                //Add Intent Data
                setResult(RESULT_OK,intent);
                finish();
                break;
             default:
                 break;

        }
    }
}
