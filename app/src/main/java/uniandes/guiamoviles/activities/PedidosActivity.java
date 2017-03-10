package uniandes.guiamoviles.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uniandes.guiamoviles.R;
import uniandes.guiamoviles.entities.Pedido;
import uniandes.guiamoviles.entities.ResponseMessage;
import uniandes.guiamoviles.rest.RestClient;

public class PedidosActivity extends AppCompatActivity {

    private String platoName;
    private int platoId;
    CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        coordinatorLayout= (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent()!=null&&getIntent().getExtras().getString("name")!= null){
            platoName=getIntent().getExtras().getString("name");
            platoId=getIntent().getExtras().getInt("id");
        }

        getSupportActionBar().setTitle(platoName);

        Button btnComment= (Button) findViewById(R.id.pedir_btn);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarPedido();
            }
        });


    }

    public void realizarPedido(){
        String cliente= ((TextView)findViewById(R.id.nombre_cliente)).getText().toString();
        String lugar= ((TextView)findViewById(R.id.lugar_pedido)).getText().toString();
        Pedido pedido= new Pedido(cliente,lugar,platoId);

        Call<ResponseMessage> call= RestClient.getInstance().getApiService().saveIntereses(pedido);
        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                ResponseMessage res=response.body();
                Snackbar snackbar=Snackbar.make(coordinatorLayout,res.getMsg(),Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {

            }
        });
    }

}
