package View;


import java.net.URL;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Karmela
 */
public class GlavniIzbornikController implements Initializable{
    
    private ViewDelegate delegate;
    @FXML private Button postaviUpit;
    @FXML private Button izmjeni;
    @FXML private Button dodaj;
    @FXML private Button statistika;
    @FXML private Button dnevnik;
    @FXML private Pane pane;
    @FXML private Label toplabela;
   
    
    
    
    public Pane init(ViewDelegate delegate) {
        this.delegate = delegate;
        return this.pane;
    }
    
    @FXML private void klikPostaviUpit(){
    	delegate.postaviScenuUpit();
    }
    
    @FXML private void postaviScenuStatistike(){
    	delegate.postaviScenuStatistika();
    	
    }
    
    @FXML private void postaviScenuDnevnika(){
    	delegate.postaviScenuDnevnikPretrazivanja();
    }
    
    @FXML public void setIme(String ime) {
        toplabela.setText(ime);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
}
