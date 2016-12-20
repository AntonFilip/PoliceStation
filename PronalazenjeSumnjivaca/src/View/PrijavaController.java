package View;

import Controller.*;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Karmela
 */
public class PrijavaController implements Initializable, ControlledScreen{
    
    private ViewDelegate delegate;
     
    @FXML private Button prijava;
    @FXML private TextField korisnickoIme;
    @FXML private PasswordField lozinka;
    
    @FXML private void prijavaKlik(ActionEvent event) {
        /*Thread t = new Thread(new Runnable() {
            @Override
            public void run() {*/
                try {
                    delegate.prijava(korisnickoIme.getText(), lozinka.getText());
                } catch (IOException ex) {
                    Logger.getLogger(PrijavaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            /*}
	});
	t.start();*/
    }
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo
    }
}
