package it.polito.tdp.bar.model;

public class Tavolo {
	
	public enum statoTavolo  {LIBERO, OCCUPATO};
	
	private int numPosti;
	private statoTavolo stato;

	
	public Tavolo(int numPosti) {
		super();
		this.numPosti = numPosti;
		this.stato= statoTavolo.LIBERO;
	}

	public int getNumPosti() {
		return numPosti;
	}

	public statoTavolo getStato() {
		return stato;
	}

	public void setStato(statoTavolo stato) {
		this.stato = stato;
	}
	
	

}
