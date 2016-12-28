package View;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PrikaziStatistikuController implements Initializable, ControlledScreen{

	
	ViewDelegate del;
	
	@FXML private Label naslov;
	@FXML private Label label1;
	@FXML private Label label2;
	@FXML private Label label3;
	
	
	
	
	@Override
	public void init(ViewDelegate delegate) {
		del = delegate;
	}
	
	public void postaviPodatke(String string1, String string2, String string3){
		
		label1.setText(string1);
		label2.setText(string2);
		label3.setText(string3);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
	
}
