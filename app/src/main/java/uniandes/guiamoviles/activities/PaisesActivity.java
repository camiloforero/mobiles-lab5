package uniandes.guiamoviles.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uniandes.guiamoviles.R;
import uniandes.guiamoviles.adapters.ImageAdapter;
import uniandes.guiamoviles.entities.Pais;
import uniandes.guiamoviles.rest.RestClient;

public class PaisesActivity extends AppCompatActivity {

    ListView listPaisesView;
    List<Pais> paises;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("paises", (Serializable) paises);
        System.out.println("on save paises " + paises);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paises);
        listPaisesView =(ListView) findViewById(R.id.paises_listview);
        listPaisesView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        /*
        paises.add(new Pais(1,"Hamburguesa",15000,"https://saboresmodelo.blob.core.windows.net/images/notes/1447867853_564cb5cd22390.png"));
        paises.add(new Pais(2,"Pizza",10000,"http://cronicadexalapa.com/wp-content/uploads/2016/08/pizza.jpg"));
        paises.add(new Pais(3,"Papas",6000,"http://s2.dmcdn.net/IZlI9/1280x720-ts8.jpg"));
        paises.add(new Pais(4,"Gaseosa",3000, "http://www.superinter.com.co/wp-content/uploads/2014/08/763-gaseosa-coca-cola-600-ml.jpg"));


        PlatoAdapter platosAdapter=new PlatoAdapter(PaisesActivity.this,paises);
        listPaisesView.setAdapter(platosAdapter);

        */
        listPaisesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Pais pais= paises.get(i);
                pais.setSelected(!pais.isSelected());
                if(pais.isSelected()){
                    view.setAlpha(1f);
                }else{
                    view.setAlpha(0.5f);
                }

                /*
                Intent intent= new Intent(PaisesActivity.this,PedidosActivity.class);
                intent.putExtra("name", paises.get(i).getNombre());
                intent.putExtra("id",  paises.get(i).getId() );
                startActivity(intent);
                */
            }
        });

        if(savedInstanceState==null){
            getPaises();
        }else{
            System.out.println("test");
            paises = (List<Pais>) savedInstanceState.getSerializable("paises");
            ImageAdapter itemsAdapter = new ImageAdapter(PaisesActivity.this, paises);
            listPaisesView.setAdapter(itemsAdapter);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save_paises_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Pais> seleccionados = new ArrayList<Pais>();
                int len = listPaisesView.getCount();
                SparseBooleanArray checked = listPaisesView.getCheckedItemPositions();
                for (int i = 0; i < len; i++)
                    if (checked.get(i)) {
                        Pais paisSeleccionado = paises.get(i);
                        seleccionados.add(paisSeleccionado);
  /* do whatever you want with the checked item */
                    }
                Intent intent = new Intent().putExtra("paisesSeleccionados", seleccionados);
                //intent.putExtra("num_paises", checked.size()+"");
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                AlertDialog dialog=createAlertDialog();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public AlertDialog createAlertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Continuar")
            .setTitle("¿Desea ir al registro de intereses?")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent= new Intent(PaisesActivity.this,GustosActivity.class);
                   // intent.putExtra("name", paises.get(i).getNombre());
                    //intent.putExtra("id",  paises.get(i).getId() );
                    startActivity(intent);
                }
            });

        return builder.create();
    }

    public void getPaises(){

    Call<List<Pais>> call = RestClient.getInstance().getApiService().getPaises();
    call.enqueue(new Callback<List<Pais>>() {
        @Override
        public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
            paises = response.body();
            ImageAdapter itemsAdapter = new ImageAdapter(PaisesActivity.this, paises);
            listPaisesView.setAdapter(itemsAdapter);

        }

        @Override
        public void onFailure(Call<List<Pais>> call, Throwable t) {

        }
    });

    }


}
