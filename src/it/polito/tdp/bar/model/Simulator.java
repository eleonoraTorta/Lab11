package it.polito.tdp.bar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import it.polito.tdp.bar.model.Event.EventType;
import it.polito.tdp.bar.model.Tavolo.statoTavolo;

public class Simulator {
	
	// Simulation parameters
	private List <Tavolo> tavoli = new ArrayList <Tavolo>();
	
	// World model
//	private List <Tavolo> tavoliLiberi;
	
	// Measures of Interest
	private int numTotClienti;
	private int clientiInsoddisfatti;
	private int clientiSoddisfatti;
	
	// Event queue
	PriorityQueue <Event> queue;
	
	public Simulator() {
		this.queue = new PriorityQueue<>();
		numTotClienti =0;
		clientiInsoddisfatti =0;
		clientiSoddisfatti =0;
	}
	
	public void addTavoli(List <Tavolo> tav){
		this.tavoli = tav;
	}
	
	public void addGruppiClienti(int time,Gruppo gruppo){
		queue.add(new Event (time, EventType.ARRIVO_GRUPPO_CLIENTI, gruppo));
	}
	
	public void run() {
		
		while(!queue.isEmpty()){
			Event e = queue.poll();  
			switch(e.getTipo()){	
			
			case ARRIVO_GRUPPO_CLIENTI:
				
				// seleziono il gruppo
				Gruppo gruppo = e.getGruppo();
				
				// controllo disponibilità tavoli, rispettivamente al gruppo in questione
					
				if( this.checkTavoli(gruppo) != null){
					// trovato un tavolo disponibile
					Tavolo t = this.checkTavoli(gruppo);
					t.setStato(statoTavolo.OCCUPATO);
					gruppo.setTavolo(t);
				
					// verrà generato in futuro un evento di uscita
					queue.add(new Event (e.getTime() + gruppo.getDurata() , EventType.USCITA_GRUPPO_CLIENTI, gruppo)); 
					
				} else{
					// nessun tavolo disponile
					// valuto se il gruppo è disposto ad andare al bancone
					
					if( this.generaRandom(gruppo.getTolleranza()) == true){
						// ha accettato --> li mando al bancone
						// è soddisfatto
						int soddisfatti = gruppo.getNumPersone();
						clientiSoddisfatti = clientiSoddisfatti + soddisfatti;
						numTotClienti = numTotClienti + soddisfatti;
					}
					else if (this.generaRandom(gruppo.getTolleranza()) == false){
						// non accetta
						// non è soddisfatto
						int insoddisfatti = gruppo.getNumPersone();
						clientiInsoddisfatti = clientiInsoddisfatti +insoddisfatti;
						numTotClienti = numTotClienti + insoddisfatti;
					}
				}
				break;
				
			case USCITA_GRUPPO_CLIENTI:
				// seleziono il gruppo
				Gruppo g = e.getGruppo();
				// individuo il tavolo
				Tavolo tav = g.getTavolo();
				// e lo libero
				tav.setStato(statoTavolo.LIBERO);
				// i clienti sono soddisfatti
				int soddisfatti = g.getNumPersone();
				clientiSoddisfatti = clientiSoddisfatti + soddisfatti;
				numTotClienti = numTotClienti + soddisfatti;
			
				break;
			}
		}
		
	}
	
	private Tavolo checkTavoli(Gruppo g){
		List <Tavolo> possibili = new ArrayList <Tavolo>();
		int posti = g.getNumPersone();
		for( Tavolo t : tavoli){
			if( t.getNumPosti() >= posti && t.getStato()== statoTavolo.LIBERO){
				possibili.add(t);
			}
		}
		double min = 1.0; 
		Tavolo best = null;
		for( Tavolo t : possibili){
			double percLibero = (t.getNumPosti() - posti) / t.getNumPosti();
			if(percLibero < 0.5 && percLibero <min){
				min = percLibero;
				best = t;
			}
		}
		return best;
		
		
	}
	
	private boolean generaRandom(float tolleranza){
		float random = (float) Math.random();
		if( random <= tolleranza  && tolleranza != 0){			
			return true;
		} else
			return false;
	}

	public int getNumTotClienti() {
		return numTotClienti;
	}

	public int getClientiInsoddisfatti() {
		return clientiInsoddisfatti;
	}

	public int getClientiSoddisfatti() {
		return clientiSoddisfatti;
	}
	
	

}
