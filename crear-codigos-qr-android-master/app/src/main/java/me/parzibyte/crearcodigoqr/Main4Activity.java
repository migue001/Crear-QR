package me.parzibyte.crearcodigoqr;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.glxn.qrgen.android.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main4Activity extends AppCompatActivity {
EditText editText,editText2,editText3;
TextView textView;
 Button button;
 public static  int idd2=0;
 String posibleTexto;
    private static final int ALTURA_CODIGO = 500, ANCHURA_CODIGO = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        editText=(EditText)findViewById(R.id.consulgrupo);
        editText2=(EditText)findViewById(R.id.consullicenciatura);
        editText3=(EditText)findViewById(R.id.consulsemestre);
        textView=(TextView)findViewById(R.id.consulnombre);
        button=(Button)findViewById(R.id.guardarcambios);
        idd2=Main2Activity.iddd;

        CrearBd crearBd=new CrearBd(Main4Activity.this,"bd_usuarioss",null,1);
        final SQLiteDatabase sqLiteDatabase=crearBd.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM usuarioss",null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                textView.setText(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3));
                editText.setText(cursor.getString(4));
                editText2.setText(cursor.getString(5));
             //  editText3.setText(String.valueOf(idd2));
                editText3.setText(cursor.getString(6));
            }
        }
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(editText.getText().toString().equalsIgnoreCase("") || editText2.getText().toString().equalsIgnoreCase("") || editText3.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(Main4Activity.this, "Rellena todos los campos de Texto", Toast.LENGTH_SHORT).show();
                }
                else {


                    CrearBd crearBd1 = new CrearBd(Main4Activity.this, "bd_usuarioss", null, 1);
                    SQLiteDatabase sqLiteDatabase1 = crearBd1.getWritableDatabase();
                    sqLiteDatabase1.execSQL("UPDATE usuarioss SET grupo='" + editText.getText().toString() + "' WHERE id=" + idd2 + "");
                    sqLiteDatabase1.execSQL("UPDATE usuarioss SET carrera='" + editText2.getText().toString() + "' WHERE id=" + idd2 + "");
                    sqLiteDatabase1.execSQL("UPDATE usuarioss SET semestre='" + editText3.getText().toString() + "' WHERE id=" + idd2 + "");
                    Toast.makeText(Main4Activity.this, "Se ha registrado", Toast.LENGTH_SHORT).show();
                    //finish();

                    String texto2 = ObtenerTexto();
                    ByteArrayOutputStream byteArrayOutputStream = QRCode.from(texto2).withSize(ANCHURA_CODIGO, ALTURA_CODIGO).stream();
                    FileOutputStream fos;
                    try {
                        fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/codigo.png");
                        byteArrayOutputStream.writeTo(fos);
                        Toast.makeText(Main4Activity.this, "Código guardado", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Main4Activity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    private String ObtenerTexto(){
       CrearBd crearBd=new CrearBd(Main4Activity.this,"bd_usuarioss",null,1);
        SQLiteDatabase sqLiteDatabase=crearBd.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from usuarioss",null);
        if(cursor!=null){
            while (cursor.moveToNext()) {
                posibleTexto = "" + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + "," + cursor.getString(6) + "," + cursor.getString(4) + "," + cursor.getString(5) + "," + cursor.getString(7) + ".";
            }
            }
        return posibleTexto;
    }
}
