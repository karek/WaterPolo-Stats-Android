package com.example.waterpolostats;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Liga extends Activity {

	private Spinner spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liga);
		
		addItemsOnSpinner();
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	private void addItemsOnSpinner() {
		spinner = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		
		List<LiDr> l = MainActivity.dbAdapter.getAllLiga();
		for(LiDr k: l) list.add(k.getNazwa());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liga, menu);
		return true;
	}
	
	public void ligaSubmit(View view) {
		EditText text = (EditText) findViewById(R.id.editText1);
		String message = text.getText().toString();
		
		long id = MainActivity.dbAdapter.createLiga(message);
		System.out.println(message +" " +id);
		addItemsOnSpinner();
	}

	public void ligaUsun(View view) {
		EditText text = (EditText) findViewById(R.id.editText1);
		String message = text.getText().toString();
		System.out.println(message);
		MainActivity.dbAdapter.deleteLiga(message);
		addItemsOnSpinner();
	}
	
	public void edLigiSubmit(View view) {
		/*EditText text = (EditText) findViewById(R.id.editText2);
		EditText text2 =  (EditText) findViewById(R.id.editText3);
		
		String kat = text.getText().toString();
		String rok = text2.getText().toString();
		long r = 0, k = 0;
		try {
	         k = Long.parseLong(kat);
	         r = Long.parseLong(rok);
	 		
	 		 String nazwa= String.valueOf(spinner.getSelectedItem());
	 		//long id =  MainActivity.dbAdapter.getLigaId(nazwa);
	 		
	         System.out.println(r+ " "+ k+ " " +nazwa +" ");
	         
	 		 MainActivity.dbAdapter.createEdycja(r,k,nazwa);
	 		
	      } catch (NumberFormatException nfe) {
	         System.out.println("NumberFormatException: " + nfe.getMessage());
	      }	*/
	}

	public void edLigiUsun(View view) {
		/*EditText text = (EditText) findViewById(R.id.editText2);
		EditText text2 =  (EditText) findViewById(R.id.editText3);
		
		String kat = text.getText().toString();
		String rok = text2.getText().toString();
		long r = 0, k = 0;
		try {
	         k = Long.parseLong(kat);
	         r = Long.parseLong(rok);
	     	 String nazwa= String.valueOf(spinner.getSelectedItem());
			System.out.println(r +" "+ k + " "+nazwa);
			long id_ligi = MainActivity.dbAdapter.getLigaId(nazwa);
			
			if(id_ligi == -1) return;
			
			Edycja ed = MainActivity.dbAdapter.getEdycja(
					MainActivity.dbAdapter.getEdycjaId(r,k,id_ligi));
			MainActivity.dbAdapter.deleteEdycja(ed);
			
	      } catch (NumberFormatException nfe) {
	         System.out.println("NumberFormatException: " + nfe.getMessage());
	      }
		
	*/
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
