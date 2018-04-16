package com.alexanderdorow.bakingtime.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragmentViewImpl<P extends MvpPresenter> extends Fragment implements MvpView {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getViewId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = getPresenter();
        presenter.onViewReady();
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeView();
            presenter.onViewStarted();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewStopped();
    }

    public abstract int getViewId();

    public abstract P getPresenter();
}
