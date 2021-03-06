package com.example.sqlitecalificaciones;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(MainActivity context, String name,
                                 SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumnos(clave integer primary key, nombre text, materia text, calificacion real)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("drop table if exists alumnos");
        db.execSQL("create table alumnos(clave integer primary key, nombre text, materia text, calificacion real)");
    }
}