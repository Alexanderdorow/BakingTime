package com.alexanderdorow.bakingtime.base;

public abstract class BasePresenterImpl<V extends MvpView> implements MvpPresenter {

    protected V view;

    public BasePresenterImpl(V view) {
        this.view = view;
    }


}
