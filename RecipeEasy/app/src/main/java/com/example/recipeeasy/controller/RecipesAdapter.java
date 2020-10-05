package com.example.recipeeasy.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeeasy.R;
import com.example.recipeeasy.databinding.RecyclerViewRecipeBinding;
import com.example.recipeeasy.model.Recipe;

import java.util.ArrayList;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {


    private final OnRecipeClickListener recipeClickListener;
    private ArrayList<Recipe> recipes;

    public RecipesAdapter( OnRecipeClickListener recipeClickListener) {
        this.recipes = new ArrayList<>();
        this.recipeClickListener = recipeClickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewRecipeBinding binding = RecyclerViewRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
            Recipe recipe = recipes.get(position);

            holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(ArrayList<Recipe> userRecipes) {
        this.recipes = userRecipes;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder
    {
        private RecyclerViewRecipeBinding binding;

        public RecipeViewHolder(@NonNull RecyclerViewRecipeBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(final Recipe recipe) {

            if(recipe.getCategory().compareTo("breakfast") == 0) {

                binding.imageCategory.setImageResource(R.drawable.img_breakfast);
            }else if(recipe.getCategory().compareTo("lunch") == 0)
            {
                binding.imageCategory.setImageResource(R.drawable.img_lunch);
            }else
            {
                binding.imageCategory.setImageResource(R.drawable.img_dinner);
            }

            binding.textDuration.setText(String.format("%02dh%02d", recipe.getDurationHours(), recipe.getDurationMinutes()));

            binding.textName.setText(recipe.getName());
            binding.textDescription.setText(recipe.getDescription());


            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        recipeClickListener.onRecipeClicked(recipe);
                }
            });
        }
    }
}
