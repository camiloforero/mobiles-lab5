package uniandes.guiamoviles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uniandes.guiamoviles.adapters.ImageAdapter;
import uniandes.guiamoviles.entities.Imagen;
import uniandes.guiamoviles.rest.RestClient;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Imagen> platos;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("platos", (Serializable) platos);
        System.out.println("on saveplatos " +platos);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.platos_listview);


        /*
        platos.add(new Imagen(1,"Hamburguesa",15000,"https://saboresmodelo.blob.core.windows.net/images/notes/1447867853_564cb5cd22390.png"));
        platos.add(new Imagen(2,"Pizza",10000,"http://cronicadexalapa.com/wp-content/uploads/2016/08/pizza.jpg"));
        platos.add(new Imagen(3,"Papas",6000,"http://s2.dmcdn.net/IZlI9/1280x720-ts8.jpg"));
        platos.add(new Imagen(4,"Gaseosa",3000, "http://www.superinter.com.co/wp-content/uploads/2014/08/763-gaseosa-coca-cola-600-ml.jpg"));


        PlatoAdapter platosAdapter=new PlatoAdapter(MainActivity.this,platos);
        listView.setAdapter(platosAdapter);

        */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Imagen plato=platos.get(i);
                plato.setSelected(!plato.isSelected());
                if(plato.isSelected()){
                    view.setAlpha(1f);
                }else{
                    view.setAlpha(0.5f);
                }

                /*
                Intent intent= new Intent(MainActivity.this,PedidosActivity.class);
                intent.putExtra("name", platos.get(i).getNombre());
                intent.putExtra("id",  platos.get(i).getId() );
                startActivity(intent);
                */
            }
        });

        if(savedInstanceState==null){
            getPlatos();
        }else{
            System.out.println("fsdlflakfñalf");
            platos= (List<Imagen>) savedInstanceState.getSerializable("platos");
            ImageAdapter itemsAdapter = new ImageAdapter(MainActivity.this,platos);
            listView.setAdapter(itemsAdapter);
        }


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
                    Intent intent= new Intent(MainActivity.this,GustosActivity.class);
                   // intent.putExtra("name", platos.get(i).getNombre());
                    //intent.putExtra("id",  platos.get(i).getId() );
                    startActivity(intent);
                }
            });

        return builder.create();
    }

    public void getPlatos(){

    Call<List<Imagen>> call = RestClient.getInstance().getApiService().getPaises();
    call.enqueue(new Callback<List<Imagen>>() {
        @Override
        public void onResponse(Call<List<Imagen>> call, Response<List<Imagen>> response) {
            platos = response.body();
            ImageAdapter itemsAdapter = new ImageAdapter(MainActivity.this,platos);
            listView.setAdapter(itemsAdapter);

        }

        @Override
        public void onFailure(Call<List<Imagen>> call, Throwable t) {

        }
    });

    }


}
