package com.alexanderdorow.bakingtime.view;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.presenter.MainPresenterImpl;
import com.alexanderdorow.bakingtime.shared.presenter.MainPresenter;
import com.alexanderdorow.bakingtime.shared.view.MainView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
