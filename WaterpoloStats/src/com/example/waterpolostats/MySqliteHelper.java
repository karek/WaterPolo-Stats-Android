package com.example.waterpolostats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteHelper  extends SQLiteOpenHelper {
	
	 public static final String TABLE_LIGA = "LIGA";
	 public static final String LIGA_ID = "id";
	 public static final String LIGA_NAZWA = "nazwa";
	  
	 public static final String TABLE_EDYCJA = "EDYCJA";  
	 
	 public static final String TABLE_DRUZYNA = "DRUZYNA";
	 
	 public static final String TABLE_ZAWODNIK = "ZAWODNIK";
	 
	 public static final String TABLE_MECZ = "MECZ";
	 
	 public static final String TABLE_GOL = "GOL";
	 
	  private static final String LIGA_CREATE = 
			"CREATE TABLE LIGA ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
			" nazwa TEXT NOT NULL );";
	  
	  private static final String DRUZYNA_CREATE =  
			"CREATE TABLE DRUZYNA ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
			" nazwa  TEXT not NULL );";

	  private static final String ZAWODNIK_CREATE =
			  "CREATE TABLE ZAWODNIK ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			  "	imie TEXT,	nazwisko TEXT not NULL,	dr_id INTEGER not NULL," +
			  "	ed_id INTEGER not NULL," +
			  "	FOREIGN KEY(dr_id) REFERENCES DRUZYNA(id)," +
			  "FOREIGN KEY(ed_id) REFERENCES EDYCJA(id) );";
	  
	  private static final String EDYCJA_CREATE = 
			  "CREATE TABLE EDYCJA (id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+" rok INTEGER NOT NULL, katWiekowa INTEGER not NULL,"
				+" liga_id INTEGER not NULL, FOREIGN KEY(liga_id) REFERENCES LIGA(id));";
	  
	  private static final String MECZ_CREATE = 
			  "CREATE TABLE MECZ ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
			  "	  	id_dr1 INTEGER not NULL," +
			  "	  	id_dr2 INTEGER not NULL," +
			  "	  	FOREIGN KEY(id_dr1) REFERENCES DRUZYNA(id)," +
			  "  	FOREIGN KEY(id_dr2) REFERENCES DRUZYNA(id));";
	  
	  private static final String GOL_CREATE = 
			  "CREATE TABLE GOL( id INTEGER PRIMARY KEY AUTOINCREMENT," +
			  "	  	poz INTEGER," +
			  " trafiony INTEGER," +
 			  "	strz_id INTEGER not NULL," +
			  "  	mecz_id INTEGER not NULL," +
			  " FOREIGN KEY(strz_id) REFERENCES ZAWODNIK(id)," +
			  "  	FOREIGN KEY(mecz_id) REFERENCES MECZ(id));";
	  
	  
	  private static final String DATABASE_NAME = "waterpolo_db";
	  private static final int DATABASE_VERSION = 2;

	  // Database creation sql statement
	/*  private static final String DATABASE_CREATE = LIGA_CREATE + "\n" + EDYCJA_CREATE
			  + "\n" + DRUZYNA_CREATE + "\n" + ZAWODNIK_CREATE + "\n" +MECZ_CREATE 
			  + "\n" + GOL_CREATE;*/

	  public MySqliteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
		  Log.d(MySqliteHelper.class.getName(), "Database creating...");

		  database.execSQL(LIGA_CREATE);
		  database.execSQL(DRUZYNA_CREATE);
		  database.execSQL(EDYCJA_CREATE);
		  database.execSQL(ZAWODNIK_CREATE);
		  database.execSQL(MECZ_CREATE);
		  database.execSQL(GOL_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySqliteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIGA);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_EDYCJA);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUZYNA);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZAWODNIK);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_MECZ);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOL);
	    onCreate(db);
	  }
	  
}
