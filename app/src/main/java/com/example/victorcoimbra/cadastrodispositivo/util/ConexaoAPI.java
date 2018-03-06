package com.example.victorcoimbra.cadastrodispositivo.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Victor Coimbra on 02/03/2018.
 */

public final class ConexaoAPI {

    public static Retrofit getRetrofit(){

        return new Retrofit.Builder()
                .baseUrl("https://controledispositivoapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
