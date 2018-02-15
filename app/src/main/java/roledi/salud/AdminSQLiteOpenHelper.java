package roledi.salud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by roledi301 on 27/12/2016.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la tabla con las columnas en la base de datos
        db.execSQL("CREATE TABLE pacientes(claveid INTEGER primary key, nombre TEXT, fecha TEXT, diagnostico TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop TABLE if exists pacientes");
        db.execSQL("CREATE TABLE pacientes(claveid INTEGER primary key, nombre TEXT, fecha TEXT, diagnostico TEXT)");
    }
}
