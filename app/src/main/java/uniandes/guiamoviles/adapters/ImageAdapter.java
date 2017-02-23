package uniandes.guiamoviles.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uniandes.guiamoviles.MainActivity;

import uniandes.guiamoviles.R;
import uniandes.guiamoviles.entities.Imagen;

/**
 * Created by ASUS on 19/02/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private List<Imagen> platos;
    private Context context;
    private static LayoutInflater inflater=null;

    public ImageAdapter(Activity mainActivity, List<Imagen> platosList){
        this.platos= platosList;
        this.context= mainActivity;
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

        Imagen plato= platos.get(i);
        if(view==null)
            view = inflater.inflate(R.layout.pais_item,null);

        //TextView nombreTextView = (TextView) view.findViewById(R.id.nombre_plato);

        ImageView imageView = (ImageView) view.findViewById(R.id.imagen_pais);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);

        //nombreTextView.setText(plato.getNombre());
        //precioTextView.setText("$"+ plato.getPrecio());


        Picasso.with(context)
                .load(plato.getImagen())
                .resize(600,200)
                .centerCrop()
                .into(imageView);

        descripcion.setText(plato.getNombre());
        if(plato.isSelected()){
            view.setAlpha(1f);
        }else{
            view.setAlpha(0.5f);
        }


        return view;


    }
}

