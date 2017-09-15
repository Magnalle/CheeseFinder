package com.raywenderlich.cheesefinder.dagger;

import android.content.Context;

import com.raywenderlich.cheesefinder.activities.CheesePresenter;
import com.raywenderlich.cheesefinder.R;
import com.raywenderlich.cheesefinder.activities.CheesePresenterImpl;

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
