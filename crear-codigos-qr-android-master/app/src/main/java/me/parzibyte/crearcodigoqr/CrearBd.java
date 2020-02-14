package me.parzibyte.crearcodigoqr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jj on 05/11/19.
 */

public class CrearBd extends SQLiteOpenHelper{

    public CrearBd(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE usuarioss (id INTEGER primary key autoincrement,nombre TEXT, apellidop TEXT,apellidom TEXT,grupo TEXT,carrera TEXT,semestre TEXT,num_cuenta TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
