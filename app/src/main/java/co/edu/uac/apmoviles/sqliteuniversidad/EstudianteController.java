package co.edu.uac.apmoviles.sqliteuniversidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

public class EstudianteController {

    private BaseDatos bd;
    private Context c;

    public EstudianteController(Context c) {
        this.bd = new BaseDatos(c,1);
        this.c = c;
    }
    public long agregarEstudiante(Estudiante e){
        try{
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DefDB.col_codigo, e.getCodigo());
            cv.put(DefDB.col_nombre, e.getNombre());
            cv.put(DefDB.col_programa, e.getPrograma());
            long id = sql.insert(DefDB.tabla_est,null,cv); //inser cuando utilizamos contentValues
            return id;
        }
        catch (Exception ex){
            return 0;
        }
    }

    public Cursor allEstudiantes(){
        try{
            SQLiteDatabase data = bd.getReadableDatabase();
            Cursor cur = data.query(DefDB.tabla_est,null,null,null,null,null,null);
            return cur;
        }
        catch(Exception ex){
            Toast.makeText(c,"Error Consulta",Toast.LENGTH_LONG).show();
           return null;
        }
    }


    /*public Cursor Consultar_Buscar(String id){

        String[] parametros= {id}; // establesco el parametro por el que quiero buscar
        String[] campos ={DefDB.col_nombre,DefDB.col_programa};//establesco los campos que quiero mostrar

        try{
            Toast.makeText(c, "entro al try", Toast.LENGTH_SHORT).show();
            SQLiteDatabase data = bd.getReadableDatabase();
            Cursor cursor = data.query(DefDB.tabla_est,campos,DefDB.col_codigo +"=?",parametros,null,null,null); //se guarda el estudiante buscado
            cursor.moveToFirst();
            return cursor;
            //cursor.close();
            //Cursor cur = data.query(DefDB.tabla_est,null,null,null,null,null,null);
        }catch(Exception ex){
            Toast.makeText(c, "entro al cath", Toast.LENGTH_SHORT).show();
            Toast.makeText(c,"Error Consulta",Toast.LENGTH_LONG).show();
            return null;
        }
        return null;

    }*/


}
