package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private ExtFlightDelaysDAO dao;
	private DefaultDirectedWeightedGraph<String,DefaultWeightedEdge> grafo;
	private List<String> vertici;
	private List<Arco> archi;
	
	private Simulator sim;

	
	public Model() {
		dao = new ExtFlightDelaysDAO();
	}
	
	public void crearGrafo() {
		
		this.vertici = new ArrayList<>(this.dao.loadAllStates());
		this.grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.vertici);
		
		this.archi = new ArrayList<>(this.dao.loadAllArchi());
		
		for(Arco a: this.archi) {
			Graphs.addEdge(this.grafo, a.getS1(), a.getS2(), a.getPeso());
		}
		
		
	}
	
	
	public int getNumeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumeroArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<String> getVertici() {
		return this.vertici;
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
	
	
	public Map<String,Integer> simulazione(int T, int G, String partenza){
		
		this.sim = new Simulator();
		this.sim.run(T, G, this.vertici, partenza , this.grafo);
		
		return sim.getMappa();
		
	}

}
