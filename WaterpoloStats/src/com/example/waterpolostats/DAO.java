package com.example.waterpolostats;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DAO {

	  private SQLiteDatabase database;
	  private MySqliteHelper dbHelper;
	  
	  public static String[] ligaCollumns = { "id", "nazwa"};
	  public static String[] edycjaCollumns = { "id", "rok", "katWiekowa", "liga_id"};
	  public static String[] druzynaCollumns = { "id", "nazwa"};
	  public static String[] zawodnikCollumns = {"id", "imie", "nazwisko", "dr_id", "ed_id"};
	  public static String[] meczCollumns = {"id", "id_dr1", "id_dr2"};
	  public static String[] golCollumns = {"id", "poz", "trafiony", "strz_id", "mecz_id"};


	  public DAO(Context context) {
	    dbHelper = new MySqliteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public long createLiga(String nazwa) {
		  ContentValues values = new ContentValues();
		  values.put(ligaCollumns[1], nazwa);
		  long insertId = database.insert(MySqliteHelper.TABLE_LIGA, null,
		        values);
		  
		  return insertId;
	  }

	  public void deleteLiga(String nazwa) {
	    
	    System.out.println("Deleted Liga: " + nazwa);
	    database.delete(MySqliteHelper.TABLE_LIGA, ligaCollumns[1]
	        + " = '" + nazwa + "'", null);
	  }

	  public long getLigaId(String nazwa) {
		  Cursor cursor = database.query(MySqliteHelper.TABLE_LIGA,
			        ligaCollumns, "? = '" + nazwa +"'",
			        new String[] {ligaCollumns[1]}, null, null, null);
		  
		  
		  if(cursor.getCount() < 1)  return -1;
		  
		  LiDr lig = cursorToLiDr(cursor);
		  cursor.close();
		  
		  return lig.getId();
	  }
	  
	  
	  public List<LiDr> getAllLiga() {
	    List<LiDr> ligi = new ArrayList<LiDr>();

	    Cursor cursor = database.query(MySqliteHelper.TABLE_LIGA,
	        ligaCollumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      LiDr akt = cursorToLiDr(cursor);
	      ligi.add(akt);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return ligi;
	  }

	  private LiDr cursorToLiDr(Cursor cursor) {
	    LiDr comment = new LiDr();
	    comment.setId(cursor.getLong(0));
	    comment.setNazwa(cursor.getString(1));
	    return comment;
	  }
	  
	  public long createDruzyna(String nazwa) {
		  ContentValues values = new ContentValues();
		  values.put(druzynaCollumns[1], nazwa);
		  long insertId = database.insert(MySqliteHelper.TABLE_DRUZYNA, null,
		        values);
		  
		  return insertId;
	  }
	  

	  public void deleteDruzyna(String nazwa) {
	   
	    System.out.println("Deleted Druzyna: " + nazwa);
	    database.delete(MySqliteHelper.TABLE_DRUZYNA, druzynaCollumns[1]
	        + " = '" + nazwa + "'", null);
	  }

	  public long getDrId(String nazwa) {
		  Cursor cursor = database.query(MySqliteHelper.TABLE_DRUZYNA,
			        druzynaCollumns, "? ='" + nazwa +"'", new String[] {druzynaCollumns[1]},
			        null, null, null);
		  
		  if(cursor.getCount() < 1) {
			  cursor.close();
			  return -1;
		  }
		  
		  LiDr lig = cursorToLiDr(cursor);
		  cursor.close();
		  
		  return lig.getId();
	  }
	  
	  
	  public List<LiDr> getAllDr() {
	    List<LiDr> dr = new ArrayList<LiDr>();

	    Cursor cursor = database.query(MySqliteHelper.TABLE_DRUZYNA,
	        druzynaCollumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      LiDr akt = cursorToLiDr(cursor);
	      dr.add(akt);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return dr;
	  }

	  public long createEdycja(long rok, long katWiekowa, String nazwaL) {
		  ContentValues values = new ContentValues();
		  long liga_id = getLigaId(nazwaL);
		  
		  values.put(edycjaCollumns[1], rok);
		  values.put(edycjaCollumns[2], katWiekowa);
		  values.put(edycjaCollumns[3], liga_id);
		  
		  long insertId = database.insert(MySqliteHelper.TABLE_EDYCJA, null,
		        values);
		  
		  return insertId;
	  }
	  
	  
	  public void deleteEdycja(Edycja ed) {
	    long id = ed.getId();
	    System.out.println("Deleted Edycja: " + ed.getId());
	    database.delete(MySqliteHelper.TABLE_EDYCJA, edycjaCollumns[0]
	        + " = " + id, null);
	  }
	  
	  public Edycja getEdycja (long id) {
		  Cursor cursor = database.query(MySqliteHelper.TABLE_EDYCJA,
			        edycjaCollumns, "? = " + id,
			         new String[] {edycjaCollumns[0]},
			         null, null, null);
		  
		  Edycja ed = cursorToEdycja(cursor);
		  cursor.close();
		  
		  return ed;	  
	  }

	  public long getEdycjaId(long rok, long katWiekowa, long liga_id) {
		  Cursor cursor = database.query(MySqliteHelper.TABLE_EDYCJA,
			        edycjaCollumns, "? = " + rok + "AND ? = " + katWiekowa 
			        + "AND ? = " +liga_id,
			         new String[] {edycjaCollumns[1], edycjaCollumns[2], edycjaCollumns[3]},
			         null, null, null);
		  
		  if(cursor.isAfterLast()) {
			  cursor.close();
			  return -1;
		  }
		  Edycja ed = cursorToEdycja(cursor);
		  cursor.close();
		  
		  return ed.getId();
	  }
	  
	  
	  public List<Edycja> getAllEd() {
	    List<Edycja> ed = new ArrayList<Edycja>();

	    Cursor cursor = database.query(MySqliteHelper.TABLE_EDYCJA,
	        edycjaCollumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Edycja akt = cursorToEdycja(cursor);
	      ed.add(akt);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return ed;
	  }
	  
	  
	  private Edycja cursorToEdycja(Cursor cursor) {
	    Edycja ed = new Edycja();
	    ed.setId(cursor.getLong(0));
	    ed.setRok(cursor.getLong(1));
	    ed.setKategoria(cursor.getLong(2));
	    ed.setLiga_id(cursor.getLong(3));
	   
	    return ed;
	  }
	  
	  
	  public long createMecz(String nazwa, String nazwa2) {
		  ContentValues values = new ContentValues();
		  
		  long id1 = getDrId(nazwa);
		  long id2 = getDrId(nazwa2);
		  
		  values.put(meczCollumns[1], id1);
		  values.put(meczCollumns[2], id2);
		  
		  long insertId = database.insert(MySqliteHelper.TABLE_MECZ, null,
		        values);
		  
		  return insertId;
	  }

	  public void deleteMecz(Mecz m) {
	    long id = m.getId();
	    System.out.println("Deleted Mecz: " + m.getId());
	    database.delete(MySqliteHelper.TABLE_MECZ, meczCollumns[0]
	        + " = " + id, null);
	  }

	  public long getMeczId(long dr1, long dr2) {
		  Cursor cursor = database.query(MySqliteHelper.TABLE_MECZ,
			        meczCollumns, "? =" + dr1 + "? = " + dr2			        
			        , new String[] {meczCollumns[1], meczCollumns[2]}, null, null, null);
		  
		  if(cursor.isAfterLast()) {
			  cursor.close();
			  return -1;
		  }
		  
		  Mecz ans = cursorToMecz(cursor);
		  cursor.close();
		  
		  return ans.getId();
	  }
	  
	  
	  public List<Mecz> getAllMecz() {
	    List<Mecz> mecze = new ArrayList<Mecz>();

	    Cursor cursor = database.query(MySqliteHelper.TABLE_MECZ,
	        meczCollumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Mecz akt = cursorToMecz(cursor);
	      mecze.add(akt);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return mecze;
	  }

	  private Mecz cursorToMecz(Cursor cursor) {
	    Mecz m = new Mecz();
	    m.setId(cursor.getLong(0));
	    m.setId_dr1(cursor.getLong(1));
	    m.setId_dr2(cursor.getLong(2));
	    return m;
	  }

	  
	  public long createZawodnik(String imie, String nazwisko, String drNazwa, long ed_id ) {
		  ContentValues values = new ContentValues();
		  
		  values.put(zawodnikCollumns[1], imie);
		  values.put(zawodnikCollumns[2], nazwisko);
		  long idDr = getDrId(drNazwa);
		  values.put(zawodnikCollumns[3], idDr);
		  values.put(zawodnikCollumns[4], ed_id);
		  
		  long insertId = database.insert(MySqliteHelper.TABLE_ZAWODNIK, null,
		        values);
		  
		  return insertId;
	  }
	  
	  public Zawodnik wybierzMaks (int poz) {
		  Zawodnik ans;
		  
		  List<Zawodnik> zz = getAllZaw();

		  ans = zz.get(0);
		  int maks = 0;
		  
		  for(Zawodnik z: zz)
		  {
			  int akt = getGoalsPl(z.getId(), poz, 1);
			  System.out.println(akt);
			  if(akt > maks)
				  ans = z;
		  }
		  
		  return ans;		  
	  }
	  
	

	  public void deleteZaw(Zawodnik zaw) {
	    long id = zaw.getId();
	    System.out.println("Deleted zawodnik: " + zaw.toString());
	    database.delete(MySqliteHelper.TABLE_ZAWODNIK, zawodnikCollumns[0]
	        + " = " + id, null);
	  }
	  
	  public void deleteZaw(String imie, String nazw) {
		 
		    System.out.println("Deleted zawodnik: " + imie+" "+nazw);
		    database.delete(MySqliteHelper.TABLE_ZAWODNIK, zawodnikCollumns[1]
		        + " = '" + imie +"' AND " +zawodnikCollumns[2] +" = '" +nazw +"'", null);
	  }

	  public long getZawodnikId(String imie, String nazwisko) {
		  Cursor cursor = database.query(MySqliteHelper.TABLE_ZAWODNIK,
			        zawodnikCollumns, 
			        zawodnikCollumns[1] +" = '" + imie + "' and " + zawodnikCollumns[2]
			        +" = '" + nazwisko+"'",
			        null, null, null, null);
		  
		  System.out.println(zawodnikCollumns[1] +" = '" + imie + "' and " + zawodnikCollumns[2]
			        +" = '" + nazwisko+"'");
		  
		  if(cursor.getCount() < 1) {
			  cursor.close();
			  return -1;
		  }
		  System.out.println("TU");
		  
		  Zawodnik res = cursorTozaw(cursor);
		  cursor.close();
		  
		  return res.getId();
	  }
	  
	  public long getZawodnikId(String imie, String nazwisko, long ed_id) {
		  Cursor cursor = database.query(MySqliteHelper.TABLE_ZAWODNIK,
			        zawodnikCollumns, 
			        "? = '" + imie + "' and ? = '" + nazwisko + "' and ? ="  + ed_id, 
			        new String[] {zawodnikCollumns[1], zawodnikCollumns[2], zawodnikCollumns[4]},
			        null, null, null);
		  
		  if(cursor.getCount() < 1) {
			  cursor.close();
			  return -1;
		  }
		  
		  Zawodnik res = cursorTozaw(cursor);
		  cursor.close();
		  
		  return res.getId();
	  }
	  
	  public List<Zawodnik> getAllZaw() {
	    List<Zawodnik> res = new ArrayList<Zawodnik>();

	    Cursor cursor = database.query(MySqliteHelper.TABLE_ZAWODNIK,
	        zawodnikCollumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Zawodnik akt = cursorTozaw(cursor);
	      res.add(akt);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return res;
	  }

	  private Zawodnik cursorTozaw(Cursor cursor) {
	    Zawodnik zaw = new Zawodnik();
	    
	    zaw.setId(cursor.getLong(0));
	    zaw.setImie(cursor.getString(1));
	    zaw.setNazwisko(cursor.getString(2));
	    zaw.setDr_id(cursor.getLong(3));
	    zaw.setEd_it(cursor.getLong(4));
	    return zaw;
	  }
	  


	  
	  public long createGol(long poz, long traf, long strz_id, long mecz_id) {
		  ContentValues values = new ContentValues();
		  
		  values.put(golCollumns[1], poz);
		  values.put(golCollumns[2], traf);
		  values.put(golCollumns[3], strz_id);
		  values.put(golCollumns[4], mecz_id);
		  
		  
		  System.out.println(values);
		  long insertId = database.insert(MySqliteHelper.TABLE_GOL, null,
		        values);
		  
		  
		  System.out.println(insertId);
		  
		  return insertId;
	  }
	  
	  
	  public int getGoalsPl(long l, int poz, int traf) {
		  
		  List<Gol> res = new ArrayList<Gol>();
		  
		    Cursor cursor = database.query(MySqliteHelper.TABLE_GOL,
		        golCollumns, golCollumns[1] + " = " + poz +" and " + golCollumns[2]
		        + " = " + traf + " and " + golCollumns[3] + " = " + l, null, null, null, null);
		    
		    
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Gol akt = cursorToGol(cursor);
		      res.add(akt);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return res.size();		  
	  }
	  
	  public void deleteGol(Gol g) {
	    long id = g.getId();
	    System.out.println("Deleted Gol: " + g.toString());
	    database.delete(MySqliteHelper.TABLE_GOL, golCollumns[0]
	        + " = " + id, null);
	  }



	  public List<Gol> getAllGol() {
	    List<Gol> res = new ArrayList<Gol>();

	    Cursor cursor = database.query(MySqliteHelper.TABLE_GOL,
	        golCollumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Gol akt = cursorToGol(cursor);
	      res.add(akt);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return res;
	  }

	  private Gol cursorToGol(Cursor cursor) {
	    Gol g = new Gol();
	    
	    g.setId(cursor.getLong(0));
	    g.setPoz(cursor.getLong(1));
	    g.setTrafiony(cursor.getLong(2));
	    g.setStrz_id(cursor.getLong(3));
	    g.setMecz_id(cursor.getLong(4));
	    return g;
	  }

}



