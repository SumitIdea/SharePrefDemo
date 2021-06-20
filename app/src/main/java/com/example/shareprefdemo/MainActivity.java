package com.example.shareprefdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e_uname, e_password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=getSharedPreferences("LogIn",MODE_PRIVATE);
        boolean logged=sharedPreferences.getBoolean("isLogged",false);
        if(logged)
        {
            startActivity(new Intent(MainActivity.this,Home.class));
        }

        e_uname=findViewById(R.id.et_nem);
        e_password=findViewById(R.id.et_password);
        login=findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=e_uname.getText().toString();
                String password=e_password.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    e_uname.setError("Please Enter Username");
                }
                else if(TextUtils.isEmpty(password))
                {
                    e_password.setError("Please Enter Password");
                }
                else
                {
                    SharedPreferences sharedPreferences=getSharedPreferences("LogIn",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putString("user_name",name);
                    editor.putString("user_password",password);
                    editor.putBoolean("isLogged",true);
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Log In Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Home.class));
                }

            }
        });
    }
}