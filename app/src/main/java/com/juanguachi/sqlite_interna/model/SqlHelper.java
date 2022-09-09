package com.juanguachi.sqlite_interna.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SqlHelper extends SQLiteOpenHelper {
    private static final String nombre_bd="clientes.bd";
    private static final int version_bd=1;
    private static final String tabla_clientes="Create table clientes(id text primaru key, nombre text, apellido text)";

    public SqlHelper(@Nullable Context context) {
        super(context, nombre_bd, null, version_bd);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_clientes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists"+tabla_clientes);
        db.execSQL(tabla_clientes);

    }


}
