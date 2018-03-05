package com.example.victorcoimbra.cadastrodispositivo.util;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Victor Coimbra on 02/03/2018.
 */

public final class ConexaoAPI {

    public static Retrofit getRetrofit(){
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();

        //TODO alterar a URL

        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.14:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
