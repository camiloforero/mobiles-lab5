package uniandes.guiamoviles.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import uniandes.guiamoviles.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EdadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EdadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EdadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EdadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EdadFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int edad=18;
    private TextView propinaText;
    private final static String TAG="Propina TAG";


    private OnFragmentInteractionListener mListener;

    public EdadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PropinaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EdadFragment newInstance(String param1, String param2) {
        EdadFragment fragment = new EdadFragment();

        /*
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG,"On create view fragment");
        return inflater.inflate(R.layout.fragment_edad, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"On view created fragment");
        propinaText= (TextView) view.findViewById(R.id.propina_text);
        Button menos= (Button) view.findViewById(R.id.menos);

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edad >18){
                    edad -=1;
                    propinaText.setText(edad+" años");
                }else{
                    Snackbar.make(propinaText,"No puedes ser menor de edad",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        Button mas= (Button) view.findViewById(R.id.mas);
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edad +=1;
                propinaText.setText(edad+" años" );
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"On attach fragment");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"On detach fragment");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
