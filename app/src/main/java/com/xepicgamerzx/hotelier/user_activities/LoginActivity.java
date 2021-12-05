package com.xepicgamerzx.hotelier.user_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.home_page_activities.MainActivity;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.UserDao;
import com.xepicgamerzx.hotelier.storage.user.model.User;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText userId, password;
    Button loginBtn;
    UserManager userManager = UserManager.getManager(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        userId = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform query
                    HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(getApplicationContext());
                    UserManager userManager = UserManager.getManager(hotelierDatabase);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userManager.login(userIdText, passwordText);

                            if (userManager.user == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Login.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                userManager.saveUser(userManager.user, getApplicationContext());
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        }
                    }).start();
                }
            }

        });
    }
}