package it.polito.tdp.bar.model;


public class Event implements Comparable<Event>  {
	
	public enum EventType{ ARRIVO_GRUPPO_CLIENTI, USCITA_GRUPPO_CLIENTI};
	
	private int time;
	private EventType tipo;
	private Gruppo gruppo;
	
	public Event(int time, EventType tipo, Gruppo gruppo) {
		super();
		this.time = time;
		this.tipo = tipo;
		this.gruppo = gruppo;

	}
	
	public int getTime() {
		return time;
	}
	
	public EventType getTipo() {
		return tipo;
	}

	public Gruppo getGruppo() {
		return gruppo;
	}

	
	
	
	public void setTime(int time) {
		this.time = time;
	}
	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}
	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Event other) {
		return this.time - other.time;
	}
	




	
	

}
