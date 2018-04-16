package com.alexanderdorow.bakingtime.main;

import com.alexanderdorow.bakingtime.db.model.Recipe;
import com.alexanderdorow.bakingtime.db.model.Step;
import com.alexanderdorow.bakingtime.base.MvpView;

public interface MainView extends MvpView {
    void inflateFragments(Recipe recipe);

    void inflateStepFragment(Step step, boolean twoPainedView);
}
