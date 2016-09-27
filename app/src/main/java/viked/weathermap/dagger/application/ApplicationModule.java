package viked.weathermap.dagger.application;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eugeniy Shein on 23.09.2016.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    ContentResolver provideContentResolver() {
        return application.getContentResolver();
    }

}
