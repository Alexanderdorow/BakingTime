package com.alexanderdorow.bakingtime.recipe;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.db.model.Ingredient;
import com.alexanderdorow.bakingtime.db.model.Recipe;
import com.alexanderdorow.bakingtime.db.model.Step;
import com.alexanderdorow.bakingtime.base.BaseFragmentViewImpl;

import java.util.List;

import butterknife.BindView;

import static com.alexanderdorow.bakingtime.main.MainViewImpl.RECIPE_EXTRA;

public class RecipeViewImpl extends BaseFragmentViewImpl<RecipePresenter> implements RecipeView, RecipeStepClickListener {

    private RecipeStepClickListener callback;
    public AdapterRecipe adapter;
    private final static String INGREDIENT_FORMAT = " - %s (%s %s)\n";
    @BindView(R.id.tv_ingredients)
    TextView tvIngredients;

    @BindView(R.id.rv_recipe_steps)
    RecyclerView rvRecipeSteps;

    @Override
    public void initializeView() {
        if (getArguments() == null) {
            return;
        }
        Recipe recipe = getArguments().getParcelable(RECIPE_EXTRA);
        if (recipe == null) {
            return;
        }
        for (Ingredient ingredient : recipe.getIngredients()) {
            tvIngredients.append(String.format(INGREDIENT_FORMAT, ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getMeasure()));
        }
        List<Step> steps = recipe.getSteps();
        adapter = new AdapterRecipe(this, steps);
        rvRecipeSteps.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvRecipeSteps.setAdapter(adapter);
        rvRecipeSteps.setNestedScrollingEnabled(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callback = (RecipeStepClickListener) context;
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_recipe;
    }

    @Override
    public RecipePresenter getPresenter() {
        return new RecipePresenterImpl(this);
    }

    @Override
    public void stepClicked(Step step) {
        adapter.notifyDataSetChanged();
        callback.stepClicked(step);
    }
}
