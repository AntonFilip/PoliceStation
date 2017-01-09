package View;

import Model.Dogadaj;
import java.net.URL;
import java.time.LocalDateTime;
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
    @FXML TextField brojSlucaja;
    @FXML Button dodaj;
    @FXML Label info;
    
    public void init(DialogManager delegate) {
        this.delegate = delegate;
    }    
    
    @FXML private void dodajDogadaj (ActionEvent event) {
        
        Dogadaj dogadaj = new Dogadaj();
        
        String poruka = "Unesite: ";
        
        if (nazivDogadaja.getText() != null)
            if (!nazivDogadaja.getText().isEmpty()) 
                dogadaj.setNaziv(nazivDogadaja.getText());
            else
                poruka = poruka.concat("naziv; ");
        
        if (pbrMjestaDogadaja.getText() != null)
            if (!pbrMjestaDogadaja.getText().isEmpty()) 
                dogadaj.setPbrMjesto(Integer.parseInt(pbrMjestaDogadaja.getText()));
            else 
                poruka = poruka.concat("poštanski broj; ");
        
        if (adresaMjestaDogadaja.getText() != null)
            if (!adresaMjestaDogadaja.getText().isEmpty())
                dogadaj.setAdresa(adresaMjestaDogadaja.getText());
            else 
                poruka = poruka.concat("adresa; ");
        
        if (vrijemeDogadaja.getText() != null)
            if (!vrijemeDogadaja.getText().isEmpty())
                dogadaj.setVrijeme(LocalDateTime.parse(vrijemeDogadaja.getText()));
            else 
                poruka = poruka.concat("vrijeme; ");
        
        if (brojSlucaja.getText() != null) 
            if (!brojSlucaja.getText().isEmpty())
                dogadaj.setBrojSlucaja(Integer.parseInt(brojSlucaja.getText()));
            else 
                poruka = poruka.concat("broj slučaja; ");
        
        if (poruka.equals("Unesite: "))
            delegate.dodajDogadaj(dogadaj);
        else info.setText(poruka);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo
    }

    
    
}
