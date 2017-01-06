package View;

import Model.Dogadaj;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Karmela
 */
public class DodajDogadajController implements Initializable {
    
    DialogManager delegate;
    
    @FXML TextField nazivDogadaja;
    @FXML TextField pbrMjestaDogadaja;
    @FXML TextField adresaMjestaDogadaja;
    @FXML TextField vrijemeDogadaja;
    @FXML Button dodaj;
    @FXML Label info;
    
    public void init(DialogManager delegate) {
        this.delegate = delegate;
    }    
    
    @FXML private void dodajDogadaj (ActionEvent event) {
        Dogadaj dogadaj = new Dogadaj();
        
        if (nazivDogadaja.getText().isEmpty()) {
            info.setText("Upišite naziv događaja.");
            
        } else {
            dogadaj.setNaziv(nazivDogadaja.getText());
        }
        
        if (pbrMjestaDogadaja.getText().isEmpty()) {
            info.setText("Upišite poštanski broj mjesta događaja.");
        } else { 
            dogadaj.setPbrMjesto(Integer.parseInt(pbrMjestaDogadaja.getText()));
        }
        
        dogadaj.setAdresa(adresaMjestaDogadaja.getText());
        dogadaj.setVrijeme(LocalTime.parse(vrijemeDogadaja.getText()));
        
        Model.PristupBaziPodataka.dodajNoviDogadaj(dogadaj);
        delegate.dodajDogadaj(dogadaj);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo
    }

    
    
}
