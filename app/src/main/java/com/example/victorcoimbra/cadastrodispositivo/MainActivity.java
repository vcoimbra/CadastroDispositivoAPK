package com.example.victorcoimbra.cadastrodispositivo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.victorcoimbra.cadastrodispositivo.dao.LoginDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gerenciarDispositivos(View view){
        Intent intent = new Intent(this, DispositivoActivity.class);
        startActivity(intent);

    }

    public void abrirSobre(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void desconectar(View view){

        LoginDAO loginDAO = new LoginDAO(this);

        loginDAO.limparBanco();

        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
        finish();
    }
}
