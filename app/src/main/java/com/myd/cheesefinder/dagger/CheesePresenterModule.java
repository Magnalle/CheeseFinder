package com.myd.cheesefinder.dagger;

import android.content.Context;

import com.myd.cheesefinder.activities.CheesePresenter;
import com.myd.cheesefinder.R;
import com.myd.cheesefinder.activities.CheesePresenterImpl;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class CheesePresenterModule {
    @Provides
    @Singleton
    CheesePresenter provideCheesePresenter(Context context) {
        List<String> cheeses = Arrays.asList(context.getResources().getStringArray(R.array.cheeses));
        return new CheesePresenterImpl(cheeses);
    }
}
