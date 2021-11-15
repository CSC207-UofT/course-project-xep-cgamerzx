package com.xepicgamerzx.hotelier.user_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.storage.user.data.UserDAO;
import com.xepicgamerzx.hotelier.storage.user.data.UserDatabase;
import com.xepicgamerzx.hotelier.storage.user.model.User;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText userId, password, email;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        userId = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        email = findViewById(R.id.emailInput);
        registerBtn = findViewById(R.id.registerBtn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(userId.getText().toString(),
                        password.getText().toString(),
                        email.getText().toString());

                if(validateInput(user)) {
                    // Insert to db
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDAO userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Successfully registered.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                } else {
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private Boolean validateInput(User user) {
        if(user.getUserName().isEmpty() ||
                user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}