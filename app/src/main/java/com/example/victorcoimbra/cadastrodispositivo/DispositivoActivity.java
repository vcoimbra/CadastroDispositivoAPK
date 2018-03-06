package com.example.victorcoimbra.cadastrodispositivo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.victorcoimbra.cadastrodispositivo.adapter.DispositivoAdapter;
import com.example.victorcoimbra.cadastrodispositivo.api.DispositivoAPI;
import com.example.victorcoimbra.cadastrodispositivo.model.Dispositivo;
import com.example.victorcoimbra.cadastrodispositivo.util.ConexaoAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DispositivoActivity extends AppCompatActivity {

    DispositivoAdapter adapter;
    RecyclerView recyclerView;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivo);
        recyclerView = findViewById(R.id.rvDispositivo);
        buscarDispositivos();
    }

    private List<Dispositivo> buscarDispositivos(){
        DispositivoAPI api = ConexaoAPI.getRetrofit().create(DispositivoAPI.class);
        final List<Dispositivo> dispositivos = new ArrayList<>();

        api.findAll()
                .enqueue(new Callback<List<Dispositivo>>() {
                    @Override
                    public void onResponse(Call<List<Dispositivo>> call, Response<List<Dispositivo>> response) {
                        dispositivos.addAll(response.body());
                        adapter = new DispositivoAdapter(dispositivos, DispositivoActivity.this);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Dispositivo>> call, Throwable t) {
                        Toast.makeText(DispositivoActivity.this, R.string.problema_aplicacao, Toast.LENGTH_LONG).show();

                    }
                });
        return dispositivos;
    }

    public void adicionarDispositivo(){

    }

    public void sair(){
        finish();
    }
}
