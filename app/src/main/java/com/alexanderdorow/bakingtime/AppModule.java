package com.alexanderdorow.bakingtime;

import com.alexanderdorow.bakingtime.shared.api.Api;
import com.alexanderdorow.bakingtime.utils.RetrofitUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public Api api() {
        return RetrofitUtils.getApiInstance();
    }

}
