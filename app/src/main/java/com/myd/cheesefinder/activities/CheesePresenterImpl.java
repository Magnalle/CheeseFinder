package com.myd.cheesefinder.activities;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MYD on 9/14/17.
 */

public class CheesePresenterImpl implements CheesePresenter{
    List<String> mCheeses;

    private final int mCheesesCount;

    @Inject
    public CheesePresenterImpl(List<String> cheeses) {
        mCheeses = cheeses;
        mCheesesCount = mCheeses.size();
    }

    @Override
    public List<String> search(String query) {
        query = query.toLowerCase();

        List<String> result = new LinkedList<>();

        for (int i = 0; i < mCheesesCount; i++) {
            if (mCheeses.get(i).toLowerCase().contains(query)) {
                result.add(mCheeses.get(i));
            }
        }

        return result;
    }

}
