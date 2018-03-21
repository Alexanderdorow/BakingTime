package com.alexanderdorow.bakingtime.shared.presenter;

public interface MvpPresenter {
    void onViewReady();

    void onViewStarted();

    void onViewStopped();
}
