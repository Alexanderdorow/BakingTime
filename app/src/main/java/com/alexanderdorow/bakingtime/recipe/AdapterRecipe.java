package com.alexanderdorow.bakingtime.recipe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.db.model.Step;

import java.util.List;

public class AdapterRecipe extends RecyclerView.Adapter<RecipeItemViewHolder> {
    private List<Step> steps;
    private RecipeStepClickListener callback;

    AdapterRecipe(RecipeStepClickListener callback, List<Step> steps) {
        this.callback = callback;
        this.steps = steps;
    }

    @NonNull
    @Override
    public RecipeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new RecipeItemViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemViewHolder holder, int position) {
        holder.bind(steps.get(position));
    }

    public List<Step> getItems(){
        return steps;
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }
}
