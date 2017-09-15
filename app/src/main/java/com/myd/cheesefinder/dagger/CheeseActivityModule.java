package com.myd.cheesefinder.dagger;

import com.myd.cheesefinder.activities.CheeseActivity;
import com.myd.cheesefinder.adapters.CheeseAdapter;

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
