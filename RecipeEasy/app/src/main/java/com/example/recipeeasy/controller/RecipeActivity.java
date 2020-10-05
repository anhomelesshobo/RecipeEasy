package com.example.recipeeasy.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.recipeeasy.api.RecipeRequestListener;
import com.example.recipeeasy.databinding.ActivityRecipeBinding;
import com.example.recipeeasy.model.Recipe;
import com.example.recipeeasy.model.RecipesService;
import com.example.recipeeasy.model.UserService;

public class RecipeActivity extends AppCompatActivity {
    private ActivityRecipeBinding binding;
    private int recipeId;
    private int getUserId()
    {
        return UserService.getInstance().getCurrentUser().getId();
    }

    private String getCategory()
    {
        String category = null;
        int categoryId = binding.radioGroupCategory.getCheckedRadioButtonId();
        if (categoryId != -1) {
            category = (String) findViewById(categoryId).getTag();
        }

        return category;
    }

    public String getName() {
        return binding.editTextName.getText().toString();
    }

    public int getDurationHours() {
        return binding.spinnerTimePicker.getHour();
    }

    public int getDurationMinutes() {
        return binding.spinnerTimePicker.getMinute();
    }

    public String getDescription() {
        return binding.multilineDescription.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecipeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.spinnerTimePicker.setIs24HourView(true);
        binding.spinnerTimePicker.setHour(0);
        binding.spinnerTimePicker.setMinute(0);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            Recipe recipe = extras.getParcelable(RecipesActivity.KEY_EDIT_RECIPE_EXTRA);

            recipeId = recipe.getId();

            ((RadioButton)binding.radioGroupCategory.findViewWithTag(recipe.getCategory())).setChecked(true);
            binding.editTextName.setText(recipe.getName());
            binding.spinnerTimePicker.setHour(recipe.getDurationHours());
            binding.spinnerTimePicker.setMinute(recipe.getDurationMinutes());
            binding.multilineDescription.setText(recipe.getDescription());

            binding.buttonSave.setOnClickListener(editClickListener);
            binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecipesService.getInstance().delete(recipeId, recipeRequestListener, RecipeActivity.this);
                }
            });
        }else {
            binding.buttonDelete.setVisibility(View.GONE);
            binding.buttonSave.setText("Add");

            binding.buttonSave.setOnClickListener(addClickListener);
        }




    }

    private View.OnClickListener addClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            RecipesService.getInstance().add(getCategory(), getName(), getDurationHours(), getDurationMinutes(), getDescription(), getUserId(), recipeRequestListener , RecipeActivity.this);



        }
    };

    private View.OnClickListener editClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            RecipesService.getInstance().edit(recipeId, getCategory(), getName(), getDurationHours(), getDurationMinutes(), getDescription(), recipeRequestListener , RecipeActivity.this);


        }
    };

    private RecipeRequestListener recipeRequestListener = new RecipeRequestListener() {
        @Override
        public void onResponse(boolean success) {
            if (success) {
                finishOK();
            } else
            {
                Toast.makeText(RecipeActivity.this,"Invalid infos...",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void finishOK()
    {
        setResult(RESULT_OK);
        finish();
    }
}