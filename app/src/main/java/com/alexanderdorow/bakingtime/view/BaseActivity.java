package com.alexanderdorow.bakingtime.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexanderdorow.bakingtime.shared.presenter.MvpPresenter;
import com.alexanderdorow.bakingtime.shared.view.MvpView;

public abstract class BaseActivity<P extends MvpPresenter> extends AppCompatActivity implements MvpView {

    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        presenter = getPresenter();
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
