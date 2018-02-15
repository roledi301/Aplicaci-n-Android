package roledi.salud;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificacionActivity extends AppCompatActivity {

    private EditText var1, var2, var3, var4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion);

        var1 = (EditText)findViewById(R.id.claveid);
        var2 = (EditText)findViewById(R.id.nombre);
        var3 = (EditText) findViewById(R.id.fecha);
        var4 = (EditText) findViewById(R.id.diagnostico);
    }

    //Boton Volver: regresar al menu principal
    public void principal(View w){
        Intent i = new Intent(this,PrincipalActivity.class);
        startActivity(i);
    }

    //Boton Modificar
    public void modificar (View v){
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
        //Si no queda ningun campo vacío, modificamos al paciente
        else{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String claveid = var1.getText().toString();
            String nombre = var2.getText().toString();
            String fecha = var3.getText().toString();
            String diagnostico = var4.getText().toString();

            ContentValues datos = new ContentValues();
            datos.put("claveid", claveid);
            datos.put("nombre", nombre);
            datos.put("fecha", fecha);
            datos.put("diagnostico", diagnostico);

            //Actualizamos al paciente
            int conteo = bd.update("pacientes", datos, "claveid="+claveid, null);
            bd.close();

            //Limpiar edittext
            var1.setText("");
            var2.setText("");
            var3.setText("");
            var4.setText("");

            //Mensaje del boton
            if(conteo == 1 ){
                Toast.makeText(this,"Los datos fueron modificados",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"No existe el paciente",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
