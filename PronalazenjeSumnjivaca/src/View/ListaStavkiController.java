package View;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Controller.ViewDelegate;
import Model.Dokaz;
import Model.Osumnjiceni;
import Model.Slucaj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ListaStavkiController implements Initializable, ControlledScreen {

	ViewDelegate del;
	@FXML
	private ListView<String> list;
	@FXML
	private Button odaberi;
	
	private String predmet;
	

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public void postaviListuOsumnjiceni(Map<Osumnjiceni, Float> lista) {

		ObservableList<String> data = FXCollections.observableArrayList();
		
		LinkedHashMap<Osumnjiceni, Float> sorted = (LinkedHashMap<Osumnjiceni, Float>) sortByValue(lista);

		for (Map.Entry<Osumnjiceni, Float> entry : sorted.entrySet()) {
			String output = String.format("%-10s%-30s%-30s%-10s", entry.getKey().getOib().toString(),
					entry.getKey().getIme(), entry.getKey().getPrezime(), entry.getValue().toString());
			//data.add(entry.getKey().getOib() + " - " + entry.getKey().getIme() + " " + entry.getKey().getPrezime() + " "
			//		+ entry.getValue());
			data.add(output);
		}
		list.setItems(data);
		predmet = "Osumnjiceni";
	}
	
	
	

	public void postaviListuSlucaj(Map<Slucaj, Float> lista) {
		ObservableList<String> data = FXCollections.observableArrayList();

		LinkedHashMap<Slucaj, Float> sorted = (LinkedHashMap<Slucaj, Float>) sortByValue(lista);

		for (Map.Entry<Slucaj, Float> entry : sorted.entrySet()) {
			
			String output = String.format("%-10s%-60s%-10s", entry.getKey().getBrojSlucaja().toString(),
					entry.getKey().getNazivSlucaja(), entry.getValue().toString());
			data.add(output);
			//data.add(entry.getKey().getBrojSlucaja() + " - " + entry.getKey().getNazivSlucaja() + " "
				//	+ entry.getValue());
		}

		list.setItems(data);
		predmet = "Slucaj";
	}

	public void postaviListuDokaz(Map<Dokaz, Float> lista){
		ObservableList<String> data = FXCollections.observableArrayList();
		
		LinkedHashMap<Dokaz, Float> sorted = (LinkedHashMap<Dokaz, Float>) sortByValue(lista);
		
		for (Map.Entry<Dokaz, Float> entry : sorted.entrySet()) {
			String output = String.format("%-10s%-30s%-30s%-10s", entry.getKey().getID().toString(), entry.getKey().getNazivSlucaja(), entry.getKey().getNaziv(), entry.getValue().toString()+"%");
			data.add(output);
			//data.add(entry.getKey().getID() + " - " + entry.getKey().getNazivSlucaja() + " - " + entry.getKey().getNaziv() + " " + entry.getValue());
		}
		
		list.setItems(data);
		predmet = "Dokaz";
		
	}
	
	@FXML
	private void odaberiStavku(){
		String select;
		
		switch (predmet) {
		case "Osumnjiceni": 
			select = list.getSelectionModel().getSelectedItem().substring(0,10).trim();
			Osumnjiceni kriminalac = new Osumnjiceni();
			kriminalac.setOib(Long.parseLong(select));
			System.out.println(select);
			del.prikaziPodatkeKriminalca(kriminalac);
			
		case "Slucaj":
			select = list.getSelectionModel().getSelectedItem().substring(0,10).trim();
			Slucaj slucaj = new Slucaj();
			slucaj.setBrojSlucaja(Integer.parseInt(select));
			System.out.println(select);
			del.prikaziPodatkeSlucaja(slucaj);
			
		case "Dokaz":
			select = list.getSelectionModel().getSelectedItem().substring(0,10).trim();
			Dokaz dokaz = new Dokaz();
			dokaz.setID(Integer.parseInt(select));
			System.out.println(select);
			del.prikaziPodatkeDokaza(dokaz);
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(ViewDelegate delegate) {
		del = delegate;

	}

}
