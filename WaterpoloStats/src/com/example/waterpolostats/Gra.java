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
import android.widget.Spinner;

public class Gra extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gra);
		// Show the Up button in the action bar.
		setupActionBar();
		spinPozKol();
		addItemsOnSpinner();
	}

	Spinner spinner;
	
	private void addItemsOnSpinner() {
		spinner = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		
		List<Zawodnik> l = MainActivity.dbAdapter.getAllZaw();
		
		for(Zawodnik k: l) list.add(k.getImie() + " " +k.getNazwisko());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}

	
	Spinner poz,kol;
	public void spinPozKol() {
		
		poz = (Spinner) findViewById(R.id.spinner2);
		kol = (Spinner) findViewById(R.id.spinner3);
		
		List<String> list = new ArrayList<String>();
		
		list.add("Lewe skrzyd³o");
		list.add("Prawe skrzyd³o");
		list.add("Dobijak");
		list.add("Lewe pó³skrzyd³o");
		list.add("Prawe pó³skrzyd³o");
		list.add("Srodek ty³");
		list.add("W³asna po³owa");
	
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		poz.setAdapter(dataAdapter);
		
		List<String> list2 = new ArrayList<String>();
		
		list2.add("Niebieski");
		list2.add("Bia³y");	
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list2);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		kol.setAdapter(dataAdapter2);
	}
	
	int getPos(View view) {
		String curr = String.valueOf(poz.getSelectedItem()); 
		
		if(curr.equals("Lewe skrzyd³o")) return 0;
		if(curr.equals("Dobijak")) return 1;
		if(curr.equals("Prawe skrzyd³o")) return 2;
		if(curr.equals("Lewe pó³skrzyd³o")) return 3;
		if(curr.equals("Srodek ty³")) return 4;
		if(curr.equals("Prawe pó³skrzyd³o")) return 5;
		
		return 6;	
	}
	
	
	public void gol(View view) {
		String curr = String.valueOf(spinner.getSelectedItem()); 
		String[] separated = curr.split(" ");
		
		System.out.println(separated[0] + "\n" +separated[1]);
		int pos = getPos(view);
		long strz_id = MainActivity.dbAdapter.getZawodnikId(separated[0], separated[1]);
		
		MainActivity.dbAdapter.createGol(pos, 1, strz_id, 0);		
	}
	
	
	public void pudlo(View view) {
		String curr = String.valueOf(spinner.getSelectedItem()); 
		String[] separated = curr.split(" ");
		
		int pos = getPos(view);
		long strz_id = MainActivity.dbAdapter.getZawodnikId(separated[0], separated[1]);
		
		MainActivity.dbAdapter.createGol(pos, 0, strz_id, 0);				
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
		getMenuInflater().inflate(R.menu.gra, menu);
		return true;
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
