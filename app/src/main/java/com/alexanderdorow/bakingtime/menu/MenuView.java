package com.alexanderdorow.bakingtime.menu;

import com.alexanderdorow.bakingtime.db.model.Recipe;
import com.alexanderdorow.bakingtime.base.MvpView;

import java.util.List;

public interface MenuView extends MvpView {
    void setItemsOnAdapter(List<Recipe> body);

    void goToMainActivity();

    void showLoading(boolean show);

    void showError(boolean show);
}
