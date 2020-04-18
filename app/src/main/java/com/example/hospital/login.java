package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Pair;
import android.widget.Toast;
import com.example.hospital.Utils.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class login extends AppCompatActivity implements Constants {
        Button callSignUp, login_btn;
        ImageView image;
        TextView logoText, sloganText;
        TextInputLayout usernameL, passwordL;
        TextInputEditText userName, password;
        SharedPreferences sharedPreferences;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //This Line will hide the status bar from the screen
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_login);

            //Hooks
            callSignUp = findViewById(R.id.signup_screen);
            image = findViewById(R.id.logo_image);
            logoText = findViewById(R.id.logo_name);
            sloganText = findViewById(R.id.slogan_name);
            usernameL = findViewById(R.id.username);
            passwordL = findViewById(R.id.password);
            login_btn = findViewById(R.id.login_btn);

            userName = findViewById(R.id.nameEdit);
            password = findViewById(R.id.passwordEdit);


            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = userName.getText().toString();
                    String pass = password.getText().toString();

                    if (pass.isEmpty()){
                        passwordL.setError("Enter your password");
                        requestFocus(password);
                    }

                    if (name.isEmpty()){
                            usernameL.setError("Enter Name");
                            requestFocus(userName);
                    }

                    if ( !name.isEmpty() && !pass.isEmpty()){
                            go(name,pass);
                    }

                    }

            });

            callSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(login.this, SignUpActivity.class);
                    Pair[] pairs = new Pair[7];
                    pairs[0] = new Pair<View, String>(image, "logo_image");
                    pairs[1] = new Pair<View, String>(logoText, "logo_text");
                    pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
                    pairs[3] = new Pair<View, String>(usernameL, "username_tran");
                    pairs[4] = new Pair<View, String>(passwordL, "password_tran");
                    pairs[5] = new Pair<View, String>(login_btn, "button_tran");
                    pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tran");
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this, pairs);
                        startActivity(intent, options.toBundle());
                    }
                }
            });

        }
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private void go(String name , String pass){

            Toast.makeText(this,"done", Toast.LENGTH_LONG).show();

        sharedPreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Name) && sharedPreferences.contains(passwordKey)){
                String savedName = sharedPreferences.getString(Name,"");
                String savedPass = sharedPreferences.getString(passwordKey,"");
                if (name.equals(savedName) && savedPass.equals(pass)){
                    Toast.makeText(login.this, "Toast", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(login.this, Dashboard.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"wrong password",Toast.LENGTH_LONG ).show();
                }
            }  else {
                Toast.makeText(getApplicationContext(),"no user saved yet , please sign up", Toast.LENGTH_LONG).show();
            }
        }
    }
