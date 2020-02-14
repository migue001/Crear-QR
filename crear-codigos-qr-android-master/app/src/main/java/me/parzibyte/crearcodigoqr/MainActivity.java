package me.parzibyte.crearcodigoqr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.glxn.qrgen.android.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private static final int CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO = 1;
    private static final int ALTURA_CODIGO = 500, ANCHURA_CODIGO = 500;
    private EditText etTextoParaCodigo,nombre,apep,apem,grupo,semestre,carrera,num_cuenta;
    private boolean tienePermisoParaEscribir = false; // Para los permisos en tiempo de ejecución


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String dele="DELETE FROM usuarioss";
        CrearBd conex = new CrearBd(this, "bd_usuarioss", null, 1);
        SQLiteDatabase db = conex.getReadableDatabase();
        db.execSQL(dele);
       // etTextoParaCodigo = findViewById(R.id.etTextoParaCodigo);
nombre=findViewById(R.id.nombre);
        apep=findViewById(R.id.apellidop);
        apem=findViewById(R.id.apellidom);
        grupo=findViewById(R.id.grupo);
        semestre=findViewById(R.id.semestre);
        carrera=findViewById(R.id.carrera);
        num_cuenta=(EditText)findViewById(R.id.num_cuenta);
        final ImageView imagenCodigo = findViewById(R.id.ivCodigoGenerado);
        Button btnGenerar = findViewById(R.id.btnGenerar);
        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().equalsIgnoreCase("") || apep.getText().toString().equalsIgnoreCase("") || apem.getText().toString().equalsIgnoreCase("") || grupo.getText().toString().equalsIgnoreCase("") || semestre.getText().toString().equalsIgnoreCase("") || carrera.getText().toString().equalsIgnoreCase("") || num_cuenta.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Rellena todos los campos de texto", Toast.LENGTH_SHORT).show();
                }
                else {


                    String texto = obtenerTextoParaCodigo();
                    if (texto.isEmpty()) return;

                    Bitmap bitmap = QRCode.from(texto).withSize(ANCHURA_CODIGO, ALTURA_CODIGO).bitmap();
                    imagenCodigo.setImageBitmap(bitmap);
                    String texto2 = obtenerTextoParaCodigo();
                    if (texto2.isEmpty()) return;
                    if (!tienePermisoParaEscribir) {
                        noTienePermiso();
                        return;
                    }
                    // Crear stream del código QR
                    ByteArrayOutputStream byteArrayOutputStream = QRCode.from(texto2).withSize(ANCHURA_CODIGO, ALTURA_CODIGO).stream();
                    // E intentar guardar
                    FileOutputStream fos;
                    try {
                        fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/codigo.png");
                        byteArrayOutputStream.writeTo(fos);
                        Toast.makeText(MainActivity.this, "Código guardado", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    registrarusuario();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });



        /*
         * Debería pedirse cuando se está a punto de realizar la acción, no
         * al inicio; pero ese no es el propósito de este código
         * */
        verificarYPedirPermisos();

    }

    private void registrarusuario() {
        try{
            CrearBd conexion=new CrearBd(this,"bd_usuarioss",null,1);
            SQLiteDatabase db=conexion.getWritableDatabase();
            db.execSQL("INSERT INTO usuarioss (nombre, apellidop,apellidom,grupo,carrera,semestre,num_cuenta)VALUES('"+nombre.getText().toString()+"','"+apep.getText().toString()+"','"+apem.getText().toString()+"','"+grupo.getText().toString()+"','"+carrera.getText().toString()+"','"+semestre.getText().toString()+"','"+num_cuenta.getText().toString()+"')");
        }catch (Exception e){
            Toast.makeText(this, "Error no se pudo guardar", Toast.LENGTH_SHORT).show();
        }
    }
    private String obtenerTextoParaCodigo() {
        String posibleTexto = nombre.getText().toString()+" "+apep.getText().toString()+" "+apem.getText().toString()+","+semestre.getText().toString()+","+grupo.getText().toString()+","+carrera.getText().toString()+","+num_cuenta.getText().toString()+".";
        /*if (posibleTexto.isEmpty()) {
        }*/

        return posibleTexto;
    }
    private void verificarYPedirPermisos() {
        if(
                ContextCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        ==
                        PackageManager.PERMISSION_GRANTED
                ) {
            // En caso de que haya dado permisos ponemos la bandera en true
            tienePermisoParaEscribir = true;
        } else {
            // Si no, entonces pedimos permisos
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO);
        }
    }

    private void noTienePermiso() {
        Toast.makeText(MainActivity.this, "No has dado permiso para escribir", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // SÍ dieron permiso
                    tienePermisoParaEscribir = true;

                } else {
                    // NO dieron permiso
                    noTienePermiso();
                }
        }
    }
}
