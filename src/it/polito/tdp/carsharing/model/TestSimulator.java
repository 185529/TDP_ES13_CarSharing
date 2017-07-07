package it.polito.tdp.carsharing.model;

public class TestSimulator {

	public static void main(String[] args) {

		Simulator sim = new Simulator(10);
		
		// un nuovo cliente ogni 10 minuti dalle 8 alle 17
		for(int time = 8*60; time <= 17*60; time += 15){
			
			sim.addCliente(time);
			
		}
		
		// avvio simulazione
		
		sim.run();
		
		System.out.format("Clienti totali: %d\n", sim.getClientiTot());
		System.out.format("Clienti insoddisfatti: %d\n", sim.getClientiInsoddisfatti());
		
	}

}
