package com.xepicgamerzx.hotelier.user_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.home_page_activities.MainActivity;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.storage.user.data.UserDAO;
import com.xepicgamerzx.hotelier.storage.user.data.UserDatabase;
import com.xepicgamerzx.hotelier.storage.user.model.User;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText userId, password;
    Button loginBtn;
    UserManager userManager = new UserManager();

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
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDAO userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDao.login(userIdText, passwordText);

                            if(user == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Login.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                userManager.saveUser(user, getApplicationContext());
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        }
                    }).start();
                }
            }

        });
    }
}