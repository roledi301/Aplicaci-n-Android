package roledi.salud;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BajaActivity extends AppCompatActivity {

    private EditText var1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baja);
        var1 = (EditText)findViewById(R.id.claveid);

    }

    //Boton Volver: Se vuelve a la ventana menu
    public void principal(View w){
        Intent i = new Intent(this,PrincipalActivity.class);
        startActivity(i);
    }

    //Boton Baja de paciente
    public void baja (View v){
        if(var1.getText().length()==0){
            var1.setError("Ingresar la clave única");
        }
        else{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String claveid = var1.getText().toString();

            int conteo = bd.delete("pacientes","claveid="+claveid,null);
            bd.close();

            var1.setText("");

            if(conteo==1){
                Toast.makeText(this, "El paciente ha sido borrado",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "No existe ese paciente", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
