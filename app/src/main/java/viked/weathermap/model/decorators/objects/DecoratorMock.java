package viked.weathermap.model.decorators.objects;

import android.content.Context;

import com.example.patternapplication.model.data.RequestedWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viked on 27.05.16.
 */
public class DecoratorMock implements TextDecorator {
    @Override
    public List<String> getText(RequestedWeather weather, Context context) {
        return new ArrayList<>();
    }
}
