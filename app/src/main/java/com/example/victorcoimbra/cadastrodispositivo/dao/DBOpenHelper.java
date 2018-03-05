package com.example.victorcoimbra.cadastrodispositivo.dao;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.victorcoimbra.cadastrodispositivo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Victor Coimbra on 03/03/2018.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Dispositivos.db";
    private static final int VERSAO_BANCO = 1;

    private Context ctx;

    public DBOpenHelper(Context context){
        super(context, DB_NAME,null,VERSAO_BANCO);
        this.ctx = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        lerEExecutarSQlScript(sqLiteDatabase, ctx, R.raw.db_criar);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        for(int i = oldVersion; i < newVersion; i++){
            String migrationFileName = String.format("from_%d-to_%d",i,(i+1));
            int migrationFileResId = ctx.getResources()
                    .getIdentifier(migrationFileName, "raw", ctx.getPackageName());

            if(migrationFileResId != 0){
                lerEExecutarSQlScript(sqLiteDatabase, ctx, migrationFileResId );
            }
        }
    }

    private void lerEExecutarSQlScript(SQLiteDatabase db, Context ctx, Integer sqlScriptResId){
        Resources res = ctx.getResources();

        try{
            InputStream is = res.openRawResource(sqlScriptResId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            executarSQLScript(db, reader);

            reader.close();
            is.close();
        }catch (IOException e){
            throw new RuntimeException("Erro ao ler arquivo do SQLite", e);
        }
    }

    private void executarSQLScript(SQLiteDatabase db, BufferedReader reader) throws IOException{
        String line;
        StringBuilder statement = new StringBuilder();
        while((line = reader.readLine()) != null){
            statement.append(line);
            statement.append("\n");
            if(line.endsWith(";")){
                String toExec = statement.toString();
                db.execSQL(toExec);
                statement = new StringBuilder();
            }
        }
    }
}
