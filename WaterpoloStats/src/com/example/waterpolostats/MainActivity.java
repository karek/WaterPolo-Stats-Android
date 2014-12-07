package com.example.waterpolostats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {

	public static DAO dbAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbAdapter = new DAO(this);
		dbAdapter.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void graj(View view) {
	    Intent intent = new Intent(this, Gra.class);
	    startActivity(intent);
	}
	
	public void dod_lige(View view) {
	    Intent intent = new Intent(this, Liga.class);
	    startActivity(intent);
	}
	
	public void dod_zaw(View view) {
	    Intent intent = new Intent(this, Zaw.class);
	    startActivity(intent);
	}

	public void dod_dr(View view) {
	    Intent intent = new Intent(this, Druz.class);
	    startActivity(intent);
	}
	
	public void staty(View view) {
	    Intent intent = new Intent(this, Stats.class);
	    startActivity(intent);
	}
	
	public void sugeruj(View view) {
	    Intent intent = new Intent(this, Sugestia.class);
	    startActivity(intent);
	}
}
