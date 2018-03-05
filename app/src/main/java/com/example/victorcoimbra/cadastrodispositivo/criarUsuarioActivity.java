package com.example.victorcoimbra.cadastrodispositivo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victorcoimbra.cadastrodispositivo.api.LoginAPI;
import com.example.victorcoimbra.cadastrodispositivo.model.Login;
import com.example.victorcoimbra.cadastrodispositivo.model.RetornoLogin;
import com.example.victorcoimbra.cadastrodispositivo.util.ConexaoAPI;
import com.example.victorcoimbra.cadastrodispositivo.util.EnumRetornoLoginAPI;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class criarUsuarioActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;
    private EditText etSenha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_usuario);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        etSenha2 = findViewById(R.id.etSenha2);
    }

    public void criar(View view){

        String usuario = etLogin.getText().toString();
        String senha = etSenha.getText().toString();
        String senha2 = etSenha2.getText().toString();

        if(!senha.equals(senha2)){
            Toast.makeText(this, "Password does't match", Toast.LENGTH_SHORT).show();
        }else{
            LoginAPI api = ConexaoAPI.getRetrofit().create(LoginAPI.class);
            Login login = new Login();
            final RetornoLogin retornoLogin = new RetornoLogin();

            login.setUsuario(usuario);
            login.setSenha(senha);

            api.salvar(login).enqueue(new Callback<RetornoLogin>() {
                @Override
                public void onResponse(Call<RetornoLogin> call, Response<RetornoLogin> response) {

                    retornoLogin.setValor(response.body().getValor());

                }

                @Override
                public void onFailure(Call<RetornoLogin> call, Throwable t) {
                    Toast.makeText(criarUsuarioActivity.this, R.string.problema_aplicacao, Toast.LENGTH_LONG).show();
                }
            });

            if(retornoLogin.getValor() != null){
                if(retornoLogin.getValor().equals(EnumRetornoLoginAPI.USUARIO_CADASTRADO.getValor())){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(retornoLogin.getValor().equals(EnumRetornoLoginAPI.USUARIO_EXISTENTE.getValor())){
                    Toast.makeText(criarUsuarioActivity.this, R.string.usuario_existente, Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(criarUsuarioActivity.this, R.string.problema_aplicacao, Toast.LENGTH_LONG).show();
            }
        }
    }
}
