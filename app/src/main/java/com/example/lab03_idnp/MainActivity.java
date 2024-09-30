package com.example.lab03_idnp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ReceptorBateria receptorBateria;//Creamos un objeto para el receiver
    private TextView txtBateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtenemos el txtView
        txtBateria = findViewById(R.id.txtLevel);

        // Inicializar el BroadcastReceiver para pasarlo al txtView
        receptorBateria = new ReceptorBateria(txtBateria);
    }

    //OnResume:  Se da cuando es visible para el usuario.
    /*
    *IntentFilter: notifica los cambios de estado de la bateria
    * registerReceiver: registra al receptos, y actualiza el estado mientras la app este en funcionando
    * Log: Confirmacion por consola
    */
    @Override
    protected void onResume() {
        super.onResume();
        // Registrar el BroadcastReceiver
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receptorBateria, filter);
        Log.i("MainActivity", "ReceptorBateria: registrado satisfactoriamente.");
    }

    //OnPause:  Se da cuando se cambia o se sale de la aplicacion principal
    /*
     * unregisterReceiver: evita que siga recibiendo actualizaciones, cuando la app no esta en primer plano
     * Log: Confirmacion por consola
     */
    @Override
    protected void onPause() {
        super.onPause();
        // Desregistrar el BroadcastReceiver
        unregisterReceiver(receptorBateria);
        Log.i("MainActivity", "ReceptorBateria: desregistrado satisfactoriamente.");
    }
}