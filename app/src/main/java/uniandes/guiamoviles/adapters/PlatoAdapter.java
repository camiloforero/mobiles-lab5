package uniandes.guiamoviles.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uniandes.guiamoviles.activities.PaisesActivity;
import uniandes.guiamoviles.R;
import uniandes.guiamoviles.entities.Pais;

/**
 * Created by ASUS on 19/02/2017.
 */

public class PlatoAdapter extends BaseAdapter{

    private List<Pais> platos;
    private Context context;
    private static LayoutInflater inflater=null;

    public PlatoAdapter(PaisesActivity paisesActivity, List<Pais> platosList){
        this.platos= platosList;
        this.context= paisesActivity;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return platos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Pais plato= platos.get(i);
        if(view==null)
            view = inflater.inflate(R.layout.platos_item,null);

        TextView nombreTextView = (TextView) view.findViewById(R.id.nombre_plato);
        TextView precioTextView = (TextView) view.findViewById(R.id.precio_plato);
        ImageView imageView = (ImageView) view.findViewById(R.id.imagen_plato);

        nombreTextView.setText(plato.getNombre());
        precioTextView.setText("$"+ plato.getPrecio());

        Picasso.with(context)
                .load(plato.getImagen())
                .resize(50,50)
                .centerCrop()
                .into(imageView);

        return view;


    }
}

