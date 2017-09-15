package com.raywenderlich.cheesefinder.dagger;

import android.app.Activity;

import com.raywenderlich.cheesefinder.activities.CheeseActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by MYD on 9/12/17.
 */

@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(CheeseActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsCheeseActivity(CheeseActivityComponent.Builder builder);
}
