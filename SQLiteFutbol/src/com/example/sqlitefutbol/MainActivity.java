package com.example.sqlitefutbol;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
 
public class MainActivity extends Activity {
	private EditText nom;
	private EditText apes;
	private EditText ed;
	private EditText nif;
	private EditText cat;
	
	private TextView res;
	
	UsuariosSQLiteHelper usdbh;
	SQLiteDatabase db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        nom = (EditText) findViewById(R.id.nombre);
		apes = (EditText) findViewById(R.id.apellidos);
		ed = (EditText) findViewById(R.id.edad);
		nif = (EditText) findViewById(R.id.dni);
		cat = (EditText) findViewById(R.id.categoria);
		
		res = (TextView) findViewById(R.id.resultado);
 
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh = new UsuariosSQLiteHelper(this, "DBFutbol", null, 1);
 
        db = usdbh.getWritableDatabase();
    }
    
    public void insertar (View view) {
    	// OPCION !    	
    	/* //Creamos el registro a insertar como objeto ContentValues
    	ContentValues v = new ContentValues();
    	v.put("nombre", nom.getText().toString());
    	v.put("apellidos", apes.getText().toString());
    	v.put("edad", ed.getText().toString());
    	v.put("dni", nif.getText().toString());
    	v.put("categoria", cat.getText().toString());
    	 
    	//Insertamos el registro en la base de datos
    	db.insert("Futbol", null, v); */
    	
    	// OPCION 2
    	// Recojemos los datos del formulario
    	String nom2 = nom.getText().toString();
    	String apes2 = apes.getText().toString();
    	String ed2 = ed.getText().toString();
    	String nif2 = nif.getText().toString();
    	String cat2 = cat.getText().toString();
    	
    	//Eliminar un registro con execSQL(), utilizando argumentos
    	String[] args = new String[]{nom2, apes2, ed2, nif2, cat2};
    	db.execSQL("INSERT INTO Futbol VALUES(?,?,?,?,?)", args);
	}
	
	public void actualizar (View view) {
		/* // OPCION 1
		// Recojemos los datos del formulario
		String nom2 = nom.getText().toString();
    	String apes2 = apes.getText().toString();
    	String ed2 = ed.getText().toString();
    	String nif2 = nif.getText().toString();
    	String cat2 = cat.getText().toString();
    	
		//Establecemos los campos-valores a actualizar
		ContentValues v = new ContentValues();
		v.put("nombre", nom2);
    	v.put("apellidos", apes2);
    	v.put("edad", ed2);
    	v.put("dni", nif2);
    	v.put("categoria", cat2);
    	
    	String[] args = new String[]{nom2, apes2, ed2, nif2, cat2};
		 
		//Actualizamos el registro en la base de datos
		db.update("Futbol", v, "nombre=? OR apellidos=? OR edad=? OR dni=? OR categoria=?", args); */
		
		// OPCION 2
		// Recojemos los datos del formulario
    	String nom2 = nom.getText().toString();
    	String apes2 = apes.getText().toString();
    	String ed2 = ed.getText().toString();
    	String nif2 = nif.getText().toString();
    	String cat2 = cat.getText().toString();
    	
		//Actualizar dos registros con update(), utilizando argumentos
		ContentValues v = new ContentValues();
		v.put("nombre", nom2);
    	v.put("apellidos", apes2);
    	v.put("edad", ed2);
    	v.put("dni", nif2);
    	v.put("categoria", cat2);
		 
		String[] args = new String[]{nom2, apes2, ed2, cat2, nif2};
		db.execSQL("UPDATE Futbol SET nombre=?, apellidos=?, edad=?, categoria=? WHERE dni=?", args);
	}
	
	public void eliminar (View view) {
		/* // OPCION 1
		String nif2 = nif.getText().toString();
		
		String[] args = new String[]{nif2};
		
		//Eliminamos el registro del usuario '6'
		db.delete("Futbol", "dni=?", args); */
		
		// OPCION 2
		String nif2 = nif.getText().toString();
		
		//Eliminar un registro con execSQL(), utilizando argumentos
		String[] args = new String[]{nif2};
		db.execSQL("DELETE FROM Futbol WHERE dni=?", args);
	}
	
	public void consultar (View view) {
		String nif2 = nif.getText().toString();
		
		//Eliminar un registro con execSQL(), utilizando argumentos
		String[] args = new String[]{nif2};		
		Cursor c = db.rawQuery("SELECT * FROM Futbol WHERE dni=?", args);
		
		if (c.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		    	 String nom2 = c.getString(0);
		     	 String apes2 = c.getString(1);
		     	 String ed2 = c.getString(2);
		     	 String nif3 = c.getString(3);
		     	 String cat2 = c.getString(4);
		     	 
		     	 res.append("Nombre: " + nom2 + " - Apellidos: " + apes2 + " - Edad: " + ed2 + " - Dni: " + nif3 + " - Categoria: " + cat2 + "\n");
		     } while(c.moveToNext());
		}
	}
}
