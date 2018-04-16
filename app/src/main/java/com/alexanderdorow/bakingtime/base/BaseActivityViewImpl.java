package com.alexanderdorow.bakingtime.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivityViewImpl<P extends MvpPresenter> extends AppCompatActivity implements MvpView {

    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = getPresenter();
        initializeView();
        onViewReady();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewStarted();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewStopped();
    }

    public abstract P getPresenter();

    public abstract int getLayoutId();

    protected void onViewReady() {
        presenter.onViewReady();
    }
}
