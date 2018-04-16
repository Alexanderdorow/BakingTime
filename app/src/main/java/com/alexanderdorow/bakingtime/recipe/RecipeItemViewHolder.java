package com.alexanderdorow.bakingtime.recipe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.db.model.Step;
import com.alexanderdorow.bakingtime.utils.ColorUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

class RecipeItemViewHolder extends RecyclerView.ViewHolder {

    private Step step;

    @BindView(R.id.tv_name)
    TextView recipeName;

    @BindView(R.id.ll_item_container)
    LinearLayout itemContainer;

    RecipeItemViewHolder(View itemView, final RecipeStepClickListener callback) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                step.setSelected(true);
                callback.stepClicked(step);
            }
        });
    }

    void bind(Step step) {
        this.step = step;
        this.itemContainer.setBackgroundColor(step.isSelected() ?
                ColorUtils.getColor(R.color.colorPrimary, itemView.getContext()) :
                ColorUtils.getColor(R.color.white, itemView.getContext()));
        recipeName.setText(step.getShortDescription());
    }


}
