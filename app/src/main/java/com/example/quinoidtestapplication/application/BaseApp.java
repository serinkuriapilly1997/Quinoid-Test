package com.example.quinoidtestapplication.application;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Created by SERIN_SEB on 27-07-2021.
 */
@HiltAndroidApp
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
