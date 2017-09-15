package com.raywenderlich.cheesefinder.dagger;

import com.raywenderlich.cheesefinder.activities.CheeseActivity;
import com.raywenderlich.cheesefinder.adapters.CheeseAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 9/12/17.
 */

@Module
public class CheeseActivityModule {

    @Provides
    CheeseActivity providesCheeseActivity(CheeseActivity activity) {
        return activity;
    }

    @Provides
    CheeseAdapter providesCheeseAdapter() {
        return new CheeseAdapter();
    }
}
