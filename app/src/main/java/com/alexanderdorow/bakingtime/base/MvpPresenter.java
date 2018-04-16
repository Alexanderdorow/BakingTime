package com.alexanderdorow.bakingtime.base;

public interface MvpPresenter {
    void onViewReady();

    void onViewStarted();

    void onViewStopped();
}
