package roledi.salud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultaActivity extends AppCompatActivity {

    private EditText var1;
    private String nombre, fecha, diagnostico;
    private TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        var1 = (EditText)findViewById(R.id.claveid);
        tv1 = (TextView) findViewById(R.id.nombreconsu);
        tv2 = (TextView) findViewById(R.id.fechaconsu);
        tv3 = (TextView) findViewById(R.id.diagnosconsu);
    }

    //Botón principal
    public void principal(View w){
        Intent i = new Intent(this,PrincipalActivity.class);
        startActivity(i);
    }

    //Boton Consulta
    public void consulta (View v){
        if(var1.getText().length()==0){
            var1.setError("por favor, ingrese la clave única");
        }
        else{

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String claveid = var1.getText().toString();
            Cursor fila = bd.rawQuery("select nombre, fecha, diagnostico from pacientes where claveid=" + claveid , null);

            if (fila.moveToFirst()){

                String nombre= fila.getString(0);
                String fecha= fila.getString(1);
                String diagnostico = fila.getString(2);

                tv1.setText("Nombre: "+nombre);
                tv2.setText("Fecha de nacimiento: "+fecha);
                tv3.setText("Diagnóstico: "+diagnostico);


            }
            else {
                Toast.makeText(this, "El paciente no se ha dado de alta", Toast.LENGTH_SHORT).show();
                bd.close();

            }

        }
    }
}
