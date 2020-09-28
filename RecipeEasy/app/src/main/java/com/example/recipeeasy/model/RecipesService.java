package com.example.recipeeasy.model;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipesService {
    private static RecipesService instance;

    private HashMap<Integer, HashMap<Integer, Recipe>> recipes;
    private int autoIncrement;

    private RecipesService() {
        recipes = new HashMap<>();

        add("breakfast", "A", 0, 20, "aaa a aaaaaa", 0);
        add("lunch", "B", 1, 30, "bbbbbb bb b bbb", 0);
        add("dinner", "C", 6, 0, "c ccc ccccc cc", 0);
        add("lunch", "D", 0, 5, "dd", 1);
    }

    public static RecipesService getInstance() {
        if (instance == null) {
            instance = new RecipesService();
        }

        return instance;
    }

    public ArrayList<Recipe> getAll(int userId) {
        if (recipes.containsKey(userId)) {
            return new ArrayList<Recipe>(recipes.get(userId).values());
        } else {
            return new ArrayList<>();
        }
    }

    public boolean add(String category, String name, int hours, int minutes, String description, int userId) {
        if (isInvalid(category, name, hours, minutes, description)) {
            return false;
        } else {
            Recipe newRecipe = new Recipe(autoIncrement, category, name, hours, minutes, description);
            autoIncrement++;

            if (recipes.containsKey(userId)) {
                recipes.get(userId).put(newRecipe.getId(), newRecipe);
            } else {
                HashMap<Integer, Recipe> userRecipes = new HashMap<>();
                userRecipes.put(newRecipe.getId(), newRecipe);

                recipes.put(userId, userRecipes);
            }

            return true;
        }
    }

    public boolean edit(final int recipeId, String category, String name, int hours, int minutes, String description, int currentUserId) {
        if (isInvalid(category, name, hours, minutes, description)) {
            return false;
        } else {
            recipes.get(currentUserId).get(recipeId).update(category, name, hours, minutes, description);
            return true;
        }
    }

    private boolean isInvalid(String category, String name, int hours, int minutes, String description) {
        return category == null || name.isEmpty() || description.isEmpty() || (hours == 0 && minutes == 0);
    }

    public void delete(int recipeId, int currentUserId) {
        recipes.get(currentUserId).remove(recipeId);
    }
}
