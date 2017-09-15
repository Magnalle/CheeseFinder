package com.raywenderlich.cheesefinder.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MYD on 9/10/17.
 *
 *
 */

@Module(subcomponents = {CheeseActivityComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context providesAppContext(Application application) {
        return application;
    }
}
