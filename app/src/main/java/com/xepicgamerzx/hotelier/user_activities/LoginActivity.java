package com.xepicgamerzx.hotelier.user_activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.home_page_activities.MainActivity;
import com.xepicgamerzx.hotelier.storage.Manage;

import java.util.Objects;

/**
 * Activity for user login
 */
public class LoginActivity extends AppCompatActivity {
    TextInputEditText userId, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        userId = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            String userIdText = Objects.requireNonNull(userId.getText()).toString();
            String passwordText = Objects.requireNonNull(password.getText()).toString();
            if (userIdText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Fill all fields.", Toast.LENGTH_SHORT).show();
            } else {
                // Perform query
                Manage manage = Manage.getManager(getApplication());
                new Thread(() -> {
                    manage.userManager.login(userIdText, passwordText, getApplicationContext());

                    if (manage.userManager.getUser() == null) {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Invalid Login.", Toast.LENGTH_SHORT).show());
                    } else {
                        manage.userManager.logInLocally(true, getApplicationContext());
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }).start();
            }
        });
    }
}