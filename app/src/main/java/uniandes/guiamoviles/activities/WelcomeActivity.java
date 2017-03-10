package uniandes.guiamoviles.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import uniandes.guiamoviles.R;
import uniandes.guiamoviles.entities.Pais;

public class WelcomeActivity extends AppCompatActivity {

    private final static int PAISES =1; //El atributo de requestCode sirve para identificar la actividad desde la cual se regresa. Por eso, debe ser diferente para las dos actividades
    private final static int GUSTOS =2;
    private final static String TAG_PAISES ="PAISES"; //Estos sirven para hacer logs, también debe ser distinto de acuerdo a la actividad
    private final static String TAG_GUSTOS ="GUSTOS";
    Button btnPaises;
    Button btnGustos;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        btnPaises = (Button) findViewById(R.id.seleccionar_paises);
        btnPaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(WelcomeActivity.this, PaisesActivity.class);
                startActivityForResult(i, PAISES);
            }
        });

        btnGustos = (Button) findViewById(R.id.seleccionar_gustos);
        btnGustos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(WelcomeActivity.this, GustosActivity.class);
                startActivityForResult(i, GUSTOS);
            }
        });


        btnRegistrar= (Button) findViewById(R.id.registrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });


    }

    private void registrar(){
        final ProgressDialog progressDialog;
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Un momento por favor");
        progressDialog.show();

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try {

                    Log.d(TAG_PAISES,"start task");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                progressDialog.dismiss();
                Snackbar.make(btnRegistrar,"Se registro la cuenta",Snackbar.LENGTH_LONG).show();
            }
        }.execute();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== PAISES){
            if(resultCode==RESULT_OK){
                Log.d(TAG_PAISES,"Llega el resultado de la actividad paises");
                ArrayList<Pais> seleccionados = (ArrayList<Pais>) data.getSerializableExtra("paisesSeleccionados");
                btnPaises.setText(TextUtils.join(", ", seleccionados));
                //btnPaises.setEnabled(false);
            }
        }

        else if(requestCode== GUSTOS){
            Log.d(TAG_GUSTOS,"Llega el resultado de la actividad gustos");
            if(resultCode==RESULT_OK){
                ArrayList<Pais> seleccionados = (ArrayList<Pais>) data.getSerializableExtra("gustosSeleccionados");
                btnGustos.setText(TextUtils.join(", ", seleccionados));
                //btnGustos.setEnabled(false);
            }
        }
    }
}
