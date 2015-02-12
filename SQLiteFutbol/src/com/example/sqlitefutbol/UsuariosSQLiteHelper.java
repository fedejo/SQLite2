package com.example.sqlitefutbol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
 
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Futbol (nombre TEXT, apellidos TEXT, edad INTEGER, dni TEXT, categoria TEXT)";
 
    public UsuariosSQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) { 
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Futbol");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}