package uniandes.guiamoviles;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uniandes.guiamoviles.adapters.ImageAdapter;
import uniandes.guiamoviles.entities.Imagen;
import uniandes.guiamoviles.rest.RestClient;

public class GustosActivity extends AppCompatActivity {

    ListView listView;
    List<Imagen> gustos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gustos);
        listView= (ListView) findViewById(R.id.gustos_listview);

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Imagen gusto=gustos.get(i);
                gusto.setSelected(!gusto.isSelected());
                if(gusto.isSelected()){
                    view.setAlpha(1f);
                }else{
                    view.setAlpha(0.5f);
                }
            }
        });



        getGustos();



    }

    public void getGustos(){

        Call<List<Imagen>> call = RestClient.getInstance().getApiService().getIntereses();
        call.enqueue(new Callback<List<Imagen>>() {
            @Override
            public void onResponse(Call<List<Imagen>> call, Response<List<Imagen>> response) {
                gustos = response.body();
                ImageAdapter itemsAdapter = new ImageAdapter(GustosActivity.this,gustos);
                listView.setAdapter(itemsAdapter);

            }

            @Override
            public void onFailure(Call<List<Imagen>> call, Throwable t) {

            }
        });

    }

}
