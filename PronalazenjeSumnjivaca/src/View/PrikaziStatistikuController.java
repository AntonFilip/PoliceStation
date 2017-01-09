package View;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import Model.Statistika;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PrikaziStatistikuController implements Initializable, ControlledScreen{

	
	ViewDelegate del;
	
	@FXML private Label naslov;
	@FXML private Label brojKrim;
	@FXML private Label postotakRS;
	@FXML private ListView<String> list;
	
	
	
	
	
	@Override
	public void init(ViewDelegate delegate) {
		del = delegate;
	}
	
	public void postaviPodatke(Statistika statistika){
		//System.out.println(statistika.getBrojKriminalaca());
		if(statistika.getBrojKriminalaca() != null)
			brojKrim.setText(statistika.getBrojKriminalaca().toString());
		if(statistika.getPostotakRiješenihSlučajeva() != null)
			postotakRS.setText(statistika.getPostotakRiješenihSlučajeva().toString());
		ObservableList<String> data = FXCollections.observableArrayList();
		
		if(statistika.getUdioTipovaOružja() != null){
			LinkedHashMap<String, Float> sorted = (LinkedHashMap<String, Float>) ListaStavkiController.sortByValue(statistika.getUdioTipovaOružja());

		for (Map.Entry<String, Float> entry : sorted.entrySet()) {
			String output = String.format("%-50s%-10s", entry.getKey(), entry.getValue().toString()+"%");
			data.add(output);
		}
		list.setItems(data);
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
	
}
