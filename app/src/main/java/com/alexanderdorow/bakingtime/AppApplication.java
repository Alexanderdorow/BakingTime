package com.alexanderdorow.bakingtime;

import android.app.Application;

public class AppApplication extends Application {

    private static AppComponent component;

    private static AppApplication INSTANCE;

    public static AppApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupDagger();
        INSTANCE = this;
    }

    private void setupDagger() {
        component = DaggerAppComponent
                .builder()
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
