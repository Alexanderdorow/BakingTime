package com.alexanderdorow.bakingtime.presenter;

import com.alexanderdorow.bakingtime.shared.presenter.MvpPresenter;
import com.alexanderdorow.bakingtime.shared.view.MvpView;

public abstract class BasePresenterImpl<V extends MvpView> implements MvpPresenter {

    protected V view;

    public BasePresenterImpl(V view) {
        this.view = view;
    }


}
