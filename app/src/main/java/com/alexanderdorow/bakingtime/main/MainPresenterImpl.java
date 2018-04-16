package com.alexanderdorow.bakingtime.main;

import com.alexanderdorow.bakingtime.AppApplication;
import com.alexanderdorow.bakingtime.base.BasePresenterImpl;
import com.alexanderdorow.bakingtime.db.daos.RecipeDao;
import com.alexanderdorow.bakingtime.db.model.Recipe;
import com.alexanderdorow.bakingtime.db.model.Step;

import javax.inject.Inject;

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {

    private boolean isTwoPane;
    private Recipe recipe;

    @Inject
    public RecipeDao recipeDao;

    MainPresenterImpl(MainView view) {
        super(view);
        AppApplication.getComponent().inject(this);
        recipe = recipeDao.getSelectedRecipe();
    }

    @Override
    public void onViewReady() {
        view.inflateFragments(recipe);
    }

    @Override
    public void onViewStarted() {
    }

    @Override
    public void onViewStopped() {

    }

    @Override
    public void isTwoPane(boolean isTwoPane) {
        this.isTwoPane = isTwoPane;
    }

    @Override
    public void onStepClicked(Step step) {
        recipe.setSelectedStep(step);
        recipeDao.update(recipe);
        view.inflateStepFragment(step, isTwoPane);
    }

}
