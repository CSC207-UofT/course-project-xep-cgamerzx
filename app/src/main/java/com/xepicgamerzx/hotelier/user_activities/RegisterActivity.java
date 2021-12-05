package com.xepicgamerzx.hotelier.user_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.UserDao;
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

                if (validateInput(user) && validatePassword(user)) {
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

                } else if (!validateInput(user)) {
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!validatePassword(user)) {
                    Toast.makeText(getApplicationContext(), "Please make a stronger password" +
                            " (at least 6 characters long with at least 1 of each: uppercase, lowercase, number, special character (!@#$%^&*()_+.))", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private Boolean validateInput(User user) {
        return !user.getUserName().isEmpty() &&
                !user.getEmail().isEmpty() && !user.getPassword().isEmpty();
    }

    /**
     * Check whether the password meets the requirement
     * (at least 6 characters in length with at least 1 lowercase letter, 1 uppercase letter,
     * 1 number, and 1 special character)
     *
     * @param user
     * @return Whether the password matches the regex
     */
    private Boolean validatePassword(User user) {
        return user.getPassword().matches("^(?=(.*[a-z]))(?=(.*[A-Z]))(?=(.*[0-9]))(?=(.*[!@#$%^&*()_+.])).{6,}$");
    }

}