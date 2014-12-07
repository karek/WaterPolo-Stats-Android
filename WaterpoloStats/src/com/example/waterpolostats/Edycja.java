package com.example.waterpolostats;

public class Edycja {
	  private long id;
	  private long rok;
	  private long kategoria;
	  private long liga_id;
	
	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	
	public long getKategoria() {
		return kategoria;
	}

	public void setKategoria(long kategoria) {
		this.kategoria = kategoria;
	}

	public long getLiga_id() {
		return liga_id;
	}

	public void setLiga_id(long liga_id) {
		this.liga_id = liga_id;
	}

	public long getRok() {
		return rok;
	}

	public void setRok(long rok) {
		this.rok = rok;
	}

}
