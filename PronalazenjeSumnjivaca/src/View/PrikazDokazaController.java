package View;

import Controller.ViewDelegate;
import Model.Dokaz;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Karmela
 */
public class PrikazDokazaController implements Initializable, ControlledScreen {

    ViewDelegate delegate;
    
    @FXML ImageView fotografija;
    @FXML ImageView otisakPrsta;
    @FXML Label id;
    @FXML Label naziv;
    @FXML Label nazivSlucaja;
    @FXML Label krvnaGrupa;
    @FXML Label dnaSekvenca;
    @FXML Label tipOruzja;
    @FXML Button ispis;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void prikaziPodatke(Dokaz dokaz) {
        
        id.setText(Integer.toString(dokaz.getID()));
        naziv.setText(dokaz.getNaziv());
        nazivSlucaja.setText(dokaz.getNazivSlucaja());
        krvnaGrupa.setText(dokaz.getKrvnaGrupa().toString());
        dnaSekvenca.setText(dokaz.getDNASekvenca().toString());
        tipOruzja.setText(dokaz.getTipOruzja().toString());
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
