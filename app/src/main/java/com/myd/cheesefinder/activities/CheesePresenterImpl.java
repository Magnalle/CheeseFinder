package com.myd.cheesefinder.activities;

import android.content.Context;

import com.myd.cheesefinder.CheeseFinderApplication;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;

/**
 * Created by MYD on 9/14/17.
 *
 *
 */

public class CheesePresenterImpl implements CheesePresenter{

    @Inject
    @Named("CheesesDependency")
    List<String> mCheeses;

    public CheesePresenterImpl(Context context) {
        ((CheeseFinderApplication)context).getAppComponent().inject(this);
    }

    @Override
    public List<String> search(String query) {
        query = query.toLowerCase();

        List<String> result = new LinkedList<>();

        for (int i = 0; i < mCheeses.size(); i++) {
            if (mCheeses.get(i).toLowerCase().contains(query)) {
                result.add(mCheeses.get(i));
            }
        }

        return result;
    }

}
