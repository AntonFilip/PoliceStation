package View;

import Controller.ViewDelegate;
import Model.*;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 */
public class DodajSlucajController implements Initializable, ControlledScreen {
    
    ViewDelegate delegate;
    
    @FXML TextField brojSlucaja;
    @FXML TextField nazivSlucaja;
    @FXML TextArea opisSlucaja;
    @FXML TextField glavniOsumnjiceni;
    @FXML TextArea popisOsumnjicenih;
    @FXML TextArea popisSvjedoka;
    @FXML TextArea popisPolicajaca;
    @FXML ComboBox statusSlucaja;
    @FXML TextArea popisDogadaja;
    @FXML Button dodaj;
    
    @Override
    public void init(ViewDelegate delegate) {
        this.delegate = delegate;
    }
    
    @FXML private void dodaj(ActionEvent event) {
        Slucaj slucaj = new Slucaj();
        
        slucaj.setBrojSlucaja(Integer.parseInt(brojSlucaja.getText()));
        slucaj.setNazivSlucaja(nazivSlucaja.getText());
        slucaj.setOpis(opisSlucaja.getText());
        
        Osumnjiceni osumnjiceni = new Osumnjiceni();
        osumnjiceni.setOib(Integer.parseInt(glavniOsumnjiceni.getText()));
        slucaj.setGlavniOsumnjiceni(osumnjiceni);
        
        Set<Osoba> popis1 = new HashSet<>();
        String[] sumnjivci = popisOsumnjicenih.getText().split(";");
        for (String sumnjivac : sumnjivci) {
            Osumnjiceni novi = new Osumnjiceni();
            novi.setOib(Integer.parseInt(sumnjivac));
            popis1.add(novi);
        }
        slucaj.setPopisOsumnjicenih(popis1);
        
        Set<Osoba> popis2 = new HashSet<>();
        String[] svjedoci = popisSvjedoka.getText().split(";");
        for (String svjedok : svjedoci) {
            Osoba novi = new Osoba();
            novi.setOib(Integer.parseInt(svjedok));
            popis2.add(novi);
        }
        slucaj.setPopisSvjedoka(popis2);
        
        Set<Pozornik> popis4 = new HashSet<>();
        String[] policajci = popisPolicajaca.getText().split(";");
        for (String policajac : policajci) {
            Pozornik novi = new Pozornik();
            novi.setOib(Integer.parseInt(policajac));
            popis4.add(novi);
        }
        slucaj.setPopisPolicajaca(popis4);
        
        if (statusSlucaja.getValue().equals("Riješen")) {
            slucaj.setStatus(TrenutniStatusSlucaja.riješen);
        } else if (statusSlucaja.getValue().equals("Otvoren")) {
            slucaj.setStatus(TrenutniStatusSlucaja.otvoren);
        } else if (statusSlucaja.getValue().equals("Zatvoren")) {
            slucaj.setStatus(TrenutniStatusSlucaja.zatvoren);
        }
        
        /*Set<Dogadaj> popis5 = new HashSet<>();
        String[] dogadaji = popisDogadaja.getText().split(";");
        for (String dogadaj : dogadaji) {
            Dogadaj novi = new Dogadaj();
        }*/
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
}
