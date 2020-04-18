package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hospital.Utils.Constants;
import com.example.hospital.Utils.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, Constants {

    Button btn, registerBtn;

    TextInputEditText userName, lastName, email, phoneNo, password;
    TextInputLayout userNameL, lastNameL, emailL, phoneNoL, passwordL;
    String username, lastname, email1, phoneno, password1;
    User user;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.nameEdit);
        lastName = findViewById(R.id.lastNameEdit);
        email = findViewById(R.id.emailEdit);
        phoneNo = findViewById(R.id.phoneEdit);
        password = findViewById(R.id.passwordEdit);
        registerBtn = findViewById(R.id.registerBtn);
        btn = findViewById(R.id.sign_in_screen);

        userNameL = findViewById(R.id.name);
        lastNameL = findViewById(R.id.username);
        emailL = findViewById(R.id.email);
        phoneNoL = findViewById(R.id.phoneNo);
        passwordL = findViewById(R.id.password);

            btn.setOnClickListener(this);
            registerBtn.setOnClickListener(this);
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.registerBtn:

                username = userName.getText().toString().trim();
                lastname = lastName.getText().toString().trim();
                email1 = email.getText().toString().trim();
                phoneno = phoneNo.getText().toString().trim();
                password1 = password.getText().toString();


                boolean flag = validate();
                if (flag) {
                    go();
                }
                break;

            case R.id.sign_in_screen:
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setContentView(R.layout.activity_sign_up);
                break;
        }

    }

    // request focus
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private boolean validateEmail() {
        if (email1.isEmpty()) {
            emailL.setError("Enter Email");
            requestFocus(email);
            return false;
        } else {
            final String EMAIL_PATTERN =
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email1);
            if (!matcher.matches()) {
                emailL.setError("write Correct Email address");
                requestFocus(email);
                return false;
            } else {
                emailL.setErrorEnabled(false);
            }
        }
        return true;
    }

    private boolean validateName() {
        if (username.isEmpty()) {
            userNameL.setError("Enter Name");
            requestFocus(userName);
            return false;
        } else {
            if (username.length() < 3) {
                userNameL.setError("minimun 3 character");
                requestFocus(userName);
                return false;
            } else {
                userNameL.setErrorEnabled(false);
            }
        }
        return true;
    }

    private boolean validateLastName() {
        if (lastname.isEmpty()) {
            lastNameL.setError("Enter LastName");
            requestFocus(lastName);
            return false;
        } else {
            if (lastname.length() < 3) {
                userNameL.setError("minimun 3 character");
                requestFocus(lastName);
                return false;
            } else {
                lastNameL.setErrorEnabled(false);
            }
        }
        return true;
    }

    private boolean validatePhone() {
        if (phoneno.isEmpty()) {
            phoneNoL.setError("Enter Email");
            requestFocus(phoneNo);
            return false;
        } else {
            final String PHONE_PATTERN =
                    "(0|91)?[7-9][0-9]{9}";
            Pattern pattern = Pattern.compile(PHONE_PATTERN);
            Matcher matcher = pattern.matcher(phoneno);
            if (!matcher.matches()) {
                phoneNoL.setError("write Correct Phone Number  “xxxxxxxxxx” ");
                requestFocus(phoneNo);
                return false;
            } else {
                phoneNoL.setErrorEnabled(false);
            }
        }
        return true;
    }

    private boolean validatePassword() {
        if (password1.isEmpty()) {
            passwordL.setError("Enter password");
            requestFocus(password);
            return false;
        } else {
            final String PASSWORD_PATTERN =
                    "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password1);
            if (!matcher.matches()) {
                passwordL.setError("Password should be alpha-numeric-symbol ");
                requestFocus(password);
                return false;
            } else {
                passwordL.setErrorEnabled(false);
            }
        }
        return true;
    }

    private boolean validate(){

        if (!validateName()){
            return false;
        }
        if (!validateLastName()){
            return false;
        }
        if (!validateEmail()){
            return false;
        }
        if (!validatePhone()){
            return false;
        }
        if (!validatePassword()){
            return false;
        }
        return true;

    }

    private void go(){
        user = User.getInstance();
        user.setUserName(username);
        user.setLastName(lastname);
        user.setEmail(email1);
        user.setPhoneNumer(phoneno);
        user.setPassword(password1);

        SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Name, username);
                    editor.putString(Phone, phoneno);
                    editor.putString(Email, email1);
                    editor.putString(lastnamekey, lastname);
                    editor.putString(passwordKey, password1);
                    editor.commit();
        Toast.makeText(SignUpActivity.this, "registered", Toast.LENGTH_SHORT).show();
        finish();
    }
}
