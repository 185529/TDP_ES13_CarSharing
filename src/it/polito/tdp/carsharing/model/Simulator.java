package it.polito.tdp.carsharing.model;

import java.util.PriorityQueue;

import it.polito.tdp.carsharing.model.Event.EventType;

public class Simulator {
	
	// parametri di simulazione --- valori impostabili all'inizio della simulazione --- sì get/set
	
	private int NC;		// numero iniziali di auto
	
	private int TRAVEL_MIN_TIME = 60;
	
	private int TRAVEL_MAX_NUM = 3;		// max di TRAVEL_MIN_TIME accettabile
	
	// stato del modello --- no get/set
	
	private int autoPresenti;
	
	// parametri di interesse --- solo get, no set
	
	private int clientiTot = 0;
	
	private int clientiInsoddisfatti = 0;
	
	// lista degli eventi --- no get/set
	
	PriorityQueue<Event> queue;

	/**
	 * @param nC
	 */
	public Simulator(int nC) {
		super();
		NC = nC;
		this.autoPresenti = this.NC;
		this.queue = new PriorityQueue<Event>();
	}

	/**
	 * @return the nC
	 */
	public int getNC() {
		return NC;
	}

	/**
	 * @param nC the nC to set
	 */
	public void setNC(int nC) {
		NC = nC;
		this.autoPresenti = this.NC;
	}

	/**
	 * @return the tRAVEL_MIN_TIME
	 */
	public int getTRAVEL_MIN_TIME() {
		return TRAVEL_MIN_TIME;
	}

	/**
	 * @param tRAVEL_MIN_TIME the tRAVEL_MIN_TIME to set
	 */
	public void setTRAVEL_MIN_TIME(int tRAVEL_MIN_TIME) {
		TRAVEL_MIN_TIME = tRAVEL_MIN_TIME;
	}

	/**
	 * @return the tRAVEL_MAX_NUM
	 */
	public int getTRAVEL_MAX_NUM() {
		return TRAVEL_MAX_NUM;
	}

	/**
	 * @param tRAVEL_MAX_NUM the tRAVEL_MAX_NUM to set
	 */
	public void setTRAVEL_MAX_NUM(int tRAVEL_MAX_NUM) {
		TRAVEL_MAX_NUM = tRAVEL_MAX_NUM;
	}

	/**
	 * @return the clientiTot
	 */
	public int getClientiTot() {
		return clientiTot;
	}

	/**
	 * @return the clientiInsoddisfatti
	 */
	public int getClientiInsoddisfatti() {
		return clientiInsoddisfatti;
	}
	
	// caricamento iniziale lista eventi --- solo arrivi clienti, non restituzioni
	
	/**
	 * Carica clienti previsti in arrivo
	 * @param time
	 */
	public void addCliente(int time){
		queue.add(new Event(time, EventType.NUOVO_CLIENTE));
	}

	/**
	 * Avvia la simulazione --- cicla tutti gli eventi
	 */
	public void run(){
		
		while(!queue.isEmpty()){
			
			Event e = queue.poll();
			
			switch(e.getType()){
			
			case NUOVO_CLIENTE:
				
				if(autoPresenti==0){
					
					this.clientiTot++;
					this.clientiInsoddisfatti++;
					
					System.out.format("Al tempo %d c'è stato un cliente insoddisfatto.\n", e.getTime());
					
				}else{
					
					this.clientiTot++;
					this.autoPresenti--;
					
					// schedulo evento di rientro
					
					int durata = this.TRAVEL_MIN_TIME * (int)(Math.random()*this.TRAVEL_MAX_NUM+1);
					
					queue.add(new Event(e.getTime()+durata, EventType.AUTO_RESTITUITA));
					
					System.out.format("Al tempo %d c'è stato un prestito. Rientro previsto al tempo %d. Auto rimanenti %d.\n", e.getTime(), e.getTime()+durata, this.autoPresenti);
					
				}
				
				break;
				
			case AUTO_RESTITUITA:
				
				this.autoPresenti++;
				
				System.out.format("Al tempo %d c'è stata una restituzione. Auto rimanenti %d.\n", e.getTime(), this.autoPresenti);
				
				break;
			
			}
			
		}
		
	}
	
}
