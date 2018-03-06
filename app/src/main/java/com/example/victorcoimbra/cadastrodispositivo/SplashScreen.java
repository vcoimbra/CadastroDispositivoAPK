package com.example.victorcoimbra.cadastrodispositivo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.victorcoimbra.cadastrodispositivo.api.LoginAPI;
import com.example.victorcoimbra.cadastrodispositivo.dao.LoginDAO;
import com.example.victorcoimbra.cadastrodispositivo.model.Login;
import com.example.victorcoimbra.cadastrodispositivo.model.RetornoLogin;
import com.example.victorcoimbra.cadastrodispositivo.util.ConexaoAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        carregar();
    }

    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.animacao_splash);
        anim.reset();

        ImageView iv = findViewById(R.id.splash);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }
        //Login login;
        //login = verificarLoginCadastrado();

       // if(login != null){
       //     if (isAutenticado(login)){
       //         Intent intent = new Intent(SplashScreen.this,
       //                 MainActivity.class);
       //         startActivity(intent);
       //         SplashScreen.this.finish();
       //     }
       // }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this,
                        loginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private Login verificarLoginCadastrado(){
        LoginDAO loginDAO = new LoginDAO(this);

        return loginDAO.getUsuario();
    }

    public boolean isAutenticado(Login login){
        final RetornoLogin retornoLogin = new RetornoLogin();

        LoginAPI api = ConexaoAPI.getRetrofit().create(LoginAPI.class);

        api.findLogin(login.getUsuario(), login.getSenha())
                .enqueue(new Callback<RetornoLogin>() {
                    @Override
                    public void onResponse(Call<RetornoLogin> call, Response<RetornoLogin> response) {
                        retornoLogin.setValor(response.body().getValor());
                    }

                    @Override
                    public void onFailure(Call<RetornoLogin> call, Throwable t) {
                        Toast.makeText(SplashScreen.this, R.string.problema_aplicacao, Toast.LENGTH_LONG).show();

                    }
                });
        if(retornoLogin.getValor() != null && retornoLogin.getValor().equals(0)){
            return true;
        }else{
            Toast.makeText(SplashScreen.this, R.string.usuario_invalido, Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
