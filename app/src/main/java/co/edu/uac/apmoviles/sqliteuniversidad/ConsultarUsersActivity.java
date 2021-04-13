package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarUsersActivity extends AppCompatActivity implements View.OnClickListener {

    EditText id,nombre,programa;
    Button consultar,eliminar,actualizar,regresar;

    BaseDatos conn;
    EstudianteController ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_users);

        conn = new BaseDatos(getApplicationContext(),1); ///establesco la conexion con la base de datos

        id = findViewById(R.id.edtId);
        nombre = findViewById(R.id.edtNombre);
        programa= findViewById(R.id.edtPrograma);

        consultar= findViewById(R.id.btnConsultar);
        eliminar = findViewById(R.id.btnEliminar);
        actualizar = findViewById(R.id.btnActualizar);
        regresar = findViewById(R.id.btnRegresar);

        consultar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        actualizar.setOnClickListener(this);
        regresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnConsultar: consultar(); break;
            case R.id.btnActualizar: actualizar(); break;
            case R.id.btnEliminar: eliminar(); break;
            case R.id.btnRegresar:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros= {id.getText().toString()};

        db.delete(DefDB.tabla_est,DefDB.col_codigo + "=?",parametros);

        Toast.makeText(getApplicationContext(),"Se Elimino el Usuario", Toast.LENGTH_LONG).show();
        limpiar();
        db.close();
    }

    private void actualizar() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String[] parametros= {id.getText().toString()}; // establesco el parametro por el que quiero buscar o guardar

        ContentValues values = new ContentValues();
        values.put(DefDB.col_nombre, nombre.getText().toString());
        values.put(DefDB.col_programa, programa.getText().toString());

        db.update(DefDB.tabla_est,values,DefDB.col_codigo + "=?",parametros);

        Toast.makeText(getApplicationContext(),"Se Actualizo con exito", Toast.LENGTH_LONG).show();

        db.close();

    }

    private void consultar() {
       /* ec = new EstudianteController(this);//instanciar el estudiante controller
        Cursor c = ec.Consultar_Buscar(id.getText().toString());

        if (c != null) {
            nombre.setText(c.getString(0));
            programa.setText(c.getString(1));
        } else {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();
        }*/

        SQLiteDatabase db = conn.getReadableDatabase(); //abro la base de datos en modo lectura

        String[] parametros= {id.getText().toString()}; // establesco el parametro por el que quiero buscar
        String[] campos ={DefDB.col_nombre,DefDB.col_programa,"edad"};//establesco los campos que quiero mostrar

        try {
            Cursor cursor = db.query(DefDB.tabla_est,campos,DefDB.col_codigo +"=?",parametros,null,null,null); //se guarda el estudiante buscado
            cursor.moveToFirst();

            nombre.setText(cursor.getString(0));
            programa.setText(cursor.getString(1));

            cursor.close();

        }catch (Exception e){
            Toast.makeText(this, "El codigo no esta registrado", Toast.LENGTH_SHORT).show();
            limpiar();
        }
       
    }

    private void limpiar() {
        nombre.setText("");
        programa.setText("");
    }
}