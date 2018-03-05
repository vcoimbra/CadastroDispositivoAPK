package com.example.victorcoimbra.cadastrodispositivo.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.victorcoimbra.cadastrodispositivo.R;

/**
 * Created by Victor Coimbra on 05/03/2018.
 */

public class DispositivoViewHolder extends RecyclerView.ViewHolder {
    public TextView uuid;
    public TextView modelo;


    public DispositivoViewHolder(View itemView) {
        super(itemView);
        this.uuid = (TextView) itemView.findViewById(R.id.tvItemUuid);
        this.modelo = (TextView) itemView.findViewById(R.id.tvItemModelo);
    }
}
