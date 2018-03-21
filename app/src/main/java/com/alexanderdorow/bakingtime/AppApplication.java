package com.alexanderdorow.bakingtime;

import android.app.Application;

public class AppApplication extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDagger();
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
