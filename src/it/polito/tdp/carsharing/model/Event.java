package it.polito.tdp.carsharing.model;

public class Event implements Comparable<Event> {
	
	public enum EventType { NUOVO_CLIENTE, AUTO_RESTITUITA };
	
	private int time;
	private EventType type;
	
	/**
	 * @param time
	 * @param tyoe
	 */
	public Event(int time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}
	
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}
	
	/**
	 * @param tyoe the type to set
	 */
	public void setTyoe(EventType type) {
		this.type = type;
	}
	
	@Override
	public int compareTo(Event other) {
		
		return this.getTime() - other.getTime();
		
	}

}
