package com.example.recipeeasy.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.recipeeasy.databinding.ActivitySignupBinding;
import com.example.recipeeasy.model.UserService;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = binding.editTextPassword.getText().toString();
                String passwordConfirmation = binding.editTextConfirmPassword.getText().toString();
                
                if(password.compareTo(passwordConfirmation) == 0)
                {
                    String username = binding.editTextUsername.getText().toString();

                    if(UserService.getInstance().signUp(username, password))
                    {
                        Intent signedUp = new Intent(SignUpActivity.this, RecipesActivity.class);
                        startActivity(signedUp);

                        finishAffinity();
                    }else
                    {
                        Toast.makeText(SignUpActivity.this, "Invalid infos...", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(SignUpActivity.this, "Password confirmation does not match!", Toast.LENGTH_SHORT).show();
                }
                

            }
        });
    }
}