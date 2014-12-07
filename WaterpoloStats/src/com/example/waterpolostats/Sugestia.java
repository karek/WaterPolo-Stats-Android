package com.example.waterpolostats;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Sugestia extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sugestia);
	}
	
	public void gener(View view) {
		String s[] = new String [9];
		
		for(int poz=0; poz<7; poz++)
		{
			Zawodnik zaw = MainActivity.dbAdapter.wybierzMaks(poz);
			s[poz] = zaw.getImie() +" " + zaw.getNazwisko();
		}
		
	    TextView t = new TextView(this); 
	    t = (TextView)findViewById(R.id.textView2);
	    t.setText(s[0]);
	    t = (TextView)findViewById(R.id.textView3);
	    t.setText(s[1]);
	    t = (TextView)findViewById(R.id.textView4);
	    t.setText(s[2]);
	    t = (TextView)findViewById(R.id.textView5);
	    t.setText(s[3]);
	    t = (TextView)findViewById(R.id.textView6);
	    t.setText(s[4]);
	    t = (TextView)findViewById(R.id.textView7);
	    t.setText(s[5]);   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sugestia, menu);
		return true;
	}

}
