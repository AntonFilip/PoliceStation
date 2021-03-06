package View;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import Model.DnevnikPretrazivanja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PrikaziDnevnikController implements Initializable, ControlledScreen{
	
	ViewDelegate del;
	
	@FXML private Button odaberi;
	@FXML private ListView<String> dnevnik;
	
	public ListView<String> getDnevnik(){
		return dnevnik;
	}

	
	@FXML private void odaberiStavku(){

		String select;
		select = dnevnik.getSelectionModel().getSelectedItem().substring(0,13).trim();
		if(!select.isEmpty() && select !=  null)
			del.odaberiStavku(select);

	}

	public void postaviPodatke(List<DnevnikPretrazivanja> list) {
		ObservableList<String> data = FXCollections.observableArrayList();
		if (list != null) {
			Collections.sort(list, new Comparator<DnevnikPretrazivanja>() {
				@Override
				public int compare(DnevnikPretrazivanja o1, DnevnikPretrazivanja o2) {
					return -(o1.getID().compareTo(o2.getID()));
				}
			});

			for (DnevnikPretrazivanja entry : list) {
				System.out.println(entry.getBrojPolicajca());
				String output = String.format("%-13s%-25s%-35s%-10s", entry.getID().toString(), entry.getIpAdresa(),
						entry.getVrijemeUpita(), entry.getBrojPolicajca().toString());
				data.add(output);
			}
			dnevnik.setItems(data);
		}

	}
	
	@Override
	public void init(ViewDelegate delegate) {
		del = delegate;	
		dnevnik.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						odaberiStavku();
					}
				}
			}
		});
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}
