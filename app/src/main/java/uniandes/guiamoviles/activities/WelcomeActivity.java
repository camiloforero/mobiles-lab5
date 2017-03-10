package uniandes.guiamoviles.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import uniandes.guiamoviles.GustosActivity;
import uniandes.guiamoviles.MainActivity;
import uniandes.guiamoviles.R;

public class WelcomeActivity extends AppCompatActivity {

    private final static int WELCOME=1111;
    private final static String TAG="WELCOME";
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
                Intent i= new Intent(WelcomeActivity.this, MainActivity.class);
                startActivityForResult(i,WELCOME);
            }
        });

        btnGustos = (Button) findViewById(R.id.seleccionar_gustos);
        btnGustos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(WelcomeActivity.this, GustosActivity.class);
                startActivityForResult(i,WELCOME);
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

                    Log.d(TAG,"start task");
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
        Log.d(TAG,"Llega el resultado de otra actividad");
        if(requestCode==WELCOME){
            if(resultCode==RESULT_OK){
                btnGustos.setText("√");
                btnGustos.setEnabled(false);
            }else if(resultCode==2){

                    btnPaises.setText("√");
                    btnPaises.setEnabled(false);

            }
        }
    }
}
