package roledi.salud;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AltaActivity extends AppCompatActivity {

    private EditText var1, var2, var3, var4; //Clave, nombre, fecha, diagnostico

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        var1 = (EditText) findViewById(R.id.claveid);
        var2 = (EditText) findViewById(R.id.nombre);
        var3 = (EditText) findViewById(R.id.fecha);
        var4 = (EditText) findViewById(R.id.diagnos);
    }

    //Boton Volver: Se vuelve a la ventana menu
    public void principal(View w){
        Intent i = new Intent(this,PrincipalActivity.class);
        startActivity(i);
    }

    //Boton Dar de alta: Se agrega al paciente en una fila de la tabla de la bd
    public void alta (View v){

        //No puede haber ningun campo vacío en la creación del paciente
        if(var1.getText().length()==0) {
            var1.setError("Por favor, ingrese la clave única");
        }
        if(var2.getText().length()==0) {
            var2.setError("Por favor, ingrese el nombre");
        }
        if(var3.getText().length()==0) {
            var3.setError("Por favor, ingrese la fecha de nacimiento");
        }
        if(var4.getText().length()==0) {
            var4.setError("Por favor, ingrese el diagnóstico");
        }
        //Si no queda ningun campo vacío, agregamos al paciente
        else{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String claveid = var1.getText().toString();
            String nombre = var2.getText().toString();
            String fecha = var3.getText().toString();
            String diagnostico = var4.getText().toString();

            Cursor fila = bd.rawQuery("select * from pacientes where claveid ="+ claveid, null);
            if(fila.moveToFirst()){
                Toast.makeText(this, "La clave id ya ha sido usada", Toast.LENGTH_SHORT).show();
            }
            else{
                ContentValues datos = new ContentValues();
                datos.put("claveid", claveid);
                datos.put("nombre", nombre);
                datos.put("fecha", fecha);
                datos.put("diagnostico", diagnostico);

                //Insertamos en la tabla
                bd.insert("pacientes" ,null,datos);
                bd.close();

                //Vaciamos los editText
                var1.setText("");
                var2.setText("");
                var3.setText("");
                var4.setText("");

                //Mensaje de éxito
                Toast.makeText(this, "La persona se ha dado de alta con éxito", Toast.LENGTH_SHORT).show();

            }
    }

}
}
