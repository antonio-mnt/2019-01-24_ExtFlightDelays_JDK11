package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		NUOVO_GIORNO
	}
	
	private EventType type;
	private int giorno;
	private int Turista;
	private String Stato;
	
	public Event(EventType type, int giorno, int turista, String stato) {
		super();
		this.type = type;
		this.giorno = giorno;
		this.Turista = turista;
		this.Stato = stato;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	public int getTurista() {
		return Turista;
	}

	public void setTurista(int turista) {
		Turista = turista;
	}

	public String getStato() {
		return Stato;
	}

	public void setStato(String stato) {
		Stato = stato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Turista;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (Turista != other.Turista)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [type=" + type + ", giorno=" + giorno + ", Turista=" + Turista + ", Stato=" + Stato + "]";
	}

	@Override
	public int compareTo(Event o) {
		return this.giorno-o.giorno;
	}
	
	
	

}
