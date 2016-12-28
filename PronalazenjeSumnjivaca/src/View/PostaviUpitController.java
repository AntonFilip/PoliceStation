package View;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PostaviUpitController implements Initializable, ControlledScreen{

		
	ViewDelegate delegate;
	@FXML private Button kriminalac;
	@FXML private Button slucaj;
	@FXML private Button dokaz;
	
	
	
	@Override
	public void init(ViewDelegate delegate) {
		this.delegate = delegate;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
