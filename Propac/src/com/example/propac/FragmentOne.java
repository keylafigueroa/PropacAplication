package com.example.propac;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
*/
import com.example.propac.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentOne extends Fragment{
	 
	ImageView ivIcon;
	TextView tvItemName;
	private ProgressDialog pDialog;
	ListView lista;
	//private	PullToRefreshLayout mPullToRefreshLayout;


	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
	
	public static String urlJson = "http://localhost:8000/api/consultarNoticias/";
	
	public static final String TAG_LIST = "lista";
	public static final String TAG_DESCRIPCION = "descripcion";
	public static final String TAG_TITULO = "titulo";
	public static final String TAG_FECHA = "fecha_publicacion";
	public static final String TAG_ID = "id";
	public static final String TAG_AUTOR = "autor";
	
	JSONArray noticias = null;
	
	ArrayList<HashMap<String, String>> noticiasList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_1, container,
				false);

		noticiasList = new ArrayList<HashMap<String, String>> ();
	
		ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
		tvItemName = (TextView) view.findViewById(R.id.frag1_text);

		tvItemName.setText(getArguments().getString(ITEM_NAME));
		ivIcon.setImageDrawable(view.getResources().getDrawable(
				getArguments().getInt(IMAGE_RESOURCE_ID)));
		
		
	/*	  mPullToRefreshLayout = new PullToRefreshLayout(container.getContext());
		  ActionBarPullToRefresh.from(getActivity())
		    .insertLayoutInto(container)
		    .listener(this)
		    .allChildrenArePullable()
		    .setup(mPullToRefreshLayout);*/   
		  
		lista = (ListView) view.findViewById(R.id.list);

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Fragment fragment = new FragmentNoticia();
				Bundle args = new Bundle();
				// getting values from selected ListItem
				String titulo="", autor="", fecha="", descripcion="";
				
				for(int i =0; i<noticiasList.size();i++){
					Log.d("Response: ", "id de las noticias "+  ((TextView) view.findViewById(R.id.idNoticia)).getText().toString());
					if(id == i){
						Log.d("Response: ", "id de la noticia "+  i);
						titulo = noticiasList.get(i).get(TAG_TITULO);
						autor = noticiasList.get(i).get(TAG_AUTOR);
						fecha = noticiasList.get(i).get(TAG_FECHA);
						descripcion = noticiasList.get(i).get(TAG_DESCRIPCION);
					}
				}
			
				/* Starting single Noticia Fragment
				args.putString(FragmentNoticia.TAG_TITULO,titulo);
				Log.d("Response: ", "aqui envia el dato titulo " );

				args.putString(FragmentNoticia.TAG_AUTOR, autor);
				args.putString(FragmentNoticia.TAG_DESCRIPCION, descripcion);
				args.putString(FragmentNoticia.TAG_FECHA, fecha);
				
				fragment.setArguments(args);
				FragmentManager frgManager = getFragmentManager();
				frgManager.beginTransaction().replace(R.id.content_frame, fragment)
						.commit();*/

				// Starting noticia activity
				Intent in = new Intent(getActivity(),
						ActivityNoticia.class);
				in.putExtra(TAG_TITULO, titulo);
				in.putExtra(TAG_DESCRIPCION, descripcion);
				in.putExtra(TAG_AUTOR, autor);
				in.putExtra(TAG_FECHA, fecha);
				startActivity(in);

			}
		});
		
//		new GetNoticias().execute();
		bindListView();
		return view;
}	
	
	public void bindListView(){
		new GetNoticias(getActivity(), lista).execute();
	}
	

/*	@Override
	public void onRefreshStarted(View view) {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... arg0) {
				try {
	                Thread.sleep(5000); // 5 seconds 

	                Log.d("Response: ", ">POR AQUI DEBE HACER PULL REFRESH " );
					// Creating service handler class instance
					ServiceHandler sh = new ServiceHandler();
			
					// Making a request to url and getting response
					
					String jsonStr = sh.makeServiceCall(urlJson, ServiceHandler.GET);
					
					
					Log.d("Response: ", "> " + jsonStr);
			
					if (jsonStr != null) {
						try {
							JSONObject jsonObj = new JSONObject(jsonStr);
							// Getting JSON Array node
							noticias = jsonObj.getJSONArray(TAG_LIST);
							Log.d("Response: ", "aqui empiezA EL JSONOBJ " );
		
							// looping through All Contacts
							for (int i = 0; i < noticias.length(); i++) {
								JSONObject c = noticias.getJSONObject(i);
								
								// Storing  JSON item in a Variable
								String id = c.getString(TAG_ID);
								String name = c.getString(TAG_AUTOR);
								String titulo = c.getString(TAG_TITULO);
								String descripcion = c.getString(TAG_DESCRIPCION);
								String fecha = c.getString(TAG_FECHA);
			
								// tmp hashmap for single contact
								HashMap<String, String> noticia = new HashMap<String, String>();
			
								// adding each child node to HashMap key => value
								noticia.put(TAG_DESCRIPCION, descripcion);
								noticia.put(TAG_TITULO, titulo);
								noticia.put(TAG_FECHA, fecha);
								noticia.put(TAG_ID, id);
								noticia.put(TAG_AUTOR, name);
		
								// adding contact to contact list
								noticiasList.add(noticia);
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					} else {
						Log.e("ServiceHandler", "Couldn't get any data from the url");
					}
				 } catch (InterruptedException e) {
	                 e.printStackTrace();
	             }
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				// Dismiss the progress dialog
				if (pDialog.isShowing())
					pDialog.dismiss();
				/**
				 * Updating parsed JSON data into ListView
				 * */
/*				ListAdapter adapter = new SimpleAdapter(
						getActivity(), noticiasList,
						R.layout.item_noticia, new String[] { TAG_TITULO, TAG_AUTOR,
								TAG_DESCRIPCION }, new int[] { R.id.titulo,
								R.id.autor, R.id.descripcion });
				lista.setAdapter(adapter);
				 
	            mPullToRefreshLayout.setRefreshComplete();
				
			}
	
			
		}.execute();
	}*/
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	class GetNoticias extends AsyncTask<Void, Void, Void> {
		
		ListView listView;
		Activity context;
		
		public GetNoticias(Activity contexto, ListView listita){
			this.context = contexto;
			this.listView = listita;
		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
            
				// Creating service handler class instance
				ServiceHandler sh = new ServiceHandler();
		
				// Making a request to url and getting response
				Log.d("Response: ", ">POR AQUI DEBE HACER ALGO " );
				String jsonStr = sh.makeServiceCall(urlJson, ServiceHandler.GET);
				
				
				Log.d("Response: ", "> " + jsonStr);
		
				if (jsonStr != null) {
					try {
						JSONObject jsonObj = new JSONObject(jsonStr);
						// Getting JSON Array node
						noticias = jsonObj.getJSONArray(TAG_LIST);
						Log.d("Response: ", "aqui empiezA EL JSONOBJ " );
	
						// looping through All Contacts
						for (int i = 0; i < noticias.length(); i++) {
							JSONObject c = noticias.getJSONObject(i);
							
							// Storing  JSON item in a Variable
							String id = c.getString(TAG_ID);
							String name = c.getString(TAG_AUTOR);
							String titulo = c.getString(TAG_TITULO);
							String descripcion = c.getString(TAG_DESCRIPCION);
							String fecha = c.getString(TAG_FECHA);
		
							// tmp hashmap for single contact
							HashMap<String, String> noticia = new HashMap<String, String>();
		
							// adding each child node to HashMap key => value
							noticia.put(TAG_DESCRIPCION, descripcion);
							noticia.put(TAG_TITULO, titulo);
							noticia.put(TAG_FECHA, fecha);
							noticia.put(TAG_ID, id);
							noticia.put(TAG_AUTOR, name);
	
							// adding contact to contact list
							noticiasList.add(noticia);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Log.e("ServiceHandler", "Couldn't get any data from the url");
				}
				return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			ListAdapter adapter = new SimpleAdapter(
					getActivity(), noticiasList,
					R.layout.item_noticia, new String[] { TAG_ID, TAG_TITULO, TAG_AUTOR,
							TAG_DESCRIPCION }, new int[] { R.id.idNoticia, R.id.titulo,
							R.id.autor, R.id.descripcion });
			lista.setAdapter(adapter);
		
			
		}
	}
}


