package com.alexanderdorow.bakingtime.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.base.BaseActivityViewImpl;
import com.alexanderdorow.bakingtime.db.model.Recipe;
import com.alexanderdorow.bakingtime.db.model.Step;
import com.alexanderdorow.bakingtime.recipe.RecipeStepClickListener;
import com.alexanderdorow.bakingtime.recipe.RecipeViewImpl;
import com.alexanderdorow.bakingtime.step.StepViewImpl;
import com.alexanderdorow.bakingtime.utils.WidgetUtils;

import butterknife.BindView;

import static com.alexanderdorow.bakingtime.widget.WidgetProvider.EXTRA_STEP;

public class MainViewImpl extends BaseActivityViewImpl<MainPresenter> implements MainView, RecipeStepClickListener {

    public static final String RECIPE_EXTRA = "recipe_extra";
    public static final String STEP_EXTRA = "step_extra";
    @BindView(R.id.recipe_container)
    FrameLayout recipeContainer;
    @Nullable
    @BindView(R.id.step_container)
    FrameLayout stepContainer;

    @Override
    public void initializeView() {
        presenter.isTwoPane(stepContainer != null);
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void inflateFragments(Recipe recipe) {
        Bundle recipeArgs = new Bundle();
        recipeArgs.putParcelable(RECIPE_EXTRA, recipe);
        inflateFragment(new RecipeViewImpl(), R.id.recipe_container, recipeArgs, false, false);
        if (stepContainer != null) {
            Bundle stepArgs = new Bundle();
            stepArgs.putParcelable(STEP_EXTRA, recipe.getSteps().get(0));
            inflateFragment(new StepViewImpl(), R.id.step_container, stepArgs, false, true);
        }
        if (getIntent().hasExtra(EXTRA_STEP)) {
            presenter.onStepClicked((Step) getIntent().getParcelableExtra(EXTRA_STEP));
        }
    }

    @Override
    public void inflateStepFragment(Step step, boolean twoPainedView) {
        WidgetUtils.updateWidget(this);
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEP_EXTRA, step);
        inflateFragment(new StepViewImpl(), twoPainedView ? R.id.step_container : R.id.recipe_container, bundle, !twoPainedView, twoPainedView);
    }

    private void inflateFragment(Fragment fragment, @IdRes int containerId, @Nullable Bundle args, boolean addToBackStack, boolean replace) {
        FragmentManager manager = getSupportFragmentManager();
        fragment.setArguments(args);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (replace) {
            fragmentTransaction.replace(containerId, fragment);
        } else {
            fragmentTransaction.add(containerId, fragment);
        }

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().toString());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void stepClicked(Step step) {
        presenter.onStepClicked(step);
    }
}
