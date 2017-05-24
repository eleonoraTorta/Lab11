/**
 * Sample Skeleton for 'Bar.fxml' Controller Class
 */

package it.polito.tdp.bar;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Gruppo;
import it.polito.tdp.bar.model.Simulator;
import it.polito.tdp.bar.model.Tavolo;
import it.polito.tdp.bar.model.Tavolo.statoTavolo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BarController {
	
	Simulator simulator;
	List <Tavolo> tavoli ;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doSimulazione(ActionEvent event) {
    	
    	simulator = new Simulator();
    	tavoli = new ArrayList <Tavolo>();
    	
    	for( int i =0 ; i <2 ;i ++){
    		Tavolo t = new Tavolo (10);
    		tavoli.add(t);
    	}
    	for( int i =0 ; i <4 ;i ++){
    		Tavolo t = new Tavolo (8);	
    		tavoli.add(t);
    	}
    	for( int i =0 ; i <4 ;i ++){
    		Tavolo t = new Tavolo (6);
    		tavoli.add(t);
    	}
    	for( int i =0 ; i <5 ;i ++){
    		Tavolo t = new Tavolo (4);
    		tavoli.add(t);
    	}
    	
    	simulator.addTavoli(tavoli);
    	
    	int time =0;
    	for (int i=0 ; i<2000 ;i ++){
    		
    		time =  time + (int) (1+ Math.random() * 10);
    		
    		int numPersone =(int) (1+  Math.random() * 10);
    		int durata = (int) (60 + Math.random() * 61);
    		float tolleranza = (float) Math.random(); 
    		Gruppo gruppo = new Gruppo(numPersone, durata, tolleranza);
    		
    		simulator.addGruppiClienti(time, gruppo);	      // riempio la coda iniziale
    	}
   
    	simulator.run();
    
    	txtResult.appendText("Numero totale clienti: " + simulator.getNumTotClienti() + "\n");
    	txtResult.appendText("Numero clienti soddisfatti: " + simulator.getClientiSoddisfatti() + "\n");
    	txtResult.appendText("Numero clienti insoddisfatti: " + simulator.getClientiInsoddisfatti() + "\n");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Bar.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";

    }

	
}

