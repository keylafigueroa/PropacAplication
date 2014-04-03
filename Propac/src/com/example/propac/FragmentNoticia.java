package com.example.propac;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FragmentNoticia extends Fragment {
		 
	ImageView ivIcon;
	TextView tvItemName;
	TextView tvTitulo;
	TextView tvDescripcion;
	TextView tvFecha;
	TextView tvAutor;
	
	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
		
	public static final String TAG_DESCRIPCION = "descripcion";
	public static final String TAG_TITULO = "titulo";
	public static final String TAG_FECHA = "fecha_publicacion";
	public static final String TAG_ID = "id";
	public static final String TAG_AUTOR = "autor";
		
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.noticia, container,false);
		
			tvTitulo = (TextView) view.findViewById(R.id.label_titulo);
			tvDescripcion = (TextView) view.findViewById(R.id.label_descripcion);
			tvFecha = (TextView) view.findViewById(R.id.label_fecha);
			tvAutor = (TextView) view.findViewById(R.id.label_autor);
			
			Log.d("Response: ", "Titulo en el fragment de noticia " + getArguments().getString(TAG_TITULO) );
			Log.d("Response: ", "Descripcion en el fragment de noticia " + getArguments().getString(TAG_DESCRIPCION ));
			Log.d("Response: ", "fecha en el fragment de noticia " + getArguments().getString(TAG_FECHA));
			Log.d("Response: ", "autor en el fragment de noticia " + getArguments().getString(TAG_AUTOR));

			tvTitulo.setText(getArguments().getString(TAG_TITULO));
			tvDescripcion.setText(getArguments().getString(TAG_DESCRIPCION));
			tvFecha.setText(getArguments().getString(TAG_FECHA));
			tvAutor.setText(getArguments().getString(TAG_AUTOR));
			
			return view;

		}	
}
