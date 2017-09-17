package com.myd.cheesefinder;

import android.app.Activity;
import android.app.Application;

import com.myd.cheesefinder.dagger.AppComponent;
import com.myd.cheesefinder.dagger.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class CheeseFinderApplication extends Application implements HasActivityInjector{

    private AppComponent appComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();

        appComponent.inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
