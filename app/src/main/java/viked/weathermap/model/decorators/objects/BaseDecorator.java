package viked.weathermap.model.decorators.objects;

import android.content.Context;

import com.example.patternapplication.model.data.RequestedWeather;

import java.util.List;

/**
 * Created by viked on 27.05.16.
 */
public class BaseDecorator implements TextDecorator {

    private TextDecorator textDecorator;

    public BaseDecorator(TextDecorator textDecorator) {
        this.textDecorator = textDecorator;
    }

    @Override
    public List<String> getText(RequestedWeather weather, Context context) {
        return textDecorator.getText(weather, context);
    }
}
