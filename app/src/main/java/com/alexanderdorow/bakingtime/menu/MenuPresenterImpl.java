package com.alexanderdorow.bakingtime.menu;

import com.alexanderdorow.bakingtime.AppApplication;
import com.alexanderdorow.bakingtime.api.Api;
import com.alexanderdorow.bakingtime.base.BasePresenterImpl;
import com.alexanderdorow.bakingtime.db.daos.RecipeDao;
import com.alexanderdorow.bakingtime.db.model.Recipe;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuPresenterImpl extends BasePresenterImpl<MenuView> implements MenuPresenter {
    @Inject
    Api api;
    @Inject
    RecipeDao recipeDao;


    public MenuPresenterImpl(MenuView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        AppApplication.getComponent().inject(this);
    }

    @Override
    public void onViewStarted() {
        if (recipeDao.countRecipes() > 0) {
            view.setItemsOnAdapter(recipeDao.getRecipes());
            return;
        }
        requestRecipes();
    }

    @Override
    public void requestRecipes() {
        view.showError(false);
        view.showLoading(true);
        api.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    List<Recipe> body = response.body();
                    recipeDao.insert(body.toArray(new Recipe[0]));
                    view.setItemsOnAdapter(body);
                } else {
                    view.showError(true);
                }
                view.showLoading(false);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                view.showLoading(false);
                view.showError(true);
            }
        });
    }

    @Override
    public void onViewStopped() {

    }

    @Override
    public void onMenuItemClicked(Recipe recipe) {
        recipeDao.deselectAllRecipes();
        recipe.setSelected(true);
        recipeDao.update(recipe);
        view.goToMainActivity();
    }
}
