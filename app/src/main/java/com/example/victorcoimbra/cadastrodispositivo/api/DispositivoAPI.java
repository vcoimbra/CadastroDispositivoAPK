package com.example.victorcoimbra.cadastrodispositivo.api;

import com.example.victorcoimbra.cadastrodispositivo.model.Dispositivo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Victor Coimbra on 03/03/2018.
 */

public interface DispositivoAPI {

    @GET("/dispositivo/uuid/{uuid}")
    Call<Dispositivo> findUuid(@Path("uuid") String uuid);

    @POST("/dispositivo")
    Call<Void> salvar(@Body Dispositivo dispositivo);

    @GET("/dispositivo")
    Call<List<Dispositivo>> findAll();
}
