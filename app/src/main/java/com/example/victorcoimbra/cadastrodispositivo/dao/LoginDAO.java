package com.example.victorcoimbra.cadastrodispositivo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.victorcoimbra.cadastrodispositivo.model.Login;

import java.util.List;

/**
 * Created by Victor Coimbra on 05/03/2018.
 */

public class LoginDAO {

    private DBOpenHelper banco;

    public LoginDAO(Context context){
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_LOGIN = "login";
    public static final String COLUNA_USUARIO = "usuario";
    public static final String COLUNA_SENHA = "senha";

    public Login getUsuario(){
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = {COLUNA_USUARIO,COLUNA_SENHA};
        Cursor cursor = db.query(true,TABELA_LOGIN,colunas,null,null,null,null,null,null);
        Login login = null;
        if(cursor != null){
            cursor.moveToFirst();
            login = new Login();
            login.setUsuario(cursor.getString(cursor.getColumnIndex(COLUNA_USUARIO)));
            login.setSenha(cursor.getString(cursor.getColumnIndex((COLUNA_SENHA))));
        }
        return login;
    }

    public String add(Login login){
        long resultado;
        SQLiteDatabase db = banco.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_USUARIO, login.getUsuario());
        values.put(COLUNA_SENHA, login.getSenha());

        resultado = db.insert(TABELA_LOGIN,null,values);
        db.close();

        if(resultado == -1){
            return "Erro ao inserir o registro";
        }else{
            return "Registro inserido com sucesso";
        }
    }

    public void limparBanco(){
        SQLiteDatabase db = banco.getReadableDatabase();
        long resultado = db.delete(TABELA_LOGIN,null,null);
        db.close();
    }


}
