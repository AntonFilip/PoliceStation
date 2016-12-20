package View;

import Controller.ViewDelegate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Karmela
 */
public class GlavniIzbornikController implements Initializable, ControlledScreen{
    
    private ViewDelegate delegate;
    
    @FXML private Label Ime;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    @FXML public void setIme(String ime) {
        Ime.setText(ime);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
}
