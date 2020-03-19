package com.telran.a16_03_20;

import android.app.Application;

import com.telran.a16_03_20.di.DependenceFactory;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new DependenceFactory(this);
    }
}
