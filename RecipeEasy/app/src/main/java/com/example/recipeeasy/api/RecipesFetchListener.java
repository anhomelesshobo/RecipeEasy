package com.example.recipeeasy.api;

import com.example.recipeeasy.model.Recipe;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface RecipesFetchListener {
    void onResponse(ArrayList<Recipe> recipes);
}
