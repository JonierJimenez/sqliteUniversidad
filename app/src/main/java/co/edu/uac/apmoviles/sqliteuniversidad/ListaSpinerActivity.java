package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListaSpinerActivity extends AppCompatActivity {

    Spinner SpinerEstudiantes;
    ArrayList<String> listaEstudiantes;
    ArrayList<Estudiante> listaObjetosEstudiantes;

    BaseDatos conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_spiner);

        SpinerEstudiantes = findViewById(R.id.listaPersona);

        conn= new BaseDatos(getApplicationContext(),1);
        consultarListaEstudiantes();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this, android.R.layout.simple_selectable_list_item,listaEstudiantes);
        SpinerEstudiantes.setAdapter(adaptador);

    }

    private void consultarListaEstudiantes() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Estudiante estudiante;

        listaObjetosEstudiantes = new ArrayList<Estudiante>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+DefDB.tabla_est,null);

        while (cursor.moveToNext()){
            estudiante = new Estudiante();

            estudiante.setCodigo(cursor.getString(0));
            estudiante.setNombre(cursor.getString(1));
            estudiante.setPrograma(cursor.getString(2));

            Log.i("ID", estudiante.getCodigo().toString());

            listaObjetosEstudiantes.add(estudiante);
        }
        obtenerLista();
    }

    private  void  obtenerLista(){

        listaEstudiantes = new ArrayList<String>();
        listaEstudiantes.add("Seleccione");
        for(int i=0; i<listaObjetosEstudiantes.size();i++){
            listaEstudiantes.add(listaObjetosEstudiantes.get(i).getCodigo() + " - "+listaObjetosEstudiantes.get(i).getNombre());
        }
    }
}