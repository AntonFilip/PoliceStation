package View;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import Model.DnevnikPretrazivanja;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LogIzDnevnikaController implements Initializable, ControlledScreen{

	ViewDelegate del;
	
	@FXML Label id;
	@FXML Label brojPolicajca;
	@FXML Label vrijemeUpita;
	@FXML Label ipAdresa;
	@FXML TextArea tekstUpita;
	@FXML TextArea sqlUpit;
	
	public void postaviPodatke(DnevnikPretrazivanja dnevnik){
		if(dnevnik.getID() != null)
			id.setText(dnevnik.getID().toString());
		if(dnevnik.getBrojPolicajca() != null)
			brojPolicajca.setText(dnevnik.getBrojPolicajca().toString());
		if(dnevnik.getVrijemeUpita() != null)
			vrijemeUpita.setText(dnevnik.getVrijemeUpita());
		if(dnevnik.getIpAdresa() != null)
			ipAdresa.setText(dnevnik.getIpAdresa());
		if(dnevnik.getTextUpita() != null)
			tekstUpita.setText(dnevnik.getTextUpita());
		if(dnevnik.getSqlUpit() != null)
			sqlUpit.setText(dnevnik.getSqlUpit());
	}
	
	@Override
	public void init(ViewDelegate delegate) {
		del = delegate;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
