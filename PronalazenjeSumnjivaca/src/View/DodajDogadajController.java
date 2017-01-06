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
    
    public void init(DialogManager delegate) {
        this.delegate = delegate;
    }
    
    
    @FXML private void dodajDogadaj (ActionEvent event) {
        Dogadaj dogadaj = new Dogadaj();
        dogadaj.setNaziv(nazivDogadaja.getText());
        dogadaj.setPbrMjesto(Integer.parseInt(pbrMjestaDogadaja.getText()));
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
