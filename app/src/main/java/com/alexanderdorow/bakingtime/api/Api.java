package com.alexanderdorow.bakingtime.api;

import com.alexanderdorow.bakingtime.db.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
