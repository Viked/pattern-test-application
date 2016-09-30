package viked.weathermap.model.decorators;

import java.util.List;

import viked.weathermap.model.decorators.objects.TextDecorator;

/**
 * Created by Eugeniy Shein on 29.09.2016.
 */

public interface IDecoratorsModel {

    TextDecorator getDecorator();

    void setDecoratorSettings(List<DecoratorItemSettings> decoratorSettings);

    List<DecoratorItemSettings> getDecoratorSettings();

}
