package it.polito.tdp.bar.model;

public class Gruppo {
	
	private int numPersone;
	private int durata;
	private float tolleranza;
	private Tavolo tavolo;

	

	public Gruppo(int numPersone, int durata, float tolleranza) {
		super();
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.tavolo  = null;

	}
	
	public int getNumPersone() {
		return numPersone;
	}

	public int getDurata() {
		return durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}
	
	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	
	

}
