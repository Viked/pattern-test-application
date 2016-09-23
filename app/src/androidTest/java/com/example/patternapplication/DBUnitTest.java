package com.example.patternapplication;

import android.test.suitebuilder.annotation.LargeTest;

//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;

/*
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Initb on 13.05.2016.
 */
//@RunWith(AndroidJUnit4.class)
@LargeTest
public class DBUnitTest {
/*
    private DBModel model;
    private RequestedWeather weather;

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void init(){
        model = new DBModel(mActivityRule.getActivity());
        model.open();
        weather = new RequestedWeather();
        weather.setId(1L);
    }

    @Test
    public void testDB() {
        Cursor temp = model.getAllData();
        assertNotNull(temp);
        assertEquals(temp.getCount(), 0);
        temp.close();
        testDBAdding();
        testDBDeleting();
    }



    private void testDBAdding() {
        model.addRec(weather);
        Cursor temp = model.getAllData();
        assertEquals(temp.getCount(), 1);
        RequestedWeather gatedWeather = model.getDataList(temp).get(0);
        assertEquals(weather, gatedWeather);
        temp.close();
    }


    private void testDBDeleting() {
        model.delRec(weather);
        Cursor temp = model.getAllData();
        assertEquals(temp.getCount(), 0);
        temp.close();
    }


    @After
    public void stop(){
        model.close();
    }
    */
}
