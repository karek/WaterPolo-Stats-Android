package com.example.waterpolostats;

public class Zawodnik {
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public long getDr_id() {
		return dr_id;
	}
	public void setDr_id(long dr_id) {
		this.dr_id = dr_id;
	}
	public long getEd_it() {
		return ed_it;
	}
	public void setEd_it(long ed_it) {
		this.ed_it = ed_it;
	}
	private long id;
	private String imie;
	private String nazwisko;
	private long dr_id;
	private long ed_it;
}
