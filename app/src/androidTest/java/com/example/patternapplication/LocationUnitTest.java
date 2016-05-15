package com.example.patternapplication;

import android.location.Location;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.patternapplication.model.CurrentLocation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.observers.TestSubscriber;

/**
 * Created by Initb on 12.05.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LocationUnitTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testLocation() {
        CurrentLocation location = new CurrentLocation();
        TestSubscriber<Location> testSubscriber = new TestSubscriber<>();
        location.getCurrentLocation(mActivityRule.getActivity())
                .subscribe(testSubscriber);
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertCompleted();
    }


}