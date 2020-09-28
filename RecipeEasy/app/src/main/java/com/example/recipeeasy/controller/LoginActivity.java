package com.example.recipeeasy.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.recipeeasy.databinding.ActivityLoginBinding;
import com.example.recipeeasy.model.UserService;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.editTextUsername.getText().toString();
                String password = binding.editTextConfirmPassword.getText().toString();

                if (UserService.getInstance().logIn(username, password)) {

                   loggedIn();

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }


            }
        });

        if (UserService.getInstance().getCurrentUser() != null) {
            loggedIn();
        }

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
    }

    private void loggedIn()
    {
        Intent loggedInIntent = new Intent(LoginActivity.this, RecipesActivity.class);
        startActivity(loggedInIntent);

        finishAffinity();
    }
}