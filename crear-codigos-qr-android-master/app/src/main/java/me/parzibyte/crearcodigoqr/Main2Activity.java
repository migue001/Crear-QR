package me.parzibyte.crearcodigoqr;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
TextView textView,textView2,textView3,textView4,textView5;
ImageButton imageButton;
public static int iddd=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=(TextView)findViewById(R.id.nombre);
        textView2=(TextView)findViewById(R.id.num_cuenta);
        textView3=(TextView)findViewById(R.id.semestre);
        textView4=(TextView)findViewById(R.id.grupo);
        textView5=(TextView)findViewById(R.id.carrera);
        imageButton=(ImageButton)findViewById(R.id.Editar_perfil);
CrearBd conexion=new CrearBd(this, "bd_usuarioss",null,1);
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from usuarioss",null);
        //cursor.moveToFirst();
        //textView.setText("Nombre:\n"+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+"\nGrupo: "+cursor.getString(4)+"\nCarrera: "+cursor.getString(5)+"\nSemestre: "+cursor.getString(6)+".");*/
        if(cursor!=null){
            while (cursor.moveToNext()) {
                iddd=cursor.getInt(0);
                textView.setText(cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
                textView2.setText(cursor.getString(7));
                textView3.setText(cursor.getString(6));
                textView4.setText(cursor.getString(4));
                textView5.setText(cursor.getString(5));
            }

            }

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Main2Activity.this,Main4Activity.class);
                    startActivity(intent);
                    finish();
                }
            });

    }

}
