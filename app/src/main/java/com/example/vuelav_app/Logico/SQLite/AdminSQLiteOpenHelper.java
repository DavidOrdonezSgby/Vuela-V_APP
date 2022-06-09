package com.example.vuelav_app.Logico.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase Base) {
        Base.execSQL("create table usuario(id int primary key, pais text, cedula text, nombre text, apellido text,fechanacimiento text, genero text, email text, telefono text, clave text, terminoscondiciones boolean, autorizacion boolean, razonsocial text, rol text)");
        Base.execSQL("create table guardados(id_guardado int primary key, id_usuario int, id_vuelo text)");
        Base.execSQL("create table vuelo(id int primary key, precio double, destino text, estado int, tipo_vuelo text, origen text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
