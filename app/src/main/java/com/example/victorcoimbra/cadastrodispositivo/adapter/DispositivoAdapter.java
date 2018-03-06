package com.example.victorcoimbra.cadastrodispositivo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.victorcoimbra.cadastrodispositivo.R;
import com.example.victorcoimbra.cadastrodispositivo.model.Dispositivo;
import com.example.victorcoimbra.cadastrodispositivo.view.DispositivoViewHolder;

import java.util.List;

/**
 * Created by Victor Coimbra on 04/03/2018.
 */

public class DispositivoAdapter extends Adapter{

    List<Dispositivo> dispositivos;
    Context context;

    public DispositivoAdapter(List<Dispositivo> dispositivos, Context context){
        this.dispositivos = dispositivos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_dispositivo,parent, false);

        DispositivoViewHolder holder = new DispositivoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        DispositivoViewHolder holder = (DispositivoViewHolder) viewHolder;
        Dispositivo dispositivo = dispositivos.get(position);
        holder.uuid.setText(dispositivo.getUuid());
        holder.modelo.setText(dispositivo.getModelo());

        holder.btnExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(context, "Clicou no botão excluir", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dispositivos.size();
    }
}
