package com.alexanderdorow.bakingtime;

import android.content.Context;

import com.alexanderdorow.bakingtime.api.Api;
import com.alexanderdorow.bakingtime.db.AppDatabase;
import com.alexanderdorow.bakingtime.db.daos.RecipeDao;
import com.alexanderdorow.bakingtime.utils.RetrofitUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public Api api() {
        return RetrofitUtils.getApiInstance();
    }

    @Provides
    public Context context() {
        return AppApplication.getInstance();
    }

    @Provides
    public AppDatabase appDatabase(Context context) {
        return AppDatabase.getDatabase(context);
    }

    @Provides
    public RecipeDao recipeDao(AppDatabase appDatabase) {
        return appDatabase.recipeDao();
    }
}
