package me.parzibyte.crearcodigoqr;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {
    private final int DURACION_SPLASH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent intent=new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },DURACION_SPLASH);
    }
}
