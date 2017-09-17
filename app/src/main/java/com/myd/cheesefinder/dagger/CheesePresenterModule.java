package com.myd.cheesefinder.dagger;

import android.content.Context;

import com.myd.cheesefinder.activities.CheesePresenter;
import com.myd.cheesefinder.R;
import com.myd.cheesefinder.activities.CheesePresenterImpl;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class CheesePresenterModule {

    @Provides
    @Named("CheesesDependency")
    List<String> provideCheeses(Context context) {
        return Arrays.asList(context.getResources().getStringArray(R.array.cheeses));
    }

    @Provides
    @Singleton
    CheesePresenter provideCheesePresenter(Context context) {
        return new CheesePresenterImpl(context);
    }
}
