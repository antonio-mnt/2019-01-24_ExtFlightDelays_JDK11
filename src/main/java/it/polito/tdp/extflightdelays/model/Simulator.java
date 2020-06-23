package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Event.EventType;


public class Simulator {
	
	
	private PriorityQueue<Event> queue = new PriorityQueue<>();
	
	private int T = 20;
	private int G = 15;
	private List<String> stati;
	private String partenza;
	
	private DefaultDirectedWeightedGraph<String,DefaultWeightedEdge> grafo;
	
	private Map<String,Integer> mappa;

	public Map<String, Integer> getMappa() {
		return this.mappa;
	}
	
	public void run(int t, int g, List<String> vertici, String p, DefaultDirectedWeightedGraph<String,DefaultWeightedEdge> graf) {
		
		this.T = t;
		this.G = g;
		this.partenza = p;
		this.stati = new ArrayList<>(vertici);
		this.grafo = graf;
		
		this.mappa = new TreeMap<String,Integer>();
		
		for(String v: this.stati) {
			this.mappa.put(v, 0);
		}
		this.mappa.put(this.partenza, this.T);
		
		this.queue.clear();
		for(int i = 0; i<this.T;i++) {
			Event e = new Event(EventType.NUOVO_GIORNO,1,i,this.partenza);
			this.queue.add(e);
		}
		
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			System.out.println(e+"\n");
			processEvent(e);
		}
		
		
		
		
		
	}

	private void processEvent(Event e) {
		
		if(e.getGiorno()<this.G) {
			
			switch(e.getType()) {
			
			case NUOVO_GIORNO:
				
				String statoAttuale = e.getStato();
				List<Velivoli> vicini = new ArrayList<>(visualizzaVelivoli(statoAttuale));
				
				double pesoTotale = 0;
				
				for(Velivoli v: vicini) {
					pesoTotale+=v.getPeso();
				}
				
				String nuovoStato = null;
				
				double prob = Math.random();
				boolean flag = false;
				double  perc = 0;
				for(Velivoli v: vicini) {
					
					perc += (((double) v.getPeso())/pesoTotale);
					
					if(perc>prob && flag==false) {
						nuovoStato = v.getStato();
						flag = true;
					}
					
				}
				
				this.mappa.put(statoAttuale, this.mappa.get(statoAttuale)-1);
				this.mappa.put(nuovoStato, this.mappa.get(nuovoStato)+1);
				
				Event ev = new Event(EventType.NUOVO_GIORNO,e.getGiorno()+1,e.getTurista(),nuovoStato);
				this.queue.add(ev);
				
				
				
				break;
			
			
			}
	
		}
		
	}
	
	
	
	public List<Velivoli> visualizzaVelivoli(String stato){
		
		List<String> verticiUscenti = new ArrayList<>(Graphs.successorListOf(this.grafo, stato));
		List<Velivoli> velivoli = new ArrayList<>();
		
		for(String s: verticiUscenti) {
			Velivoli v = new Velivoli(s,(int) this.grafo.getEdgeWeight(this.grafo.getEdge(stato, s)));
			velivoli.add(v);
			
		}
		Collections.sort(velivoli);
		return velivoli;
	}
	
	

}
