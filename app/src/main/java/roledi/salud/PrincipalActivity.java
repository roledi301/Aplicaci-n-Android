package roledi.salud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    //Boton Alta: al pulsarlo nos dirigimos a la clase/interface Alta
    public void pasaalta (View v){
        Intent i = new Intent(this, AltaActivity.class);
        startActivity(i);
    }
    //Boton Bajaa: al pulsarlo nos dirigimos a la clase/interface Baja
    public void pasabaja (View v){
        Intent i = new Intent(this, BajaActivity.class);
        startActivity(i);
    }
    //Boton Modificar: al pulsarlo nos dirigimos a la clase/interface Modificar
    public void pasamodi (View v){
        Intent i = new Intent(this, ModificacionActivity.class);
        startActivity(i);
    }
    //Boton Consultar: al pulsarlo nos dirigimos a la clase/interface Cosultar
    public void pasaconsu (View v){
        Intent i = new Intent(this,ConsultaActivity.class);
        startActivity(i);
    }
}
