package com.example.victorcoimbra.cadastrodispositivo.api;

import com.example.victorcoimbra.cadastrodispositivo.model.Login;
import com.example.victorcoimbra.cadastrodispositivo.model.RetornoLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Victor Coimbra on 01/03/2018.
 */

public interface LoginAPI {

    @GET("/login/usuario/{usuario}")
    Call<RetornoLogin> findLogin(@Query("usuario") String usuario, @Query("senha") String senha);

    @POST("/login")
    Call<RetornoLogin> salvar(@Body Login login);
}
