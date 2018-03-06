package com.example.victorcoimbra.cadastrodispositivo.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.victorcoimbra.cadastrodispositivo.R;
import com.example.victorcoimbra.cadastrodispositivo.api.DispositivoAPI;
import com.example.victorcoimbra.cadastrodispositivo.model.Dispositivo;
import com.example.victorcoimbra.cadastrodispositivo.util.ConexaoAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Victor Coimbra on 05/03/2018.
 */

public class DispositivoDialog extends DialogFragment {

    private AlertDialog.Builder builder;
    private EditText txtTitulo, txtTarefa;

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_dispositivo, null);
        txtTitulo = v.findViewById(R.id.txtUuid);
        txtTarefa = v.findViewById(R.id.txtModelo);
        builder.setView(v);
        builder.setTitle("New Gadget");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dispositivo dispositivo = new Dispositivo();

                DispositivoAPI api = ConexaoAPI.getRetrofit().create(DispositivoAPI.class);

                api.salvar(dispositivo)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });


                //TODO fazer chamada do retrofit
            }
        });
        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }
}
