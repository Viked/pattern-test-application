package com.example.patternapplication;

import android.database.Cursor;
import android.location.Location;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.patternapplication.model.CurrentLocation;
import com.example.patternapplication.model.data.RequestedWeather;
import com.example.patternapplication.model.db.DBModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.observers.TestSubscriber;

import static org.junit.Assert.*;

/**
 * Created by Initb on 13.05.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DBUnitTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testDB() {
        DBModel model = new DBModel(mActivityRule.getActivity());
        model.open();
        Cursor temp = model.getAllData();
        assertNotNull(temp);
        assertEquals(temp.getCount(), 0);
        temp.close();

        RequestedWeather weather1 = new RequestedWeather();
        weather1.setId(1L);
        model.addRec(weather1);
        temp = model.getAllData();
        assertEquals(temp.getCount(), 1);
        temp.close();

        model.delRec(weather1);
        temp = model.getAllData();
        assertEquals(temp.getCount(), 0);
        temp.close();


        model.close();
    }

}
