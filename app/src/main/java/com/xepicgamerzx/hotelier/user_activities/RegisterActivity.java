package com.xepicgamerzx.hotelier.user_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.storage.dao.UserDao;
import com.xepicgamerzx.hotelier.storage.hotelier_database.HotelierDatabase;
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
                    HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(getApplicationContext());
                    UserDao userDao = hotelierDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.insert(user);
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