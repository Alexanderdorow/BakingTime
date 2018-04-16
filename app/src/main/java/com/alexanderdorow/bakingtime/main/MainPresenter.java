package com.alexanderdorow.bakingtime.main;


import com.alexanderdorow.bakingtime.base.MvpPresenter;
import com.alexanderdorow.bakingtime.db.model.Step;

public interface MainPresenter extends MvpPresenter {
    void isTwoPane(boolean isTwoPane);

    void onStepClicked(Step step);
}
