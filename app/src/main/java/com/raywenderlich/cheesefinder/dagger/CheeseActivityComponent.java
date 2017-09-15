package com.raywenderlich.cheesefinder.dagger;

import com.raywenderlich.cheesefinder.activities.CheeseActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by MYD on 9/12/17.
 */

@Subcomponent(modules = CheeseActivityModule.class)
public interface CheeseActivityComponent extends AndroidInjector<CheeseActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CheeseActivity>{}
}
