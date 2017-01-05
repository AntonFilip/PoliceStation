package View;

import Controller.*;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Karmela
 */
public class PrijavaController implements Initializable, ControlledScreen{
    
    private ViewDelegate delegate;
     
    @FXML private Pane main;
    @FXML private Button prijava;
    @FXML private TextField korisnickoIme;
    @FXML private PasswordField lozinka;
    @FXML private Label neispravno;
    
    @FXML private void prijavaKlik(ActionEvent event) {
        
                try {
                    delegate.prijava(korisnickoIme.getText(), lozinka.getText(), this);
                } catch (IOException ex) {
                    Logger.getLogger(PrijavaController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void neispravniPodaci(){
    	neispravno.setVisible(true);
    	
    }
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        korisnickoIme.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                main.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    }
}
