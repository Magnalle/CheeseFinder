package com.myd.cheesefinder;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.myd.cheesefinder.activities.CheesePresenter;
import com.myd.cheesefinder.activities.CheesePresenterImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CheesePresenterTest {


    @Test
    public void testSearch() {
        CheesePresenter cheesePresenter = new CheesePresenterImpl(
                        InstrumentationRegistry
                                .getTargetContext()
                                .getApplicationContext());

        List<String> searchResult = cheesePresenter.search("bri");
        assertEquals(3, searchResult.size());
        searchResult = cheesePresenter.search("78y8");
        assertEquals(0, searchResult.size());
        searchResult = cheesePresenter.search("");
        assertEquals(380, searchResult.size());
    }
}
