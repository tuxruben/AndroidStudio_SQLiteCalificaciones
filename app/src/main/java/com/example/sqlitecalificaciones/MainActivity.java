package com.example.sqlitecalificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2, et3, et4;
    int posicion=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
    }
    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String clave = et1.getText().toString();
        String nombre = et2.getText().toString();
        String materia = et3.getText().toString();
        String calificacion = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("clave", clave);
        registro.put("nombre", nombre);
        registro.put("materia", materia);
        registro.put("calificacion", calificacion);
        bd.insert("alumnos", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();
    }
    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String clave = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,materia,calificacion from alumnos where clave=" + clave, null);
        if (fila.moveToFirst()) {et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona con dicha clave",Toast.LENGTH_SHORT).show();
        posicion=1;
        bd.close();
    }
    public void recorridoRegistros(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select clave,nombre,materia,calificacion from alumnos where clave=" + posicion, null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
        }
        else

            Toast.makeText(this,"No hay registros",Toast.LENGTH_LONG).show();
        posicion++;
        bd.close();
    }
    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String clave = et1.getText().toString();
        int cant = bd.delete("alumnos", "clave=" + clave, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró la persona con dicho documento", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una persona con dicho documento", Toast.LENGTH_SHORT).show();}
    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String clave = et1.getText().toString();
        String nombre = et2.getText().toString();
        String materia = et3.getText().toString();
        String calificacion = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("materia", materia);
        registro.put("calificacion", calificacion);
        int cant = bd.update("alumnos", registro, "clave=" + clave, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "no existe una persona con dicho documento", Toast.LENGTH_SHORT).show();
    }
}

