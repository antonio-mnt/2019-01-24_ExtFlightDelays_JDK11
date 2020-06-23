package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Velivoli;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<String> cmbBoxStati;

    @FXML
    private Button btnVisualizzaVelivoli;

    @FXML
    private TextField txtT;

    @FXML
    private TextField txtG;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.model.crearGrafo();
    	
    	this.txtResult.clear();
    	this.txtResult.appendText("Grafo creato!\nVertici: "+this.model.getNumeroVertici()+" Archi: "+this.model.getNumeroArchi()+"\n");
    	
    	this.cmbBoxStati.getItems().addAll(this.model.getVertici());

    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	int T = 0;
    	int G = 0;
    	
    	try {
    		T = Integer.parseInt(this.txtT.getText());
    	}catch(NumberFormatException ne) {
    		this.txtResult.setText("Inserire un numero di Turisti");
    		return;
    	}
    	
    	try {
    		G = Integer.parseInt(this.txtG.getText());
    	}catch(NumberFormatException ne) {
    		this.txtResult.setText("Inserire un numero di giorni");
    		return;
    	}
    	
    	String stato = this.cmbBoxStati.getValue();
    	
    	if(stato==null) {
    		this.txtResult.setText("Selezionare uno Stato");
    		return;
    	}
    	
    	Map<String,Integer> map = new TreeMap<>(this.model.simulazione(T, G, stato));
    	
    	this.txtResult.clear();
    	
    	for(String s: map.keySet()) {
    		this.txtResult.appendText("Stato: "+s+" turisti: "+map.get(s)+"\n");
    	}
    	
    	

    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	
    	
    	String stato = this.cmbBoxStati.getValue();
    	
    	if(stato==null) {
    		this.txtResult.setText("Selezionare uno Stato");
    		return;
    	}
    	
    	List<Velivoli> velivoli = new ArrayList<>(this.model.visualizzaVelivoli(stato));
    	
    	if(velivoli==null) {
    		this.txtResult.setText("Non ci sono velivoli");
    		return;
    	}
    	
    	this.txtResult.clear();
    	for(Velivoli v: velivoli) {
    		this.txtResult.appendText(v.toString()+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
