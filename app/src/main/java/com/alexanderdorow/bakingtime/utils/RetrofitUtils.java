package com.alexanderdorow.bakingtime.utils;

import com.alexanderdorow.bakingtime.BuildConfig;
import com.alexanderdorow.bakingtime.shared.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public static Api api;

    public static Api getApiInstance() {
        if (api == null) {
            api = buildApi();
        }
        return api;
    }

    private static Api buildApi() {
        return new Retrofit.Builder()
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.SERVER_URL)
                .build()
                .create(Api.class);
    }

    private static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

}
