package it.polito.tdp.extflightdelays.model;

public class Velivoli implements Comparable<Velivoli> {
	
	private String stato;
	private int peso;
	
	public Velivoli(String stato, int peso) {
		super();
		this.stato = stato;
		this.peso = peso;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
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
		Velivoli other = (Velivoli) obj;
		if (stato == null) {
			if (other.stato != null)
				return false;
		} else if (!stato.equals(other.stato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Velivolo [stato=" + stato + ", peso=" + peso + "]";
	}

	@Override
	public int compareTo(Velivoli o) {
		return -(this.peso-o.peso);
	}
	
	

}
