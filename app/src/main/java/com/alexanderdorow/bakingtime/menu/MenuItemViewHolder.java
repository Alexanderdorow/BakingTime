package com.alexanderdorow.bakingtime.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.db.model.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

class MenuItemViewHolder extends RecyclerView.ViewHolder {

    private Recipe recipe;
    @BindView(R.id.tv_name)
    TextView recipeName;

    MenuItemViewHolder(View itemView, final AdapterMenu.MenuClickListener callback) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onMenuItemClicked(recipe);
            }
        });
    }

    void bind(Recipe recipe) {
        this.recipe = recipe;
        recipeName.setText(recipe.getName());
    }
}
