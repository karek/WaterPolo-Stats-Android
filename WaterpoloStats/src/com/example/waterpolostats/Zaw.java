package com.example.waterpolostats;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class Zaw extends Activity {

	private Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zaw);
		// Show the Up button in the action bar.
		setupActionBar();
		addItemsOnSpinner();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	private void addItemsOnSpinner() {
		spinner = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		
		List<LiDr> l = MainActivity.dbAdapter.getAllDr();
		for(LiDr k: l) list.add(k.getNazwa());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}
	
	public void zawDod(View view) {
		EditText text = (EditText) findViewById(R.id.editText1);
		EditText text2 = (EditText) findViewById(R.id.editText2);
		
		String imie = text.getText().toString();
		String nazw = text2.getText().toString();
		
		String klub = String.valueOf(spinner.getSelectedItem());
		
		MainActivity.dbAdapter.createZawodnik(imie,nazw,klub,0);
		addItemsOnSpinner();
	}

	public void zawUsun(View view) {
		EditText text = (EditText) findViewById(R.id.editText1);
		EditText text2 = (EditText) findViewById(R.id.editText2);
		
		String imie = text.getText().toString();
		String nazw = text2.getText().toString();
		
		MainActivity.dbAdapter.deleteZaw(imie,nazw);
		addItemsOnSpinner();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zaw, menu);
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
