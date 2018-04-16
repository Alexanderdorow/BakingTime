package com.alexanderdorow.bakingtime.menu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.db.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<MenuItemViewHolder> {

    public interface MenuClickListener {
        void onMenuItemClicked(Recipe recipe);
    }

    private List<Recipe> recipes = new ArrayList<>();
    public MenuClickListener callback;

    AdapterMenu(MenuClickListener callback) {
        this.callback = callback;
    }

    void setItems(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public Recipe getItemAt(int position) {
        return this.recipes.get(position);
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new MenuItemViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
