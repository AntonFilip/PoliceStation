package View;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.ViewDelegate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Skopi
 */
public class GlavniIzbornikController implements Initializable{
    
    private ViewDelegate delegate;
    
    @FXML private MenuButton postaviUpit;
    @FXML private MenuItem postaviKriminalac;
    @FXML private MenuItem postaviSlucaj;
    @FXML private MenuItem postaviDokaz;
    
    @FXML private MenuButton izmjeni;
    @FXML private MenuItem izmjeniKriminalac;
    @FXML private MenuItem izmjeniSlucaj;
    @FXML private MenuItem izmjeniDokaz;
    
    
    @FXML private MenuButton dodaj;
    @FXML private MenuItem dodajKriminalac;
    @FXML private MenuItem dodajSlucaj;
    @FXML private MenuItem dodajDokaz;
    
    
    @FXML private Button statistika;
    @FXML private Button dnevnik;
    @FXML private Pane pane;
    @FXML private Label toplabela;
    @FXML private TextField brza;
    @FXML private Button trazi;
    @FXML private Button odjava; 
    
    public Pane init(ViewDelegate delegate) {
        this.delegate = delegate;
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        brza.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                pane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        return this.pane;
    }
    
    public void initPozornik(){
    	izmjeni.setDisable(true);
    	izmjeni.setVisible(false);
    	
    	dodaj.setDisable(true);
    	dodaj.setVisible(false);
    	
    	statistika.setDisable(true);
    	statistika.setVisible(false);
    	
    	dnevnik.setDisable(true);
    	dnevnik.setVisible(false);
    }
    
    public void initNarednik(){
    	
    	dodaj.setDisable(true);
    	dodaj.setVisible(false);
    	
    	statistika.setDisable(true);
    	statistika.setVisible(false);
    	
    	dnevnik.setDisable(true);
    	dnevnik.setVisible(false);
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
    
    @FXML private void postaviScenuUpitKriminalac(){
    	delegate.postaviScenuUpitKriminalac();
    }
    
    @FXML private void postaviScenuUpitSlucaj(){
    	delegate.postaviScenuUpitSlucaj();
    }
    
    @FXML private void postaviScenuUpitDokaz(){
    	delegate.postaviScenuUpitDokaz();
    }
    
    @FXML private void postaviScenuIzmjeneKriminalca(){
    	delegate.postaviScenuIzmjeneKriminalca();
    }
    
    @FXML private void postaviScenuIzmjeneSlucaja(){
    	delegate.postaviScenuIzmjeneSlucaja();
    }
    
    @FXML private void postaviScenuIzmjeneDokaza(){
    	delegate.postaviScenuIzmjeneDokaza();
    }
    
    @FXML private void postaviScenuDodajKriminalca(){
    	delegate.postaviScenuDodajKriminalca();
    }
    
    @FXML private void postaviScenuDodajSlucaj(){
    	delegate.postaviScenuDodajSlucaj();
    }
    
    @FXML private void postaviScenuDodajDokaz(){
    	delegate.postaviScenuDodajDokaz();
    }
    
    @FXML public void setIme(String ime) {
        toplabela.setText(ime);
    }
    
    @FXML private void brzoPretrazi(){
    	delegate.brzoPretrazi(brza.getText());
    	
    }
    
    @FXML private void odjava(){
    	delegate.odjava();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
}
