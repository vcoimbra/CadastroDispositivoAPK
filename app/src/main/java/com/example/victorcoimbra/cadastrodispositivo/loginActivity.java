package com.example.victorcoimbra.cadastrodispositivo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victorcoimbra.cadastrodispositivo.api.LoginAPI;
import com.example.victorcoimbra.cadastrodispositivo.dao.LoginDAO;
import com.example.victorcoimbra.cadastrodispositivo.model.Login;
import com.example.victorcoimbra.cadastrodispositivo.model.RetornoLogin;
import com.example.victorcoimbra.cadastrodispositivo.util.ConexaoAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;
    private CheckBox cbManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        cbManterConectado = findViewById(R.id.cbManterConectado);
    }

    public void logar(View view){

       if(isAutenticado()){
           if(cbManterConectado.isChecked()){
               manterConectado();
           }
           Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);

           finish();
       }
    }

    public boolean isAutenticado(){
        final RetornoLogin retornoLogin = new RetornoLogin();

        String login = etLogin.getText().toString();
        String senha = etSenha.getText().toString();

        LoginAPI api = ConexaoAPI.getRetrofit().create(LoginAPI.class);

        api.findLogin(login, senha)
                .enqueue(new Callback<RetornoLogin>() {
                    @Override
                    public void onResponse(Call<RetornoLogin> call, Response<RetornoLogin> response) {
                        retornoLogin.setValor(response.body().getValor());
                    }

                    @Override
                    public void onFailure(Call<RetornoLogin> call, Throwable t) {
                        Toast.makeText(loginActivity.this, R.string.problema_aplicacao, Toast.LENGTH_LONG).show();

                    }
                });
        if(retornoLogin.getValor() != null && retornoLogin.getValor().equals(0)){
            return true;
        }else{
            Toast.makeText(loginActivity.this, R.string.usuario_invalido, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void criar(View view){

        Intent intent = new Intent(this, criarUsuarioActivity.class);
        startActivity(intent);
    }

    private void manterConectado(){
        String usuario = etLogin.getText().toString();
        String senha = etSenha.getText().toString();

        LoginDAO loginDAO = new LoginDAO(this);

        Login login = new Login();
        login.setUsuario(usuario);
        login.setSenha(senha);

        loginDAO.add(login);
    }
}
