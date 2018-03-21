package com.alexanderdorow.bakingtime.presenter;

import android.util.Log;

import com.alexanderdorow.bakingtime.AppApplication;
import com.alexanderdorow.bakingtime.shared.api.Api;
import com.alexanderdorow.bakingtime.shared.presenter.MainPresenter;
import com.alexanderdorow.bakingtime.shared.view.MainView;

import javax.inject.Inject;

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {

    @Inject
    Api api;

    public MainPresenterImpl(MainView view) {
        super(view);
        AppApplication.getComponent().inject(this);
    }

    @Override
    public void onViewReady() {
        Log.i("","");
    }

    @Override
    public void onViewStarted() {

    }

    @Override
    public void onViewStopped() {

    }
}
