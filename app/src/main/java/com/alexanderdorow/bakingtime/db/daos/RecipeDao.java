package com.alexanderdorow.bakingtime.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alexanderdorow.bakingtime.db.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getRecipes();

    @Insert
    void insert(Recipe... recipe);

    @Update
    void update(Recipe... recipes);

    @Query("SELECT COUNT(*) FROM recipe")
    int countRecipes();

    @Query("SELECT * FROM recipe WHERE selected = 1")
    Recipe getSelectedRecipe();

    @Query("UPDATE recipe SET selected = 0 WHERE selected = 1")
    void    deselectAllRecipes();
}
