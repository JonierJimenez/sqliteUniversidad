package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, nombre, programa;
    Button guardar, cancelar, listado, buscar, listadoSp;
  //  BaseDatos bd;
    Estudiante e;
    EstudianteController ec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codigo = findViewById(R.id.edtcodigo);
        nombre = findViewById(R.id.edtnombre);
        programa = findViewById(R.id.edtprograma);
        guardar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        listado = findViewById(R.id.btnlistado);
        listadoSp = findViewById(R.id.btnListadoSpiner);
        buscar=findViewById(R.id.btnbuscar);


        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        listado.setOnClickListener(this);
        listadoSp.setOnClickListener(this);
        buscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.btnguardar:
                ec = new EstudianteController(this);
                e = new Estudiante(codigo.getText().toString(), nombre.getText().toString(), programa.getText().toString());
                ec.agregarEstudiante(e);
                Toast.makeText(this, "Estudiante Agregado", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnlistado:
                ec = new EstudianteController(this);
                Cursor c = ec.allEstudiantes();
                if (c != null) {
                    String cadena = "";
                    while (c.moveToNext()) {
                        cadena = cadena + (c.getString(1))+",";
                    }
                    Toast.makeText(this, cadena, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();

                }

                //creo una instancia hacia una activity que se llama
                break;

            case R.id.btnListadoSpiner:
                i = new Intent(getApplicationContext(),ListaSpinerActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.btnbuscar:
                i= new Intent(getApplicationContext(),ConsultarUsersActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
