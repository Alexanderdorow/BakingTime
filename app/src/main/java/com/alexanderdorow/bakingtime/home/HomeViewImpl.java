package com.alexanderdorow.bakingtime.home;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.base.BaseActivityViewImpl;

public class HomeViewImpl extends BaseActivityViewImpl<HomePresenter> implements HomeView {
    @Override
    public void initializeView() {

    }

    @Override
    public HomePresenter getPresenter() {
        return new HomePresenterImpl(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }
}
