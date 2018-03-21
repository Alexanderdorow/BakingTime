package com.alexanderdorow.bakingtime;

import com.alexanderdorow.bakingtime.presenter.MainPresenterImpl;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainPresenterImpl activity);
}
