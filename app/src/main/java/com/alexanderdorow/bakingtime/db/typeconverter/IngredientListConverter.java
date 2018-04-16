package com.alexanderdorow.bakingtime.db.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.alexanderdorow.bakingtime.db.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class IngredientListConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<Ingredient> stringToIngredientList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<Ingredient>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ingredientListToString(ArrayList<Ingredient> ingredientList) {
        return gson.toJson(ingredientList);
    }
}
