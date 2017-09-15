package com.myd.cheesefinder.dagger;

import android.app.Application;

import com.myd.cheesefinder.activities.CheesePresenter;
import com.myd.cheesefinder.CheeseFinderApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        CheesePresenterModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(CheeseFinderApplication app);

    void inject(CheesePresenter target);
}
