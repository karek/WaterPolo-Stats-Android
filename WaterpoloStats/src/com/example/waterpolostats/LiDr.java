package com.example.waterpolostats;

public class LiDr {
	  private long id;
	  private String nazwa;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getNazwa() {
	    return nazwa;
	  }

	  public void setNazwa(String comment) {
	    this.nazwa = comment;
	  }

	  @Override
	  public String toString() {
	    return nazwa;
	  }
}
