package com.eamosse.gmail.gestion.myapplication.ui;

import android.os.Bundle;

import com.eamosse.gmail.gestion.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Executor executor;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        longRunningOp();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Cette méthode simule l'exécution d'un long Job; Si ce job est lancé sur le thread principal
     * de l'application, il risque de bloquer l'interface jusqu'à la fin de la boucle.
     * Ce serait une mauvaise pratique car pendant ce temps, l'utilisateur ne pourrait pas utiliser
     * l'application.
     * Pour éviter de bloquer l'utilisateur, nous avons créer un thread secondaire qui exécutera ce
     * job en tâche de fond.
     * Ainsi, la uiThread (ou main thread, ou thread principal) peut continuer à gérer l'interface
     * utilisateur tandis que le thread secondaire s'occupe du job long.
     */
    private void longRunningOp() {
        executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Cette boucle va bloquer le thread jusqu'à la fin de l'itération
            for (long i = 0; i < 10000; i++) {
                System.out.println("Nombre " + i);
            }
            // Il n'est pas possible de modifier la vue dans un thread secondaire.
            // Pour afficher un toast par exemple, il faut revenir sur le thread principal.
            // Nous utilisons pour cela un handler, qui va exécuter cette action sur le thread
            // principal.
            // On peut appeler un handler dans n'importe quel thread, l'action sera exécutée sur le
            // le threa principal.
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Hello world", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}