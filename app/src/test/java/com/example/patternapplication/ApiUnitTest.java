package com.example.patternapplication;

import android.test.suitebuilder.annotation.SmallTest;

import com.example.patternapplication.model.WeatherApiRequestInterface;
import com.example.patternapplication.model.WeatherModel;
import com.example.patternapplication.model.data.RequestedWeather;

import org.junit.Test;


/**
 * Created by Initb on 13.05.2016.
 */
public class ApiUnitTest {

/*
    @Test
    @SmallTest
    public void testApi() {
        TestSubscriber<RequestedWeather> testSubscriber = new TestSubscriber<>();
        WeatherApiRequestInterface api = WeatherModel.create();
        api.getWeather(50.45075, 30.11704).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertCompleted();
    }
    */
}
