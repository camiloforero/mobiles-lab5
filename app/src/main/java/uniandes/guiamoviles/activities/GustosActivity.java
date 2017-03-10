package uniandes.guiamoviles.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uniandes.guiamoviles.R;
import uniandes.guiamoviles.adapters.ImageAdapter;
import uniandes.guiamoviles.entities.Pais;
import uniandes.guiamoviles.entities.Pedido;
import uniandes.guiamoviles.rest.RestClient;

public class GustosActivity extends AppCompatActivity {

    ListView listGustosView;
    List<Pais> gustos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gustos);
        listGustosView = (ListView) findViewById(R.id.gustos_listview);
        listGustosView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Pais> seleccionados = new ArrayList<Pais>();
                int len = listGustosView.getCount();
                SparseBooleanArray checked = listGustosView.getCheckedItemPositions();
                for (int i = 0; i < len; i++)
                    if (checked.get(i)) {
                        Pais paisSeleccionado = gustos.get(i);
                        seleccionados.add(paisSeleccionado);
  /* do whatever you want with the checked item */
                    }
                Intent intent = new Intent().putExtra("gustosSeleccionados", seleccionados);
                //intent.putExtra("num_paises", checked.size()+"");
                setResult(RESULT_OK, intent);
                finish();



            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listGustosView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Pais gusto=gustos.get(i);
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

    public void saveGustos(Pedido gustos, final View v){
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
        /*
        Call<ResponseMessage> call= RestClient.getInstance().getApiService().saveIntereses(gustos);
        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                ResponseMessage res=response.body();
                Snackbar.make(v, "Se guardaron tus preferencias", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {

            }
        });

        */
    }

    public void getGustos(){

        Call<List<Pais>> call = RestClient.getInstance().getApiService().getIntereses();
        call.enqueue(new Callback<List<Pais>>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                gustos = response.body();
                ImageAdapter itemsAdapter = new ImageAdapter(GustosActivity.this,gustos);
                listGustosView.setAdapter(itemsAdapter);

            }

            @Override
            public void onFailure(Call<List<Pais>> call, Throwable t) {

            }
        });

    }

}
