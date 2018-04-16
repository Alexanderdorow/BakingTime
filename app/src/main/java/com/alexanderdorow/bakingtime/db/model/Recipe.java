package com.alexanderdorow.bakingtime.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.alexanderdorow.bakingtime.db.typeconverter.IngredientListConverter;
import com.alexanderdorow.bakingtime.db.typeconverter.StepListConverter;

import java.util.ArrayList;

@Entity(tableName = "recipe")
public class Recipe implements Parcelable {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private boolean selected;
    @TypeConverters(IngredientListConverter.class)
    private ArrayList<Ingredient> ingredients;
    @TypeConverters(StepListConverter.class)
    private ArrayList<Step> steps;

    public Recipe(@NonNull String id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, boolean selected) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.selected = selected;
    }

    @Ignore
    private Recipe(Parcel in) {
        id = in.readString();
        name = in.readString();
        selected = in.readByte() != 0;
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        steps = in.createTypedArrayList(Step.CREATOR);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static Creator<Recipe> getCREATOR() {
        return CREATOR;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeByte((byte) (selected ? 1 : 0));
        parcel.writeTypedList(ingredients);
        parcel.writeTypedList(steps);
    }

    public void setSelectedStep(Step selected) {
        for (Step step : steps) {
            step.setSelected(step.getId().equals(selected.getId()));
        }
    }
}
