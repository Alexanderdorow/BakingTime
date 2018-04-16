package com.alexanderdorow.bakingtime.menu;

import com.alexanderdorow.bakingtime.base.MvpPresenter;
import com.alexanderdorow.bakingtime.db.model.Recipe;

public interface MenuPresenter extends MvpPresenter {

    void onMenuItemClicked(Recipe recipe);

    void requestRecipes();
}
