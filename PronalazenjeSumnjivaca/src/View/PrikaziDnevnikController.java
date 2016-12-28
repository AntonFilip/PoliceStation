package View;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class PrikaziDnevnikController implements Initializable, ControlledScreen{
	
	ViewDelegate del;
	
	@FXML private ListView<String> dnevnik;
	
	public ListView<String> getDnevnik(){
		return dnevnik;
	}

	@Override
	public void init(ViewDelegate delegate) {
		del = delegate;	
	}
	
	public void postaviPodatke(ObservableList<String> items){
		dnevnik.setItems(items);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}
