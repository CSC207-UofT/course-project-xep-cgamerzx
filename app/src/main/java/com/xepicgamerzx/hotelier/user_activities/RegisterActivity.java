package com.xepicgamerzx.hotelier.user_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.user.UserManager;
import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText userId, password, email;
    TextView errorText;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        userId = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        email = findViewById(R.id.emailInput);
        registerBtn = findViewById(R.id.registerBtn);
        errorText = findViewById(R.id.errorRegistrationText);


        registerBtn.setOnClickListener(v -> {
            User user = new User(Objects.requireNonNull(userId.getText()).toString(),
                    Objects.requireNonNull(password.getText()).toString(),
                    Objects.requireNonNull(email.getText()).toString());

            if (validateInput(user) && validatePassword(user)) {
                // Insert to db
                HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(getApplicationContext());
                UserManager userManager = UserManager.getManager(hotelierDatabase);
                new Thread(() -> {
                    userManager.registerUser(user);
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Successfully registered.", Toast.LENGTH_SHORT).show());
                }).start();

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            } else if (!validateInput(user)) {
                Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!validatePassword(user)) {
                String errorMsg = "Please make a stronger password" +
                        " (at least 6 characters long with at least 1 of each: uppercase, lowercase, number, special character (!@#$%^&*()_+.))";
                errorText.setText(errorMsg);
                errorText.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Please make a stronger password" +
                        " (at least 6 characters long with at least 1 of each: uppercase, lowercase, number, special character (!@#$%^&*()_+.))", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
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
     * @param user User being validated
     * @return Whether the password matches the regex
     */
    private Boolean validatePassword(User user) {
        return user.getPassword().matches("^(?=(.*[a-z]))(?=(.*[A-Z]))(?=(.*[0-9]))(?=(.*[!@#$%^&*()_+.])).{6,}$");
    }

}