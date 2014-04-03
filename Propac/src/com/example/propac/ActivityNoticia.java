package com.example.propac;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityNoticia  extends Activity {
		
	ImageView ivIcon;
	TextView tvItemName;
	TextView tvTitulo;
	TextView tvDescripcion;
	TextView tvFecha;
	TextView tvAutor;
	
	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";

	// JSON node keys
	public static final String TAG_DESCRIPCION = "descripcion";
	public static final String TAG_TITULO = "titulo";
	public static final String TAG_FECHA = "fecha_publicacion";
	public static final String TAG_ID = "id";
	public static final String TAG_AUTOR = "autor";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.noticia);
	        
	    // getting intent data
	    Intent in = getIntent();
	    
	    // Get JSON values from previous intent
	    String titulo = in.getStringExtra(TAG_TITULO);
	    String descripcion = in.getStringExtra(TAG_DESCRIPCION);
	    String fecha = in.getStringExtra(TAG_FECHA);
	    String autor = in.getStringExtra(TAG_AUTOR);
	    
	       
	    // Displaying all values on the screen
	    tvTitulo = (TextView) findViewById(R.id.label_titulo);
		tvDescripcion = (TextView) findViewById(R.id.label_descripcion);
		tvFecha = (TextView) findViewById(R.id.label_fecha);
		tvAutor = (TextView) findViewById(R.id.label_autor);
		

		tvTitulo.setText(titulo);
	    tvDescripcion.setText(descripcion);
	    tvFecha.setText(fecha);
	    tvAutor.setText(autor);
	    
	    ActionBar actionBar = getActionBar();

// //       actionBar.setIcon(Drawable);             //Establecer icono
	    actionBar.setTitle("Noticias");        //Establecer titulo
	    actionBar.setDisplayShowHomeEnabled(true);
	    actionBar.setSubtitle(titulo); 
	}
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		  getMenuInflater().inflate(R.menu.menu_noticia, menu);
		 return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		// This method is for menu. This menu items will appear in all
		//activities extends this class. I have use this menus to navigate 
		//between activities. You can change this code as you wish 
		// 
		
		
		  switch (item.getItemId()) {
          case R.id.action_regresar:
              Toast.makeText(this, "Pull to Refresh in ListView", Toast.LENGTH_SHORT).show();
              Log.d("Response: ", "> Llamar al otro fragment " );
              return true;
   
      }
		
		return super.onOptionsItemSelected(item);
	}*/
}


