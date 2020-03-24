package com.example.permissionstesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CONTACTS_REQUEST_CODE = 1;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.boton_camara);
        mButton.setOnClickListener(v -> {
            solicitarAccesoCamara();
        });
    }

    private void solicitarAccesoCamara() {
        //No tiene el permiso dado
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                //Debe mostrar una explicación del por qué necesita el permiso}
                Toast.makeText(this, "Mostrará explicación del por qué necesita el permiso. ", Toast.LENGTH_SHORT).show();
            } else {
                //No mostrar explicación solo solicitar el permiso
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_REQUEST_CODE);
            }
        } else {
            //Ya tiene el permiso otorgado
            Toast.makeText(this, "El permiso ya ha sido otorgado previaemente, puede leer los contactos libremente.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CONTACTS_REQUEST_CODE) {
            // Si la solicitud se anula, el grantResult está vacío, POR ESO DEBE VERIFICARSE EL TAMAÑO DEL ARRAY
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Se otorgó el permiso de leer los contactos correctamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "NO SE OTORGÓ EL PERMISO DE LEER LOS CONTACTOS.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
