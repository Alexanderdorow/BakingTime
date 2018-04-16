package com.alexanderdorow.bakingtime.db.typeconverter;

import android.arch.persistence.room.TypeConverter;

import com.alexanderdorow.bakingtime.db.model.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StepListConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<Step> stringToStepList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<Step>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String stepListToString(ArrayList<Step> stepList) {
        return gson.toJson(stepList);
    }
}
