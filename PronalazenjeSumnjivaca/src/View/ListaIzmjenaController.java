package View;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListaIzmjenaController implements Initializable, ControlledScreen {

	ViewDelegate del;
	@FXML
	private ListView<String> list;
	@FXML
	private Button odaberi;
	@FXML Label prva;
	@FXML Label druga;
	@FXML Label treca;
	@FXML Label cetvrta;
	private String predmet;
	

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public void postaviListuOsumnjiceni(Set<Osumnjiceni> lista) {
		labeleSumnjivac();
		ObservableList<String> data = FXCollections.observableArrayList();
		list.getItems().clear();
		if(lista != null){
//			LinkedHashMap<Osumnjiceni, Float> sorted = (LinkedHashMap<Osumnjiceni, Float>) sortByValue(lista);
			List<Osumnjiceni> list2 = lista.stream().sorted((Osumnjiceni o1, Osumnjiceni o2) -> o1.getPrezime().compareTo(o2.getPrezime())).collect(Collectors.toList());
			
		for (Osumnjiceni entry : list2) {
			String output = String.format("%-20s%-30s%-30s", entry.getOib().toString(),
					entry.getIme(), entry.getPrezime());
			//data.add(entry.getKey().getOib() + " - " + entry.getKey().getIme() + " " + entry.getKey().getPrezime() + " "
			//		+ entry.getValue());
			data.add(output);
		}
		list.setItems(data);
		}
		predmet = "Osumnjiceni";
	}
	
	
	

	public void postaviListuSlucaj(Set<Slucaj> lista) {
		labeleSlucaj();
		ObservableList<String> data = FXCollections.observableArrayList();
		list.getItems().clear();
//		LinkedHashMap<Slucaj, Float> sorted = (LinkedHashMap<Slucaj, Float>) sortByValue(lista);
		List<Slucaj> list2 = lista.stream().sorted((Slucaj o1, Slucaj o2) -> o1.getBrojSlucaja().compareTo(o2.getBrojSlucaja())).collect(Collectors.toList());
		for (Slucaj entry : list2) {
			
			String output = String.format("%-20s%-60s", entry.getBrojSlucaja().toString(),
					entry.getNazivSlucaja());
			data.add(output);
			//data.add(entry.getKey().getBrojSlucaja() + " - " + entry.getKey().getNazivSlucaja() + " "
				//	+ entry.getValue());
		}

		list.setItems(data);
		predmet = "Slucaj";
	}

	public void postaviListuDokaz(Map<Dokaz, Float> lista){
		labeleDokaz();
		ObservableList<String> data = FXCollections.observableArrayList();
		list.getItems().clear();
		LinkedHashMap<Dokaz, Float> sorted = (LinkedHashMap<Dokaz, Float>) sortByValue(lista);
		
		for (Map.Entry<Dokaz, Float> entry : sorted.entrySet()) {
			String output = String.format("%-15s%-30s%-30s%-10s", entry.getKey().getID().toString(), entry.getKey().getNazivSlucaja(), entry.getKey().getNaziv(), entry.getValue().toString()+"%");
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
			select = list.getSelectionModel().getSelectedItem().substring(0,15).trim();
			Osumnjiceni kriminalac = new Osumnjiceni();
			kriminalac.setOib(Long.parseLong(select));
			System.out.println(select);
			del.postaviScenuIzmjeneKriminalca(kriminalac);
			break;
		case "Slucaj":
			select = list.getSelectionModel().getSelectedItem().substring(0,15).trim();
			Slucaj slucaj = new Slucaj();
			slucaj.setBrojSlucaja(Integer.parseInt(select));
			System.out.println(select);
			del.postaviScenuIzmjeneSlucaja(slucaj);
			break;
		case "Dokaz":
			select = list.getSelectionModel().getSelectedItem().substring(0,15).trim();
			Dokaz dokaz = new Dokaz();
			dokaz.setID(Integer.parseInt(select));
			System.out.println(select);
			del.postaviScenuIzmjeneDokaza();
                        break;
		}
		
	}
	
	@FXML private void labeleSumnjivac(){
		prva.setText("OIB");
		druga.setText("Ime");
		treca.setText("");
		cetvrta.setText("Prezime");
	}
	@FXML private void labeleSlucaj(){
		prva.setText("Broj slučaja");
		druga.setText("");
		treca.setText("Naziv slučaja");
		cetvrta.setText("");
	}
	@FXML private void labeleDokaz(){
		prva.setText("ID");
		druga.setText("Naziv slučaja");
		treca.setText("");
		cetvrta.setText("Naziv");
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(ViewDelegate delegate) {
		del = delegate;
		list.getItems().clear();
		

	}

}
