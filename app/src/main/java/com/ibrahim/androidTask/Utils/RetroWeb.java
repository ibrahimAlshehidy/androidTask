package com.ibrahim.androidTask.Utils;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroWeb {
    static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60,TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build();
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {


            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).readTimeout(60,TimeUnit.SECONDS)
                    .connectTimeout(60,TimeUnit.SECONDS)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl("http://fesal.rmal.com.sa/task/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;

    }

    private static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();


                okhttp3.Response response = chain.proceed(request);
                return response;
            }
        };
    }

}
