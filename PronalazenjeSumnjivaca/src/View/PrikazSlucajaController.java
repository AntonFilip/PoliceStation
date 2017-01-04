package View;

import Controller.ViewDelegate;
import Model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Karmela
 */
public class PrikazSlucajaController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML ImageView fotografija;
    @FXML Label broj;
    @FXML Label naziv;
    @FXML Label opis;
    @FXML Label glavniOsumnjiceni;
    @FXML ListView popisOsumnjicenih;
    @FXML ListView popisSvjedoka;
    @FXML ListView popisDokaza;
    @FXML ListView popisPolicajaca;
    @FXML Label status;
    @FXML ListView popisDogadaja;
    @FXML Button ispis;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    public void prikaziPodatke(Slucaj slucaj) {
        fotografija.setImage((Image) slucaj.getFotografijeSlučaja());
        broj.setText(Integer.toString(slucaj.getBrojSlucaja()));
        naziv.setText(slucaj.getNazivSlucaja());
        opis.setText(slucaj.getOpis());
        glavniOsumnjiceni.setText(Integer.toString(slucaj.getGlavniOsumnjiceni().getOib()));
        status.setText(slucaj.getStatus().toString());
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}
